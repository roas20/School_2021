package Swing;
import javax.swing.*;

import Swing.TrainerGUI.logoutButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemberGUI extends JFrame{
	private static String[] member = new String[4];
	private JPanel nameP = new JPanel();
	private JPanel infoP = new JPanel();
	private JPanel scheduleP = new JPanel();
	private JLabel MembernameL = new JLabel();
	private JLabel nameL = new JLabel(); //이름 라벨
	private JLabel infoL = new JLabel("<등록 정보>"); //등록정보 라벨
	private JLabel scheduleL = new JLabel("<시간표>");
	private JButton logoutB = new JButton("로그아웃");
	private JPanel MinfoP = new JPanel();
	private JLabel info = new JLabel();
	private JTextField sche = new JTextField();
	JButton M_add = new JButton("추가");

	private String[] day = {"월","화","수","목","금","토","일"}; //체크박스 문자열로 사용할 문자열 배열
	private String[] time = {"11~12","12~13","13~14","14~15","15~16"}; //strsex의 문자열로 사용할 문자열 배열

	private JComboBox<String> strday = new JComboBox<String>(day);
	private JComboBox<String> strtime = new JComboBox<String>(time); 
	
	public MemberGUI(String[] member){
		setTitle("Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(null);
		scheduleP.setLayout(null);
		
		nameP.setBounds(20,20,100,30);
		infoP.setBounds(20,60,100,30);
		MinfoP.setBounds(130,60,300,30);
		scheduleP.setBounds(20,140,550,200);
		sche.setBounds(70,40,300,100);
		
		strday.setBounds(410, 40, 100, 30);
		strtime.setBounds(410, 80, 100, 30);
		
		logoutB.setBounds(470,100,100,30);
		M_add.setBounds(410, 120, 60, 25);
		M_add.addActionListener(new M_Add());
		
		scheduleL.setBounds(20,10,100,30);
		MembernameL.setBounds(20,20,150,30);
		
		nameP.setBackground(Color.LIGHT_GRAY);
		infoP.setBackground(Color.LIGHT_GRAY);
		scheduleP.setBackground(Color.LIGHT_GRAY);
		
		logoutB.setBackground(Color.LIGHT_GRAY);
		logoutB.addActionListener(new logoutButton());
		
		nameL.setText(member[1] + "님");
		info.setText("생년월일 :" + member[2] + " 성별 :" + member[3]);
		
		nameP.add(nameL);
		infoP.add(infoL);
		MinfoP.add(info);
		
		scheduleP.add(scheduleL);
		scheduleP.add(strday);
		scheduleP.add(strtime);
		scheduleP.add(M_add);
		scheduleP.add(sche);
		
		c.add(nameP);
		c.add(infoP);
		c.add(scheduleP);
		c.add(logoutB);
		c.add(MinfoP);
		
		setSize(600,400); //프레임 크키
		setVisible(true); //프레임 출력
		setResizable(false); //프레임 크기르 사용자가 지정할 수 없도록 함
	}
	//독립된 클래스로 이벤트 리스너 작성
	//로그아웃 버튼 눌렀을 때
	class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginGUI(); //로그인 화면으로 돌아감
			setVisible(false); // 창 안보이게 하기 
		}
	}

	class M_Add implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			sche.setText(strday.getSelectedItem().toString()+ " "+strtime.getSelectedItem().toString());
			sche.setEnabled(false); // 입력 비활성화
		}
	}
	
	
	public static void main(String[] args) {
		new MemberGUI(member);
	}
}
