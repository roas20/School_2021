package Swing;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;

public class LoginGUI extends JFrame{
	private JTextField JF[]  = new JTextField[4]; //ID�Է��ʵ�
	private JButton login = new JButton("�α���"); //�α��� ��ư
	private JButton Alogin = new JButton("������");
	private JPanel IDJP = new JPanel(); //���̵� �Է°� �α��� ��ư�� �ִ� �г�
	private ImageIcon icon = new ImageIcon("C:\\Users\\gksek\\�ｺ��.jpg"); //Ȩ �̹��� ����
	//private ImageIcon icon = new ImageIcon("image/�ｺ��.jpg");
	private JLabel Homelabel = new JLabel(icon); //icon�� label�� ����
	private String[] Member = new String[4];
	private String[] Trainer = new String[4];
	private JButton M_add = new JButton("ȸ�� ����"); //ȸ�� �߰�
	private JButton T_add = new JButton("Ʈ���̳� ����");
	private JButton shownotice = new JButton("��������");
	private JLabel notice =  new JLabel();
	public LoginGUI() {
		setTitle("Health"); //������ Ÿ��Ʋ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �����츦 ������ ���α׷� ����
		Container c = getContentPane(); //����Ʈ���� �˾Ƴ���
		
		c.setLayout(null); //����Ʈ���� ��ġ������ ����
		IDJP.setLayout(null); //IDJP�г��� ��ġ������ ����
		
		for(int i=0;i<JF.length;i++) { // JF���̸�ŭ �ݺ�
			JF[i] = new JTextField(""); //�ؽ�Ʈ �ʵ� ����
			JF[i].setBackground(Color.WHITE); //���� ����
			JF[i].setHorizontalAlignment(JLabel.CENTER); //�� ��ġ ����
			JF[i].setSize(30,30); //�ؽ�Ʈ �ʵ� ������ ����
			JF[i].setLocation(65 + i * 50, 130); //��ġ ����
			JF[i].setFont(new Font("Arial", Font.PLAIN, 30)); //��Ʈ�� �۾� ������ ����
			IDJP.add(JF[i]); //IDJP�гο� JF[i]����
		}
		
		Homelabel.setBounds(0,0,300,360); //Homelabel�� ������,��ġ ����
		
		login.setBounds(70,200,80,30); //login�� ������, ��ġ ����
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
		
		IDJP.setBounds(290,0,292,370); //IDJP�� ������,��ġ ����
		IDJP.setBackground(Color.WHITE); //IDJP ���� ����
		IDJP.add(login); //IDJP�� login��ư ����
		IDJP.add(Alogin);
		IDJP.add(M_add);
		IDJP.add(T_add);
		IDJP.add(notice);
		IDJP.add(shownotice);
		
		c.add(Homelabel); //����Ʈ�ҿ� Homelabel ����
		c.add(IDJP); //����Ʈ�ҿ� IDJP �г� ����
		
		setSize(600,400); //������ ũŰ
		setVisible(true); //������ ���
		setResizable(false); //������ ũ�⸣ ����ڰ� ������ �� ������ ��
	}
	
