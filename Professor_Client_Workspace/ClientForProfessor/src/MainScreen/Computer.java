package MainScreen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Communication.Transit;
import Controller.Manager;
import FileTransfer.SendFile;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

//��ǻ�� �ڸ� Ŭ����
public class Computer extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private String id = null;
	private String userName = null;
	private String startedTime = null;
	private boolean isOccupied = false;
	private int pcNumber = 0;
	private JLabel numberLabel = null;
	private JLabel nameLabel = null;
	private JLabel timeLabel = null;
	private ImageIcon img = null;
	private ImageIcon standardImg = new ImageIcon(".\\image\\��ǻ���гα⺻.png");
	private ImageIcon connecteImg = new ImageIcon(".\\image\\��ǻ���г�����.png");
	private JPopupMenu computerPopUpMenu = null;
	private JMenuItem privateChatPopUpMenuItem = null;		//1:1 ��ȭ
	private JMenuItem sendNotePopUpMenuItem = null;			//����������
	private JMenuItem sendFilePopUpMenuItem = null;			//���Ϻ�����
	private JMenuItem screenShotPopUpMenuItem = null;		//��ũ�� �� ���
	private JMenuItem seeMovingImagePopUpMenuItem = null;	//���� ȭ�� ����
	private JMenuItem seeMenuItem = null;	//����

	//������
	public Computer(int pcNumber){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.initializeMember(pcNumber);
		this.setSize(80, 100);
		this.setLayout(null);
		this.createLabel();
		this.createPopUpMenu();
		this.mountEventHandler();
	}

	public void changeComputerState(String name, String id){
		System.out.println("Occupied");
		this.id = id;
		this.userName = name;
		if(name != null){
			nameLabel.setText(name);
			img = connecteImg;
		}
		else if(name == null){
			nameLabel.setText("");
			img = standardImg;
		}
		repaint();
	}

	public void initializeMember(int pcNumber){
		this.pcNumber = pcNumber;
		img = standardImg;
	}

	public int getPCNumber(){
		return pcNumber;
	}

	public void createLabel(){
		numberLabel = new JLabel("PC-" + Integer.toString(pcNumber));
		numberLabel.setBounds(5, 3, 45, 22); 
		numberLabel.setFont(new Font("Gulim",Font.BOLD, 12));
		this.add(numberLabel);

		nameLabel = new JLabel("");
		nameLabel.setBounds(5, 35, 111, 19);
		this.add(nameLabel);

		timeLabel = new JLabel("");
		timeLabel.setBounds(12, 85, 97, 15);
		this.add(timeLabel);
	}

	public void occupyPosition(String userName, String startedTime, boolean isOccupied, ImageIcon img){
		this.userName = userName;
		this.startedTime = startedTime;
		this.isOccupied = isOccupied;
		this.img = img;
		nameLabel.setText(userName);
		timeLabel.setText(startedTime);
		this.repaint();
	}

	public void paintComponent(Graphics g){
		g.drawImage(img.getImage(),0, 0, 121, 126, null, null);
	}

	public void mountEventHandler(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//���콺 ������ ��ư
				if(e.getButton() == MouseEvent.BUTTON3){
					setUpEdge();
					//�˾� ����
					computerPopUpMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//���콺 ���� ��ư
				if(e.getButton() == MouseEvent.BUTTON1){
					setUpEdge();
					Transit.sendMsg("�������ǥ��/" + id + "/" + userName + "/" + pcNumber);
				}
			}
		});

		sendNotePopUpMenuItem.addActionListener(this);
		privateChatPopUpMenuItem.addActionListener(this);
		sendFilePopUpMenuItem.addActionListener(this);
		screenShotPopUpMenuItem.addActionListener(this);
		seeMovingImagePopUpMenuItem.addActionListener(this);
		seeMenuItem.addActionListener(this);
	}

	public void setUpEdge(){
		Vector<Computer> list = ComputerScreen.computerList;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getPCNumber() != pcNumber){
				list.get(i).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			}else{
				list.get(i).setBorder(new LineBorder(new Color(0, 0, 0), 3));
			}
		}
	}

	public void actionPerformed(ActionEvent e){
		//�˾��޴��� ����������
		if(e.getSource() == sendNotePopUpMenuItem){
			//�޽��� �Է¹޴� ���̾�α� ���
			String msg = JOptionPane.showInputDialog("�޽����� �Է��Ͻʽÿ�.");
			//�ƹ��͵� �Է� ������ ��� ����
			if(msg.length() == 0){
				JOptionPane.showMessageDialog(this, "�޽����� �Է��� �ֽʽÿ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			}
			//�Է� �� ��� ����
			else{
				Transit.sendMsg("����/" + msg + "/" + this.id);
			}
		}
		//�˾��޴��� 1:1��ȭ
		else if(e.getSource() == privateChatPopUpMenuItem){
			Transit.sendMsg("1:1��ȭ��û/" + this.id);
		}
		//�˾��޴��� ���Ϻ�����
		else if(e.getSource() == sendFilePopUpMenuItem){
			//������ ���� ����
			JFileChooser fileOpen = new JFileChooser("C:\\");
			fileOpen.showOpenDialog(this);
			File fileName = fileOpen.getSelectedFile();

			if(fileName == null){
				JOptionPane.showMessageDialog(fileOpen, "������ ������ ������ �ֽñ� �ٶ��ϴ�.", "File Send Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
				new SendFile(Manager.MAINFRAME, fileName);
				Transit.sendMsg("��������/" + this.id);
			}
		}
		//�˾��޴��� ��ũ���� ���
		else if(e.getSource() == screenShotPopUpMenuItem){
			Transit.sendMsg("��ũ������û/" + this.id);
		}
		//�˾��޴��� ����ȭ�� ����
		else if(e.getSource() == seeMovingImagePopUpMenuItem){
			Transit.sendMsg("����ȭ���û/" + this.id);
		}
		//�˾��޴��� ����
		else if(e.getSource() == seeMenuItem){
			Transit.sendMsg("���������û/" + this.id);
		}
	}

	//�˾� �޴� ����
	private void createPopUpMenu(){
		//�˾� �޴� ����
		computerPopUpMenu = new JPopupMenu();
		sendNotePopUpMenuItem = new JMenuItem("���� ������");  
		privateChatPopUpMenuItem = new JMenuItem("1:1 ��ȭ");    
		sendFilePopUpMenuItem = new JMenuItem("���� ������"); 
		screenShotPopUpMenuItem = new JMenuItem("��ũ�� �� ���");
		seeMovingImagePopUpMenuItem = new JMenuItem("���� ȭ�� ����");
		seeMenuItem = new JMenuItem("����");

		//�˾� �޴��� �޴������� ���� �ֱ�
		computerPopUpMenu.add(sendNotePopUpMenuItem);
		computerPopUpMenu.add(privateChatPopUpMenuItem);
		computerPopUpMenu.add(sendFilePopUpMenuItem);
		computerPopUpMenu.add(screenShotPopUpMenuItem);
		computerPopUpMenu.add(seeMovingImagePopUpMenuItem);
		computerPopUpMenu.addSeparator();
		computerPopUpMenu.add(seeMenuItem);
		
		//������ ����Ʈ�� �˾� �޴� �߰�
		this.add(computerPopUpMenu);
	} //�˾� �޴� ���� �Լ� ��
}