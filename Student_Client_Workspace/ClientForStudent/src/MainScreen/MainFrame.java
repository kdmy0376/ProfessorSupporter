package MainScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;

import Communication.Transit;
import Controller.Manager;
import FileTransfer.SendFile;
import FontDialog.FontDialog;
import Note.NoteAllForm;
import ScreenShot.CaptureScreen;
import ScreenShot.ImageClient;


public class MainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	//���ϸ޴�
	private JMenu fileMenu = null;
	private JMenuItem logoutMenuItem = null;	//�α׾ƿ�
	private JMenuItem exitMenuItem = null;		//���α׷� ����
	//ä�ø޴�
	private JMenu chatMenu = null;
	private JMenuItem createChatRoomMenuItem = null;	//�� �����
	private JMenuItem enterChatRoomMenuItem = null;		//�� �����ϱ�
	private JMenuItem privateChatMenuItem = null;		//1:1��ȭ�ϱ�
	private JMenu mainChatFormMenu = null;				//���� ��ȭ�� ����
	private JMenuItem changeFontMenuItem = null;		//�۲� �ٲٱ�
	private JMenuItem changeColorMenuItem = null;		//���� �ٲٱ�
	private JMenuItem sendNoteMenuItem = null;			//���� ������
	private JMenuItem sendAllNoteMenuItem = null;		//��ü���������� �޴�������
	//���۸޴�
	private JMenu sendMenu = null;
	private JMenuItem sendFileMenuItem = null;	//���� ������
	private JMenuItem sendMailMenuItem = null;	//���� ������
	//����޴�
	private JCheckBoxMenuItem showStudentListCheckMenuItem = null;	//������ ��� ���̱�
	private JCheckBoxMenuItem showChatListCheckMenuItem = null;		//��ȭ ��� ���̱�
	private JCheckBoxMenuItem showToolBarCheckMenuItem = null;		//���� ���̱�
	private JCheckBoxMenuItem showStatusBarCheckMenuItem = null; 	//����ǥ�ù� ���̱�
	//��Ʈ��ũ�޴�
	private JMenu networkMenu = null;
	private JMenuItem showCurrentIpAddressMenuItem = null;	//���� IP�ּ� ����
	//���򸻸޴�
	private JMenuItem informationMenuItem = null;	//���α׷� ����
	//��ü ���̾ƿ�
	private JPanel mainWestPanel = null;	//����
	private JPanel statusBarPanel = null;	//�ϴ�
	private JPanel toolBarPanel = null;		//���
	private JPanel mainEastPanel = null;	//����
	//�⺻ ����
	private JToggleButton showStudentListToggleButton = null;	//������ ���̱�
	private JToggleButton showChatListToggleButton = null;		//��ȭ ��� ���̱�
	private JToggleButton showStatusToggleButton = null;		//����ǥ�ù� ���̱�
	private JButton sendNoteButton = null;		//����
	private JButton chattingButton = null;		//ä�� 
	private JButton createChatRoomButton = null;	//�游���
	private JButton sendFileButton = null;			//���Ϻ�����
	private JButton sendEmailButton = null;			//���Ϻ�����
	//���� ��ȭ��
	private JList<String> chatRoomList = null;					//��ȭ�� ����Ʈ
	private JTextPane contentOfChattingTextPane = null;			//��ȭ����
	private JComboBox<String> connectedStudentComboBox = null;	//������ ��� �޺��ڽ�
	private JTextField inputTextField = null;					//��ȭ ���� �Է� �ؽ�Ʈ �ʵ�
	private JButton sendTextButton = null;						//������ ��ư
	//������ ���
	public JList<String> connectedStudentList = null;
	private JList<String> informationOfStudentList = null;
	//������ ��� �˾� �޴�
	private JPopupMenu connectorListPopUpMenu = null;
	private JMenuItem privateChatPopUpMenuItem = null;
	private JMenuItem sendNotePopUpMenuItem = null;
	private JMenuItem sendFilePopUpMenuItem = null;
	
	private final String TITLE = "Professor Supporter";	//������â ����
	private JLabel statusBarLabel = null;
	private JPanel contentPane = null;
	private Manager manager = new Manager();

	private String roomName = null;		//���õ� �� �̸�
	private String fontName = null;		//��Ʈ �̸�
	private int fontSize = 0;			//��Ʈ ũ��
	private int fontColor = 0;			//��Ʈ ����
	private String toUser = null;		//�ӼӸ� ���� ���
	private String selectedId = null;	//������ ����

	//������
	public MainFrame() {
		this.setIconImage(new ImageIcon(".\\image\\�л���л�.png").getImage());
		this.setTitle(TITLE);		//������â ����
		this.setSize(731, 500); 	//������ ������
		this.displayCenter();		//������ ��ġ
		this.createMenu();			//�޴� ����
		this.initializeMember();	//��� ���� �ʱ�ȭ
		this.createToolBar();		//���-���� ����
		this.createConnectorList();	//����-������ ��� ����
		this.createStatusBar();		//�ϴ�-����ǥ�ù� ����
		this.createChatList();		//����-��ȭ ��� ����
		this.createPopUpMenu();		//�˾� �޴� ����
		this.mountEventHandler();	//�̺�Ʈ ó���� ���
	} //������ ��

	//ȭ�� �߾� ǥ��
	private void displayCenter(){
		//ȭ���� ũ��
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();	
		//�������� ũ��
		Dimension frameDimension = this.getSize();
		//ȭ�� ����/2 - ������ ����/2
		int xPosition = (int)(screenDimension.getWidth()/2 - frameDimension.getWidth()/2);
		//ȭ�� ����/2 - ������ ����/2
		int yPosition = (int)(screenDimension.getHeight()/2 - frameDimension.getHeight()/2) - 20;
		this.setLocation(xPosition, yPosition);
	} //ȭ�� �߾� ǥ�� �Լ� ��

	//���õ� ���̸� ��������
	public String getSelectedRoomName(){
		return roomName;
	}
	//��ȭ ���� ��������
	public JTextPane getChatContent(){
		return contentOfChattingTextPane;
	}
	//������ ��� �޺��ڽ� ��������
	public JComboBox<String> getConnectedStudentComboBox(){
		return connectedStudentComboBox;
	}
	//������ ��� ��������
	public JList<String> getConnectedStudentList(){
		return connectedStudentList;
	}
	//��ȭ�� ��� ��������
	public JList<String> getChatRoomList(){
		return chatRoomList;
	}
	//������ ���� ��� ��������
	public JList<String> getInformationOfStudentList(){
		return informationOfStudentList;
	}

	//��Ʈ �̸� ����
	public void setFontName(String fontName){
		this.fontName = fontName;
	}
	//��Ʈ ũ�⼳��
	public void setFontSize(int fontSize){
		this.fontSize = fontSize;
	}
	//��Ʈ ������
	public void setFontColor(int fontColor){
		this.fontColor = fontColor;
	}

	//��� ���� �ʱ�ȭ �� ����
	private void initializeMember(){
		this.contentPane = new JPanel();		//������ ������ ������
		this.fontName = "����";
		this.fontSize = 12;
		this.fontColor = Color.BLACK.getRGB();
		this.toUser = "��ο���";
		this.setContentPane(contentPane);
	} //��� ���� �ʱ�ȭ �� ���� �Լ� ��

	//�̺�Ʈ ó���� ���
	private void mountEventHandler(){
		//���ϸ޴�
		logoutMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);	
		//ä�ø޴�
		createChatRoomMenuItem.addActionListener(this);
		enterChatRoomMenuItem.addActionListener(this);
		privateChatMenuItem.addActionListener(this);
		changeFontMenuItem.addActionListener(this);
		changeColorMenuItem.addActionListener(this);
		sendNoteMenuItem.addActionListener(this);
		sendAllNoteMenuItem.addActionListener(this);
		//���� �޴�
		sendFileMenuItem.addActionListener(this);
		sendMailMenuItem.addActionListener(this);
		//����޴�
		showToolBarCheckMenuItem.addActionListener(this);
		showChatListCheckMenuItem.addActionListener(this);
		showStudentListCheckMenuItem.addActionListener(this);
		showStatusBarCheckMenuItem.addActionListener(this);		
		//��Ʈ��ũ �޴�
		showCurrentIpAddressMenuItem.addActionListener(this);
		//���� �޴�
		informationMenuItem.addActionListener(this);
		//������ ��� �˾� �޴�
		sendNotePopUpMenuItem.addActionListener(this); 
		privateChatPopUpMenuItem.addActionListener(this);   
		sendFilePopUpMenuItem.addActionListener(this);
		//�⺻ ����
		showChatListToggleButton.addActionListener(this);
		showStudentListToggleButton.addActionListener(this);
		showStatusToggleButton.addActionListener(this);
		sendNoteButton.addActionListener(this);
		chattingButton.addActionListener(this);
		createChatRoomButton.addActionListener(this);
		sendFileButton.addActionListener(this);
		sendEmailButton.addActionListener(this);
		//�޽��� ���� â���� ����Ű�� ���� �̺�Ʈ
		inputTextField.addActionListener(this);
		sendTextButton.addActionListener(this);
		connectedStudentComboBox.addActionListener(this);
		//X��ư ������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.out.println("[����ȭ��] X ��ư Ŭ��");
				MainFrame.this.exit();
			}
		});
		//�������� X�� ������ �� �ƹ��� ������ ����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//��ȭ�� ����Ʈ
		chatRoomList.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//���� �����ϰ� ���콺 �����ʹ�ư�� ������ ���� �߻�
				if(e.getButton() == MouseEvent.BUTTON3 && !chatRoomList.isSelectionEmpty()){
					//�� �̸� ����
					roomName = (String)chatRoomList.getSelectedValue();
					//������̸� ���̸����� [���]����
					if(roomName.startsWith("[���]")){
						roomName = roomName.substring(roomName.indexOf("]") + 2);
					}
					//���̸����� ���� �ο����� �����ο��� ǥ�� ����(1\2)
					roomName = roomName.substring(0, roomName.indexOf("("));
					//������ Flag���� ��
					Transit.sendMsg("������/" + roomName);
				}
			}
		});
		//������ ����Ʈ
		connectedStudentList.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//������ �����ϰ� ���콺 �����ʹ�ư�� ������ ���� �߻�
				if(e.getButton() == MouseEvent.BUTTON3 && !connectedStudentList.isSelectionEmpty()){
					//������ ������ ���
					selectedId = connectedStudentList.getSelectedValue().toString();
					//�˾� ����
					connectorListPopUpMenu.show(e.getComponent(), e.getX(), e.getY());
				}
				//���� ���� �� ���콺 ����
				else if(e.getButton() == MouseEvent.BUTTON1 && !connectedStudentList.isSelectionEmpty()){
					//������ ������ ���
					selectedId = connectedStudentList.getSelectedValue().toString();
					Transit.sendMsg("�������ǥ��/" + selectedId + "/" + "����" + "/" + "����");
				}
			}
		});
	} //�̺�Ʈ ó���� ��� �Լ� ��

	//�޽��� �Է�â�� �޽����� �Է��ϰ� ���ͳ� ��ư ������ ��� ����
	private void sendMessage(){
		String message = inputTextField.getText();
		if(message.length() == 0){
			message = " ";
		}
		Transit.sendMsg("���Ǵ�ȭ/" + message + "/" + fontName + "/" + fontSize + "/" + fontColor + "/" + toUser);
		inputTextField.setText("");
	}

	//�̺�Ʈ ó����
	public void actionPerformed(ActionEvent e){
		//���α׷� ���� �޴�
		if(e.getSource() == informationMenuItem){
			//�޽��� ��ȭ���� ǥ��(� ���� �߾�, �޽���, Ÿ��Ʋ, �޽�����ȭ����)
			JOptionPane.showMessageDialog(this, "Professor Supporter\n�������� ������ ���α׷��Դϴ�.", "���α׷� ����", JOptionPane.INFORMATION_MESSAGE);
		}
		//���� �޴�������
		else if(e.getSource() == exitMenuItem){
			this.exit();
		}
		//����ǥ�ù� ���̱� �޴��������̳� ��۹�ư
		else if(e.getSource() == showStatusBarCheckMenuItem || e.getSource() == showStatusToggleButton){
			if (statusBarPanel.isVisible()) {
				statusBarPanel.setVisible(false);
				showStatusToggleButton.setSelected(false);
				showStatusBarCheckMenuItem.setSelected(false);
			} else {
				statusBarPanel.setVisible(true);
				showStatusToggleButton.setSelected(true);
				showStatusBarCheckMenuItem.setSelected(true);
			}
		}
		//������ ��� ���̱� �޴��������̳� ��۹�ư
		else if(e.getSource() == showStudentListToggleButton || e.getSource() == showStudentListCheckMenuItem)
		{
			if (mainWestPanel.isVisible()) {
				mainWestPanel.setVisible(false);
				showStudentListToggleButton.setSelected(false);
				showStudentListCheckMenuItem.setSelected(false);
			} else {
				mainWestPanel.setVisible(true);
				showStudentListToggleButton.setSelected(true);
				showStudentListCheckMenuItem.setSelected(true);
			}
		}
		//��Ȯ ��� ���̱� �޴��������̳� ��۹�ư
		else if(e.getSource() == showChatListToggleButton || e.getSource() == showChatListCheckMenuItem){
			if (mainEastPanel.isVisible()) {
				mainEastPanel.setVisible(false);
				showChatListToggleButton.setSelected(false);
				showChatListCheckMenuItem.setSelected(false);
			} else {
				mainEastPanel.setVisible(true);
				showChatListToggleButton.setSelected(true);
				showChatListCheckMenuItem.setSelected(true);
			}
		}
		//���� ���̱� �޴�������
		else if(e.getSource() == showToolBarCheckMenuItem){
			if (toolBarPanel.isVisible()) {
				toolBarPanel.setVisible(false);
			} else {
				toolBarPanel.setVisible(true);
			}
		}
		//��ȭ�� �����ϱ� �޴�������
		else if(e.getSource() == enterChatRoomMenuItem){
			//���� ���� ������ ��� ����
			if(chatRoomList.isSelectionEmpty()){
				JOptionPane.showMessageDialog(this, "������ ���� ������ �ֽñ� �ٶ��ϴ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			}
			//���� ���� ���� ��� ����
			else{
				//������ �� �̸� ����
				roomName = (String)chatRoomList.getSelectedValue();
				//���̸����� ���� �ο����� �����ο��� ǥ�� ����(1\2)
				roomName = roomName.substring(0, roomName.indexOf("("));
				//��Ʈ�ѷ��� Ű�� ����
				manager.service("JoinRoom");
			}
		}
		//��ȭ�� ����� �޴��������̳� ��� ��ư
		else if(e.getSource() == createChatRoomMenuItem || e.getSource() == createChatRoomButton){
			Manager.MAKEROOMFORM.setVisible(true);
		}
		//1:1��ȭ �޴�������
		else if(e.getSource() == privateChatMenuItem){
			Transit.sendMsg("��ü����/1:1��ȭ/");
		}
		//���� �޴�������
		else if(e.getSource() == sendNoteMenuItem){
			Transit.sendMsg("��ü����/����/");
		}
		//��ü ���� ������ ������
		else if(e.getSource() == sendAllNoteMenuItem){
			new NoteAllForm().setVisible(true);
		}
		//�۲� �ٲٱ� �޴�������
		else if(e.getSource() == changeFontMenuItem){
			FontDialog fontChooser = new FontDialog();
			//��Ʈ ���̾�α� ���
			int result = fontChooser.showDialog(this);
			//Ȯ�� ��ư ������ ��� ����
			if (result == FontDialog.OK_OPTION){
				//������ ��Ʈ ����
				Font font = fontChooser.getSelectedFont(); 
				//��Ʈ ���� �� �� ��� ��������
				if(font == null){
					return;
				}
				//��Ʈ �̸� ����
				fontName = fontChooser.getSelectedFontFamily();
				//��Ʈ ũ�� ����
				fontSize = fontChooser.getSelectedFontSize();
			}
		}
		//���� �ٲٱ� �޴�������
		else if(e.getSource() == changeColorMenuItem){
			fontColor = JColorChooser.showDialog(this, "���ڻ�", this.contentOfChattingTextPane.getForeground()).getRGB();
		}
		//���� ������ �޴��������̳� ��۹�ư
		else if(e.getSource() == sendFileMenuItem || e.getSource() == sendFileButton){
			Transit.sendMsg("��ü����/���� ������/");
		}
		//�޽��� ���� â���� ����Ű
		else if(e.getSource() == inputTextField){
			this.sendMessage();
		}
		//�޽��� ������ ��ư
		else if(e.getSource() == sendTextButton){
			this.sendMessage();
		}
		//�α׾ƿ� �޴�������
		else if(e.getSource() == logoutMenuItem){
			//�α׾ƿ� ���� ���� ���̾�α� ���
			int x = JOptionPane.showConfirmDialog(this, "���� �α� �ƿ� �Ͻðڽ��ϱ�?", 
					"Logout", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			//Ȯ�� ������ �� ����
			if(x == JOptionPane.OK_OPTION){
				//��ȭâ ����
				this.contentOfChattingTextPane.setText("");
				//���� �� �Ⱥ��̰� ��
				this.setVisible(false);
				//���� �� �ڿ�����
				this.dispose();
				//��Ʈ�ѷ��� Ű�� ����
				manager.service("Logout");
			}
		}
		//������ ��� �޺��ڽ�
		else if(e.getSource() == connectedStudentComboBox){
			//������ ���� ����
			toUser = this.connectedStudentComboBox.getSelectedItem().toString();
		}
		//�˾��޴��� ���� ������
		else if(e.getSource() == sendNotePopUpMenuItem){
			//�޽��� �Է¹޴� ���̾�α� ���
			String msg = JOptionPane.showInputDialog("�޽����� �Է��Ͻʽÿ�.");
			//�ƹ��͵� �Է� ������ ��� ����
			if(msg.length() == 0){
				JOptionPane.showMessageDialog(this, "�޽����� �Է��� �ֽʽÿ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			}
			//�Է� �� ��� ����
			else{
				Transit.sendMsg("����/" + msg + "/" + this.selectedId);
			}
		}
		//�˾� �޴��� 1:1 ��ȭ
		else if(e.getSource() == privateChatPopUpMenuItem){
			Transit.sendMsg("1:1��ȭ��û/" + selectedId);
		}
		//�˾� �޴��� ���� ������
		else if(e.getSource() == sendFilePopUpMenuItem){
			//������ ���� ����
			//String selectedId = this.connectedStudentList.getSelectedValue().toString();
			JFileChooser fileOpen = new JFileChooser("C:\\");
			fileOpen.showOpenDialog(this);
			File fileName = fileOpen.getSelectedFile();

			if(fileName == null){
				JOptionPane.showMessageDialog(fileOpen, "������ ������ ������ �ֽñ� �ٶ��ϴ�.", "File Send Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
				new SendFile(Manager.MAINFRAME, fileName);
				Transit.sendMsg("��������/" + selectedId);
			}
		}
		//��ü���������� ���پ����� 
		else if(e.getSource() == sendNoteButton){
			new NoteAllForm().setVisible(true);  
		}
		//1:1��ȭ ���پ�����
		else if(e.getSource() == chattingButton){
			Transit.sendMsg("��ü����/1:1��ȭ/");
		}
	}
	
	//���� ó��
	private void exit(){
		//���� �����Ұ��� ����� ���̾�α�
		int x = JOptionPane.showConfirmDialog(this, "���� �����ðڽ��ϱ�?", "Quit", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE);
		//Ȯ�� ��ư ������ �� ����
		if(x == JOptionPane.OK_OPTION){
			//Flag ���� Out ����
			manager.service("Logout");
			//���α׷� ����
			System.exit(0);
		}			
	}
	
	//�˾� �޴� ����
	private void createPopUpMenu(){
		//�˾� �޴� ����
		connectorListPopUpMenu = new JPopupMenu();
		sendNotePopUpMenuItem = new JMenuItem("���� ������");  
		privateChatPopUpMenuItem = new JMenuItem("1:1 ��ȭ");    
		sendFilePopUpMenuItem = new JMenuItem("���� ������"); 
		
		//�˾� �޴��� �޴������� ���� �ֱ�
		connectorListPopUpMenu.add(sendNotePopUpMenuItem);
		connectorListPopUpMenu.add(privateChatPopUpMenuItem);
		connectorListPopUpMenu.add(sendFilePopUpMenuItem);
		
		//������ ����Ʈ�� �˾� �޴� �߰�
		connectedStudentList.add(connectorListPopUpMenu);
	} //�˾� �޴� ���� �Լ� ��

	//�޴� ����
	private void createMenu(){
		//�޴��� ����
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		//���ϸ޴�
		fileMenu = new JMenu("����");
		menuBar.add(fileMenu);
		logoutMenuItem = new JMenuItem("�α׾ƿ�");
		fileMenu.add(logoutMenuItem);
		fileMenu.addSeparator();
		fileMenu.addSeparator();
		exitMenuItem = new JMenuItem("���α׷� ����");
		exitMenuItem.setIcon(new ImageIcon(".\\image\\����(23x20).jpg"));
		fileMenu.add(exitMenuItem);

		//ä�ø޴�
		chatMenu = new JMenu("ä��");
		menuBar.add(chatMenu);
		createChatRoomMenuItem = new JMenuItem("��ȭ�� �����");
		createChatRoomMenuItem.setIcon(new ImageIcon(".\\image\\�游���޴�(23x20).jpg"));
		chatMenu.add(createChatRoomMenuItem);
		enterChatRoomMenuItem = new JMenuItem("��ȭ�� �����ϱ�");
		chatMenu.add(enterChatRoomMenuItem);
		privateChatMenuItem	= new JMenuItem("1:1��ȭ");
		chatMenu.add(privateChatMenuItem);
		chatMenu.addSeparator();
		mainChatFormMenu = new JMenu("���� ��ȭ�� ����");
		chatMenu.add(mainChatFormMenu);
		changeFontMenuItem = new JMenuItem("�۲� �ٲٱ�");
		mainChatFormMenu.add(changeFontMenuItem);
		changeColorMenuItem = new JMenuItem("���� �ٲٱ�");
		mainChatFormMenu.add(changeColorMenuItem);
		chatMenu.addSeparator();
		chatMenu.addSeparator();
		sendNoteMenuItem = new JMenuItem("����������");
		sendNoteMenuItem.setIcon(new ImageIcon(".\\image\\����������޴�(23x20).jpg"));
		chatMenu.add(sendNoteMenuItem);
		sendAllNoteMenuItem = new JMenuItem("��ü ����������");
		chatMenu.add(sendAllNoteMenuItem);

		//���۸޴�
		sendMenu = new JMenu("����");
		menuBar.add(sendMenu);
		sendFileMenuItem = new JMenuItem("���� ������");
		sendMenu.add(sendFileMenuItem);
		sendMenu.addSeparator();
		sendMenu.addSeparator();
		sendMailMenuItem = new JMenuItem("�̸��� ������");
		sendMenu.add(sendMailMenuItem);

		//���� �޴�
		JMenu viewMenu = new JMenu("����");
		menuBar.add(viewMenu);
		showToolBarCheckMenuItem = new JCheckBoxMenuItem("���� ���̱�");
		viewMenu.add(showToolBarCheckMenuItem);
		viewMenu.addSeparator();
		showStudentListCheckMenuItem = new JCheckBoxMenuItem("������ �л� ��� ���̱�");
		viewMenu.add(showStudentListCheckMenuItem);
		showChatListCheckMenuItem = new JCheckBoxMenuItem("��ȭ�� ��� ���̱�");
		viewMenu.add(showChatListCheckMenuItem); 
		viewMenu.addSeparator();
		showStatusBarCheckMenuItem = new JCheckBoxMenuItem("����ǥ�ù� ���̱�");
		viewMenu.add(showStatusBarCheckMenuItem);
		networkMenu = new JMenu("��Ʈ��ũ");
		menuBar.add(networkMenu);
		showCurrentIpAddressMenuItem = new JMenuItem("���� PC�� IP�ּ� ���̱�");
		networkMenu.add(showCurrentIpAddressMenuItem);
		//���õ� ���·� ����
		JMenuItem[] menuItem = {showToolBarCheckMenuItem, showStudentListCheckMenuItem,
				showChatListCheckMenuItem, showStatusBarCheckMenuItem};
		for(int i=0; i<menuItem.length; i++){
			menuItem[i].setSelected(true);
		}

		//���� �޴�
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);
		informationMenuItem = new JMenuItem("���α׷� ����");
		informationMenuItem.setIcon(new ImageIcon(".\\image\\����(23x20).jpg"));
		helpMenu.add(informationMenuItem);
	} //�޴� ���� �Լ� ��

	//���� ����
	private void createToolBar(){
		//������ ���� ��ü ���̾ƿ�
		contentPane.setLayout(new BorderLayout(0, 0));

		toolBarPanel = new JPanel();
		toolBarPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(toolBarPanel, BorderLayout.NORTH);
		toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.X_AXIS));
		JToolBar toolBar = new JToolBar();
		toolBarPanel.add(toolBar);
		toolBar.setBackground(SystemColor.controlHighlight);

		showStudentListToggleButton = new JToggleButton(new ImageIcon(".\\image\\��Ϻ���(25x22).jpg"));
		toolBar.add(showStudentListToggleButton);
		showChatListToggleButton = new JToggleButton(new ImageIcon(".\\image\\ä�ú��̱�(25x22).jpg"));
		toolBar.add(showChatListToggleButton);
		showStatusToggleButton = new JToggleButton(new ImageIcon(".\\image\\����ǥ�ú���(25x22).jpg"));
		toolBar.add(showStatusToggleButton);
		toolBar.add(new JLabel(new ImageIcon(".\\image\\������(3x26).jpg")));
		sendNoteButton = new JButton(new ImageIcon(".\\image\\����(25x22).jpg"));
		toolBar.add(sendNoteButton);
		chattingButton = new JButton(new ImageIcon(".\\image\\ä��(25x22).jpg"));
		toolBar.add(chattingButton);
		createChatRoomButton = new JButton(new ImageIcon(".\\image\\�游���(25x22).jpg"));
		toolBar.add(createChatRoomButton);
		toolBar.add(new JLabel(new ImageIcon(".\\image\\������(3x26).jpg")));
		sendFileButton = new JButton(new ImageIcon(".\\image\\���Ϻ�����(25x22).jpg"));
		toolBar.add(sendFileButton);
		sendEmailButton = new JButton(new ImageIcon(".\\image\\�̸��Ϻ�����(25x22).jpg"));
		toolBar.add(sendEmailButton);
		//���õ� ���·� ����
		JToggleButton[] toggleButton= {showStudentListToggleButton, showChatListToggleButton, showStatusToggleButton};
		for(int i=0; i<toggleButton.length; i++){
			toggleButton[i].setSelected(true);
		}
	} //���� ���� �Լ� ��

	//����ǥ�ù� ����
	private void createStatusBar(){
		statusBarPanel = new JPanel();
		statusBarPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(statusBarPanel, BorderLayout.SOUTH);
		statusBarPanel.setLayout(new GridLayout(0, 1, 0, 0));

		statusBarLabel = new JLabel("���");
		statusBarPanel.add(statusBarLabel);
	} //����ǥ�ù� ���� �Լ� ��
	
	//������ ��� ����
	private void createConnectorList(){
		mainWestPanel = new JPanel();
		mainWestPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(mainWestPanel, BorderLayout.WEST);
		mainWestPanel.setLayout(new BorderLayout(0, 0));
		//�����ڵ��� ����
		JPanel infomationOfStudentTitledPanel = new JPanel();
		infomationOfStudentTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�������� �������� �� PC����", 
												TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainWestPanel.add(infomationOfStudentTitledPanel, BorderLayout.NORTH);
		infomationOfStudentTitledPanel.setLayout(new BorderLayout(0, 0));

		informationOfStudentList = new JList<String>();
		informationOfStudentList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		informationOfStudentList.setOpaque(false);
		JScrollPane informationOfStudentScrollPane = new JScrollPane(informationOfStudentList);
		informationOfStudentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		infomationOfStudentTitledPanel.add(informationOfStudentScrollPane, BorderLayout.CENTER);
		//������ ���
		JPanel connectedStudentTitledPanel = new JPanel();
		connectedStudentTitledPanel.setBorder(new TitledBorder(null, "������ ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainWestPanel.add(connectedStudentTitledPanel, BorderLayout.CENTER);
		connectedStudentTitledPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane connectedStudentScrollPane = new JScrollPane();
		connectedStudentTitledPanel.add(connectedStudentScrollPane, BorderLayout.CENTER);

		connectedStudentList = new JList<String>();
		connectedStudentList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		connectedStudentScrollPane.setViewportView(connectedStudentList);
		connectedStudentList.setOpaque(false);
	} //������ ��� ���� �Լ� ��
	
	//��ȭ ��� ����
	private void createChatList(){
		mainEastPanel = new JPanel();
		mainEastPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(mainEastPanel, BorderLayout.CENTER);
		mainEastPanel.setLayout(new BorderLayout(0, 0));

		//��ȭ�� ���
		JPanel chatRoomTitledPanel = new JPanel();
		chatRoomTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uB300\uD654\uBC29 \uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainEastPanel.add(chatRoomTitledPanel, BorderLayout.NORTH);
		chatRoomTitledPanel.setLayout(new BorderLayout(0, 0));

		chatRoomList = new JList<String>();
		chatRoomList.setToolTipText("\uC120\uD0DD\uD55C \uCEF4\uD4E8\uD130\uC758 \uC790\uB9AC \uC815\uBCF4\uB97C \uBCF4\uC5EC\uC90D\uB2C8\uB2E4.");
		chatRoomList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		chatRoomList.setOpaque(false);
		JScrollPane chatRoomScrollPane = new JScrollPane(chatRoomList);
		chatRoomTitledPanel.add(chatRoomScrollPane, BorderLayout.CENTER);

		//��ȭ ���� ���
		JPanel contentOfChattingTitledPanel = new JPanel();
		contentOfChattingTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uB300\uD654 \uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainEastPanel.add(contentOfChattingTitledPanel, BorderLayout.CENTER);
		contentOfChattingTitledPanel.setLayout(new BorderLayout(0, 0));

		contentOfChattingTextPane = new JTextPane();
		contentOfChattingTextPane.setToolTipText("\uC811\uC18D\uC790\uB4E4\uC758 \uB85C\uADF8 \uAE30\uB85D\uC785\uB2C8\uB2E4.");
		contentOfChattingTextPane.setEditable(false);
		contentOfChattingTextPane.setSize(50, 100);

		JScrollPane contentOfChattingScrollPane = new JScrollPane(contentOfChattingTextPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentOfChattingTitledPanel.add(contentOfChattingScrollPane, BorderLayout.CENTER);
		Vector<String> userlist = new Vector<String>();
		userlist.add("��ο���");	//�⺻������ ��ο��� ������ ����
		connectedStudentComboBox = new JComboBox<String>(userlist);
		
		//��ȭ ���� �Է�
		JPanel inputTextPanel = new JPanel();
		inputTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentOfChattingTitledPanel.add(inputTextPanel, BorderLayout.SOUTH);
		inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.X_AXIS));
		inputTextPanel.add(connectedStudentComboBox);

		inputTextField = new JTextField();
		inputTextPanel.add(inputTextField);
		inputTextField.setColumns(10);

		sendTextButton = new JButton("������");
		inputTextPanel.add(sendTextButton);
	} //��ȭ ��� ���� �Լ� ��
}