package Chat;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.Manager;

public class MakeRoomForm extends JFrame implements ActionListener, ItemListener{
	//�󺧵�
	private JLabel jl_MakeRoomForm_title;
	private JLabel jl_MakeRoomForm_roomname;
	private JLabel jl_MakeRoomForm_roompw;
	private JLabel jl_MakeRoomForm_personnumber;
	//���� ���� �ο� �����ϴ� �޺��ڽ�
	private JComboBox jcb_MakeRoomForm_personnumber;
	//���� �Է� â
	private JTextField jtf_MakeRoomForm_roomname;
	private JTextField jtf_MakeRoomForm_roompw;
	//��ư��
	private JButton jb_MakeRoomForm_makeroom;
	private JButton jb_MakeRoomForm_cancle;
	
	//��Ʈ�ѷ� Ŭ����
	private Manager manager;
	//���� �ο��� ��Ʈ ���� �⺻������ ���õŰ� ��
	private String personlimit = "2";
	private String titlecolor = "BLACK";
	
	public MakeRoomForm(){
		super("�游���");
		
		this.setLayout(null);
		this.setSize(270, 250);
		//ũ�� ���� �Ұ���
		this.setResizable(false);
		
		manager = new Manager();
		
		//�� ���� �ο�
		String[] person = {"2", "4", "8", "16"};
		
		Container con = this.getContentPane();
		
		jl_MakeRoomForm_title    		= new JLabel("�� �����");
		jl_MakeRoomForm_roomname  		= new JLabel("������");
		jl_MakeRoomForm_roompw			= new JLabel("��й�ȣ");
		jl_MakeRoomForm_personnumber	= new JLabel("�ο�");
		jcb_MakeRoomForm_personnumber 	= new JComboBox(person);
		jtf_MakeRoomForm_roomname 		= new JTextField(20);
		jtf_MakeRoomForm_roompw			= new JTextField(20);
		jb_MakeRoomForm_makeroom 		= new JButton("�����");
		jb_MakeRoomForm_cancle 			= new JButton("���");
		
		//��ư ���� ����
		jb_MakeRoomForm_makeroom.setFont(new Font("Dialog", Font.BOLD, 12));
		jb_MakeRoomForm_makeroom.setMargin(new Insets(0, 0, 0, 0));
		jb_MakeRoomForm_makeroom.setHorizontalTextPosition(JButton.LEFT);
		
		jb_MakeRoomForm_cancle.setFont(new Font("Dialog", Font.BOLD, 12));
		jb_MakeRoomForm_cancle.setMargin(new Insets(0, 0, 0, 0));
		jb_MakeRoomForm_cancle.setHorizontalTextPosition(JButton.LEFT);
		
		//�����ӿ� ������Ʈ ����
		con.add(jl_MakeRoomForm_title);
		con.add(jl_MakeRoomForm_roomname);
		con.add(jl_MakeRoomForm_roompw);
		con.add(jl_MakeRoomForm_personnumber);
		con.add(jcb_MakeRoomForm_personnumber);
		con.add(jtf_MakeRoomForm_roomname);
		con.add(jtf_MakeRoomForm_roompw);
		con.add(jb_MakeRoomForm_makeroom);
		con.add(jb_MakeRoomForm_cancle);
	    
		//������Ʈ ����
		jl_MakeRoomForm_title.setBounds(108, 3, 80, 25);
		jl_MakeRoomForm_roomname.setBounds(5, 30, 80, 25);
		jl_MakeRoomForm_roompw.setBounds(5, 60, 80, 25);
		jl_MakeRoomForm_personnumber.setBounds(5, 100, 80, 25);
		jcb_MakeRoomForm_personnumber.setBounds(50, 100, 50, 25);
		jtf_MakeRoomForm_roomname.setBounds(60, 30, 200, 25);
		jtf_MakeRoomForm_roompw.setBounds(60, 60, 200, 25);
		jb_MakeRoomForm_makeroom.setBounds(60, 150, 65, 30);
		jb_MakeRoomForm_cancle.setBounds(140, 150, 65, 30);
		
		//�������� ������ ȭ�� ���߾ӿ� ��ġ�ϰ� �ϱ� ���� ��ǥ
		int x = (int)this.getToolkit().getScreenSize().getWidth() / 2 - (int)this.getSize().getWidth() / 2;
		int y = (int)this.getToolkit().getScreenSize().getHeight() / 2 - (int)this.getSize().getHeight() / 2;
		
		//������ ��� ��ǥ ����
		this.setLocation(x, y);
		//�������� X������ �� �ƹ� �۾� ���ϰ� ����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//�������� X������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				//������ �Ⱥ��̰� ��
				MakeRoomForm.this.setVisible(true);
				//�ڿ�����
				MakeRoomForm.this.dispose();
			}
		});
		
		//�׼� ���
		jcb_MakeRoomForm_personnumber.addItemListener(this);
		jb_MakeRoomForm_makeroom.addActionListener(this);
		jb_MakeRoomForm_cancle.addActionListener(this);
	}
	
	//�� ���� ��� �޼���
	public String getInfo(){
		//�Է��� ���̸� ����
		String roomname = this.jtf_MakeRoomForm_roomname.getText();
		//�Է��� ��й�ȣ ����
		String roompw = this.jtf_MakeRoomForm_roompw.getText();
		return roomname + "/" + personlimit + "/" + titlecolor + "/" + roompw;
	}

	public void actionPerformed(ActionEvent e){
		//�游��� ��ư ������ �� ����
		if(e.getSource() == jb_MakeRoomForm_makeroom){
			//��Ʈ�ѷ��� ���� �� Ű �� ����
			manager.service("MakeRoom");
			jtf_MakeRoomForm_roomname.setText("");
			jtf_MakeRoomForm_roompw.setText("");
		}
		//��� ��ư ������ �� ����
		else{
			//������ �Ⱥ��̰� ��
			this.setVisible(true);
			//�ڿ����� 
			this.dispose();
		}
	}

	public void itemStateChanged(ItemEvent e){
		//���� �ο� �������� �� ����
		if(e.getSource() == this.jcb_MakeRoomForm_personnumber){
			//���� �ο� ��
			personlimit = this.jcb_MakeRoomForm_personnumber.getSelectedItem().toString();
		}
	}
}