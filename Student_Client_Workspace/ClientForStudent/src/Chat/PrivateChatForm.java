package Chat;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Communication.Transit;

//1:1��ȭ �� ���� Ŭ����
public class PrivateChatForm extends JFrame implements ActionListener{
	public JTextPane jtp_PrivateChatForm_text;
	private JButton jb_PrivateChatForm_send;
	private JTextField jtf_PrivateChatForm_sendMsg;
	private JScrollPane jsp_PrivateChatForm_forchat;
	//���� ���̵� 
	public String receiver;
	
	public PrivateChatForm(String receiver){
		this.setIconImage(new ImageIcon(".\\image\\�л���л�.png").getImage());		//������ �̹���
		this.receiver = receiver;
		this.setTitle("[" + receiver + "]�԰� 1:1ä��");
		
		this.setLayout(null);
		this.setSize(300, 300);
		//������ ũ�� ���� �Ұ���
		this.setResizable(false);
		
		Container con = this.getContentPane();
		
		jtp_PrivateChatForm_text = new JTextPane();
		jb_PrivateChatForm_send = new JButton("������");
		jtf_PrivateChatForm_sendMsg = new JTextField();
		
		jsp_PrivateChatForm_forchat	= new JScrollPane(jtp_PrivateChatForm_text, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//������ ȭ�� ���߾ӿ� ��ġ��Ű�� ���� ��ǥ
		int x = (int)this.getToolkit().getScreenSize().getWidth() / 2 - (int)this.getSize().getWidth() / 2;
		int y = (int)this.getToolkit().getScreenSize().getHeight() / 2 - (int)this.getSize().getHeight() / 2;
		
		jtp_PrivateChatForm_text.setEditable(false);
		
		//��ư �Ӽ� ����
		jb_PrivateChatForm_send.setFont(new Font("Dialog", Font.BOLD, 12));
		jb_PrivateChatForm_send.setMargin(new Insets(0, 0, 0, 0));
		jb_PrivateChatForm_send.setHorizontalTextPosition(JButton.LEFT);
		
		con.add(jsp_PrivateChatForm_forchat);
		con.add(jb_PrivateChatForm_send);
		con.add(jtf_PrivateChatForm_sendMsg);
		
		jsp_PrivateChatForm_forchat.setBounds(20, 10, 250, 200);
		jtf_PrivateChatForm_sendMsg.setBounds(20, 220, 180, 25);
		jb_PrivateChatForm_send.setBounds(210, 220, 60, 25);
		
		//�̺�Ʈ ���
		jtf_PrivateChatForm_sendMsg.addActionListener(this);
		jb_PrivateChatForm_send.addActionListener(this);
		
		//�������� X�� ������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				//������ Flag���� ����
				Transit.sendMsg("1:1��ȭ/��ȭâ���� �����̽��ϴ�./" + PrivateChatForm.this.receiver);
				//â �Ⱥ��̰� ��
				PrivateChatForm.this.setVisible(false);
				//â �ڿ����� 
				PrivateChatForm.this.dispose();
			}
		});
		//�������� X�� ������ �� �ƹ� �۾��� ����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.setLocation(x, y);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		//�޽��� �Է�â�� �Է��� �޽��� ����
		String msg = jtf_PrivateChatForm_sendMsg.getText();
		//�ƹ��͵� �Է����� �ʾ��� �� ���� ����
		if(msg.length() == 0){
			msg = " ";
		}
		//������ Flag���� ����
		Transit.sendMsg("1:1��ȭ/" + msg + "/" + receiver);
		//�޽��� �Է�â ��
		jtf_PrivateChatForm_sendMsg.setText("");
	}
}