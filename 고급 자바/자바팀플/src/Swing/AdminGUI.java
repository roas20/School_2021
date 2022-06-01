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
	//�г� ����
	private Panel nameP = new Panel(); //������ �̸� �г�
	private Panel listP = new Panel(); // ����Ʈ �г�
	private Panel memberP = new Panel(); //ȸ�� ���� �г�
	private Panel trainerP = new Panel(); //Ʈ���̳� ���� �г�
	private Panel noticeP = new Panel(); //�������� �г�
	
	
	private JTextField Minputid = new JTextField();
	private JTextField Tinputid = new JTextField();
	
	private JTextField Moutput = new JTextField();
	private JTextField Toutput = new JTextField();
	
	private JTextArea inputnotice = new JTextArea();
	//�� ����
	private JLabel Aname = new JLabel("������"); // namep�� �����Ǵ� ��

	//��ư ����
	private JButton A_Notice = new JButton("��������"); 
	private JButton A_Trainer = new JButton("Ʈ���̳ʰ���");
	private JButton A_Member = new JButton("ȸ������");
	private JButton A_logout = new JButton("�α׾ƿ�");
	private JButton Mshow = new JButton("���� ����"); //ȸ�� ���� ����
	private JButton Tshow = new JButton("���� ����"); //Ʈ���̳� ���� ����
	private JButton Mfix = new JButton("����"); //ȸ�� ���� ����
	private JButton Tfix = new JButton("����"); //Ʈ���̳� ���� ����
	private JButton addnotice = new JButton("�������� ���"); //�������� ���
	private JButton fixnotice = new JButton("����"); //�������� ����
	private JButton	shownotice = new JButton("���� ����"); //�������� ����
	public AdminGUI() {
		setTitle("Health");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		//Layout ���ֱ�
		c.setLayout(null);
	
		noticeP.setLayout(null);
		trainerP.setLayout(null);
		memberP.setLayout(null);
		listP.setLayout(null);
		
		//�г� ��ġ, ũ�� ����
		nameP.setBounds(20, 20, 110, 30);
		listP.setBounds(20, 60, 100, 300);
		memberP.setBounds(130, 60, 440, 300);
		trainerP.setBounds(130, 60, 440, 300 );
		noticeP.setBounds(130, 60, 440, 300);
		Minputid.setBounds(20,42,100,25);
		Tinputid.setBounds(20,42,100,25);
		Moutput.setBounds(20, 100, 300, 80);
		Toutput.setBounds(20, 100, 300, 80);
		
		//��ư ��ġ, ũ�� ����
		A_Notice.setBounds(0, 0, 110, 30);
		A_Notice.addActionListener(new noticeButton());
		A_Trainer.setBounds(0, 30, 110, 30);
		A_Trainer.addActionListener(new trainerButton());
		A_Member.setBounds(0, 60, 110, 30);
		A_Member.addActionListener(new memberButton());
		
		A_logout.setBounds(470,20,100,30);
		A_logout.addActionListener(new logoutButton()); //ActionListener �ޱ�

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
		//������
		noticeP.setBackground(Color.LIGHT_GRAY);
		trainerP.setBackground(Color.LIGHT_GRAY);
		memberP.setBackground(Color.LIGHT_GRAY);
		nameP.setBackground(Color.LIGHT_GRAY);
		listP.setBackground(Color.LIGHT_GRAY);
		A_Notice.setBackground(Color.LIGHT_GRAY);
		A_Trainer.setBackground(Color.LIGHT_GRAY);
		A_Member.setBackground(Color.LIGHT_GRAY);
		A_logout.setBackground(Color.LIGHT_GRAY);
		//�� �гο� ��, ��ư, ��ũ���� �߰�
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
		
		//����Ʈ�ҿ� �г� �߰�
		c.add(listP);
		c.add(nameP);
		c.add(A_logout);
		c.add(memberP);
		c.add(trainerP);
		c.add(noticeP);
		
		setSize(600,400);
		setVisible(true);
		setResizable(false); //������ ũ�⸣ ����ڰ� ������ �� ������ ��
	}
	
	//������ Ŭ������ �̺�Ʈ ������ �ۼ�
	//�α׾ƿ� ��ư�� ������ ��
	class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginGUI(); //�α��� ȭ������ ���ư�
			setVisible(false); // â �Ⱥ��̰� �ϱ� 
		}
	}
	
	//ȸ�� ���� ��ư�� ������ ��
	class memberButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(false);
			memberP.setVisible(true);
			trainerP.setVisible(false);
		}
	}
	
	//Ʈ���̳� ���� ��ư�� ������ ��
	class trainerButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(false);
			memberP.setVisible(false);
			trainerP.setVisible(true);
		}
	}

	//�������� ��ư�� ������ ��
	class noticeButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			noticeP.setVisible(true);
			memberP.setVisible(false);
			trainerP.setVisible(false);
		}
	}

	//ȸ�� ���� ������
	private class showMInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\����\\" + Minputid.getText()); //���� �Է� ��Ʈ�� ����
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"ȸ���� �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //������ ���� ��
				String c=null;
				Moutput.setText(""); //�ʱ�ȭ ��Ŵ
				while((c=br.readLine())!=null) { 
					Moutput.setText(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	//Ʈ���̳� ���� ������
	private class showTInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\����\\" + Tinputid.getText()); //���� �Է� ��Ʈ�� ����
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"Ʈ���̳ʰ� �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //������ ���� ��
				String c=null;
				Toutput.setText(""); //�ʱ�ȭ ��Ŵ
				while((c=br.readLine())!=null) { 
					Toutput.setText(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	//ȸ�� ���� ����
	private class fixMInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\����\\" + Minputid.getText()); 
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(Moutput.getText()); //Minput�� ����Ǿ� �ִ� ���ڸ� ���Ͽ� ����
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//Ʈ������ ���� ����
	private class fixTInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\����\\" + Tinputid.getText()); 
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(Toutput.getText()); //Tinputid�� ����Ǿ� �ִ� ���ڸ� ���Ͽ� ����
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//�������� ���
	private class addnotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\��������\\����"); 
				FileWriter fw = new FileWriter(file, true);
				
				fw.write(inputnotice.getText()); //inputnotice�� ����Ǿ� �ִ� ���ڸ� ���Ͽ� ����
					
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//�������� �ҷ�����
	private class shownotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Temp\\��������\\����"); //���� �Է� ��Ʈ�� ����
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"ȸ���� �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //������ ���� ��
				String c=null;
				inputnotice.setText(""); //�ʱ�ȭ ��Ŵ
			
				while((c=br.readLine())!=null) { 
					inputnotice.setText(c+"\n"); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
	
	//�������� ����
	private class fixnotice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\��������\\����"); 
				FileWriter fw = new FileWriter(file, true); 
			
				new FileOutputStream(file).close();
				fw.write(inputnotice.getText()); //inputnotice�� ����Ǿ� �ִ� ���ڸ� ���Ͽ� ����
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
		
	public static void main(String[] args) {
		new AdminGUI();
	}
}
