package Swing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TrainerGUI extends JFrame{

	private static String[] trainer = new String[4];
	//�ǳ� ����
	Panel namep = new Panel();
	Panel listp = new Panel();

	Panel writep = new Panel();
	
	//�� ���� �� ��ġ ũ�� ����
	JLabel writeL = new JLabel("<����>");
	JLabel nameL = new JLabel();
	
	//��ư ����
	JButton t_write = new JButton("���� �ۼ�");
	JButton t_logout = new JButton("�α׾ƿ�");
	JButton t_add = new JButton("�߰�");
	JButton t_fix = new JButton("����");
	JButton t_del = new JButton("����");
	
	//����
	JTextArea tf1 = new JTextArea(20,60);
	JTextField tf2 = new JTextField(20);
	
	JButton open  = new JButton("����");
	JButton save =  new JButton("����");
	JButton newnotepad = new JButton("���� �����");
	
	public TrainerGUI(String[] trainer) {
		setTitle("Trainer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		listp.setLayout(null);
		writep.setLayout(null);
		
		//�ǳ� ��ġ �� ũ�� ����
		namep.setBounds(20, 20, 100, 30);
		listp.setBounds(20, 60, 100, 300);
		writep.setBounds(130, 60, 440, 300);
		
		//�ǳ� ���� ����
		namep.setBackground(Color.LIGHT_GRAY);
		listp.setBackground(Color.LIGHT_GRAY);
		writep.setBackground(Color.LIGHT_GRAY);
		
		writeL.setBounds(15, 10, 100, 25);
		
		//��ư ��ġ �� ũ�� ����
		t_write.setBounds(0, 0, 100, 30);
		t_logout.setBounds(470,20,100,30);
		t_add.setBounds(400, 450, 60, 25);
		t_fix.setBounds(470, 450, 60, 25);
		t_del.setBounds(540, 450, 60, 25);
		
		//��ư ���� ����
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
		
		// �����̳ʿ� �ǳ� �߰�
		c.add(listp);
		c.add(namep);
		c.add(t_logout);
		c.add(writep);
		
		nameL.setText(trainer[1]+ "��");
		//�����ǳڿ� �� �߰�
		namep.add(nameL);
		
		//����Ʈ�ǳڿ� ��ư �߰�
		listp.add(t_write);
	
		writep.add(writeL);
		writep.add(open);
		writep.add(save);
		writep.add(newnotepad);
		writep.add(tf1);
		writep.add(tf2);
		
		setSize(600,400);
		setVisible(true);
		setResizable(false); //������ ũ�⸣ ����ڰ� ������ �� ������ ��
	}
	
	//������ Ŭ������ �̺�Ʈ ������ �ۼ�
	class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginGUI(); //�α��� ȭ������ ���ư�
			setVisible(false); // â �Ⱥ��̰� �ϱ� 
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
				fr = new FileReader("C:\\Temp\\����\\" + tf2.getText()); //���� �Է� ��Ʈ�� ����
			} catch (FileNotFoundException e2) { //������ ������ �����޼���â ����
				JOptionPane.showMessageDialog(null,"������ �����ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr); 
			try { //������ ���� ��
				String c=null;
				tf1.setText(""); //�ʱ�ȭ ��Ŵ
				while((c=br.readLine())!=null) { 
					tf1.append(c); 
				}
				fr.close();
				br.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	
	private class MyActionListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\����\\" + tf2.getText()); //tf2�� ��η� ���� �����
				FileWriter fw = new FileWriter(file, true); 

				new FileOutputStream(file).close();
				fw.write(tf1.getText()); //tf1�� ����Ǿ� �ִ� ���ڸ� ���Ͽ� ����
				
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
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
