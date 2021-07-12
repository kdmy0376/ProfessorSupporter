package ScreenShot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class CapturedScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private Image img = null;
	private BufferedImage bufImage = null;
	//	private JPanel contentPane = null;

	//������
	public CapturedScreen() {
//		//�޴��� ����
//		JMenuBar menuBar = new JMenuBar();
//		this.setJMenuBar(menuBar);
//		
//		JMenu menu = new JMenu("�л�ȭ��");
//		menuBar.add(menu);
		//		contentPane = new JPanel();
		//		this.setContentPane(contentPane);
		System.out.println("ĸ�� ��ũ�� ������ ����");
		this.setIconImage(new ImageIcon(".\\image\\�л��.png").getImage());		//������ �̹���
		this.setTitle("�л� ȭ��");	//������â ����
		this.setSize(400,400);		//������ ũ��
		//		this.setFocusable(false);
		this.displayCenter();		//ȭ�� �߾� ǥ��
		//X��ư ������ �� ����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				CapturedScreen.this.dispose();
			}
		});
	} //������ ��

	//���� ȭ�� ���̱�
	public void showScreenShot(BufferedImage bufImage){
		this.bufImage = bufImage;	//���� �̹��� ����
		int w = this.getWidth();
		int h = this.getHeight();
		this.img = bufImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.drawImage(img, w, h);
	}

	private void drawImage(Image img, int x, int y) {
		Graphics g = this.getGraphics();
		g.drawImage(img, 0, 0, x, y, this);
		this.paint(g);
		this.repaint();
	}

	public void paint(Graphics g) {
		if (this.img != null) {
			g.drawImage(this.img, 0, 0, this.img.getWidth(this), this.img.getHeight(this), this);
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
}