package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class trainerSignUp extends JFrame{
	private JPanel signUpPanel = new JPanel(new GridLayout(18,1)); //행의 수가 18이고 열의 수가 1인 Panel 생성
	private JButton join = new JButton("가입하기"); //가입 버튼 생성
	private JTextField id = new JTextField("아이디",20); //id 텍스트 필드
	private JTextField name = new JTextField("이름",10); //name 텍스트 필드
	private JTextField day = new JTextField("일",5); //day 텍스트 필드
	private JLabel Ltrainer = new JLabel("트레이너 추가");
	private JLabel dateOfbirth = new JLabel("생년월일"); //문자열 라벨 생성
	private JLabel Lsex = new JLabel("성별"); //문자열 라벨 생성
	private JLabel Lagree = new JLabel("개인정보 수집 및 이용 동의(필수)");  //문자열 라벨 생성
	private JCheckBox[] Jagree = new JCheckBox[2]; //체크박스 배열
	private String[] agree = {"동의","비동의"}; //체크박스 문자열로 사용할 문자열 배열
	private String[] sex = {"성별","남","여"}; //strsex의 문자열로 사용할 문자열 배열
	private JComboBox<String> year = new JComboBox<String>(); // 년도가 저장되는 콤보박스 생성
	private JComboBox<String> month = new JComboBox<String>(); // 월이 저장되는 콤보박스 생성
	private JComboBox<String> strsex = new JComboBox<String>(sex); // sex가 저장되는 콤보박스 생성
	public trainerSignUp() {
		setTitle("회원가입"); //프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 윈도우를 닫으면 프로그램 종료
		
		Container c = getContentPane(); //컨텐트팬을 알아낸다.
		c.setLayout(new FlowLayout()); //컨텐트팬에 FlowLayout 배치관리자 달기
	
		signUpPanel.add(Ltrainer);
		signUpPanel.add(id); //signUpPanel에 id 삽입
		signUpPanel.add(name); //signUpPanel에 Lastname 삽입
		
		signUpPanel.add(dateOfbirth); //signUpPanel에 dateOfbirth 삽입
		
		year.addItem("년도"); //year 콤보박스에 "년도"아이템 삽입
		for(int i=1993;i<=2002;i++) { 
			year.addItem(Integer.toString(i)); //1993 ~ 2002를 int로 바꿔 year의 콤보박스의 아이템으로 저장
			signUpPanel.add(year); //signUpPanel에 year 추가
		}
		
		month.addItem("월"); //month 콤보박스에 "월"아이템 삽입
		for(int i=1;i<=12;i++) { 
			month.addItem(Integer.toString(i)); //1 ~ 12를 int로 바꿔 month의 콤보박스의 아이템으로 저장
			signUpPanel.add(month); //signUpPanel에 month 삽입
		}
		
		signUpPanel.add(day); //signUpPanel에 day 삽입
		signUpPanel.add(Lsex); //signUpPanel에 Lsex 삽입
		signUpPanel.add(strsex); //signUpPanel에 strsex 삽입
		signUpPanel.add(Lagree); //signUpPanel에 Lagree 삽입
		
		for(int i=0;i<agree.length;i++) { 
			Jagree[i] = new JCheckBox(agree[i]); //agree[]의 문자열로 체크박스 생성
			Jagree[i].setBorderPainted(true); //채크박스의 외곽선이 보이도록 설정
			signUpPanel.add(Jagree[i]); //signUpPanel에 체크박스 삽입
			Jagree[i].addItemListener(new MyItemListener()); //체크박스에 Item 리스너 등록
		}
		signUpPanel.add(join); //signUpPanel에 join 삽입
	
		join.addActionListener(new MyActionListener()); //버튼에 Action리스너 등록
		join.addActionListener(new saveInfo());
		
		c.add(signUpPanel); //컨텐트팬에 signUpPanel 삽입
		
//		Dimension frameSize = getSize();
//	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
//	    setLocation((windowSize.width - frameSize.width) / 2,
//	                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
//	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
		setSize(300,450); //프레임 크기 300x560
		setVisible(true); //프레임 출력
	}
	class MyActionListener implements ActionListener{ //모든 값이 입력되었는지 체크
		public void actionPerformed(ActionEvent e) {
			if(id.getText().trim().length()==0||id.getText().trim().equals("아이디")){  //텍스트필드 id의 length가 0이거나 id의 텍스트가 "아이디"이면
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요","아이디 입력",JOptionPane.WARNING_MESSAGE); //메시지 다이얼로그 생성
			}
			if(year.getSelectedIndex()==0){ //year의 선택된 목록이 없으면
				JOptionPane.showMessageDialog(null, "생일년도를 선택해주세요","생일년도 선택",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
			}
			if(month.getSelectedIndex()==0){ //month의 선택된 목록이 없으면
				JOptionPane.showMessageDialog(null, "월을 선택해주세요","월 선택",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
			}
			if(day.getText().trim().length()==0||day.getText().trim().equals("일")){ //텍스트필드 day의 length가 0이거나 day의 텍스트가 "일"이면
				JOptionPane.showMessageDialog(null, "일를 입력해주세요","일 입력",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
			}
			if(name.getText().trim().length()==0||id.getText().trim().equals("이름")){ //텍스트필드 Firstname의 length가 0이거나 Firstname의 텍스트가 "이름"이면
				JOptionPane.showMessageDialog(null, "이름를 입력해주세요","이름 입력",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
			}
			if(strsex.getSelectedIndex()==0){ //strsex의 선택된 목록이 없으면
				JOptionPane.showMessageDialog(null, "성별을 선택해주세요","성별 선택",JOptionPane.WARNING_MESSAGE);//메시지 다이얼로그 생성
			}
		}
	}
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) { //체크박스의 선택 상태가 변하면 itemStateChanger()가 호출됨
			if(e.getStateChange() == ItemEvent.SELECTED) {  //채크박스가 선택된 경우
				if(e.getItem() == Jagree[1]) { //"비동의"이면
					JOptionPane.showMessageDialog(null, "동의하지 않으면 가입이 불가합니다.","동의 체크",JOptionPane.WARNING_MESSAGE); //메시지 다이얼로그 생성
				}
			}
		}
	}
	
	private class saveInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\정보\\"+ id.getText()); //경로로 파일 만들기
				FileWriter fw = new FileWriter(file, true); 
				//파일에 정보 저장
				fw.write(id.getText()+","+name.getText()+","+year.getSelectedItem()+"년 "+ month.getSelectedItem()+"월 "+day.getText()+"일 "+","+strsex.getSelectedItem());
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new trainerSignUp();
	}
}