	//�Էµ� id�� ����� ȸ���� �ִ��� �˻�
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\����\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()); //���� �Է� ��Ʈ�� ����

				login.addActionListener(new loginButton()); //Action �̺�Ʈ ������ �ޱ�
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"������ �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}
	}
	
	//�Էµ� id�� �α���
	class loginButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(JF[0].getText().trim().length()==1&&JF[1].getText().trim().length()==1&&JF[2].getText().trim().length()==1&&JF[3].getText().trim().length()==1) { //�ؽ�Ʈ �ʵ��� ���� 1������ �˻�
				if(JF[0].getText().equals("T")) { //JF[0] ���� T�̸�
					if(Topenfile()) {
						new TrainerGUI(Trainer); //Ʈ���̳ʷ� �α���
						setVisible(false); //â �Ⱥ��̰� �ϱ� 
					}
				}
				else if(isNumber(JF[0].getText().trim())){ //jF[0]�� �����̸�
					if(Mopenfile()) {
						new MemberGUI(Member);
						setVisible(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "���̵� �ٽ� Ȯ�����ּ���","�ٽ� �Է�",JOptionPane.WARNING_MESSAGE); //�޽��� ���̾�α� ����
				}
			}
			else //�ؽ�Ʈ �ʵ� ���� ��������
				JOptionPane.showMessageDialog(null, "���� �ϳ��� �Է��ϼ���","���� ������ �Է� ����",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
		}
	}
	
	
	//�Էµ� id�� ������ ���̵����� Ȯ���ϴ� ActionListener
	class AdminloginButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(JF[0].getText().trim().length()==1&&JF[1].getText().trim().length()==1&&JF[2].getText().trim().length()==1&&JF[3].getText().trim().length()==1) { //�ؽ�Ʈ �ʵ��� ���� 1������ �˻�
				if(JF[0].getText().equals("A")&&JF[1].getText().equals("5")&&JF[2].getText().equals("2")&&JF[3].getText().equals("7")) { //JF[0]���� A�̰� JF[1]���� 5�̰� JF[2]���� 2�̰� JF[3]���� 7�̸�
					new AdminGUI(); //�����ڷ� �α���
					setVisible(false); //â �Ⱥ��̰� �ϱ� 
				}
				else {
					JOptionPane.showMessageDialog(null, "������ ���̵� �ƴմϴ�.","�ٽ� �Է�",JOptionPane.WARNING_MESSAGE); //�޽��� ���̾�α� ����
				}
			}
			else //�ؽ�Ʈ �ʵ� ���� ��������
				JOptionPane.showMessageDialog(null, "���� �ϳ��� �Է��ϼ���","���� ������ �Է� ����",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
		}
	}
	
	public boolean Mopenfile() {
		int ok = 0;
		try {	
			BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\����\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()));
			while(true) {
				String text = br.readLine();
				if(text != null) {
					String[] strArray = text.split(",");
					for(int i=0;i<strArray.length;i++) {
						Member[i] = strArray[i];
						System.out.println(Member[i]);
					}
					ok=1;
					JOptionPane.showMessageDialog(null, "�α����� �����մϴ�.");
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
			BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\����\\"+JF[0].getText()+JF[1].getText()+JF[2].getText()+JF[3].getText()));
			while(true) {
				String text = br.readLine();
				if(text != null) {
					String[] strArray = text.split(",");
					for(int i=0;i<strArray.length;i++) {
						Trainer[i] = strArray[i];
						System.out.println(Trainer[i]);
					}
					ok=1;
					JOptionPane.showMessageDialog(null, "�α����� �����մϴ�.");
					return true;
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return false;
	}
	
	//�Էµ� id�� �������� �ƴ��� Ȯ���ϴ� �޼ҵ�
	static boolean isNumber(String str) { 
		boolean result = true;
		// null, �����Ͻ�
		if (str == null || str.length() == 0) {
			result = false;
		}
		// null�̳� ������ �ƴҽ�
		else {
			for (int i = 0; i < str.length(); i++) {
				int c = (int) str.charAt(i);
				// ���ڰ� �ƴ϶��
				if (c < 48 || c > 57) {
					result = false;
				}
			}
		}
		return result;
	}
	
	//ȸ�� �߰�
	class MaddButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new memberSignUp();
		}
	}

	//Ʈ���̳� �߰�
	class TaddButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new trainerSignUp();
		}
	}
	
	class shownotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\��������\\����"); //���� �Է� ��Ʈ�� ����
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"������ �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //������ ���� ��
				String c=null;
				notice.setText(""); //�ʱ�ȭ ��Ŵ
				while((c=br.readLine())!=null) { 
					notice.setText(c+"\n"); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void main(String[] args) {
		new LoginGUI();
	}
}
