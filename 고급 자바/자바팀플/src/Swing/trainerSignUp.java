package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class trainerSignUp extends JFrame{
	private JPanel signUpPanel = new JPanel(new GridLayout(18,1)); //���� ���� 18�̰� ���� ���� 1�� Panel ����
	private JButton join = new JButton("�����ϱ�"); //���� ��ư ����
	private JTextField id = new JTextField("���̵�",20); //id �ؽ�Ʈ �ʵ�
	private JTextField name = new JTextField("�̸�",10); //name �ؽ�Ʈ �ʵ�
	private JTextField day = new JTextField("��",5); //day �ؽ�Ʈ �ʵ�
	private JLabel Ltrainer = new JLabel("Ʈ���̳� �߰�");
	private JLabel dateOfbirth = new JLabel("�������"); //���ڿ� �� ����
	private JLabel Lsex = new JLabel("����"); //���ڿ� �� ����
	private JLabel Lagree = new JLabel("�������� ���� �� �̿� ����(�ʼ�)");  //���ڿ� �� ����
	private JCheckBox[] Jagree = new JCheckBox[2]; //üũ�ڽ� �迭
	private String[] agree = {"����","����"}; //üũ�ڽ� ���ڿ��� ����� ���ڿ� �迭
	private String[] sex = {"����","��","��"}; //strsex�� ���ڿ��� ����� ���ڿ� �迭
	private JComboBox<String> year = new JComboBox<String>(); // �⵵�� ����Ǵ� �޺��ڽ� ����
	private JComboBox<String> month = new JComboBox<String>(); // ���� ����Ǵ� �޺��ڽ� ����
	private JComboBox<String> strsex = new JComboBox<String>(sex); // sex�� ����Ǵ� �޺��ڽ� ����
	public trainerSignUp() {
		setTitle("ȸ������"); //�������� Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �����츦 ������ ���α׷� ����
		
		Container c = getContentPane(); //����Ʈ���� �˾Ƴ���.
		c.setLayout(new FlowLayout()); //����Ʈ�ҿ� FlowLayout ��ġ������ �ޱ�
	
		signUpPanel.add(Ltrainer);
		signUpPanel.add(id); //signUpPanel�� id ����
		signUpPanel.add(name); //signUpPanel�� Lastname ����
		
		signUpPanel.add(dateOfbirth); //signUpPanel�� dateOfbirth ����
		
		year.addItem("�⵵"); //year �޺��ڽ��� "�⵵"������ ����
		for(int i=1993;i<=2002;i++) { 
			year.addItem(Integer.toString(i)); //1993 ~ 2002�� int�� �ٲ� year�� �޺��ڽ��� ���������� ����
			signUpPanel.add(year); //signUpPanel�� year �߰�
		}
		
		month.addItem("��"); //month �޺��ڽ��� "��"������ ����
		for(int i=1;i<=12;i++) { 
			month.addItem(Integer.toString(i)); //1 ~ 12�� int�� �ٲ� month�� �޺��ڽ��� ���������� ����
			signUpPanel.add(month); //signUpPanel�� month ����
		}
		
		signUpPanel.add(day); //signUpPanel�� day ����
		signUpPanel.add(Lsex); //signUpPanel�� Lsex ����
		signUpPanel.add(strsex); //signUpPanel�� strsex ����
		signUpPanel.add(Lagree); //signUpPanel�� Lagree ����
		
		for(int i=0;i<agree.length;i++) { 
			Jagree[i] = new JCheckBox(agree[i]); //agree[]�� ���ڿ��� üũ�ڽ� ����
			Jagree[i].setBorderPainted(true); //äũ�ڽ��� �ܰ����� ���̵��� ����
			signUpPanel.add(Jagree[i]); //signUpPanel�� üũ�ڽ� ����
			Jagree[i].addItemListener(new MyItemListener()); //üũ�ڽ��� Item ������ ���
		}
		signUpPanel.add(join); //signUpPanel�� join ����
	
		join.addActionListener(new MyActionListener()); //��ư�� Action������ ���
		join.addActionListener(new saveInfo());
		
		c.add(signUpPanel); //����Ʈ�ҿ� signUpPanel ����
		
//		Dimension frameSize = getSize();
//	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
//	    setLocation((windowSize.width - frameSize.width) / 2,
//	                (windowSize.height - frameSize.height) / 2); //ȭ�� �߾ӿ� ����
//	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
		setSize(300,450); //������ ũ�� 300x560
		setVisible(true); //������ ���
	}
	class MyActionListener implements ActionListener{ //��� ���� �ԷµǾ����� üũ
		public void actionPerformed(ActionEvent e) {
			if(id.getText().trim().length()==0||id.getText().trim().equals("���̵�")){  //�ؽ�Ʈ�ʵ� id�� length�� 0�̰ų� id�� �ؽ�Ʈ�� "���̵�"�̸�
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���","���̵� �Է�",JOptionPane.WARNING_MESSAGE); //�޽��� ���̾�α� ����
			}
			if(year.getSelectedIndex()==0){ //year�� ���õ� ����� ������
				JOptionPane.showMessageDialog(null, "���ϳ⵵�� �������ּ���","���ϳ⵵ ����",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
			}
			if(month.getSelectedIndex()==0){ //month�� ���õ� ����� ������
				JOptionPane.showMessageDialog(null, "���� �������ּ���","�� ����",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
			}
			if(day.getText().trim().length()==0||day.getText().trim().equals("��")){ //�ؽ�Ʈ�ʵ� day�� length�� 0�̰ų� day�� �ؽ�Ʈ�� "��"�̸�
				JOptionPane.showMessageDialog(null, "�ϸ� �Է����ּ���","�� �Է�",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
			}
			if(name.getText().trim().length()==0||id.getText().trim().equals("�̸�")){ //�ؽ�Ʈ�ʵ� Firstname�� length�� 0�̰ų� Firstname�� �ؽ�Ʈ�� "�̸�"�̸�
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���","�̸� �Է�",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
			}
			if(strsex.getSelectedIndex()==0){ //strsex�� ���õ� ����� ������
				JOptionPane.showMessageDialog(null, "������ �������ּ���","���� ����",JOptionPane.WARNING_MESSAGE);//�޽��� ���̾�α� ����
			}
		}
	}
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) { //üũ�ڽ��� ���� ���°� ���ϸ� itemStateChanger()�� ȣ���
			if(e.getStateChange() == ItemEvent.SELECTED) {  //äũ�ڽ��� ���õ� ���
				if(e.getItem() == Jagree[1]) { //"����"�̸�
					JOptionPane.showMessageDialog(null, "�������� ������ ������ �Ұ��մϴ�.","���� üũ",JOptionPane.WARNING_MESSAGE); //�޽��� ���̾�α� ����
				}
			}
		}
	}
	
	private class saveInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("C:\\Temp\\����\\"+ id.getText()); //��η� ���� �����
				FileWriter fw = new FileWriter(file, true); 
				//���Ͽ� ���� ����
				fw.write(id.getText()+","+name.getText()+","+year.getSelectedItem()+"�� "+ month.getSelectedItem()+"�� "+day.getText()+"�� "+","+strsex.getSelectedItem());
				fw.close();
			}catch(IOException e1) {
				JOptionPane.showMessageDialog(null,"���� ����","Message",JOptionPane.ERROR_MESSAGE);
			}
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new trainerSignUp();
	}
}
