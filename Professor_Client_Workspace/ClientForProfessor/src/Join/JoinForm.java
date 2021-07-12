package Join;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import Communication.Transit;
import Controller.Manager;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class JoinForm extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	public Container contentPane = null;		//������ ������ �ޱ� ���� ���� 
	public CardLayout cardLayout = null;		//��ü ī�� ���̾ƿ�
	//ù��° ȭ��(��� �� ��ħ)
	private JPanel joinPolicyPanel = null;		//ȸ�����Ծ��, ��������������ħ, ��ư�� ��� �г� ����
	private JPanel joinFormPanel = null;		//ȸ�� ��� ��� �г� ����
	private JPanel contentPanel = null;			//ȸ�����Ծ�� �� ��������������ħ ��� �г� ����
	private JPanel buttonPanel = null;			//��ư �г� ����
	private JButton okButton = null;			//Ȯ�� ��ư ����
	private JRadioButton agreeRadioButtonOfJoinTerms = null;		//ȸ�����Ծ���� �����մϴ� ��ư
	private JRadioButton disagreeRadioButtonOfJoinTerms = null;		//ȸ�����Ծ���� �������� �ʽ��ϴ� ��ư
	private JRadioButton agreeRadioButtonOfPrivacyPolicy = null;	//��������������ħ�� �����մϴ� ��ư
	private JRadioButton disagreeRadioButtonOfPrivacyPolicy = null;	//��������������ħ�� �������� �ʽ��ϴ� ��ư
	//�ι�° ȭ��(��Ͼ��)
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
	
	//ȸ�����Ծ��
	private final String joinTerms = "1. �� ���α׷��� ��ϴ��б� ��ǻ�ͼ���Ʈ������������ ���������� �������� ��ϴ� ����Ʈ�Դϴ�.\n" +
			"2. ȸ�� ���� �� �̿� �ڰ��� ��ϴ��б� ��ǻ�������к� ��ǻ�ͼ���Ʈ���������� ���������� �����մϴ�.\n" +
			"3. �� ���α׷����� �����Ǵ� �н� ���� �� ���� �ڷ���� ������ �н� ������ ���ؼ��� Ȱ���ϰ�, ������ �ƴ� �ٸ� ������� �����Ѵٵ��� �ٸ� �ܺ� ����Ʈ�� �����ϴ� ���� �����մϴ�.\n" + 
			"4. ������ ���̵� �� ��й�ȣ�� Ÿ���� �̿��ϴ� ���� �����մϴ�. Ÿ���� ���̵� �����ϴ� ��쿡��, "+
			"��ϴ��б��� ��Ģ���� �����ϰ� �ִ� ���� ���������� ���ϴ� ���ݻ������� �ٷ���� ���̹Ƿ� ������ �����Ͻñ� �ٶ��ϴ�.";
	//��������������ħ
	private final String privacyPolicy = "1. �� ���α׷������� �ֹε�Ϲ�ȣ, ��ȭ��ȣ, �ּ� ��� ���� ���������� ���������� �������� �ʽ��ϴ�.\n" +  
			"2. �л��� �й��� �̸��� ���ο��θ� Ȯ���ϱ� ���� �������� ���˴ϴ�.\n" +
			"3. ������ ���õ� �л��� �Խù��� �������� �ش� ������ �н� ���� �� ���� �򰡿��� Ȱ��˴ϴ�.\n" + 
			"4. �α��ε� �������� ���������� �÷����� �������� �ڷ� �� �Խù����� ��ǻ�ͼ���Ʈ���� ������ ���������� �о����� ����� �й����� ������ ���ؼ� Ȱ��Ǵ� ���� �������� �� ���Դϴ�.\n" + 
			"5. �� ���α׷����� �����Ǵ� �ڷ���� ��ǻ�ͼ���Ʈ���� ���� �������� Ȱ��Ǵ� ���� ��Ģ���� �մϴ�. " +
			"�ܺο� ����Ǵ� ���� ����� �����ϸ�, ���� �ܺο� ����Ǿ� �������� �ϵ鿡 ���ؼ��� �� ���α׷��� ��ڴ� ��� ������ å���� ���� �ʽ��ϴ�.";

	//������
	public JoinForm() {
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());
		this.setModalityType(ModalityType.APPLICATION_MODAL);	//��� ��ȭ����
		this.initializeMember();		//��� ���� �ʱ�ȭ
		this.setSize(594, 554);			//ȸ������ â ũ��
		this.displayCenter();			//ȭ�� ��ġ �߾� ����
		this.createJoinPolicyForm();	//ȸ������ ��� �� ��ħ ��� ����
		this.createJoinForm();			//ȸ������ ��� ����
		//this.initializeRegisterForm();	//ȸ�� ��� ��� ������Ʈ �ʱ�ȭ
		this.mountEventHandler();		//�̺�Ʈ ó���� ���
	} //������ ��

	//��� ���� �ʱ�ȭ �� ����
	private void initializeMember(){
		contentPane = this.getContentPane();			//������ ������ ������
		cardLayout = new CardLayout();					//ī�� ���̾ƿ� ����
		contentPane.setLayout(cardLayout); 				//��ü ī�� ���̾ƿ�
		joinPolicyPanel = new JPanel();					//ȸ�����Ծ��, ��������������ħ, ��ư�� ��� �г� ����
		joinPolicyPanel.setLayout(new BorderLayout());	//ȸ�����Ծ��, ��������������ħ, ��ư�� ��� �г� ���̾ƿ�
		contentPanel = new JPanel();					//ȸ�����Ծ�� �� ��������������ħ ����� ��� �г� ����
		contentPanel.setLayout(null);					//ȸ�����Ծ�� �� ��������������ħ ��� �г� ���̾ƿ�
		buttonPanel = new JPanel();						//��ư �г� ����
		joinFormPanel = new JPanel();					//ȸ�� ��� ��� �� �г� ����
		joinFormPanel.setLayout(null);					//ȸ�� ��� ��� �� �г� ���̾ƿ�
		contentPane.add("JoinPolicy", joinPolicyPanel);	//JoinPolicy��� �̸��� �г� ���
		contentPane.add("JoinForm", joinFormPanel);		//JoinForm�̶�� �̸��� �г� ���
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
		int yPosition = (int)(screenDimension.getHeight()/2 - frameDimension.getHeight()/2);
		this.setLocation(xPosition, yPosition);
	} //ȭ�� �߾� ǥ�� �Լ� ��

	//�̺�Ʈ ó���� ���
	public void mountEventHandler(){
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//��� �� ��ħ ȭ��
		okButton.addActionListener(this);
		//ȸ������ ��� ȭ��
		duplicationCheckButton.addActionListener(this);
		okButtonInJoinForm.addActionListener(this);
		initializeButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//X��ư ������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.out.println("[ȸ������ â] X ��ư Ŭ��");
				cardLayout.show(contentPane, "JoinPolicy");
				Manager.LOGINFORM.setAlwaysOnTop(true);
			}
		});
	} //�̺�Ʈ ó���� ��� �Լ� ��

	//�̺�Ʈ ó����
	public void actionPerformed(ActionEvent e){
		//��� �� ��ħ ȭ��
		//Ȯ�� ��ư
		if(e.getSource() == okButton){
			//ȸ�����Ծ���� ��������������ħ �� �� ������ ��
			if(agreeRadioButtonOfJoinTerms.isSelected() && agreeRadioButtonOfPrivacyPolicy.isSelected()){
				cardLayout.show(contentPane, "JoinForm");
			}
			//�������� �ʽ��ϴٿ� üũ���ִ� ���
			else{
				//�޽��� ��ȭ���� ǥ��(� ���� �߾�, �޽���, Ÿ��Ʋ, �޽�����ȭ����)
				JOptionPane.showMessageDialog(this, "���� �ش� ���� �����ϼž� �����Ͻ� �� �ֽ��ϴ�.", "���α׷� �޽���", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//ȸ������ ��� ȭ��
		//�ߺ�Ȯ�� ��ư
		else if(e.getSource() == duplicationCheckButton){
			System.out.println("�ߺ�Ȯ�� ��ư�� ���������ϴ�.");
			String id = idTextField.getText();
			Transit.sendMsg("�ߺ�Ȯ��/" + id);		
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
			Manager.LOGINFORM.setAlwaysOnTop(true);
			this.dispose();
			cardLayout.show(contentPane, "JoinPolicy");
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
		
		//������ ȸ������ �Ӽ� 10�� ����
		Transit.sendMsg("ȸ������/" + id + "/" + password + "/" + name + "/" + email + "/" + sex + "/" + homepage
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

	//��� ��� ����
	private void createJoinPolicyForm(){
		//ȸ�����Ծ��
		//
		//ȸ�����Ծ�� �г� ����
		JPanel joinTermsPanel = new JPanel();
		joinTermsPanel.setLayout(null);	
		joinTermsPanel.setBackground(SystemColor.menu);
		joinTermsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		joinTermsPanel.setBounds(12, 10, 554, 222);
		contentPanel.add(joinTermsPanel);
		//ȸ�����Ծ�� ���̺�
		JLabel joinTermsLabel = new JLabel("ȸ�����Ծ��");
		joinTermsLabel.setBounds(12, 10, 79, 15);
		joinTermsPanel.add(joinTermsLabel);
		//��ũ�� ���� ����
		JScrollPane joinTermsScrollPane = new JScrollPane();
		joinTermsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		joinTermsScrollPane.setBounds(12, 35, 530, 152);
		joinTermsPanel.add(joinTermsScrollPane);
		//�ؽ�Ʈ ���� ����
		JTextArea joinTermsTextArea = new JTextArea();
		joinTermsTextArea.setEditable(false);		//���� �Ұ��� ����
		joinTermsTextArea.setWrapStyleWord(true);	//�ܾ� ���� �ٹٲ� ����
		joinTermsTextArea.setLineWrap(true);		//�ڵ� �ٹٲ� ����
		joinTermsTextArea.setText(joinTerms);		//�ؽ�Ʈ ����
		joinTermsScrollPane.setViewportView(joinTermsTextArea);
		
		//���� ��ư ����
		agreeRadioButtonOfJoinTerms = new JRadioButton("�����մϴ�.");
		agreeRadioButtonOfJoinTerms.setSelected(true);
		agreeRadioButtonOfJoinTerms.setBackground(SystemColor.menu);
		agreeRadioButtonOfJoinTerms.setBounds(12, 193, 121, 23);
		joinTermsPanel.add(agreeRadioButtonOfJoinTerms); 

		disagreeRadioButtonOfJoinTerms = new JRadioButton("�������� �ʽ��ϴ�.");
		disagreeRadioButtonOfJoinTerms.setBackground(SystemColor.menu);
		disagreeRadioButtonOfJoinTerms.setBounds(137, 193, 139, 23);
		joinTermsPanel.add(disagreeRadioButtonOfJoinTerms);
		//���� ��ư �׷� ����
		ButtonGroup joinTermsGroup = new ButtonGroup();
		joinTermsGroup.add(agreeRadioButtonOfJoinTerms);
		joinTermsGroup.add(disagreeRadioButtonOfJoinTerms);
		//
		//ȸ������ ��� ��

		//��������������ħ
		//
		//��������������ħ �г� ����
		JPanel privacyPolicyPanel = new JPanel();
		privacyPolicyPanel.setBackground(SystemColor.menu);
		privacyPolicyPanel.setLayout(null);
		privacyPolicyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		privacyPolicyPanel.setBounds(12, 254, 554, 213);
		contentPanel.add(privacyPolicyPanel);
		//��������������ħ ���̺�
		JLabel privacyPolicyLabel = new JLabel("����������޹�ħ");
		privacyPolicyLabel.setBounds(12, 10, 132, 15);
		privacyPolicyPanel.add(privacyPolicyLabel);
		//��ũ�� ���� ����
		JScrollPane privacyPolicyScrollPane = new JScrollPane(); 
		privacyPolicyScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		privacyPolicyScrollPane.setBounds(12, 35, 530, 143);
		privacyPolicyPanel.add(privacyPolicyScrollPane);
		//�ؽ�Ʈ ���� ����
		JTextArea privacyPolicyTextArea = new JTextArea();
		privacyPolicyTextArea.setEditable(false);		//���� �Ұ��� ����
		privacyPolicyTextArea.setWrapStyleWord(true);	//�ܾ� ���� �ٹٲ� ����
		privacyPolicyTextArea.setLineWrap(true);		//�ڵ� �ٹٲ� ����
		privacyPolicyTextArea.setText(privacyPolicy);	//�ؽ�Ʈ ����
		privacyPolicyScrollPane.setViewportView(privacyPolicyTextArea);
		
		//���� ��ư ����
		agreeRadioButtonOfPrivacyPolicy = new JRadioButton("�����մϴ�.");
		agreeRadioButtonOfPrivacyPolicy.setSelected(true);
		agreeRadioButtonOfPrivacyPolicy.setBackground(SystemColor.menu);
		agreeRadioButtonOfPrivacyPolicy.setBounds(12, 184, 121, 23);
		privacyPolicyPanel.add(agreeRadioButtonOfPrivacyPolicy); 

		disagreeRadioButtonOfPrivacyPolicy = new JRadioButton("�������� �ʽ��ϴ�.");
		disagreeRadioButtonOfPrivacyPolicy.setBackground(SystemColor.menu);
		disagreeRadioButtonOfPrivacyPolicy.setBounds(137, 184, 139, 23);
		privacyPolicyPanel.add(disagreeRadioButtonOfPrivacyPolicy);
		//���� ��ư �׷� ����
		ButtonGroup privacyPolicyGroup = new ButtonGroup();
		privacyPolicyGroup.add(agreeRadioButtonOfPrivacyPolicy);
		privacyPolicyGroup.add(disagreeRadioButtonOfPrivacyPolicy);
		//
		//��������������ħ ��

		//Ȯ�� ��ư ��ġ
		okButton = new JButton("Ȯ��");
		buttonPanel.add(okButton);
		//������ ������ ��ġ
		joinPolicyPanel.add(contentPanel, BorderLayout.CENTER);
		joinPolicyPanel.add(buttonPanel, BorderLayout.SOUTH);
	} //��� ��� ���� �Լ� ��

	//ȸ�� ���� ��� ����
	private void createJoinForm(){
		//���̵�
		JPanel idLabelPanel = new JPanel();
		idLabelPanel.setBackground(SystemColor.inactiveCaption);
		idLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idLabelPanel.setBounds(12, 73, 132, 32);
		joinFormPanel.add(idLabelPanel);
		idLabelPanel.setLayout(null);

		JLabel idLabel = new JLabel("���̵�");
		idLabel.setBounds(12, 10, 57, 15);
		idLabelPanel.add(idLabel);

		//��й�ȣ
		JPanel passwordLabelPanel = new JPanel();
		passwordLabelPanel.setBackground(SystemColor.inactiveCaption);
		passwordLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordLabelPanel.setLayout(null);
		passwordLabelPanel.setBounds(12, 104, 132, 32);
		joinFormPanel.add(passwordLabelPanel);

		JLabel passwordLabel = new JLabel("��й�ȣ");
		passwordLabel.setBounds(12, 10, 57, 15);
		passwordLabelPanel.add(passwordLabel);

		//��й�ȣ Ȯ��
		JPanel passwordCheckLabelPanel = new JPanel();
		passwordCheckLabelPanel.setBackground(SystemColor.inactiveCaption);
		passwordCheckLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordCheckLabelPanel.setLayout(null);
		passwordCheckLabelPanel.setBounds(12, 135, 132, 32);
		joinFormPanel.add(passwordCheckLabelPanel);

		JLabel passwordCheckLabel = new JLabel("��й�ȣ Ȯ��");
		passwordCheckLabel.setBounds(12, 10, 89, 15);
		passwordCheckLabelPanel.add(passwordCheckLabel);

		//�̸�
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.setBackground(SystemColor.inactiveCaption);
		nameLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameLabelPanel.setLayout(null);
		nameLabelPanel.setBounds(12, 166, 132, 32);
		joinFormPanel.add(nameLabelPanel);

		JLabel nameLabel = new JLabel("�̸�");
		nameLabel.setBounds(12, 10, 57, 15);
		nameLabelPanel.add(nameLabel);

		//�̸���
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.setBackground(SystemColor.inactiveCaption);
		emailLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailLabelPanel.setLayout(null);
		emailLabelPanel.setBounds(12, 197, 132, 32);
		joinFormPanel.add(emailLabelPanel);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(12, 10, 57, 15);
		emailLabelPanel.add(emailLabel);

		//����
		JPanel sexLabelPanel = new JPanel();
		sexLabelPanel.setBackground(SystemColor.inactiveCaption);
		sexLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexLabelPanel.setLayout(null);
		sexLabelPanel.setBounds(12, 228, 132, 32);
		joinFormPanel.add(sexLabelPanel);

		JLabel sexLabel = new JLabel("����");
		sexLabel.setBounds(12, 10, 57, 15);
		sexLabelPanel.add(sexLabel);

		//Ȩ������
		JPanel homepageLabelPanel = new JPanel();
		homepageLabelPanel.setBackground(SystemColor.inactiveCaption);
		homepageLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageLabelPanel.setLayout(null);
		homepageLabelPanel.setBounds(12, 259, 132, 32);
		joinFormPanel.add(homepageLabelPanel);

		JLabel homepageLabel = new JLabel("Ȩ������");
		homepageLabel.setBounds(12, 10, 57, 15);
		homepageLabelPanel.add(homepageLabel);

		//��ȭ��ȣ
		JPanel phoneLabelPanel = new JPanel();
		phoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		phoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneLabelPanel.setLayout(null);
		phoneLabelPanel.setBounds(12, 290, 132, 32);
		joinFormPanel.add(phoneLabelPanel);

		JLabel phoneLabel = new JLabel("��ȭ��ȣ");
		phoneLabel.setBounds(12, 10, 57, 15);
		phoneLabelPanel.add(phoneLabel);

		//�ڵ��� ��ȣ
		JPanel cellPhoneLabelPanel = new JPanel();
		cellPhoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		cellPhoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneLabelPanel.setLayout(null);
		cellPhoneLabelPanel.setBounds(12, 321, 132, 32);
		joinFormPanel.add(cellPhoneLabelPanel);

		JLabel cellPhoneLabel = new JLabel("�ڵ��� ��ȣ");
		cellPhoneLabel.setBounds(12, 10, 72, 15);
		cellPhoneLabelPanel.add(cellPhoneLabel);

		//�ּ�
		JPanel addressLabelPanel = new JPanel();
		addressLabelPanel.setBackground(SystemColor.inactiveCaption);
		addressLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressLabelPanel.setLayout(null);
		addressLabelPanel.setBounds(12, 352, 132, 32);
		joinFormPanel.add(addressLabelPanel);

		JLabel addressLabel = new JLabel("�ּ�");
		addressLabel.setBounds(12, 10, 57, 15);
		addressLabelPanel.add(addressLabel);

		//�ڱ�Ұ�
		JPanel introductionLabelPanel = new JPanel();
		introductionLabelPanel.setBackground(SystemColor.inactiveCaption);
		introductionLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionLabelPanel.setLayout(null);
		introductionLabelPanel.setBounds(12, 383, 132, 77);
		joinFormPanel.add(introductionLabelPanel);

		JLabel introductionLabel = new JLabel("�ڱ�Ұ�");
		introductionLabel.setBounds(12, 31, 57, 15);
		introductionLabelPanel.add(introductionLabel);

		//���̵� �Է¶�
		JPanel idTextFieldPanel = new JPanel();
		idTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idTextFieldPanel.setBounds(144, 73, 422, 32);
		joinFormPanel.add(idTextFieldPanel);
		idTextFieldPanel.setLayout(null);

		idTextField = new JTextField();
		idTextField.setBounds(6, 5, 116, 21);
		idTextFieldPanel.add(idTextField);
		idTextField.setColumns(10);

		//�ߺ�Ȯ�� ��ư
		duplicationCheckButton = new JButton("�ߺ�Ȯ��");
		duplicationCheckButton.setBounds(134, 4, 91, 23);
		idTextFieldPanel.add(duplicationCheckButton);

		//ȸ������ ��� �̹���
		JPanel joinBannerPanel = new JPanel();
		joinBannerPanel.setBackground(SystemColor.inactiveCaption);
		joinBannerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		joinBannerPanel.setBounds(0, 0, 578, 57);
		joinFormPanel.add(joinBannerPanel);
		joinBannerPanel.setLayout(null);

		JLabel joinBannerLabel = new JLabel("Join"); 
		joinBannerLabel.setFont(new Font("Miriam", Font.BOLD, 42));
		joinBannerLabel.setBounds(12, 10, 181, 37);
		joinBannerPanel.add(joinBannerLabel);

		//��й�ȣ �Է¶�
		JPanel passwordTextFieldPanel = new JPanel();
		passwordTextFieldPanel.setLayout(null);
		passwordTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordTextFieldPanel.setBounds(144, 104, 422, 32);
		joinFormPanel.add(passwordTextFieldPanel);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(6, 5, 116, 21);
		passwordTextFieldPanel.add(passwordTextField);

		//��й�ȣ Ȯ�� �Է¶�
		JPanel passwordCheckTextFieldPanel = new JPanel();
		passwordCheckTextFieldPanel.setLayout(null);
		passwordCheckTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordCheckTextFieldPanel.setBounds(144, 135, 422, 32);
		joinFormPanel.add(passwordCheckTextFieldPanel);

		passwordCheckTextField = new JPasswordField();
		passwordCheckTextField.setBounds(6, 5, 116, 21);
		passwordCheckTextFieldPanel.add(passwordCheckTextField);

		//�̸� �Է¶�
		JPanel nameTextFieldPanel = new JPanel();
		nameTextFieldPanel.setLayout(null);
		nameTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameTextFieldPanel.setBounds(144, 166, 422, 32);
		joinFormPanel.add(nameTextFieldPanel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(6, 5, 116, 21);
		nameTextFieldPanel.add(nameTextField);

		//�̸��� �Է¶�
		JPanel emailTextFieldPanel = new JPanel();
		emailTextFieldPanel.setLayout(null);
		emailTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailTextFieldPanel.setBounds(144, 197, 422, 32);
		joinFormPanel.add(emailTextFieldPanel);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(6, 5, 220, 21);
		emailTextFieldPanel.add(emailTextField);

		//���� ���ö�
		JPanel sexComboBoxPanel = new JPanel();
		sexComboBoxPanel.setLayout(null);
		sexComboBoxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexComboBoxPanel.setBounds(144, 228, 422, 32);
		joinFormPanel.add(sexComboBoxPanel);

		String[] sex = {"��", "��"};
		sexComboBox = new JComboBox<String>(sex);
		sexComboBox.setBounds(6, 5, 60, 21);
		sexComboBoxPanel.add(sexComboBox);

		//Ȩ������ �Է¶�
		JPanel homepageTextFieldPanel = new JPanel();
		homepageTextFieldPanel.setLayout(null);
		homepageTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageTextFieldPanel.setBounds(144, 259, 422, 32);
		joinFormPanel.add(homepageTextFieldPanel);

		homepageTextField = new JTextField();
		homepageTextField.setColumns(10);
		homepageTextField.setBounds(6, 5, 221, 21);
		homepageTextFieldPanel.add(homepageTextField);

		//��ȭ��ȣ �Է¶�
		JPanel phoneTextFieldPanel = new JPanel();
		phoneTextFieldPanel.setLayout(null);
		phoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneTextFieldPanel.setBounds(144, 290, 422, 32);
		joinFormPanel.add(phoneTextFieldPanel);

		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(6, 5, 116, 21);
		phoneTextFieldPanel.add(phoneTextField);

		//�ڵ��� ��ȣ �Է¶�
		JPanel cellPhoneTextFieldPanel = new JPanel();
		cellPhoneTextFieldPanel.setLayout(null);
		cellPhoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneTextFieldPanel.setBounds(144, 321, 422, 32);
		joinFormPanel.add(cellPhoneTextFieldPanel);

		cellPhoneTextField = new JTextField();
		cellPhoneTextField.setColumns(10);
		cellPhoneTextField.setBounds(6, 5, 116, 21);
		cellPhoneTextFieldPanel.add(cellPhoneTextField);

		//�ּ� �Է¶�
		JPanel addressTextFieldPanel = new JPanel();
		addressTextFieldPanel.setLayout(null);
		addressTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressTextFieldPanel.setBounds(144, 352, 422, 32);
		joinFormPanel.add(addressTextFieldPanel);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(6, 5, 404, 21);
		addressTextFieldPanel.add(addressField);

		//�ڱ�Ұ� �Է¶�
		JPanel introductionTextAreaPanel = new JPanel();
		introductionTextAreaPanel.setLayout(null);
		introductionTextAreaPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionTextAreaPanel.setBounds(144, 383, 422, 77);
		joinFormPanel.add(introductionTextAreaPanel);

		JScrollPane introductionScrollPane = new JScrollPane();
		introductionScrollPane.setBounds(6, 10, 404, 58);
		introductionTextAreaPanel.add(introductionScrollPane);
		introductionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		introductionTextArea = new JTextArea(); 
		introductionScrollPane.setViewportView(introductionTextArea);
		introductionTextArea.setWrapStyleWord(true);
		introductionTextArea.setLineWrap(true);
		
		//��ư �г�
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 470, 578, 36);
		joinFormPanel.add(buttonPanel);
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
		
		
		idTextField.setText("Professors");
		passwordTextField.setText("1");
		passwordCheckTextField.setText("1");
		nameTextField.setText("�輺��");
		emailTextField.setText("shkim1454@knu.ac.kr");
		sexComboBox.setSelectedIndex(0);
		homepageTextField.setText("https://220.66.213.180/iSoftLab/");
		phoneTextField.setText("02-1234-1234");
		cellPhoneTextField.setText("010-1234-1234");
		addressField.setText("�����");
		introductionTextArea.setText("�ȳ��ϼ���");
	} //ȸ�� ���� ��� ���� �Լ� ��
}