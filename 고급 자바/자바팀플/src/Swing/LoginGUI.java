package Swing;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;

public class LoginGUI extends JFrame{
	private JTextField JF[]  = new JTextField[4]; //ID입력필드
	private JButton login = new JButton("로그인"); //로그인 버튼
	private JButton Alogin = new JButton("관리자");
	private JPanel IDJP = new JPanel(); //아이디 입력과 로그인 버튼이 있는 패널
	private ImageIcon icon = new ImageIcon("C:\\Users\\gksek\\헬스장.jpg"); //홈 이미지 생성
	//private ImageIcon icon = new ImageIcon("image/헬스장.jpg");
	private JLabel Homelabel = new JLabel(icon); //icon을 label에 저장
	private String[] Member = new String[4];
	private String[] Trainer = new String[4];
	private JButton M_add = new JButton("회원 가입"); //회원 추가
	private JButton T_add = new JButton("트레이너 가입");
	private JButton shownotice = new JButton("공지사항");
	private JLabel notice =  new JLabel();
	public LoginGUI() {
		setTitle("Health"); //프레임 타이틀 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 윈도우를 닫으면 프로그램 종료
		Container c = getContentPane(); //컨텐트팬을 알아낸다
		
		c.setLayout(null); //컨탠트팬의 배치관리자 제거
		IDJP.setLayout(null); //IDJP패널의 배치관리자 제거
		
		for(int i=0;i<JF.length;i++) { // JF길이만큼 반복
			JF[i] = new JTextField(""); //텍스트 필드 생성
			JF[i].setBackground(Color.WHITE); //배경색 지정
			JF[i].setHorizontalAlignment(JLabel.CENTER); //라벨 위치 설정
			JF[i].setSize(30,30); //텍스트 필드 사이즈 설정
			JF[i].setLocation(65 + i * 50, 130); //위치 설정
			JF[i].setFont(new Font("Arial", Font.PLAIN, 30)); //폰트와 글씨 사이즈 설정
			IDJP.add(JF[i]); //IDJP패널에 JF[i]부착
		}
		
		Homelabel.setBounds(0,0,300,360); //Homelabel의 사이즈,위치 설정
		
		login.setBounds(70,200,80,30); //login의 사이즈, 위치 설정
		login.addActionListener(new MyActionListener());
		
		Alogin.setBounds(160,200,80,30);
		Alogin.addActionListener(new AdminloginButton());

		M_add.setBounds(190,330,100,30);
		M_add.addActionListener(new MaddButton());
		
		T_add.setBounds(60,330,120,30);
		T_add.addActionListener(new TaddButton());
		
		shownotice.setBounds(10, 10, 100, 25);
		shownotice.addActionListener(new shownotice());
		notice.setBounds(20, 20, 300, 100);
		
		IDJP.setBounds(290,0,292,370); //IDJP의 사이즈,위치 설정
		IDJP.setBackground(Color.WHITE); //IDJP 배경색 설정
		IDJP.add(login); //IDJP에 login버튼 부착
		IDJP.add(Alogin);
		IDJP.add(M_add);
		IDJP.add(T_add);
		IDJP.add(notice);
		IDJP.add(shownotice);
		
		c.add(Homelabel); //컨텐트팬에 Homelabel 부착
		c.add(IDJP); //컨텐트팬에 IDJP 패널 부착
		
		setSize(600,400); //프레임 크키
		setVisible(true); //프레임 출력
		setResizable(false); //프레임 크기르 사용자가 지정할 수 없도록 함
	}
	
