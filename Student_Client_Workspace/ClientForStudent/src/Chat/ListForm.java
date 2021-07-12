package Chat;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Communication.Transit;
import Controller.Manager;
import FileTransfer.SendFile;

//����Ʈ ���� �� 
public class ListForm extends JDialog{
	//���� ��� ���� ����Ʈ
	public JList jl_ListForm_guestlist;
	//��ư��
	public JButton jb_ListForm_ok;
	private JButton jb_ListForm_cancle;
	private JScrollPane jsp_ListForm_forguestlist;
	
	private TitledBorder tborder_ListForm_forIds;
	//���̸�
	private String roomname;
	//�� Ÿ��Ʋ
	private String title;
	
	public ListForm(String roomname, String title , String[] users){
		//Ÿ��Ʋ�� modal ����
		super(Manager.MAINFRAME, title, true);
		this.roomname = roomname;
		this.title = title;
		
		this.setLayout(null);
		this.setSize(200, 325);
		//ũ�� ���� �Ұ���
		this.setResizable(false);
		
		Container con = this.getContentPane();
		
		//����Ʈ�� ���� ��� ����
		jl_ListForm_guestlist = new JList(users);
		jb_ListForm_ok = new JButton("Ȯ��");
		jb_ListForm_cancle = new JButton("���");
		
		jsp_ListForm_forguestlist = new JScrollPane(jl_ListForm_guestlist, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		con.add(jsp_ListForm_forguestlist);
		con.add(jb_ListForm_ok);
		con.add(jb_ListForm_cancle);
		
		JButton[] buttons = {jb_ListForm_ok, jb_ListForm_cancle};
		
		//��ư�� ���� ���� ����
		for(int i = 0; i < buttons.length; i++){
			buttons[i].setFont(new Font("Dialog", Font.BOLD, 12));
			buttons[i].setMargin(new Insets(0, 0, 0, 0));
			buttons[i].setHorizontalTextPosition(JButton.LEFT);
		}
		
		//����Ʈ�� Ÿ��Ʋ���� ����
		tborder_ListForm_forIds = BorderFactory.createTitledBorder("���� ����Ʈ");
		tborder_ListForm_forIds.setTitleJustification(TitledBorder.LEFT);
		jsp_ListForm_forguestlist.setBorder(tborder_ListForm_forIds);
		
		jsp_ListForm_forguestlist.setBounds(0, 0, 200, 250);
		jb_ListForm_ok.setBounds(40, 255, 50, 30);
		jb_ListForm_cancle.setBounds(100, 255, 50, 30);
		
		//�������� ȭ�� ���߾ӿ� ��ġ�ϰ� �ϱ� ���� ��ǥ
		int x = (int)this.getToolkit().getScreenSize().getWidth() / 2 - (int)this.getSize().getWidth() / 2;
		int y = (int)this.getToolkit().getScreenSize().getHeight() / 2 - (int)this.getSize().getHeight() / 2;
		
		//Ȯ�� ��ư ������ �� ����
		this.jb_ListForm_ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				handler();
			}
		});
		
		//��� ��ư ������ �� ����
		this.jb_ListForm_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//������ �Ⱥ��̰� ��
				ListForm.this.setVisible(false);
				//�ڿ�����
				ListForm.this.dispose();
			}
		});
		
		//�������� X������ �� �ڿ����� �۾���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//������ ��ǥ ����
		this.setLocation(x, y);
		//������ ������
		this.setVisible(true);
	}
	
	private void handler(){
		//Ÿ��Ʋ�� �������� �� ����
		if(title.equals("������")){
			//�Ⱥ��̰� ��
			setVisible(false);
			//�ڿ�����
			dispose();
		}
		//Ÿ��Ʋ�� �ʴ��� �� ����
		else if(title.equals("�ʴ�")){
			//������ ����� ���� �� ����
			if(jl_ListForm_guestlist.isSelectionEmpty()){
				JOptionPane.showMessageDialog(this, "�ʴ��� ����� ������ �ֽñ� �ٶ��ϴ�.", "Invite Error", JOptionPane.WARNING_MESSAGE);
			}
			//������ ����� ���� �� ����
			else{
				//������ ���� ����
				String selectedId = jl_ListForm_guestlist.getSelectedValue().toString();
				//������ Flag���� ����
				Transit.sendMsg("�ʴ�/" + "/" + roomname + "/" + selectedId);
				//������ �Ⱥ��̰� ��
				setVisible(false);
				//�ڿ�����
				dispose();
			}
		}
		//Ÿ��Ʋ�� ������ �� ����
		else if(title.equals("����")){
			//������ ����� ���� �� ����
			if(jl_ListForm_guestlist.isSelectionEmpty()){
				JOptionPane.showMessageDialog(this, "������ ���� ����� ������ �ֽñ� �ٶ��ϴ�.", "InstantMsg Error", JOptionPane.WARNING_MESSAGE);
			}
			//������ ����� ���� �� ����
			else{
				//������ ���� ����
				String selectedId = jl_ListForm_guestlist.getSelectedValue().toString();
				//�Է��� �޽��� ����
				String msg = JOptionPane.showInputDialog("�޽����� �Է��Ͻʽÿ�.");
				//�Է��� �޽����� ���� �� ����
				if(msg.length() == 0){
					JOptionPane.showMessageDialog(this, "�޽����� �Է��� �ֽʽÿ�.", "InstantMsg Error", JOptionPane.WARNING_MESSAGE);
				}
				//�Է��� �޽����� ���� �� ����
				else{
					//������ Flag���� ����
					Transit.sendMsg("����/" + msg + "/" + selectedId);
					//������ �Ⱥ��̰� ��
					setVisible(false);
					//�ڿ�����
					dispose();
				}
			}
		}
		//Ÿ��Ʋ�� 1:1��ȭ�� �� ����
		else if(title.equals("1:1��ȭ")){
			//������ ����� ���� �� ����
			if(jl_ListForm_guestlist.isSelectionEmpty()){
				JOptionPane.showMessageDialog(this, "1:1��ȭ�� �� ����� ������ �ֽñ� �ٶ��ϴ�.", "PrivateChat Error", JOptionPane.WARNING_MESSAGE);
			}
			//������ ����� ���� �� ����
			else{
				//������ ���� ����
				String selectedId = jl_ListForm_guestlist.getSelectedValue().toString();
				//������ Flag���� ����
				Transit.sendMsg("1:1��ȭ��û/" + selectedId);
				//������ �Ⱥ��̰� ��
				setVisible(false);
				//�ڿ�����
				dispose();
			}
		}
		//Ÿ��Ʋ�� ���� �������� �� ����
		else if(title.equals("���� ������")){
			//������ ����� ���� �� ����
			if(jl_ListForm_guestlist.isSelectionEmpty()){
				JOptionPane.showMessageDialog(this, "������ ���� ����� ������ �ֽñ� �ٶ��ϴ�.", "SendFile Error", JOptionPane.WARNING_MESSAGE);
			}
			//������ ����� ���� �� ����
			else{
				//������ ���� ����
				String selectedId = jl_ListForm_guestlist.getSelectedValue().toString();	
				//JFileChooser ����
				JFileChooser fileOpen = new JFileChooser("C:\\");
				fileOpen.showOpenDialog(this);
				File fileName = fileOpen.getSelectedFile();

				if(fileName == null){
					JOptionPane.showMessageDialog(fileOpen, "������ ������ ������ �ֽñ� �ٶ��ϴ�.", "File Send Error", JOptionPane.WARNING_MESSAGE);
				}
				else{
					new SendFile(Manager.MAINFRAME, fileName);
					Transit.sendMsg("��������/" + selectedId);
					//������ �Ⱥ��̰� ��
					setVisible(false);
					//�ڿ�����
					dispose();
				}
			}
		}
	}
}