package Chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.Manager;

public class MakeRoomForm extends JFrame implements ActionListener, ItemListener{
	private static final long serialVersionUID = 1L;
	//���� ���� �ο� �����ϴ� �޺��ڽ�
	private JComboBox<String> personNumberComboBox = null;
	//���� �Է� â
	private JTextField roomNameTextField = null;
	private JTextField roomPasswordTextField = null;
	//��ư
	private JButton makeRoomButton = null;
	private JButton cancelButton = null;
	//��Ʈ�ѷ� Ŭ����
	private Manager manager = null;
	//���� �ο��� ��Ʈ ���� �⺻������ ����
	private String personLimit = "2";
	private String titleColor = "BLACK";
	private Container contentPane = null;
	private final String[] person = {"2", "4", "8", "16"};

	//������
	public MakeRoomForm(){
		super("�� �����");			//������â ����
		this.setLayout(null);		//��ü ���̾ƿ�
		this.initializeMember();	//��� ���� �ʱ�ȭ
		this.createMakeRoomForm();	//�� ����� ��� ����
		this.setSize(270, 220);		//������ ũ��
		this.displayCenter();		//ȭ�� �߾� ��ġ
		this.setResizable(false);	//ũ�� ���� �Ұ���
		this.mountEventHandler();	//�̺�Ʈ ó���� ���
	} //������ ��

	//��� ���� �ʱ�ȭ
	private void initializeMember(){
		contentPane = this.getContentPane();
		manager = new Manager();
	} //��� ���� �ʱ�ȭ �Լ� ��

	//�̺�Ʈ ó���� ���
	private void mountEventHandler(){
		personNumberComboBox.addItemListener(this);
		makeRoomButton.addActionListener(this);
		cancelButton.addActionListener(this);

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
	} //�̺�Ʈ ó���� ��� �Լ� ��

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

	//�� ���� ���
	public String getInfo(){
		//�Է��� ���̸� ����
		String roomNameLabel = this.roomNameTextField.getText();
		//�Է��� ��й�ȣ ����
		String roomPassword = this.roomPasswordTextField.getText();
		return roomNameLabel + "/" + personLimit + "/" + titleColor + "/" + roomPassword;
	}

	//�̺�Ʈ ó����
	public void actionPerformed(ActionEvent e){
		//�游��� ��ư
		if(e.getSource() == makeRoomButton){
			//��Ʈ�ѷ��� ���� �� Ű �� ����
			manager.service("MakeRoom");
			roomNameTextField.setText("");
			roomPasswordTextField.setText("");
		}
		//��� ��ư
		else{
			this.setVisible(true);	//������ �Ⱥ��̰� ����
			this.dispose();			//�ڿ����� 
		}
	}

	public void itemStateChanged(ItemEvent e){
		//���� �ο� �������� �� ����
		if(e.getSource() == this.personNumberComboBox){
			//���� �ο� ��
			personLimit = this.personNumberComboBox.getSelectedItem().toString();
		}
	}

	//�� ����� ��� ����
	private void createMakeRoomForm(){
		//������Ʈ ����
		JLabel titleLabel = new JLabel("�� �����");
		JLabel roomNameLabel = new JLabel("������");
		JLabel roomPasswordLabel = new JLabel("��й�ȣ");
		JLabel personNumberLabel = new JLabel("�ο�");
		personNumberComboBox = new JComboBox<String>(person);
		roomNameTextField = new JTextField(20);
		roomPasswordTextField = new JTextField(20);
		makeRoomButton = new JButton("�����");
		cancelButton = new JButton("���");

		//��ư ���� ����
		makeRoomButton.setFont(new Font("Dialog", Font.BOLD, 12));
		makeRoomButton.setMargin(new Insets(0, 0, 0, 0));
		makeRoomButton.setHorizontalTextPosition(JButton.LEFT);

		cancelButton.setFont(new Font("Dialog", Font.BOLD, 12));
		cancelButton.setMargin(new Insets(0, 0, 0, 0));
		cancelButton.setHorizontalTextPosition(JButton.LEFT);

		//������ ������ �߰�
		contentPane.add(titleLabel);
		contentPane.add(roomNameLabel);
		contentPane.add(roomPasswordLabel);
		contentPane.add(personNumberLabel);
		contentPane.add(personNumberComboBox);
		contentPane.add(roomNameTextField);
		contentPane.add(roomPasswordTextField);
		contentPane.add(makeRoomButton);
		contentPane.add(cancelButton);

		//������Ʈ ����, ũ��, ��ġ
		titleLabel.setBounds(108, 3, 80, 25);
		roomNameLabel.setBounds(5, 30, 80, 25);
		roomPasswordLabel.setBounds(5, 60, 80, 25);
		personNumberLabel.setBounds(5, 100, 80, 25);
		personNumberComboBox.setBounds(50, 100, 50, 25);
		roomNameTextField.setBounds(60, 30, 200, 25);
		roomPasswordTextField.setBounds(60, 60, 200, 25);
		makeRoomButton.setBounds(60, 150, 65, 30);
		cancelButton.setBounds(140, 150, 65, 30);
	} //�� ����� ��� ���� �Լ� ��
}