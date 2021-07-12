package Communication;


import java.io.*;
import java.net.*;
import javax.swing.*;

import Controller.Manager;
import MessageHandler.MsgHandler;

//������ ����ϱ� ���� Ŭ����
public class Transit extends Thread{
	public static BufferedReader br;
	public static BufferedWriter bw;
	
	public Transit(){
		try{
			Socket s = new Socket("localhost", 20000);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		}catch(Exception e){
			JOptionPane.showMessageDialog(Manager.LOGINFORM, "������ ���� ���� �Դϴ�.", "Server Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//������ �޽��� �����ϴ� �޼���
	public static void sendMsg(String msg){
		System.out.println("Client To Server msg : " + msg);
		try{
			bw.write(msg + "\n");
			bw.flush();
		}catch(IOException ioe){
			ioe.getStackTrace();
		}
	}
	
	//�����κ��� �޽����� �޴� �޼���
	public void run(){
		try{
			String line = null;
			
			while((line = br.readLine())!=null){
				System.out.println("������ ���� ���� : " + line);
				new MsgHandler(line);
			}
		}catch(Exception e){
			System.exit(0);
		}
	}
}