package Controller;


import javax.swing.*;

import Communication.Transit;
import Login.LoginForm;


public class CommandSet{
	//�޴��� Ŭ�������� Ű���� ����
	public CommandSet(String key){
		Manager manager = new Manager();
		
		//Ű���� Join�� �� ���� 
		if(key.equals("Join")){
			//ȸ�� ����â ���
			manager.showNextForm(key);
		}
		//Ű���� Abort�� �� ����
		else if(key.equals("Abort")){
			//�α��� �� ���
			manager.showNextForm(key);
		}
		//Ű���� Login�� ��� ����
		else if(key.equals("Login")){
			//�α��� ���� �ּҾ���
			LoginForm frame = Manager.LOGINFORM;
			
			//�Է��� ���� ����
			String id = frame.getID();
			String pw = frame.getPassword();
			String pcNumber = "������ ��ǻ��";
			
			//������ ����
			System.out.println("[�α��� â - �α��� ��ư] ���̵�, ��й�ȣ�� ������ ����");
			Transit.sendMsg("Login/" + id + "/" + pw + "/" + pcNumber + "/" + "true");
		}
		//Ű ���� Logout�� ��� ����
		else if(key.equals("Logout")){
			//������ Flag���� ������
			Transit.sendMsg("Logout/");
		}
		//Ű ���� MakeRoom�� ��� ����
		else if(key.equals("MakeRoom")){
			//���� ������ ���ͼ� ����
			String roominfo = Manager.MAKEROOMFORM.getInfo();
			//�� ������ ������ ����
			Transit.sendMsg("MakeRoom/" + roominfo);
		}
		//Ű ���� JoinRoom�� ��� ����
		else if(key.equals("JoinRoom")){
			//���̸� ����
			String roomname = Manager.MAINFRAME.getSelectedRoomName();
			//��� ���� ��� ����
			if(roomname.startsWith("[")){
				//�Է��� ��й�ȣ ����
				String roompw = JOptionPane.showInputDialog(Manager.MAINFRAME, "��й�ȣ�� �Է��Ͻʽÿ�.");
				//��� ��ȣ �Է����� �ʾ��� ��� ����
				if(roompw == null){
					JOptionPane.showMessageDialog(Manager.MAINFRAME, "��й�ȣ�� �Է����� �����̽��ϴ�.", "PW Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//������ Flag���� ����
				Transit.sendMsg("JoinRoom/" + roomname.substring(roomname.indexOf("]") + 2) + "/" + roompw);
			}
			else{
				//������ Flag���� ����
				Transit.sendMsg("JoinRoom/" + roomname + "/" + null);
			}
		}
		//Ű ���� Adjustment�� ��� ����
		else if(key.equals("Adjustment")){
			//������ Flag���� ����
			Transit.sendMsg("Adjustment/");
		}
	}
}