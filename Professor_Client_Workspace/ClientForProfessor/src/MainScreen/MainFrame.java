package MainScreen;
import java.awt.Dimension;
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

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;

import Communication.Transit;
import Controller.Manager;
import FileTransfer.SendFile;
import FontDialog.FontDialog;
import Join.MainJoinForm;
import Note.NoteAllForm;
import ScreenShot.ImageServer;

public class MainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	//���� �޴�
	private JMenu fileMenu = null;						
	private JMenuItem logoutMenuItem = null;	//�α׾ƿ� �޴�������
	private JMenuItem exitMenuItem = null;		//���α׷� ���� �޴�������
	//ä�� �޴�
	private JMenu chatMenu = null;						
	private JMenuItem createChatRoomMenuItem = null;	//�� ����� �޴�������
	private JMenuItem enterChatRoomMenuItem = null;		//�� ���� �޴�������
	private JMenuItem privateChatMenuItem = null;		//1:1 ��ȭ �޴�������
	private JMenu mainChatFormMenu = null;				//���� ä�ù� ���� ���� �޴�
	private JMenuItem changeFontMenuItem = null;		//�۲� �ٲٱ� �޴�������
	private JMenuItem changeColorMenuItem = null;		//���� �ٲٱ� �޴�������
	private JMenuItem sendNoteMenuItem = null;			//���������� �޴�������
	private JMenuItem sendAllNoteMenuItem = null;		//��ü���������� �޴�������
	//�л����� �޴�
	private JMenu manageStudentMenu = null;				
	private JMenuItem registerStudentMenuItem = null;	//�л����� ��� �޴�������
	private JMenuItem modifyStudentMenuItem = null;		//�л����� ���� �޴�������
	private JMenuItem deleteStudentMenuItem = null;		//�л����� ���� �޴�������
	private JMenuItem searchStudentMenuItem = null;		//�л����� �˻� �޴�������
	//���� �޴�
	private JMenu observeMenu = null;					
	private JMenuItem screenShotMenuItem = null;		//��ũ�� �� ��� �޴�������
	private JMenuItem observeStudentPCMenuItem = null;	//�л� ȭ�� ���� �޴�������
	private JMenuItem setUpObserveMenuItem = null;		//���� ���� �޴�������
	//���� �޴�
	private JMenu sendMenu = null;							
	private JMenuItem sendFileMenuItem = null;	//���� ������
	private JMenuItem sendMailMenuItem = null;	//���� ������
	//��Ʈ��ũ �޴�
	private JMenu networkMenu = null;						
	private JMenuItem showCurrentIpAddressMenuItem = null;	//���� PC ������ �ּ� ���� �޴�������
	private JMenuItem setUpClassRoomMenuItem = null;		//���ǽ� ���� �޴�������
	//���� �޴�
	private JCheckBoxMenuItem showStudentListCheckMenuItem = null;	//������ ��� ���� üũ �޴�������
	private JCheckBoxMenuItem showChatListCheckMenuItem = null;		//��ȭ ��� ���� üũ �޴�������
	private JCheckBoxMenuItem showToolBarCheckMenuItem = null;		//���ٺ��̱� üũ �޴�������
	private JCheckBoxMenuItem showStatusBarCheckMenuItem = null; 	//����ǥ�ù� ���̱� üũ �޴�������
	private JCheckBoxMenuItem showCenterCheckMenuItem = null;		//�߾� ȭ�� ���̱� üũ �޴�������
	//���� �޴�
	private JMenuItem guideProgramMenuItem = null;	//���α׷� �ȳ� �޴�������
	private JMenuItem informationMenuItem = null; 	//���α׷� ���� �޴�������
	//��ü ���̾ƿ�
	private JPanel toolBarPanel = null;		//���
	private JPanel mainWestPanel = null;	//����
	private JPanel mainCenterPanel = null;	//�߾�
	private JPanel mainEastPanel = null;	//����
	private JPanel statusBarPanel = null;	//�ϴ�
	//�⺻ ����
	private JToggleButton showStudentListToggleButton = null;	//������ ��� ���� ��۹�ư
	private JToggleButton showCenterToggleButton = null;		//�߾� ȭ�� ���� ��۹�ư
	private JToggleButton showChatListToggleButton = null;		//��ȭ ��� ���� ��۹�ư
	private JToggleButton showStatusToggleButton = null;		//����ǥ�ù� ���� ��۹�ư
	private JButton sendNoteButton = null;			//���������� ��ư
	private JButton chattingButton = null;			//��ȭ�ϱ� ��ư
	private JButton createChatRoomButton = null;	//�� ����� ��ư  
	private JButton sendFileButton = null;			//���Ϻ����� ��ư
	private JButton sendEmailButton = null;			//�̸��Ϻ����� ��ư
	//��������
	private JToolBar observeToolBar = null;		
	private JButton screenShotButton = null;	//��ũ�� �� ��ư
	private JButton videoButton = null;			//ȭ�� ���� ��ư
	//�л����� ����
	private JToolBar manageStudentToolBar = null;	
	private JButton registerStudentButton = null;	//�л����� ��� ��ư
	private JButton modifyStudentButton = null;		//�л����� ���� ��ư 
	private JButton deleteStudentButton = null;		//�л����� ���� ��ư
	//�л��˻� ����
	private JToolBar searchStudentToolBar = null;		
	private JLabel searchStudentLabel = null;			//�л��˻� ���̺�
	private JTextField searchStudentTextField = null;	//�л��˻� �ؽ�Ʈ �ʵ�  
	private JButton searchStudentButton = null;			//�л����� �˻� ��ư 
	//���� ��ȭ��
	private JList<String> chatRoomList = null;					//��ȭ�� ����Ʈ
	private JTextPane contentOfChattingTextPane = null;			//��ȭ����
	private JComboBox<String> connectedStudentComboBox = null;	//������ ��� �޺��ڽ�
	private JTextField inputTextField = null;					//��ȭ ���� �Է� �ؽ�Ʈ �ʵ�
	private JButton sendTextButton = null;						//������ ��ư
	//������ ���
	private JList<String> connectedStudentList = null;				//������ ����Ʈ
	private JTextField numberOfConnectedStudentTextField = null;	//������ �� �ؽ�Ʈ �ʵ�
	private JList<String> informationOfConnectedStudentList = null; //������ ����
	//������ ��� �˾� �޴�
	private JPopupMenu connectorListPopUpMenu = null;
	private JMenuItem privateChatPopUpMenuItem = null;		//1:1 ��ȭ
	private JMenuItem sendNotePopUpMenuItem = null;			//����������
	private JMenuItem sendFilePopUpMenuItem = null;			//���Ϻ�����
	private JMenuItem screenShotPopUpMenuItem = null;		//��ũ�� �� ���
	private JMenuItem seeMovingImagePopUpMenuItem = null;	//���� ȭ�� ����
	private JMenuItem releaseMovingImagePopUpMenuItem = null;	//���� ȭ�� ���� ����
	private JMenuItem remoteControlPopUpMenuItem = null;	//���� ����
	private JMenuItem releasePopUpMenuItem = null;			//���� ����

	private JTabbedPane mainCenterTabbedPane = null;	//�߾� ��ȭ��
	private final String TITLE = "Professor Supporter";	//������â ����
	private JPanel contentPane = null;			//������ ����
	private JLabel statusBarLabel = null;		//����ǥ�ù� ���̺�
	private Manager manager = new Manager();	//��Ʈ�ѷ� ����

	private String roomName = null;	//���õ� �� �̸�
	private String fontName = null;	//��Ʈ �̸�
	private int fontSize = 0;		//��Ʈ ũ��
	private int fontColor = 0;		//��Ʈ ����
	private String toUser = null;	//�ӼӸ� ���� ���
	private String selectedId = null;	//������ ����
	public ComputerScreen computerScreen = null;	//���� �߾� ��ǻ�� ��ġ ȭ��
	public ImageServer imageServer = null;

	//������
	public MainFrame() {
		//this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);	//�ִ�ȭ ���·� ������â ǥ��
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());		//������ �̹���
		this.setTitle(TITLE);		//������â ����
		this.setSize(1360, 720); 	//������ ������
		this.displayCenter();		//������ ��ġ
		this.createMenu();			//�޴� ����
		this.initializeMember();	//��� ���� �ʱ�ȭ
		this.createToolBar();		//���-���� ����
		this.createStatusBar();		//�ϴ�-����ǥ�ù� ����
		this.createConnectorList();	//����-������ ��� ����
		this.createCenterScreen();	//�߾�-ȭ�� ����
		this.createChatList();		//����-��ȭ ��� ����
		this.createPopUpMenu();		//�˾� �޴� ����
		this.mountEventHandler();	//�̺�Ʈ ó���� ���
		new ImageServer().start();
	} //������ ��
	
	public void imageServerStart(){
		imageServer = new ImageServer();
		imageServer.start();
	}

	//��� ���� �ʱ�ȭ �� ����
	private void initializeMember(){
		this.contentPane = new JPanel();
		this.fontName = "����";
		this.fontSize = 12;
		this.fontColor = Color.BLACK.getRGB();
		this.toUser = "��ο���";
		this.setContentPane(contentPane);
	} //��� ���� �ʱ�ȭ �� ���� �Լ� ��

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
	//������ ���� ��� ��������
	public JList<String> getInformationOfConnectedStudentList(){
		return informationOfConnectedStudentList;
	}
	//��ȭ�� ��� ��������
	public JList<String> getChatRoomList(){
		return chatRoomList;
	}
	//������ �� �ؽ�Ʈ �ʵ� ��������
	public JTextField getNumberOfConnector(){
		return numberOfConnectedStudentTextField;
	}
	//�߾� ȭ�� �� ���� ��������
	public JTabbedPane getTabbedPane(){
		return mainCenterTabbedPane;
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
		//���� �޴�
		screenShotMenuItem.addActionListener(this);
		observeStudentPCMenuItem.addActionListener(this);
		setUpObserveMenuItem.addActionListener(this);
		//����޴�
		showToolBarCheckMenuItem.addActionListener(this);
		showChatListCheckMenuItem.addActionListener(this);
		showStudentListCheckMenuItem.addActionListener(this);
		showStatusBarCheckMenuItem.addActionListener(this);		
		showCenterCheckMenuItem.addActionListener(this);
		//�л�����
		registerStudentMenuItem.addActionListener(this);
		modifyStudentMenuItem.addActionListener(this);	
		deleteStudentMenuItem.addActionListener(this);	
		searchStudentMenuItem.addActionListener(this);
		//��Ʈ��ũ �޴�
		showCurrentIpAddressMenuItem.addActionListener(this);
		setUpClassRoomMenuItem.addActionListener(this);
		//�⺻ ����
		showCenterToggleButton.addActionListener(this);
		showChatListToggleButton.addActionListener(this);
		showStudentListToggleButton.addActionListener(this);
		showStatusToggleButton.addActionListener(this);
		sendNoteButton.addActionListener(this);
		chattingButton.addActionListener(this);
		createChatRoomButton.addActionListener(this);
		sendFileButton.addActionListener(this);
		sendEmailButton.addActionListener(this);
		//���� ����
		screenShotButton.addActionListener(this);
		videoButton.addActionListener(this);
		//�л����� ����
		registerStudentButton.addActionListener(this);  
		modifyStudentButton.addActionListener(this); 
		deleteStudentButton.addActionListener(this);
		//�л��˻� ����
		searchStudentButton.addActionListener(this);
		//���� �޴�
		guideProgramMenuItem.addActionListener(this);
		informationMenuItem.addActionListener(this);
		//������ ��� �˾� �޴�
		sendNotePopUpMenuItem.addActionListener(this); 
		privateChatPopUpMenuItem.addActionListener(this);   
		sendFilePopUpMenuItem.addActionListener(this);
		screenShotPopUpMenuItem.addActionListener(this);
		seeMovingImagePopUpMenuItem.addActionListener(this);
		remoteControlPopUpMenuItem.addActionListener(this);
		releasePopUpMenuItem.addActionListener(this);
		releaseMovingImagePopUpMenuItem.addActionListener(this);
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
		//���α׷� ���� �޴�������
		if(e.getSource() == informationMenuItem){
			//�޽��� ��ȭ���� ǥ��(� ���� �߾�, �޽���, Ÿ��Ʋ, �޽�����ȭ����)
			JOptionPane.showMessageDialog(this, "Professor Supporter\n�������� ������ ���α׷��Դϴ�.", 
					"���α׷� ����", JOptionPane.INFORMATION_MESSAGE);
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
		//�߾� ȭ�� ���̱� �޴��������̳� ��۹�ư
		else if(e.getSource() == showCenterCheckMenuItem || e.getSource() == showCenterToggleButton){
			if (mainCenterPanel.isVisible()) {
				mainCenterPanel.setVisible(false);
				showCenterToggleButton.setSelected(false);  
				showCenterCheckMenuItem.setSelected(false);
			} else {
				mainCenterPanel.setVisible(true);
				showCenterToggleButton.setSelected(true);
				showCenterCheckMenuItem.setSelected(true);
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
			String selectedId = this.connectedStudentList.getSelectedValue().toString();
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
		//������ �ּ� �����ֱ� �޴�������
		else if(e.getSource() == showCurrentIpAddressMenuItem){
			String ipAddress = null;
			try {
				ipAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(Manager.MAINFRAME, "���� ��ǻ���� IP �ּ� : "+ipAddress, "IP �ּ�", JOptionPane.WARNING_MESSAGE);
		}
		//���α׷� ��� �ȳ� �޴�������
		else if(e.getSource() == guideProgramMenuItem){
			mainCenterTabbedPane.addTab("ȯ���մϴ�.", Manager.WELCOMEPROGRAM);
		}
		//�˾� �޴��� ��ũ�� �� ���
		else if(e.getSource() == screenShotPopUpMenuItem){
			Transit.sendMsg("��ũ������û/" + selectedId);
		}
		//�˾� �޴��� ���� ȭ�� ����
		else if(e.getSource() == seeMovingImagePopUpMenuItem){
			Transit.sendMsg("����ȭ���û/" + selectedId);
		}
		//�˾� �޴��� ����ȭ�� ���� ����
		else if(e.getSource() == releaseMovingImagePopUpMenuItem){
			Transit.sendMsg("����ȭ��������û/" + selectedId);
		}
		//�˾� �޴��� ���� ����
		else if(e.getSource() == remoteControlPopUpMenuItem){
			Transit.sendMsg("���������û/" + selectedId); 
		}
		//�˾� �޴��� ���� ����
		else if(e.getSource() == releasePopUpMenuItem){
			Transit.sendMsg("��������/" + selectedId); 
		}
		//�л����� ��� �޴������۰� ���� ��ư
		else if(e.getSource() == registerStudentMenuItem || e.getSource() == registerStudentButton){
			mainCenterTabbedPane.addTab("�л����� ���", Manager.MAINJOINFORM);
		}
		//�л����� ���� �޴������۰� ���� ��ư
		else if(e.getSource() == modifyStudentMenuItem || e.getSource() == modifyStudentButton){
			Manager.UPDATEFORM.initialize();
			mainCenterTabbedPane.addTab("�л����� ����", Manager.UPDATEFORM);
		}
		//�л����� ���� �޴������۰� ���� ��ư
		else if(e.getSource() == deleteStudentMenuItem || e.getSource() == deleteStudentButton){
			Manager.DELETEFORM.initialize();
			mainCenterTabbedPane.addTab("�л����� ����", Manager.DELETEFORM);
		}
		//�л����� �˻�  ���ٹ�ư
		else if(e.getSource() == searchStudentButton){
			Transit.sendMsg("�л��˻�/" + searchStudentTextField.getText());
			//Manager.MAINFRAME.getTabbedPane().addTab("�л����� �˻����", Manager.SEARCHFORM);
		}
		//�л����� �˻� �޴�������
		else if(e.getSource() == searchStudentMenuItem){
			Manager.SFORM.setVisible(true);  
		}
		//���������� ����
		else if(e.getSource() == sendNoteButton){
			 new NoteAllForm().setVisible(true);
		}
		//ä���ϱ� ����
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
		screenShotPopUpMenuItem = new JMenuItem("��ũ�� �� ���");
		seeMovingImagePopUpMenuItem = new JMenuItem("���� ȭ�� ����");
		releaseMovingImagePopUpMenuItem = new JMenuItem("���� ȭ�� ���� ����");
		remoteControlPopUpMenuItem = new JMenuItem("����");
		releasePopUpMenuItem = new JMenuItem("��������");

		//�˾� �޴��� �޴������� ���� �ֱ�
		connectorListPopUpMenu.add(sendNotePopUpMenuItem);
		connectorListPopUpMenu.add(privateChatPopUpMenuItem);
		connectorListPopUpMenu.add(sendFilePopUpMenuItem);
		connectorListPopUpMenu.add(screenShotPopUpMenuItem);
		connectorListPopUpMenu.add(seeMovingImagePopUpMenuItem);
		connectorListPopUpMenu.add(releaseMovingImagePopUpMenuItem);
		connectorListPopUpMenu.addSeparator();
		connectorListPopUpMenu.add(remoteControlPopUpMenuItem);
		connectorListPopUpMenu.add(releasePopUpMenuItem);

		//������ ����Ʈ�� �˾� �޴� �߰�
		connectedStudentList.add(connectorListPopUpMenu);
	} //�˾� �޴� ���� �Լ� ��

	//�޴� ����
	private void createMenu(){
		//�޴��� ����
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		//���� �޴�
		fileMenu = new JMenu("����");
		menuBar.add(fileMenu);
		logoutMenuItem = new JMenuItem("�α� �ƿ�");
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
		mainChatFormMenu = new JMenu("���� ��ȭ�� ���� ����");
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
		sendMailMenuItem = new JMenuItem("���� ������");
		sendMenu.add(sendMailMenuItem);

		//���ø޴�
		observeMenu = new JMenu("����");
		menuBar.add(observeMenu);
		screenShotMenuItem = new JMenuItem("�л�PC ��ũ���� ���");
		observeMenu.add(screenShotMenuItem);
		observeStudentPCMenuItem = new JMenuItem("�л�PC ȭ�� ����");
		observeMenu.add(observeStudentPCMenuItem);
		observeMenu.addSeparator();
		setUpObserveMenuItem = new JMenuItem("���� ����");
		observeMenu.add(setUpObserveMenuItem);

		//���� �޴�
		JMenu viewMenu = new JMenu("����");
		menuBar.add(viewMenu);
		showToolBarCheckMenuItem = new JCheckBoxMenuItem("���� ���̱�");
		viewMenu.add(showToolBarCheckMenuItem);
		viewMenu.addSeparator();
		showCenterCheckMenuItem = new JCheckBoxMenuItem("��ǻ�� ��ġȭ�� ���̱�");
		viewMenu.add(showCenterCheckMenuItem);
		showStudentListCheckMenuItem = new JCheckBoxMenuItem("������ �л� ��� ���̱�");
		viewMenu.add(showStudentListCheckMenuItem);
		showChatListCheckMenuItem = new JCheckBoxMenuItem("��ȭ�� ��� ���̱�");
		viewMenu.add(showChatListCheckMenuItem); 
		viewMenu.addSeparator();
		showStatusBarCheckMenuItem = new JCheckBoxMenuItem("����ǥ�ù� ���̱�");
		viewMenu.add(showStatusBarCheckMenuItem);
		//���õ� ���·� ����
		JMenuItem[] menuItem = {showToolBarCheckMenuItem, showCenterCheckMenuItem, showStudentListCheckMenuItem,
				showChatListCheckMenuItem, showStatusBarCheckMenuItem};
		for(int i=0; i<menuItem.length; i++){
			menuItem[i].setSelected(true);
		}

		//�л����� �޴�
		manageStudentMenu = new JMenu("�л�����");
		menuBar.add(manageStudentMenu);
		registerStudentMenuItem = new JMenuItem("�л����� ���");
		registerStudentMenuItem.setIcon(new ImageIcon(".\\image\\�л���ϸ޴�(23x20).jpg"));
		manageStudentMenu.add(registerStudentMenuItem);
		manageStudentMenu.addSeparator();
		modifyStudentMenuItem = new JMenuItem("�л����� ����");
		manageStudentMenu.add(modifyStudentMenuItem);
		deleteStudentMenuItem = new JMenuItem("�л����� ����");
		deleteStudentMenuItem.setIcon(new ImageIcon(".\\image\\�л������޴�(23x20).jpg"));
		manageStudentMenu.add(deleteStudentMenuItem);
		manageStudentMenu.addSeparator();
		searchStudentMenuItem = new JMenuItem("�л����� �˻�");
		manageStudentMenu.add(searchStudentMenuItem);

		//��Ʈ��ũ
		networkMenu = new JMenu("��Ʈ��ũ");
		menuBar.add(networkMenu);
		showCurrentIpAddressMenuItem = new JMenuItem("���� IP�ּ� ����");
		networkMenu.add(showCurrentIpAddressMenuItem);
		setUpClassRoomMenuItem = new JMenuItem("���ǽ� ����");
		networkMenu.add(setUpClassRoomMenuItem);

		//���� �޴�
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);
		guideProgramMenuItem = new JMenuItem("���α׷� �ȳ�");
		helpMenu.add(guideProgramMenuItem);
		helpMenu.addSeparator();
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
		JToolBar standardToolBar = new JToolBar();
		toolBarPanel.add(standardToolBar);
		standardToolBar.setBackground(SystemColor.controlHighlight);

		showStudentListToggleButton = new JToggleButton( new ImageIcon(".\\image\\��Ϻ���(25x22).jpg"));
		standardToolBar.add(showStudentListToggleButton);
		showCenterToggleButton = new JToggleButton(new ImageIcon(".\\image\\��ǻ�ͺ���(25x22).jpg"));
		standardToolBar.add(showCenterToggleButton);
		showChatListToggleButton = new JToggleButton(new ImageIcon(".\\image\\ä�ú��̱�(25x22).jpg"));
		standardToolBar.add(showChatListToggleButton);
		showStatusToggleButton = new JToggleButton(new ImageIcon(".\\image\\����ǥ�ú���(25x22).jpg"));
		standardToolBar.add(showStatusToggleButton);
		standardToolBar.add(new JLabel(new ImageIcon(".\\image\\������(3x26).jpg")));
		sendNoteButton = new JButton(new ImageIcon(".\\image\\����(25x22).jpg"));
		standardToolBar.add(sendNoteButton);
		chattingButton = new JButton(new ImageIcon(".\\image\\ä��(25x22).jpg"));
		standardToolBar.add(chattingButton);
		createChatRoomButton = new JButton(new ImageIcon(".\\image\\�游���(25x22).jpg"));
		standardToolBar.add(createChatRoomButton);
		standardToolBar.add(new JLabel(new ImageIcon(".\\image\\������(3x26).jpg")));
		sendFileButton = new JButton(new ImageIcon(".\\image\\���Ϻ�����(25x22).jpg"));
		standardToolBar.add(sendFileButton);
		sendEmailButton = new JButton(new ImageIcon(".\\image\\�̸��Ϻ�����(25x22).jpg"));
		standardToolBar.add(sendEmailButton);
		//���õ� ���·� ����
		JToggleButton[] toggleButton= {showStudentListToggleButton, showCenterToggleButton, showChatListToggleButton, showStatusToggleButton};
		for(int i=0; i<toggleButton.length; i++){
			toggleButton[i].setSelected(true);
		}

		observeToolBar = new JToolBar();
		toolBarPanel.add(observeToolBar);
		screenShotButton = new JButton(new ImageIcon(".\\image\\ī�޶����(25x22).jpg"));
		observeToolBar.add(screenShotButton);
		videoButton = new JButton(new ImageIcon(".\\image\\����(25x22).jpg"));
		observeToolBar.add(videoButton);

		manageStudentToolBar = new JToolBar();
		toolBarPanel.add(manageStudentToolBar);
		registerStudentButton = new JButton(new ImageIcon(".\\image\\������������(25x22).jpg"));
		manageStudentToolBar.add(registerStudentButton);
		modifyStudentButton = new JButton(new ImageIcon(".\\image\\�л���������(25x22).jpg"));
		manageStudentToolBar.add(modifyStudentButton);
		deleteStudentButton = new JButton(new ImageIcon(".\\image\\�л���������(25x22).jpg"));
		manageStudentToolBar.add(deleteStudentButton);

		JToolBar blankToolBar = new JToolBar();
		toolBarPanel.add(blankToolBar);
		blankToolBar.add(new JLabel("����������������������������������������������������������������������"));

		searchStudentToolBar = new JToolBar();
		toolBarPanel.add(searchStudentToolBar);
		searchStudentLabel = new JLabel("�л��˻� : ");
		searchStudentToolBar.add(searchStudentLabel);
		searchStudentTextField = new JTextField();
		searchStudentToolBar.add(searchStudentTextField);
		searchStudentTextField.setColumns(10);
		searchStudentButton = new JButton(new ImageIcon(".\\image\\����˻�(25x22).jpg"));
		searchStudentToolBar.add(searchStudentButton);
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

	//�߾� ȭ�� ����
	private void createCenterScreen(){
		mainCenterPanel = new JPanel();
		mainCenterPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(mainCenterPanel, BorderLayout.CENTER);
		mainCenterPanel.setLayout(new BorderLayout(0, 0));
		mainCenterTabbedPane = new JTabbedPane(JTabbedPane.TOP);

		//�����ο� ���̱� ���� �г� ����
		JPanel panel = new JPanel();
		mainCenterTabbedPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		//��ũ������ ���� �� �гο� ���
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		//��ǻ�� ��ġ ȭ�� ���� �� ��ũ�����ο� ���
		computerScreen = new ComputerScreen(6);
		scrollPane.setViewportView(computerScreen);
		//�����ο� �г� ���̱�
		mainCenterTabbedPane.addTab("���ǽ� �л� PC �¼�", panel);
		//���� �߾� �гο� ������ ���̱�
		mainCenterPanel.add(mainCenterTabbedPane, BorderLayout.CENTER);
	} //�߾� ȭ�� ���� �Լ� ��

	//������ ��� ����
	private void createConnectorList(){
		mainWestPanel = new JPanel();
		mainWestPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(mainWestPanel, BorderLayout.WEST);
		mainWestPanel.setLayout(new BorderLayout(0, 0));
		//������ ��
		JPanel numberOfConnectedStudentTitledPanel = new JPanel();
		numberOfConnectedStudentTitledPanel.setBorder(new TitledBorder(null, "������ ��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainWestPanel.add(numberOfConnectedStudentTitledPanel, BorderLayout.NORTH);
		numberOfConnectedStudentTitledPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel numberOfConnectedStudentLabelPanel = new JPanel();
		numberOfConnectedStudentLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		numberOfConnectedStudentTitledPanel.add(numberOfConnectedStudentLabelPanel);

		JLabel numberOfConnectedStudentLabel = new JLabel("���� ����� ������ �� : ");
		numberOfConnectedStudentLabelPanel.add(numberOfConnectedStudentLabel);

		numberOfConnectedStudentTextField = new JTextField();
		numberOfConnectedStudentTextField.setToolTipText("���� ����� �����ڵ��� ���� ǥ�õ˴ϴ�.");
		numberOfConnectedStudentTextField.setEditable(false);
		numberOfConnectedStudentTitledPanel.add(numberOfConnectedStudentTextField);
		numberOfConnectedStudentTextField.setColumns(10);
		//������ ���
		JPanel listOfConnectedStudentTitledPanel = new JPanel();
		listOfConnectedStudentTitledPanel.setBorder(new TitledBorder(null, "������ ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainWestPanel.add(listOfConnectedStudentTitledPanel, BorderLayout.CENTER);
		listOfConnectedStudentTitledPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane listOfConnectedStudentScrollPane = new JScrollPane();
		listOfConnectedStudentTitledPanel.add(listOfConnectedStudentScrollPane, BorderLayout.CENTER);

		connectedStudentList = new JList<String>();
		connectedStudentList.setToolTipText("���� ����� �����ڵ��� ����� ǥ�õ˴ϴ�.");
		connectedStudentList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listOfConnectedStudentScrollPane.setViewportView(connectedStudentList);
		connectedStudentList.setOpaque(false);
		//�����ڵ��� ����
		JPanel informationOfConnectedStudentTitledPanel = new JPanel();
		informationOfConnectedStudentTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�������� �������� �� PC����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainWestPanel.add(informationOfConnectedStudentTitledPanel, BorderLayout.SOUTH);
		informationOfConnectedStudentTitledPanel.setLayout(new BorderLayout(0, 0));

		informationOfConnectedStudentList = new JList<String>();
		informationOfConnectedStudentList.setToolTipText("���� �����ڵ��� �������� �� PC������ ǥ���մϴ�.");
		informationOfConnectedStudentList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		informationOfConnectedStudentList.setOpaque(false);
		JScrollPane informationOfConnectedStudentScrollPane = new JScrollPane(informationOfConnectedStudentList);
		informationOfConnectedStudentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		informationOfConnectedStudentTitledPanel.add(informationOfConnectedStudentScrollPane, BorderLayout.CENTER);
	} //������ ��� ���� �Լ� ��

	//��ȭ ��� ����
	private void createChatList(){
		mainEastPanel = new JPanel();
		mainEastPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(mainEastPanel, BorderLayout.EAST);
		mainEastPanel.setLayout(new BorderLayout(0, 0));

		//��ȭ�� ���
		JPanel chatRoomTitledPanel = new JPanel();
		chatRoomTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��ȭ�� ���", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainEastPanel.add(chatRoomTitledPanel, BorderLayout.NORTH);
		chatRoomTitledPanel.setLayout(new BorderLayout(0, 0));

		chatRoomList = new JList<String>();
		chatRoomList.setToolTipText("������ ��ȭ����� ����Դϴ�.");
		chatRoomList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		chatRoomList.setOpaque(false);
		JScrollPane chatRoomScrollPane = new JScrollPane(chatRoomList);
		chatRoomTitledPanel.add(chatRoomScrollPane, BorderLayout.CENTER);

		//��ȭ ���� ���
		JPanel contentOfChattingTitledPanel = new JPanel();
		contentOfChattingTitledPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��ȭ ���", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainEastPanel.add(contentOfChattingTitledPanel, BorderLayout.CENTER);
		contentOfChattingTitledPanel.setLayout(new BorderLayout(0, 0));

		contentOfChattingTextPane = new JTextPane();
		contentOfChattingTextPane.setToolTipText("��ȭ ����� ǥ�õ˴ϴ�.");
		contentOfChattingTextPane.setEditable(false);
		contentOfChattingTextPane.setSize(50, 100);

		JScrollPane contentOfChattingScrollPane = new JScrollPane(contentOfChattingTextPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentOfChattingTitledPanel.add(contentOfChattingScrollPane, BorderLayout.CENTER);

		//��ȭ ���� �Է�
		JPanel inputTextPanel = new JPanel();
		inputTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentOfChattingTitledPanel.add(inputTextPanel, BorderLayout.SOUTH);

		GridBagLayout gbl_inputTextPanel = new GridBagLayout();
		gbl_inputTextPanel.columnWidths = new int[]{116, 116, 116, 0, 0};
		gbl_inputTextPanel.rowHeights = new int[]{23, 0};
		gbl_inputTextPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_inputTextPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		inputTextPanel.setLayout(gbl_inputTextPanel);

		GridBagConstraints gbc_connectedStudentComboBox = new GridBagConstraints();
		Vector<String> userlist = new Vector<String>();
		userlist.add("��ο���");	//�⺻������ ��ο��� ������ ����
		connectedStudentComboBox = new JComboBox<String>(userlist);
		gbc_connectedStudentComboBox.fill = GridBagConstraints.BOTH;
		gbc_connectedStudentComboBox.anchor = GridBagConstraints.WEST;
		gbc_connectedStudentComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_connectedStudentComboBox.gridx = 0;
		gbc_connectedStudentComboBox.gridy = 0;
		inputTextPanel.add(connectedStudentComboBox, gbc_connectedStudentComboBox);

		inputTextField = new JTextField();
		GridBagConstraints gbc_inputTextField = new GridBagConstraints();
		gbc_inputTextField.gridwidth = 2;
		gbc_inputTextField.fill = GridBagConstraints.BOTH;
		gbc_inputTextField.insets = new Insets(0, 0, 0, 5);
		gbc_inputTextField.gridx = 1;
		gbc_inputTextField.gridy = 0;
		inputTextPanel.add(inputTextField, gbc_inputTextField);
		inputTextField.setColumns(10);

		sendTextButton = new JButton("������");  
		GridBagConstraints gbc_sendTextButton = new GridBagConstraints();
		gbc_sendTextButton.anchor = GridBagConstraints.EAST;
		gbc_sendTextButton.gridx = 3;
		gbc_sendTextButton.gridy = 0;
		inputTextPanel.add(sendTextButton, gbc_sendTextButton);
	} //��ȭ ��� ���� �Լ� ��
}