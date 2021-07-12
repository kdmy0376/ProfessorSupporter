package SplashScreen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import ScreenShot.CaptureScreen;

import Controller.Manager;

public class SplashScreen extends JWindow implements Runnable{
	private static final long serialVersionUID = 1L;
	private Container contentPane = null;		//������ ������ �ޱ� ���� ����
	private JProgressBar progressBar = null;	//���α׷��� �� ����
	private ImageIcon img = null;				//�ε� ȭ�� �̹��� ����
	//�̹����� ���
	private final String imagePath = ".\\image\\Professor Version(450x208).png";
	
	//������
	public SplashScreen(){
		this.initializeMember();			//��� ���� �ʱ�ȭ
		this.setSize(450,208);				//�ε� ȭ�� ũ�� 
		this.displayCenter();				//�ε� ȭ�� �߾� ��ġ
	} //������ ��
	
	//��� ���� �ʱ�ȭ �� ����
	private void initializeMember(){
		contentPane = this.getContentPane();				//������ ������ ������
		img = new ImageIcon(imagePath);						//�̹��� ������ ����
		progressBar = new JProgressBar();					//���α׷��� �� ����
		progressBar.setVisible(false);						//���α׷��� �� ������ �ʱ�
		progressBar.setMinimum(0);							//���α׷��� �� �ּҰ� 0	
		progressBar.setMaximum(100);						//���α׷��� �� �ִ밪 100
		contentPane.setLayout(new BorderLayout());			//��ü ���� ���̾ƿ�
		contentPane.add(progressBar, BorderLayout.SOUTH);	//���α׷��� �� ����
	} //��� ���� �ʱ�ȭ �� ���� �Լ� ��
	
	//�׸���
	public void paint(Graphics g){
		g.drawImage(img.getImage(),0, 0, 450, 208, null, null);
	} //�׸��� �Լ� ��
	
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
	
	//�α��� â ǥ��
	public void createLoginWindow(){
		threadSleep(2000);					//�α��� â ǥ�õǱ� �� ��� ����
		Manager.LOGINFORM.setVisible(true);	//�α��� ȭ�� ���̱�
	} //�α��� â ǥ�� �Լ� ��
	
	//���α׷��� �� ���� ����
	public void startProgressBar(){
		System.out.println("[�ε�ȭ��] ���α׷��� �� ���� ����");
		this.setAlwaysOnTop(true);	//�� ���� ǥ��
		new Thread(this).start();
	} //���α׷��� �� ���� ���� �Լ� ��
	
	//���α׷��� �� ���� ������
	public void run(){
		threadSleep(1000);					//���α׷��� �� ǥ�õǱ� �� ��� ����
		progressBar.setVisible(true);		//���α׷��� �� ǥ��		
		threadSleep(1000);					//���α׷��� �� ǥ�õ� �� ��� ����
		//���α׷��� �ٿ� ���� ���� ǥ��
		for(int i=0; i<100; i++){
			progressBar.setValue(i+1);
			threadSleep(10);
		}
		this.setVisible(false); //�ε� ȭ���� �������� ����
		this.dispose();			//�ε� ȭ�� ���ҽ� ����
		Manager.MAINFRAME.setVisible(true);	//����ȭ�� ǥ��
		//new CaptureScreen().start();
	} //���α׷��� �� ���� ������ �Լ� ��
	
	//������ ����
	private void threadSleep(long mills){
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} //������ ���� �Լ� ��
}