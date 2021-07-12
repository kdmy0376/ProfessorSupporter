package Search;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import Communication.Transit;
import Controller.Manager;

public class SearchResultForm extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField idTextField = null;
	private JTextField passwordTextField = null;
	private JTextField nameTextField = null;
	private JTextField emailTextField = null;
	private JTextField sexTextField = null;
	private JTextField homepageTextField = null;
	private JTextField phoneTextField = null;
	private JTextField cellPhoneTextField = null;
	private JTextField addressField = null;
	private JTextArea introductionTextArea = null;
	private JButton okButton = null;


	//생성자
	public SearchResultForm() {
		this.createSearchForm();
		this.mountEventHandler();
	}

	//컴포넌트 값 설정
	public void setComponentValue(String[] list){

		JTextField[] textField = {idTextField, passwordTextField, nameTextField, emailTextField, sexTextField, homepageTextField,
				phoneTextField, cellPhoneTextField, addressField};
		for(int i=0; i<textField.length; i++){
			textField[i].setText(list[i]);
		}
		introductionTextArea.setText(list[list.length-1]);
	}

	//이벤트 처리기 등록
	public void mountEventHandler(){
		//회원수정 양식 화면
		okButton.addActionListener(this);
	} //이벤트 처리기 등록 함수 끝

	//이벤트 처리기
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == okButton){
			Manager.MAINFRAME.getTabbedPane().remove(this);
		}
	} //이벤트 처리기 함수 끝


	//회원 수정 양식 생성
	private void createSearchForm(){
		//회원 수정 양식 폼 패널 레이아웃
		this.setLayout(null);					
		//아이디
		JPanel idLabelPanel = new JPanel();
		idLabelPanel.setBackground(SystemColor.inactiveCaption);
		idLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idLabelPanel.setBounds(12, 74, 132, 32);
		this.add(idLabelPanel);
		idLabelPanel.setLayout(null);

		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(12, 10, 57, 15);
		idLabelPanel.add(idLabel);

		//비밀번호
		JPanel passwordLabelPanel = new JPanel();
		passwordLabelPanel.setBackground(SystemColor.inactiveCaption);
		passwordLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordLabelPanel.setLayout(null);
		passwordLabelPanel.setBounds(12, 105, 132, 32);
		this.add(passwordLabelPanel);

		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(12, 10, 57, 15);
		passwordLabelPanel.add(passwordLabel);

		//이름
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.setBackground(SystemColor.inactiveCaption);
		nameLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameLabelPanel.setLayout(null);
		nameLabelPanel.setBounds(12, 136, 132, 32);
		this.add(nameLabelPanel);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(12, 10, 57, 15);
		nameLabelPanel.add(nameLabel);

		//이메일
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.setBackground(SystemColor.inactiveCaption);
		emailLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailLabelPanel.setLayout(null);
		emailLabelPanel.setBounds(12, 167, 132, 32);
		this.add(emailLabelPanel);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(12, 10, 57, 15);
		emailLabelPanel.add(emailLabel);

		//성별
		JPanel sexLabelPanel = new JPanel();
		sexLabelPanel.setBackground(SystemColor.inactiveCaption);
		sexLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexLabelPanel.setLayout(null);
		sexLabelPanel.setBounds(12, 198, 132, 32);
		this.add(sexLabelPanel);

		JLabel sexLabel = new JLabel("성별");
		sexLabel.setBounds(12, 10, 57, 15);
		sexLabelPanel.add(sexLabel);

		//홈페이지
		JPanel homepageLabelPanel = new JPanel();
		homepageLabelPanel.setBackground(SystemColor.inactiveCaption);
		homepageLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageLabelPanel.setLayout(null);
		homepageLabelPanel.setBounds(12, 229, 132, 32);
		this.add(homepageLabelPanel);

		JLabel homepageLabel = new JLabel("홈페이지");
		homepageLabel.setBounds(12, 10, 57, 15);
		homepageLabelPanel.add(homepageLabel);

		//전화번호
		JPanel phoneLabelPanel = new JPanel();
		phoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		phoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneLabelPanel.setLayout(null);
		phoneLabelPanel.setBounds(12, 260, 132, 32);
		this.add(phoneLabelPanel);

		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setBounds(12, 10, 57, 15);
		phoneLabelPanel.add(phoneLabel);

		//핸드폰 번호
		JPanel cellPhoneLabelPanel = new JPanel();
		cellPhoneLabelPanel.setBackground(SystemColor.inactiveCaption);
		cellPhoneLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneLabelPanel.setLayout(null);
		cellPhoneLabelPanel.setBounds(12, 291, 132, 32);
		this.add(cellPhoneLabelPanel);

		JLabel cellPhoneLabel = new JLabel("핸드폰 번호");
		cellPhoneLabel.setBounds(12, 10, 72, 15);
		cellPhoneLabelPanel.add(cellPhoneLabel);

		//주소
		JPanel addressLabelPanel = new JPanel();
		addressLabelPanel.setBackground(SystemColor.inactiveCaption);
		addressLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressLabelPanel.setLayout(null);
		addressLabelPanel.setBounds(12, 322, 132, 32);
		this.add(addressLabelPanel);

		JLabel addressLabel = new JLabel("주소");
		addressLabel.setBounds(12, 10, 57, 15);
		addressLabelPanel.add(addressLabel);

		//자기소개
		JPanel introductionLabelPanel = new JPanel();
		introductionLabelPanel.setBackground(SystemColor.inactiveCaption);
		introductionLabelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionLabelPanel.setLayout(null);
		introductionLabelPanel.setBounds(12, 353, 132, 106);
		this.add(introductionLabelPanel);

		JLabel introductionLabel = new JLabel("자기소개");
		introductionLabel.setBounds(12, 49, 57, 15);
		introductionLabelPanel.add(introductionLabel);

		//아이디 입력란
		JPanel idTextFieldPanel = new JPanel();
		idTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		idTextFieldPanel.setBounds(144, 74, 422, 32);
		this.add(idTextFieldPanel);
		idTextFieldPanel.setLayout(null);

		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setBounds(6, 5, 116, 21);
		idTextFieldPanel.add(idTextField);
		idTextField.setColumns(10);

		//비밀번호 입력란
		JPanel passwordTextFieldPanel = new JPanel();
		passwordTextFieldPanel.setLayout(null);
		passwordTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordTextFieldPanel.setBounds(144, 105, 422, 32);
		this.add(passwordTextFieldPanel);

		passwordTextField = new JTextField();
		passwordTextField.setEditable(false);
		passwordTextField.setBounds(6, 5, 116, 21);
		passwordTextFieldPanel.add(passwordTextField);

		//이름 입력란
		JPanel nameTextFieldPanel = new JPanel();
		nameTextFieldPanel.setLayout(null);
		nameTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameTextFieldPanel.setBounds(144, 136, 422, 32);
		this.add(nameTextFieldPanel);

		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setColumns(10);
		nameTextField.setBounds(6, 5, 116, 21);
		nameTextFieldPanel.add(nameTextField);

		//이메일 입력란
		JPanel emailTextFieldPanel = new JPanel();
		emailTextFieldPanel.setLayout(null);
		emailTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailTextFieldPanel.setBounds(144, 167, 422, 32);
		this.add(emailTextFieldPanel);

		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setColumns(10);
		emailTextField.setBounds(6, 5, 220, 21);
		emailTextFieldPanel.add(emailTextField);

		//성별 선택란
		JPanel sexComboBoxPanel = new JPanel();
		sexComboBoxPanel.setLayout(null);
		sexComboBoxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sexComboBoxPanel.setBounds(144, 198, 422, 32);
		this.add(sexComboBoxPanel);

		sexTextField = new JTextField();
		sexTextField.setEditable(false);
		sexTextField.setBounds(6, 6, 220, 21);
		sexComboBoxPanel.add(sexTextField);
		sexTextField.setColumns(10);

		String[] sex = {"남", "여"};

		//홈페이지 입력란
		JPanel homepageTextFieldPanel = new JPanel();
		homepageTextFieldPanel.setLayout(null);
		homepageTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homepageTextFieldPanel.setBounds(144, 229, 422, 32);
		this.add(homepageTextFieldPanel);

		homepageTextField = new JTextField();
		homepageTextField.setEditable(false);
		homepageTextField.setColumns(10);
		homepageTextField.setBounds(6, 5, 221, 21);
		homepageTextFieldPanel.add(homepageTextField);

		//전화번호 입력란
		JPanel phoneTextFieldPanel = new JPanel();
		phoneTextFieldPanel.setLayout(null);
		phoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		phoneTextFieldPanel.setBounds(144, 260, 422, 32);
		this.add(phoneTextFieldPanel);

		phoneTextField = new JTextField();
		phoneTextField.setEditable(false);
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(6, 5, 116, 21);
		phoneTextFieldPanel.add(phoneTextField);

		//핸드폰 번호 입력란
		JPanel cellPhoneTextFieldPanel = new JPanel();
		cellPhoneTextFieldPanel.setLayout(null);
		cellPhoneTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cellPhoneTextFieldPanel.setBounds(144, 291, 422, 32);
		this.add(cellPhoneTextFieldPanel);

		cellPhoneTextField = new JTextField();
		cellPhoneTextField.setEditable(false);
		cellPhoneTextField.setColumns(10);
		cellPhoneTextField.setBounds(6, 5, 116, 21);
		cellPhoneTextFieldPanel.add(cellPhoneTextField);

		//주소 입력란
		JPanel addressTextFieldPanel = new JPanel();
		addressTextFieldPanel.setLayout(null);
		addressTextFieldPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addressTextFieldPanel.setBounds(144, 322, 422, 32);
		this.add(addressTextFieldPanel);

		addressField = new JTextField();
		addressField.setEditable(false);
		addressField.setColumns(10);
		addressField.setBounds(6, 5, 404, 21);
		addressTextFieldPanel.add(addressField);

		//자기소개 입력란
		JPanel introductionTextAreaPanel = new JPanel();
		introductionTextAreaPanel.setLayout(null);
		introductionTextAreaPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introductionTextAreaPanel.setBounds(144, 353, 422, 106);
		this.add(introductionTextAreaPanel);

		JScrollPane introductionScrollPane = new JScrollPane();
		introductionScrollPane.setBounds(6, 10, 404, 88);
		introductionTextAreaPanel.add(introductionScrollPane);
		introductionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		introductionTextArea = new JTextArea();
		introductionTextArea.setEditable(false);
		introductionScrollPane.setViewportView(introductionTextArea);
		introductionTextArea.setWrapStyleWord(true);
		introductionTextArea.setLineWrap(true);

		//버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 484, 578, 36);
		this.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		okButton = new JButton("확인");
		buttonPanel.add(okButton);

		JLabel lblNewLabel = new JLabel("\u25A0 \uAC80\uC0C9 \uACB0\uACFC");
		lblNewLabel.setBounds(12, 49, 132, 15);
		add(lblNewLabel);

	} //회원 가입 양식 생성 함수 끝
}