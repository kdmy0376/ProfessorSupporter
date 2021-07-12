package Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Manager;


public class LoginForm extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	//������ ������ �ޱ� ���� ����
	private Container contentPane = null;
	//������Ʈ ����
	private JTextField iDTextField = null;
	private JPasswordField passwordTextField = null;
	private JLabel registerLabel = null;
	private JButton loginButton = null;
	private JButton cancelButton = null;
	//��Ʈ�ѷ� Ŭ���� ����
	private Manager manager = null;	
	//�̹����� ���
	private final ImageIcon img = new ImageIcon(".\\image\\LoginBanner(320x57).png");

	//������
	public LoginForm(){
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());
		this.setTitle("Log-In");			//������â ����
		this.setAlwaysOnTop(true);			//�׻� ���� ǥ��
		this.setSize(325, 200);				//�α��� â ũ��
		this.displayCenter();				//�α��� ȭ�� �߾� ��ġ
		this.initializeMember();			//������� �ʱ�ȭ
		this.setLayout(null);				//��ü ���̾ƿ� ����
		this.setResizable(false);			//ũ�� ���� �Ұ���
		this.createLoginForm();				//�α��� ��� ����
		this.addEventHandler();				//�̺�Ʈ ó���� ���
		
//		numberTextField.setText("3");
		iDTextField.setText("Professors");
		passwordTextField.setText("1");
	} //������ ��

	//������� ���� �� �ʱ�ȭ
	private void initializeMember(){
		manager = new Manager();		//��Ʈ�ѷ� Ŭ���� ��ü ����
		contentPane = getContentPane();	//������ ������ ������
	} //������� ���� �� �ʱ�ȭ ��
	
	//ȭ�� �߾� ǥ��
	private void displayCenter(){
		//ȭ���� ũ��
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();	
		//�������� ũ��
		Dimension frameDimension = this.getSize();
		//ȭ�� ����/2 - ������ ����/2
		int xPosition = (int)(screenDimension.getWidth()/2 - frameDimension.getWidth()/2);
		//ȭ�� ����/2 - ������ ����/2
		int yPosition = (int)(screenDimension.getHeight()/2 - frameDimension.getHeight()/2);
		this.setLocation(xPosition, yPosition);
	} //ȭ�� �߾� ǥ�� �Լ� ��

	//�̺�Ʈ ó���� ���
	private void addEventHandler(){
		loginButton.addActionListener(this);	//�α��� ��ư
		cancelButton.addActionListener(this);	//��� ��ư
		registerLabel.addMouseListener(this);	//��� ���̺�
		//�������� X������ �� �ƹ� �۾� ���ϰ� ����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//�������� X��ư ������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.out.println("[�α��� â] X ��ư Ŭ��");
				LoginForm.this.exit();
			}
		});
	} //�̺�Ʈ ó���� ��� �Լ� ��
	
	//�������� X������ �� ����
	private void exit(){
		int result = JOptionPane.showConfirmDialog(this, "���� �����ðڽ��ϱ�?", "Quit", JOptionPane.OK_CANCEL_OPTION, 
																					JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.OK_OPTION){ //0
			System.exit(0);	//���α׷� ����
		}
	}

	//���̵� ��������
	public String getID(){
		return iDTextField.getText();
	}

	//��й�ȣ ��������
	public String getPassword(){
		String password = "";
		for(char c : passwordTextField.getPassword()){
			password += c;
		}
		return password;
	}

	//�̺�Ʈ ó����
	public void actionPerformed(ActionEvent actionEvent){
		//�α��� ��ư
		if(actionEvent.getSource() == loginButton){
			System.out.println("[�α��� â] �α��� ��ư Ŭ��");
			//��Ʈ�ѷ��� ���� �� Ű ����
			manager.service("Login");	
			this.iDTextField.setText("");		
			this.passwordTextField.setText(""); 
		}
		//��� ��ư
		else if(actionEvent.getSource() == cancelButton){
			System.out.println("[�α��� â] ��� ��ư Ŭ��");
			this.exit();
		}
	}
	
	//���콺 �̺�Ʈ ó��
	//���� ���Խ�
	public void mouseEntered(MouseEvent e) {
		//�� ��� Ŀ���� ��ȯ
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		LoginForm.this.setCursor(cursor);
	} //���� ���Խ� ó�� ��
	
	//���� Ż���
	public void mouseExited(MouseEvent e) {
		//�⺻ Ŀ���� ��ȯ
		Cursor cursor = Cursor.getDefaultCursor();
		LoginForm.this.setCursor(cursor);
	} //���� Ż��� ó�� ��
	
	//���� �ȿ��� ���콺 ��ư ���� ��
	public void mousePressed(MouseEvent e) {
		//��� ���̺�
		if(e.getSource() == registerLabel){
			//���ڻ� �Ķ������� ����
			registerLabel.setForeground(Color.BLUE);
		}
	} //���� �ȿ��� ���콺 ��ư ���� �� ó�� ��
	
	//���� �ȿ��� ���콺 ��ư�� ������ �� �������� ��
	public void mouseReleased(MouseEvent e) {
		//��� ���̺�
		if(e.getSource() == registerLabel){
			//���ڻ� ���������� ����
			registerLabel.setForeground(Color.BLACK);
		}
	} //���� �ȿ��� ���콺 ��ư�� ������ �� �������� �� ó�� ��
	
	//���� �ȿ��� ���콺 ��ư�� �������� ������ ��
	public void mouseClicked(MouseEvent e) {
		//��� ��� ����
		this.setAlwaysOnTop(false);
		Manager.JOINFORM.setVisible(true);
	} //���� �ȿ��� ���콺 ��ư�� �������� ������ �� ó�� ��
	//
	//���콺 �̺�Ʈ ó�� �Լ� ��
	
	//�α��� ��� ����
	private void createLoginForm(){
		//�α��� â ��� �̹���
		JPanel imagePanel = new JPanel(null){
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g){
				g.drawImage(img.getImage(),0, 0, 320, 57, null, null);
			}
		};
		imagePanel.setBounds(0, 0, 320, 57);

		//���̵�
		JLabel idLabel = new JLabel("���̵�");
		idLabel.setBounds(23, 80, 62, 15);
		iDTextField = new JTextField(); 
		iDTextField.setBounds(97, 75, 197, 21);
		//��й�ȣ
		JLabel passwordLabel = new JLabel("��й�ȣ");
		passwordLabel.setBounds(23, 105, 62, 15);
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(97, 102, 197, 21);
		//��� ���̺�
		registerLabel = new JLabel("���"); 
		registerLabel.setBounds(23, 132, 31, 15);
		//�α��� ��ư
		loginButton = new JButton("�α���");
		loginButton.setBounds(134, 132, 74, 23);
		//��� ��ư
		cancelButton = new JButton("���");
		cancelButton.setBounds(220, 132, 74, 23);
		//������ ������ ������Ʈ �߰�
		contentPane.add(imagePanel);
		contentPane.add(idLabel);
		contentPane.add(iDTextField);
		contentPane.add(passwordLabel);
		contentPane.add(passwordTextField);
		contentPane.add(registerLabel);
		contentPane.add(loginButton);
		contentPane.add(cancelButton);
	} //�α��� ��� ���� �Լ� ��
}