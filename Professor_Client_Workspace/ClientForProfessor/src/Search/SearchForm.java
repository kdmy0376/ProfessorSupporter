package Search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Communication.Transit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;


	public SearchForm() {
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());		//������ �̹���
		setTitle("�л��˻�");
		setBounds(100, 100, 281, 144);
		this.displayCenter();		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ȯ��");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						SearchForm.this.setVisible(false);
						Transit.sendMsg("�л��˻��޴�������/"+textField.getText());
					}
				});
				panel.add(okButton);
			}
			{
				JButton cancelButton = new JButton("���");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText("");
						SearchForm.this.dispose();
					}
				});
				panel.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("\uD559\uC0DD \uC774\uB984 : ");
				lblNewLabel.setBounds(12, 22, 64, 15);
				panel.add(lblNewLabel);
			}
			{
				textField = new JTextField();
				textField.setBounds(82, 19, 161, 21);
				panel.add(textField);
				textField.setColumns(10);
			}
		}
	}
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

	public void initializeComponent(){
		textField.setText("");
	}
}