package ScreenShot;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

import Communication.Transit;


public class ImageClient extends Thread{
	BufferedImage bufImage = null;
	//��Ʈ�� ����
	ObjectOutputStream objectOutputStream = null;
	//����
	Socket socket = null;
	//������
	public ImageClient(String option){
		try {
			socket = new Socket("localhost", 20001);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("��Ʈ�� ���� ����");
			
			if(option.equals("ScreenShot")){
				this.captureScreen();
			}
			else if(option.equals("NonStop")){
				this.captureScreenNonstop();
			}
			this.sendImage();
			
			//���� ��Ʈ�� �ݱ�
			socket.close();
			objectOutputStream.close();
		}catch (IOException e) {
			System.out.println("���� ���� ����");
		}
	} //������ ��
	
	//�̹��� ����
	public void sendImage(){
		this.captureScreen();
		try {//�̹��� ��ü ����
			ImageIO.write(bufImage, "jpg", objectOutputStream);
			System.out.println("������ �̹��� ���� �Ϸ�");
		} catch (IOException e) {
			System.out.println("�̹��� ���� ����");
		}
	}
	//����ȭ��
	private void captureScreenNonstop(){
		Robot robot = null;
		try{
			robot = new Robot();
			Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			//ĸ�� �ϱ�
			bufImage = createJustScaledImage(robot.createScreenCapture(area), 400);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//��ũ�� ĸ��
	private void captureScreen(){
		Robot robot = null;
		try{
			robot = new Robot();
			Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			//ĸ�� �ϱ�
			bufImage = robot.createScreenCapture(area);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//������
	public void run(){
		while(true){
			try {
				System.out.println("�̹��� ��� ������ ����");
				socket = new Socket("localhost", 20001);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				this.captureScreenNonstop();
				ImageIO.write(bufImage, "jpg", objectOutputStream);
				System.out.println("�̹��� ���� �Ϸ�");
				
				socket.close();
				objectOutputStream.close();
			} catch (IOException e) {
				System.out.println("�̹��� ��� ������ ����");
				break;
			}
		}
		Transit.sendMsg("������â���̱�/");
	}

	//����� ó��
	private BufferedImage createJustScaledImage(BufferedImage image, int targetSize) throws Exception{
		double resizeRatio = 1.0;
		if(image.getWidth() > image.getHeight()){
			resizeRatio = (double)targetSize / image.getWidth();
		}
		else{
			resizeRatio = (double)targetSize / image.getHeight();
		}

		if(resizeRatio > 1){
			throw new Exception("Error");
		}

		int width = (int)(image.getWidth() * resizeRatio);
		int height = (int)(image.getHeight() * resizeRatio);

		int type = (image.getTransparency() == Transparency.OPAQUE)? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB; 
		BufferedImage bufferedImage = new BufferedImage(width, height, type);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		AffineTransform xform = AffineTransform.getScaleInstance(resizeRatio,  resizeRatio);
		g2.drawRenderedImage((RenderedImage)image, xform);

		g2.dispose();

		return bufferedImage;
	}
}