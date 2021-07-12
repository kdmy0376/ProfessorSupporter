package ScreenShot;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

//
public class CaptureScreen extends Thread{//�̹��� ���ϴ� Ŭ����
	protected BufferedImage img1 = null;
	protected BufferedImage img2 = null;
	protected int dx = 8;  //�̹����� ���� ���� ũ��
	protected int dy = 6;  //�̹����� ���� ���� ũ��
	protected int parameter1 = 50;  //�ΰ��� 5
	protected int parameter2 = 10;
	protected boolean match = true;

	public CaptureScreen() {//������
	}
	
	public void compare() {//�� �Լ�
		//img1 = ToBufImage(GrayFilter.createDisabledImage(img1));	//�׷������;���
		//img2 = ToBufImage(GrayFilter.createDisabledImage(img2));

		//�̹����� ���� ���� ����
		int bx = (int)(img1.getWidth() / dx);  //dx(x���� �ʺ�)�� ���� ����
		//���α���
		int by = (int)(img1.getHeight() / dy);  //dy(y���� �ʺ�)�� ���� ����

		System.out.println("start");  //����(������)

		for (int y = 0; y < dy; y++) { //��� ���� �˻�
			for (int x = 0; x < dx; x++) {
				int b1 = Bright(img1.getSubimage(x*bx, y*by, bx - 1, by - 1));
				int b2 = Bright(img2.getSubimage(x*bx, y*by, bx - 1, by - 1));
				int diff = Math.abs(b1 - b2);  //�̹��� ������ ���� �ٸ���
				if (diff > parameter1) {   		//�ٸ��� �ΰ��� �̻��϶�
					this.match = false;  		//false�� �ٲ۴�.
				}
			}
		}
		System.out.println("end");  //��(������)
	}

	protected int Bright(BufferedImage img) {   //�׷��� ���� ������ ��� ��
		Raster r = img.getData();
		int total = 0;
		for (int y = 0; y < r.getHeight(); y++) { //���� �ϳ��� ���Ͽ�
			for (int x = 0; x < r.getWidth(); x++) {
				total += r.getSample(r.getMinX() + x, r.getMinY() + y, 0); //����ä��
			}
		}
		return (int)(total / ((r.getWidth()/parameter2)*(r.getHeight()/parameter2)));  //
	}

	/////////////////////////////////////////////////////////////////////////////////
	//��ũ�� ĸ��
	private BufferedImage captureScreen(){
		Robot robot = null;
		Rectangle area = null;
		BufferedImage bufImage = null;
		try{
			robot = new Robot();
			area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			bufImage = robot.createScreenCapture(area);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bufImage;
	}
	/////////////////////////////////////////////////////////////////////////////////
	
	public void run(){
		System.out.println("������ ����");
		try{
			while(true){
				match = true;
				img1 = captureScreen();	//1. ��� 
				System.out.println("1.���");
				Thread.sleep(3000);		//2. 5����
				System.out.println("2. �޽� ��");
				img2 = captureScreen();	//3. �ι�° ���� ���
				System.out.println("3. �� ���");
				compare();				//4. ��
				System.out.println("4. �� �Ϸ� ��");
				System.out.println("5. ��ġ ��� : " + match); //5. ��� ǥ��

				if(match == false){
					new ImageClient("ScreenShot");
				}
			}
		}catch(Exception e){
			System.out.println("������ ����");
		}
	}
	
	//�̹��� ������ ó��
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