package ScreenShot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;



public class ImageServer extends Thread{
	Vector<ImageGuest> guestlist;
	ServerSocket serverSocket;
	public static boolean check = false;
	//������
	public ImageServer(){
		guestlist = new Vector<ImageGuest>();
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(20001);
			while(true){
				//Ŭ���̾�Ʈ ���
				System.out.println("[���� Ŭ���̾�Ʈ] Ŭ���̾�Ʈ �����");
				Socket socket = serverSocket.accept();
				System.out.println("[���� Ŭ���̾�Ʈ] Ŭ���̾�Ʈ ����");
				
				ImageGuest imageGuest = new ImageGuest(socket, this);
				imageGuest.start();
			}
		} catch (IOException e) {
			System.out.println("[�̹��� ����] ������ ����");
		}
	}
}