	//입력된 id로 저장된 회원이 있는지 검사
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\정보\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()); //문자 입력 스트림 생성

				login.addActionListener(new loginButton()); //Action 이벤트 리스너 달기
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"파일이 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}
	}
	
	//입력된 id로 로그인
	class loginButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(JF[0].getText().trim().length()==1&&JF[1].getText().trim().length()==1&&JF[2].getText().trim().length()==1&&JF[3].getText().trim().length()==1) { //텍스트 필드의 값이 1개인지 검사
				if(JF[0].getText().equals("T")) { //JF[0] 값이 T이면
					if(Topenfile()) {
						new TrainerGUI(Trainer); //트레이너로 로그인
						setVisible(false); //창 안보이게 하기 
					}
				}
				else if(isNumber(JF[0].getText().trim())){ //jF[0]이 숫자이면
					if(Mopenfile()) {
						new MemberGUI(Member);
						setVisible(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디를 다시 확인해주세요","다시 입력",JOptionPane.WARNING_MESSAGE); //메시지 다이얼로그 생성
				}
			}
			else //텍스트 필드 값이 여러개면
				JOptionPane.showMessageDialog(null, "숫자 하나만 입력하세요","숫자 여러개 입력 오류",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
		}
	}
	
	
	//입력된 id가 관리자 아이디인지 확인하는 ActionListener
	class AdminloginButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(JF[0].getText().trim().length()==1&&JF[1].getText().trim().length()==1&&JF[2].getText().trim().length()==1&&JF[3].getText().trim().length()==1) { //텍스트 필드의 값이 1개인지 검사
				if(JF[0].getText().equals("A")&&JF[1].getText().equals("5")&&JF[2].getText().equals("2")&&JF[3].getText().equals("7")) { //JF[0]값이 A이고 JF[1]값이 5이고 JF[2]값이 2이고 JF[3]값이 7이면
					new AdminGUI(); //관리자로 로그인
					setVisible(false); //창 안보이게 하기 
				}
				else {
					JOptionPane.showMessageDialog(null, "관리자 아이디가 아닙니다.","다시 입력",JOptionPane.WARNING_MESSAGE); //메시지 다이얼로그 생성
				}
			}
			else //텍스트 필드 값이 여러개면
				JOptionPane.showMessageDialog(null, "숫자 하나만 입력하세요","숫자 여러개 입력 오류",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
		}
	}
	
	public boolean Mopenfile() {
		int ok = 0;
		try {	
			BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\정보\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()));
			while(true) {
				String text = br.readLine();
				if(text != null) {
					String[] strArray = text.split(",");
					for(int i=0;i<strArray.length;i++) {
						Member[i] = strArray[i];
						System.out.println(Member[i]);
					}
					ok=1;
					JOptionPane.showMessageDialog(null, "로그인을 축하합니다.");
					return true;
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return false;
	}
	
	public boolean Topenfile() {
		int ok = 0;
		try {	
			BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\정보\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()));
			while(true) {
				String text = br.readLine();
				if(text != null) {
					String[] strArray = text.split(",");
					for(int i=0;i<strArray.length;i++) {
						Trainer[i] = strArray[i];
						System.out.println(Trainer[i]);
					}
					ok=1;
					JOptionPane.showMessageDialog(null, "로그인을 축하합니다.");
					return true;
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return false;
	}
	
	//입력된 id가 숫자인지 아닌지 확인하는 메소드
	static boolean isNumber(String str) { 
		boolean result = true;
		// null, 공백일시
		if (str == null || str.length() == 0) {
			result = false;
		}
		// null이나 공백이 아닐시
		else {
			for (int i = 0; i < str.length(); i++) {
				int c = (int) str.charAt(i);
				// 숫자가 아니라면
				if (c < 48 || c > 57) {
					result = false;
				}
			}
		}
		return result;
	}
	
	//회원 추가
	class MaddButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new memberSignUp();
		}
	}

	//트레이너 추가
	class TaddButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new trainerSignUp();
		}
	}
	
	class shownotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\공지사항\\공지"); //문자 입력 스트림 생성
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"파일이 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //파일이 있을 때
				String c=null;
				notice.setText(""); //초기화 시킴
				while((c=br.readLine())!=null) { 
					notice.setText(c+"\n"); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"열기 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void main(String[] args) {
		new LoginGUI();
	}
}
