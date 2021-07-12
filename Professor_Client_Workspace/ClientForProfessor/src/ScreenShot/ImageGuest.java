package ScreenShot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

import Controller.Manager;

public class ImageGuest extends Thread{
	//�̹��� ���� ���� ����
	private ImageServer server = null;
	//��Ʈ�� ����
	ObjectInputStream objectInputStream = null;
	//Ŭ���̾�Ʈ�� ����
	public Socket socket = null;
	
	BufferedImage bufferedImage = null;
	
	//������
	public ImageGuest(Socket socket, ImageServer server){
		this.socket = socket;
		this.server = server;
		try{
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("��Ʈ�� ���� ����");
			
			bufferedImage = ImageIO.read(objectInputStream);
//			if(server.check == false){
				Manager.CAPTUREDSCREEN.setVisible(true);
//				server.check = true;
//			}
			Manager.CAPTUREDSCREEN.showScreenShot(bufferedImage);
			
		}catch(IOException e){
			System.out.println("�̹��� �Խ�Ʈ ������ ����");
			try {
				socket.close();
				objectInputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}