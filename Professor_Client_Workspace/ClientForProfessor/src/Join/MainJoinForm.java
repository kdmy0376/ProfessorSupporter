package Join;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import Communication.Transit;
import Controller.Manager;

public class MainJoinForm extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField idTextField = null;
	private JPasswordField passwordTextField = null;
	private JPasswordField passwordCheckTextField = null;
	private JTextField nameTextField = null;
	private JTextField emailTextField = null;
	private JComboBox<String> sexComboBox = null;
	private JTextField homepageTextField = null;
	private JTextField phoneTextField = null;
	private JTextField cellPhoneTextField = null;
	private JTextField addressField = null;
	private JTextArea introductionTextArea = null;
	private JButton okButtonInJoinForm = null;
	private JButton initializeButton = null;
	private JButton cancelButton = null;
	private JButton duplicationCheckButton = null;

	//������
	public MainJoinForm() {
		this.createJoinForm();
		this.mountEventHandler();
	}

	//�̺�Ʈ ó���� ���
	public void mountEventHandler(){
		//ȸ������ ��� ȭ��
		duplicationCheckButton.addActionListener(this);
		okButtonInJoinForm.addActionListener(this);
		initializeButton.addActionListener(this);
		cancelButton.addActionListener(this);
	} //�̺�Ʈ ó���� ��� �Լ� ��

	//�̺�Ʈ ó����
	public void actionPerformed(ActionEvent e){
		//ȸ������ ��� ȭ��
		//�ߺ�Ȯ�� ��ư
		if(e.getSource() == duplicationCheckButton){
			System.out.println("�ߺ�Ȯ�� ��ư�� ���������ϴ�.");
			String id = idTextField.getText();
			Transit.sendMsg("�����ߺ�Ȯ��/" + id);		
		}
		//Ȯ�� ��ư
		else if(e.getSource() == okButtonInJoinForm){
			System.out.println("[ȸ������ ȭ��] Ȯ�� ��ư Ŭ��");
			this.registerMember();	//ȸ�� ��� ó��
		}
		//�ʱ�ȭ ��ư
		else if(e.getSource() == initializeButton){
			this.initializeRegisterForm();
		}
		//��� ��ư
		else if(e.getSource() == cancelButton){
			Manager.MAINFRAME.getTabbedPane().remove(this);
		}
	} //�̺�Ʈ ó���� �Լ� ��

	//ȸ�� ��� ó��
	private void registerMember(){
		//���� ������Ʈ���� ������ ����
		String id = idTextField.getText();
		String password = "";
		for(char c : passwordTextField.getPassword()){
			password += c;
		}
		String name = nameTextField.getText();
		String email = emailTextField.getText();
		String sex = (String)sexComboBox.getSelectedItem();
		String homepage = homepageTextField.getText();
		String phone = phoneTextField.getText();
		String cellPhone = cellPhoneTextField.getText();
		String address = addressField.getText();
		String introduction = introductionTextArea.getText();
		
//		String[] componentValues = {id, password, name, email, sex, homepage, phone, cellPhone, address, introduction};
//		for(int i=0; i<componentValues.length; i++){
//			if(componentValues[i].length() == 0){
//				componentValues[i] = " ";
//			}
//		}

		//������ ȸ������ �Ӽ� 10�� ����
		Transit.sendMsg("����ȸ������/" + id + "/" + password + "/" + name + "/" + email + "/" + sex + "/" + homepage
				+ "/" + phone + "/" + cellPhone + "/" + address + "/" + introduction);
	} //ȸ�� ��� ó�� �Լ� ��

	//ȸ�� ��� ��� ������Ʈ �ʱ�ȭ
	public void initializeRegisterForm(){
		idTextField.setText("");
		passwordTextField.setText("");
		passwordCheckTextField.setText("");
		nameTextField.setText("");
		emailTextField.setText("");
		sexComboBox.setSelectedIndex(0);
		homepageTextField.setText("");
		phoneTextField.setText("");
		cellPhoneTextField.setText("");
		addressField.setText("");
		introductionTextArea.setText("");
	} //ȸ�� ��� ��� ������Ʈ �ʱ�ȭ �Լ� ��

	//ȸ�� ���� ��� ����
	private void createJoinForm(){
		//ȸ�� ��� ��� �� �г� ���̾ƿ�
		this.setLayout(null);					
		//���̵�
		JPanel idLabelPanel = new JPanel();
		idLabelPanel.setBackground(SystemColor.inactiveCaption);
		idLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idLabelPanel.setBounds(12, 44, 132, 32);
		this.add(idLabelPanel);
		idLabelPanel.setLayout(null);

		JLabel idLabel = new JLabel("���̵�");
		idLabel.setBounds(12, 10, 57, 15);
		idLabelPanel.add(idLabel);

		//��й�ȣ
		JPanel passwordLabelPanel = new JPanel();
		passwordLabelPanel.setBackground(SystemColor.inactiveCaption);
		passwordLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordLabelPanel.setLayout(null);
		passwordLabelPanel.setBounds(12, 75, 132, 32);
		this.add(passwordLabelPanel);

		JLabel passwordLabel = new JLabel("��й�ȣ");
		passwordLabel.setBounds(12, 10, 57, 15);
		passwordLabelPanel.add(passwordLabel);

		//��й�ȣ Ȯ��
		JPanel passwordCheckLabelPanel = new JPanel();
		passwordCheckLabelPanel.setBackground(SystemColor.inactiveCaption);
		passwordCheckLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordCheckLabelPanel.setLayout(null);
		passwordCheckLabelPanel.setBounds(12, 106, 132, 32);
		this.add(passwordCheckLabelPanel);

		JLabel passwordCheckLabel = new JLabel("��й�ȣ Ȯ��");
		passwordCheckLabel.setBounds(12, 10, 89, 15);
		passwordCheckLabelPanel.add(passwordCheckLabel);

		//�̸�
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.setBackground(SystemColor.inactiveCaption);
		nameLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameLabelPanel.setLayout(null);
		nameLabelPanel.setBounds(12, 137, 132, 32);
		this.add(nameLabelPanel);

		JLabel nameLabel = new JLabel("�̸�");
		nameLabel.setBounds(12, 10, 57, 15);
		nameLabelPanel.add(nameLabel);

		//�̸���
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.setBackground(SystemColor.inactiveCaption);
		emailLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailLabelPanel.setLayout(null);
		emailLabelPanel.setBounds(12, 168, 132, 32);
		this.add(emailLabelPanel);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(12, 10, 57, 15);
		emailLabelPanel.add(emailLabel);

		//����
		JPanel sexLabelPanel = new JPanel();
		sexLabelPanel.setBackground(SystemColor.inactiveCaption);
		sexLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexLabelPanel.setLayout(null);
		sexLabelPanel.setBounds(12, 199, 132, 32);
		this.add(sexLabelPanel);

		JLabel sexLabel = new JLabel("����");
		sexLabel.setBounds(12, 10, 57, 15);
		sexLabelPanel.add(sexLabel);

		//Ȩ������
		JPanel homepageLabelPanel = new JPanel();
		homepageLabelPanel.setBackground(SystemColor.inactiveCaption);
		homepageLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageLabelPanel.setLayout(null);
		homepageLabelPanel.setBounds(12, 230, 132, 32);
		this.add(homepageLabelPanel);

		JLabel homepageLabel = new JLabel("Ȩ������");
		homepageLabel.setBounds(12, 10, 57, 15);
		homepageLabelPanel.add(homepageLabel);

		//��ȭ��ȣ
		JPanel phoneLabelPanel = new JPanel();
		phoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		phoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneLabelPanel.setLayout(null);
		phoneLabelPanel.setBounds(12, 261, 132, 32);
		this.add(phoneLabelPanel);

		JLabel phoneLabel = new JLabel("��ȭ��ȣ");
		phoneLabel.setBounds(12, 10, 57, 15);
		phoneLabelPanel.add(phoneLabel);

		//�ڵ��� ��ȣ
		JPanel cellPhoneLabelPanel = new JPanel();
		cellPhoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		cellPhoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneLabelPanel.setLayout(null);
		cellPhoneLabelPanel.setBounds(12, 292, 132, 32);
		this.add(cellPhoneLabelPanel);

		JLabel cellPhoneLabel = new JLabel("�ڵ��� ��ȣ");
		cellPhoneLabel.setBounds(12, 10, 72, 15);
		cellPhoneLabelPanel.add(cellPhoneLabel);

		//�ּ�
		JPanel addressLabelPanel = new JPanel();
		addressLabelPanel.setBackground(SystemColor.inactiveCaption);
		addressLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressLabelPanel.setLayout(null);
		addressLabelPanel.setBounds(12, 323, 132, 32);
		this.add(addressLabelPanel);

		JLabel addressLabel = new JLabel("�ּ�");
		addressLabel.setBounds(12, 10, 57, 15);
		addressLabelPanel.add(addressLabel);

		//�ڱ�Ұ�
		JPanel introductionLabelPanel = new JPanel();
		introductionLabelPanel.setBackground(SystemColor.inactiveCaption);
		introductionLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionLabelPanel.setLayout(null);
		introductionLabelPanel.setBounds(12, 354, 132, 111);
		this.add(introductionLabelPanel);

		JLabel introductionLabel = new JLabel("�ڱ�Ұ�");
		introductionLabel.setBounds(12, 46, 57, 15);
		introductionLabelPanel.add(introductionLabel);

		//���̵� �Է¶�
		JPanel idTextFieldPanel = new JPanel();
		idTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idTextFieldPanel.setBounds(144, 44, 422, 32);
		this.add(idTextFieldPanel);
		idTextFieldPanel.setLayout(null);

		idTextField = new JTextField();
		idTextField.setBounds(6, 5, 116, 21);
		idTextFieldPanel.add(idTextField);
		idTextField.setColumns(10);

		//�ߺ�Ȯ�� ��ư
		duplicationCheckButton = new JButton("�ߺ�Ȯ��");
		duplicationCheckButton.setBounds(134, 4, 91, 23);
		idTextFieldPanel.add(duplicationCheckButton);

		//��й�ȣ �Է¶�
		JPanel passwordTextFieldPanel = new JPanel();
		passwordTextFieldPanel.setLayout(null);
		passwordTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordTextFieldPanel.setBounds(144, 75, 422, 32);
		this.add(passwordTextFieldPanel);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(6, 5, 116, 21);
		passwordTextFieldPanel.add(passwordTextField);

		//��й�ȣ Ȯ�� �Է¶�
		JPanel passwordCheckTextFieldPanel = new JPanel();
		passwordCheckTextFieldPanel.setLayout(null);
		passwordCheckTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordCheckTextFieldPanel.setBounds(144, 106, 422, 32);
		this.add(passwordCheckTextFieldPanel);

		passwordCheckTextField = new JPasswordField();
		passwordCheckTextField.setBounds(6, 5, 116, 21);
		passwordCheckTextFieldPanel.add(passwordCheckTextField);

		//�̸� �Է¶�
		JPanel nameTextFieldPanel = new JPanel();
		nameTextFieldPanel.setLayout(null);
		nameTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameTextFieldPanel.setBounds(144, 137, 422, 32);
		this.add(nameTextFieldPanel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(6, 5, 116, 21);
		nameTextFieldPanel.add(nameTextField);

		//�̸��� �Է¶�
		JPanel emailTextFieldPanel = new JPanel();
		emailTextFieldPanel.setLayout(null);
		emailTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailTextFieldPanel.setBounds(144, 168, 422, 32);
		this.add(emailTextFieldPanel);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(6, 5, 220, 21);
		emailTextFieldPanel.add(emailTextField);

		//���� ���ö�
		JPanel sexComboBoxPanel = new JPanel();
		sexComboBoxPanel.setLayout(null);
		sexComboBoxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexComboBoxPanel.setBounds(144, 199, 422, 32);
		this.add(sexComboBoxPanel);

		String[] sex = {"��", "��"};
		sexComboBox = new JComboBox(sex);
		sexComboBox.setBounds(6, 5, 60, 21);
		sexComboBoxPanel.add(sexComboBox);

		//Ȩ������ �Է¶�
		JPanel homepageTextFieldPanel = new JPanel();
		homepageTextFieldPanel.setLayout(null);
		homepageTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageTextFieldPanel.setBounds(144, 230, 422, 32);
		this.add(homepageTextFieldPanel);

		homepageTextField = new JTextField();
		homepageTextField.setColumns(10);
		homepageTextField.setBounds(6, 5, 221, 21);
		homepageTextFieldPanel.add(homepageTextField);

		//��ȭ��ȣ �Է¶�
		JPanel phoneTextFieldPanel = new JPanel();
		phoneTextFieldPanel.setLayout(null);
		phoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneTextFieldPanel.setBounds(144, 261, 422, 32);
		this.add(phoneTextFieldPanel);

		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(6, 5, 116, 21);
		phoneTextFieldPanel.add(phoneTextField);

		//�ڵ��� ��ȣ �Է¶�
		JPanel cellPhoneTextFieldPanel = new JPanel();
		cellPhoneTextFieldPanel.setLayout(null);
		cellPhoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneTextFieldPanel.setBounds(144, 292, 422, 32);
		this.add(cellPhoneTextFieldPanel);

		cellPhoneTextField = new JTextField();
		cellPhoneTextField.setColumns(10);
		cellPhoneTextField.setBounds(6, 5, 116, 21);
		cellPhoneTextFieldPanel.add(cellPhoneTextField);

		//�ּ� �Է¶�
		JPanel addressTextFieldPanel = new JPanel();
		addressTextFieldPanel.setLayout(null);
		addressTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressTextFieldPanel.setBounds(144, 323, 422, 32);
		this.add(addressTextFieldPanel);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(6, 5, 404, 21);
		addressTextFieldPanel.add(addressField);

		//�ڱ�Ұ� �Է¶�
		JPanel introductionTextAreaPanel = new JPanel();
		introductionTextAreaPanel.setLayout(null);
		introductionTextAreaPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionTextAreaPanel.setBounds(144, 354, 422, 111);
		this.add(introductionTextAreaPanel);

		JScrollPane introductionScrollPane = new JScrollPane();
		introductionScrollPane.setBounds(6, 10, 404, 91);
		introductionTextAreaPanel.add(introductionScrollPane);
		introductionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		introductionTextArea = new JTextArea(); 
		introductionScrollPane.setViewportView(introductionTextArea);
		introductionTextArea.setWrapStyleWord(true);
		introductionTextArea.setLineWrap(true);

		//��ư �г�
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 493, 578, 36);
		this.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Ȯ�� ��ư
		okButtonInJoinForm = new JButton("Ȯ��");
		buttonPanel.add(okButtonInJoinForm);
		//�ʱ�ȭ ��ư
		initializeButton = new JButton("�ʱ�ȭ");
		buttonPanel.add(initializeButton);
		//��� ��ư
		cancelButton = new JButton("���"); 
		buttonPanel.add(cancelButton);
	} //ȸ�� ���� ��� ���� �Լ� ��
}