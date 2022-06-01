package Swing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TrainerGUI extends JFrame{

	private static String[] trainer = new String[4];
	//판넬 생성
	Panel namep = new Panel();
	Panel listp = new Panel();

	Panel writep = new Panel();
	
	//라벨 생성 및 위치 크기 설정
	JLabel writeL = new JLabel("<일지>");
	JLabel nameL = new JLabel();
	
	//버튼 생성
	JButton t_write = new JButton("일지 작성");
	JButton t_logout = new JButton("로그아웃");
	JButton t_add = new JButton("추가");
	JButton t_fix = new JButton("수정");
	JButton t_del = new JButton("삭제");
	
	//일지
	JTextArea tf1 = new JTextArea(20,60);
	JTextField tf2 = new JTextField(20);
	
	JButton open  = new JButton("열기");
	JButton save =  new JButton("저장");
	JButton newnotepad = new JButton("새로 만들기");
	
	public TrainerGUI(String[] trainer) {
		setTitle("Trainer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		listp.setLayout(null);
		writep.setLayout(null);
		
		//판넬 위치 및 크기 설정
		namep.setBounds(20, 20, 100, 30);
		listp.setBounds(20, 60, 100, 300);
		writep.setBounds(130, 60, 440, 300);
		
		//판넬 색상 설정
		namep.setBackground(Color.LIGHT_GRAY);
		listp.setBackground(Color.LIGHT_GRAY);
		writep.setBackground(Color.LIGHT_GRAY);
		
		writeL.setBounds(15, 10, 100, 25);
		
		//버튼 위치 및 크기 설정
		t_write.setBounds(0, 0, 100, 30);
		t_logout.setBounds(470,20,100,30);
		t_add.setBounds(400, 450, 60, 25);
		t_fix.setBounds(470, 450, 60, 25);
		t_del.setBounds(540, 450, 60, 25);
		
		//버튼 색상 설정
		t_write.setBackground(Color.LIGHT_GRAY);
		t_logout.setBackground(Color.LIGHT_GRAY);
		t_add.setBackground(Color.WHITE);
		t_fix.setBackground(Color.WHITE);
		t_del.setBackground(Color.WHITE);
		
		//ActionListener
		t_logout.addActionListener(new logoutButton());
		t_write.addActionListener(new writeButton());
		
		open.setBounds(205, 250, 60, 25);
		save.setBounds(270, 250, 60, 25);
		newnotepad.setBounds(335, 250, 100, 25);
		
		tf1.setBounds(20,42,390,200);
		tf2.setBounds(20,250,180,25);
		
		open.addActionListener(new MyActionListener());
		save.addActionListener(new MyActionListener2());	
		newnotepad.addActionListener(new MyActionListener3());
		
		// 컨테이너에 판넬 추가
		c.add(listp);
		c.add(namep);
		c.add(t_logout);
		c.add(writep);
		
		nameL.setText(trainer[1]+ "님");
		//네임판넬에 라벨 추가
		namep.add(nameL);
		
		//리스트판넬에 버튼 추가
		listp.add(t_write);
	
		writep.add(writeL);
		writep.add(open);
		writep.add(save);
		writep.add(newnotepad);
		writep.add(tf1);
		writep.add(tf2);
		
		setSize(600,400);
		setVisible(true);
		setResizable(false); //프레임 크기르 사용자가 지정할 수 없도록 함
	}
	
	//독립된 클래스로 이벤트 리스너 작성
	class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginGUI(); //로그인 화면으로 돌아감
			setVisible(false); // 창 안보이게 하기 
		}
	}
	class writeButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			writep.setVisible(true);
		}
	}
	
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\일지\\" + tf2.getText()); //문자 입력 스트림 생성
			} catch (FileNotFoundException e2) { //파일이 없으면 에러메세지창 띄우기
				JOptionPane.showMessageDialog(null,"파일이 없습니다.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //파일이 있을 때
				String c=null;
				tf1.setText(""); //초기화 시킴
				while((c=br.readLine())!=null) { 
					tf1.append(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"열기 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	private class MyActionListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\일지\\" + tf2.getText()); //tf2의 경로로 파일 만들기
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(tf1.getText()); //tf1에 저장되어 있는 문자를 파일에 저장
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"저장 실패","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MyActionListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			tf1.setText(null);
			tf2.setText(null);
		}
	}
	
	
	public static void main(String[] args) {
		new TrainerGUI(trainer);
	}
}
