package Swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AdminGUI extends JFrame{
	//패널 생성
	private Panel nameP = new Panel(); //관리자 이름 패널
	private Panel listP = new Panel(); // 리스트 패널
	private Panel memberP = new Panel(); //회원 관리 패널
	private Panel trainerP = new Panel(); //트레이너 관리 패널
	private Panel noticeP = new Panel(); //공지사항 패널
	
	
	private JTextField Minputid = new JTextField();
	private JTextField Tinputid = new JTextField();
	
	private JTextField Moutput = new JTextField();
	private JTextField Toutput = new JTextField();
	
	private JTextArea inputnotice = new JTextArea();
	//라벨 생성
	private JLabel Aname = new JLabel("관리자"); // namep에 부착되는 라벨

	//버튼 생성
	private JButton A_Notice = new JButton("공지사항"); 
	private JButton A_Trainer = new JButton("트레이너관리");
	private JButton A_Member = new JButton("회원관리");
	private JButton A_logout = new JButton("로그아웃");
	private JButton Mshow = new JButton("정보 보기"); //회원 정보 보기
	private JButton Tshow = new JButton("정보 보기"); //트레이너 정보 보기
	private JButton Mfix = new JButton("수정"); //회원 정보 수정
	private JButton Tfix = new JButton("수정"); //트레이너 정보 수정
	private JButton addnotice = new JButton("공지사항 등록"); //공지사항 등록
	private JButton fixnotice = new JButton("수정"); //공지사항 수정
	private JButton	shownotice = new JButton("정보 보기"); //공지사항 보기
	public AdminGUI() {
		setTitle("Health");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		//Layout 없애기
		c.setLayout(null);
	
		noticeP.setLayout(null);
		trainerP.setLayout(null);
		memberP.setLayout(null);
		listP.setLayout(null);
		
		//패널 위치, 크기 지정
		nameP.setBounds(20, 20, 110, 30);
		listP.setBounds(20, 60, 100, 300);
		memberP.setBounds(130, 60, 440, 300);
		trainerP.setBounds(130, 60, 440, 300 );
		noticeP.setBounds(130, 60, 440, 300);
		Minputid.setBounds(20,42,100,25);
		Tinputid.setBounds(20,42,100,25);
		Moutput.setBounds(20, 100, 300, 80);
		Toutput.setBounds(20, 100, 300, 80);
		
		//버튼 위치, 크기 지정
		A_Notice.setBounds(0, 0, 110, 30);
		A_Notice.addActionListener(new noticeButton());
		A_Trainer.setBounds(0, 30, 110, 30);
		A_Trainer.addActionListener(new trainerButton());
		A_Member.setBounds(0, 60, 110, 30);
		A_Member.addActionListener(new memberButton());
		
		A_logout.setBounds(470,20,100,30);
		A_logout.addActionListener(new logoutButton()); //ActionListener 달기

		Mshow.setBounds(130,42,100,25);
		Mshow.addActionListener(new showMInfo());
		Mfix.setBounds(250, 42, 100, 25);
		Mfix.addActionListener(new fixMInfo());

		Tshow.setBounds(130,42,100,25);
		Tshow.addActionListener(new showTInfo());
		Tfix.setBounds(250, 42, 100, 25);
		Tfix.addActionListener(new fixTInfo());
		
		inputnotice.setBounds(20, 42, 300, 80);
		addnotice.setBounds(20, 150, 120, 25);
		addnotice.addActionListener(new addnotice());

		fixnotice.setBounds(200, 150, 120, 25);
		fixnotice.addActionListener(new fixnotice());
		
		shownotice.setBounds(100,10,100,25);
		shownotice.addActionListener(new shownotice());
		//색지정
		noticeP.setBackground(Color.LIGHT_GRAY);
		trainerP.setBackground(Color.LIGHT_GRAY);
		memberP.setBackground(Color.LIGHT_GRAY);
		nameP.setBackground(Color.LIGHT_GRAY);
		listP.setBackground(Color.LIGHT_GRAY);
		A_Notice.setBackground(Color.LIGHT_GRAY);
		A_Trainer.setBackground(Color.LIGHT_GRAY);
		A_Member.setBackground(Color.LIGHT_GRAY);
		A_logout.setBackground(Color.LIGHT_GRAY);
		//각 패널에 라벨, 버튼, 스크롤팬 추가
		nameP.add(Aname);
		
		listP.add(A_Notice);
		listP.add(A_Trainer);
		listP.add(A_Member);
		
		noticeP.add(inputnotice);
		noticeP.add(addnotice);
		noticeP.add(fixnotice);
		noticeP.add(shownotice);
		
		memberP.add(Minputid);
		memberP.add(Moutput);
		memberP.add(Mshow);
		memberP.add(Mfix);
		
		trainerP.add(Tinputid);
		trainerP.add(Toutput);
		trainerP.add(Tshow);
		trainerP.add(Tfix);
		
		//컨텐트팬에 패널 추가
		c.add(listP);
		c.add(nameP);
		c.add(A_logout);
		c.add(memberP);
		c.add(trainerP);
		c.add(noticeP);
		
		setSize(600,400);
		setVisible(true);
		setResizable(false); //프레임 크기르 사용자가 지정할 수 없도록 함
	}
	
	//독립된 클래스로 이벤트 리스너 작성
	//로그아웃 버튼을 눌렀을 떄
	class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginGUI(); //로그인 화면으로 돌아감
			setVisible(false); // 창 안보이게 하기 
		}
	}
	
	//회원 관리 버튼을 눌렀을 때
	class memberButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(false);
			memberP.setVisible(true);
			trainerP.setVisible(false);
		}
	}
	
	//트레이너 관리 버튼을 눌렀을 떄
	class trainerButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(false);
			memberP.setVisible(false);
			trainerP.setVisible(true);
		}
	}

	//공지사항 버튼을 눌렀을 때
	class noticeButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(true);
			memberP.setVisible(false);
			trainerP.setVisible(false);
		}
	}

	//회원 정보 보여줌
	private class showMInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\정보\\" + Minputid.getText()); //문자 입력 스트림 생성
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"회원이 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //파일이 있을 때
				String c=null;
				Moutput.setText(""); //초기화 시킴
				while((c=br.readLine())!=null) { 
					Moutput.setText(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"열기 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	//트레이너 정보 보여줌
	private class showTInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\정보\\" + Tinputid.getText()); //문자 입력 스트림 생성
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"트레이너가 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //파일이 있을 때
				String c=null;
				Toutput.setText(""); //초기화 시킴
				while((c=br.readLine())!=null) { 
					Toutput.setText(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"열기 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	//회원 정보 수정
	private class fixMInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\정보\\" + Minputid.getText()); 
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(Moutput.getText()); //Minput에 저장되어 있는 문자를 파일에 저장
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//트레이저 정보 수정
	private class fixTInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\정보\\" + Tinputid.getText()); 
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(Toutput.getText()); //Tinputid에 저장되어 있는 문자를 파일에 저장
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//공지사항 등록
	private class addnotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\공지사항\\공지"); 
				FileWriter fw = new FileWriter(file, true);
				
				fw.write(inputnotice.getText()); //inputnotice에 저장되어 있는 문자를 파일에 저장
					
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//공지사항 불러오기
	private class shownotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\공지사항\\공지"); //문자 입력 스트림 생성
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"회원이 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //파일이 있을 때
				String c=null;
				inputnotice.setText(""); //초기화 시킴
			
				while((c=br.readLine())!=null) { 
					inputnotice.setText(c+"\n"); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"열기 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
	
	//공지사항 수정
	private class fixnotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\공지사항\\공지"); 
				FileWriter fw = new FileWriter(file, true); 
			
				new FileOutputStream(file).close();
				fw.write(inputnotice.getText()); //inputnotice에 저장되어 있는 문자를 파일에 저장
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
		
	public static void main(String[] args) {
		new AdminGUI();
	}
}
