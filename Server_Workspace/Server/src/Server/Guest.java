package Server;

import java.io.*;
import java.net.*;

import MessageHandler.MsgHandler;


public class Guest extends Thread{
	private BufferedReader br;
	private BufferedWriter bw;
	private Server server;
	
	//Ŭ���̾�Ʈ���� �Ѿ���� �޽����� ó���ϱ����� Ŭ����
	private MsgHandler msgHandler;
	
	//Ŭ���̾�Ʈ�� �������� �л����� �˷��ִ� ����
	public boolean isProfessor;
	
	//Ŭ���̾�Ʈ�� �ǽ� ��ȣ
	public String pcNumber;
	
	//Ŭ���̾�Ʈ�� ���̵�
	public String id;
	
	//Ŭ���̾�Ʈ�� �г���
	public String alias;
	
	//Ŭ���̾�Ʈ�� �������� �˷��ִ� ����
	public boolean isCaptain;
	
	
	//Ŭ���̾�Ʈ�� ����
	public Socket s;
	
	public Guest(Socket s, Server server){
		this.server = server;
		this.s = s;
		msgHandler = new MsgHandler(server, this);
		try{
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		}catch(IOException e){
			System.out.println("Guest constructor Error");
		}
	}
	
	//Ŭ���̾�Ʈ�� �޽����� �����ϴ� �޼���
	public void sendMsg(String msg){
		try{
			System.out.println("guest : " + msg);
			bw.write(msg + "\n");
			bw.flush();
		}catch(IOException e){
			System.out.println("Guest To Client");
		}
	}
	
	//Ŭ���̾�Ʈ���� ������ �޽����� ó���ϴ� Ŭ������ �Ѱ��ִ� �޼�
	public void run(){
		try{
			String line = null;
			while((line = br.readLine()) != null){
				msgHandler.manageMsg(line);
			}
		}catch(Exception e){
			System.out.println("Guest Exception");
		}
	}
}