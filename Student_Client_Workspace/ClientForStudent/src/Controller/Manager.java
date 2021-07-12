package Controller;


import javax.swing.*;

import Chat.MakeRoomForm;
import Join.JoinForm;
import Login.LoginForm;
import MainScreen.MainFrame;
import ScreenShot.CaptureScreen;
import SplashScreen.SplashScreen;

import java.util.*;

//��Ʈ�ѷ� Ŭ����
public class Manager{
	private static HashMap<String, JFrame> commands;
	
	//������ �ּ�
	public static LoginForm LOGINFORM;
	public static MainFrame MAINFRAME;
	public static MakeRoomForm MAKEROOMFORM;
	public static JoinForm JOINFORM;
	public static SplashScreen SPLASHSCREEN;
	public static CaptureScreen captureScreen = null;
	
	static{
		commands = new HashMap<String, JFrame>();
		
		LOGINFORM = new LoginForm();
		MAINFRAME = new MainFrame();
		MAKEROOMFORM = new MakeRoomForm();
		JOINFORM = new JoinForm();
		captureScreen = new CaptureScreen();
		SPLASHSCREEN = new SplashScreen();
		
		commands.put("Login", MAINFRAME);
		commands.put("Abort", LOGINFORM);
		commands.put("MakeRoom", null);
		commands.put("Logout", LOGINFORM);
		commands.put("JoinRoom", null);
		commands.put("Update", null);
	}
	//Ű�� CommandSet���� ����
	public void service(String command){
		if(commands.containsKey(command)){
			new CommandSet(command);
		}
		else{
			
		}
	}
	//Ű�� �ش��ϴ� �������� �����ִ� �޼�
	public void showNextForm(String command){
		if(commands.containsKey(command)){
			commands.get(command).setVisible(true);
		}
	}
}