package Note;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//���� ���� ���� Ŭ����
public class NoteForm extends JFrame{
	public JTextPane jtp_NoteForm_text;
	private JButton jb_NoteForm_ok;
	private JScrollPane jsp_NoteForm_forMsg;
	
	public NoteForm(){
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());		//������ �̹���
		this.setTitle("���� ������");
		
		this.setLayout(null);
		this.setSize(300, 300);
		//â ũ�� ���� �Ұ���
		this.setResizable(false);
		
		Container con = this.getContentPane();
		
		jtp_NoteForm_text = new JTextPane();
		jb_NoteForm_ok = new JButton("Ȯ��");
		jsp_NoteForm_forMsg	= new JScrollPane(jtp_NoteForm_text, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//������ ȭ�� ���߾ӿ� ��ġ�ϰ� �ϴ� ��ǥ
		int x = (int)this.getToolkit().getScreenSize().getWidth() / 2 - (int)this.getSize().getWidth() / 2;
		int y = (int)this.getToolkit().getScreenSize().getHeight() / 2 - (int)this.getSize().getHeight() / 2;
		
		jtp_NoteForm_text.setEditable(false);
		
		con.add(jsp_NoteForm_forMsg);
		con.add(jb_NoteForm_ok);
		
		jsp_NoteForm_forMsg.setBounds(20, 10, 250, 200);
		jb_NoteForm_ok.setBounds(110, 220, 80, 25);
		
		//�������� X�� ������ �� DISPOSE��Ŵ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Ȯ�� ��ư ������ �� â DISPOSE��Ŵ
		jb_NoteForm_ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				NoteForm.this.setVisible(false);
				NoteForm.this.dispose();
			}
		});
		//ȭ���� ������ ��ǥ�� ��ġ ��Ŵ
		this.setLocation(x, y);
		//â ����
		this.setVisible(true);
	}
}