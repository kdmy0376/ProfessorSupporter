package Server;

import java.net.*;
import java.util.*;

import Database.DBConnection;
import Database.DBTable;

public class Server extends Thread{
	//���ǿ� �����ϴ� ���� ����ϴ� �޼���
	public Vector<Guest> guestlist;
	
	//�� �̸��� �濡 �����ϴ� ��������ϴ� �޼���
	HashMap<String, Vector<Guest>> roomForGuest;
	
	//�濡 ���� �����ϴ� �޼���
	HashMap<String, String[]> roomInfo;
	
	//���Ͽ��� ����ϴ� �޼���
	public Server(){
		this.initializeMember();
		this.createDatabase();
	}
	
	public void run(){
		try{
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(20000);
			while(true){
				System.out.println("[����] Ŭ���̾�Ʈ �����");
				Socket s = ss.accept();
				System.out.println("[����] Ŭ���̾�Ʈ ����");
				Guest g = new Guest(s, this);
				g.start();
			}
		}catch(Exception e){
			System.out.println("���� ����");
		}
	}
	
	//������� �ʱ�ȭ
	private void initializeMember(){
		guestlist = new Vector<Guest>();
		roomForGuest = new HashMap<String, Vector<Guest>>();
		roomInfo = new HashMap<String, String[]>();
	}
	
	//���� Ŭ���̾�Ʈ�� ��ǻ�� �ڸ�ǥ��
	public void occupyComputer(String msg){	//��ǻ��ǥ��/id/pw/pcNumber/��������/
		System.out.println("���� Ŭ���̾�Ʈ���� ��ǻ���ڸ� ǥ��  : " + msg);	
		for(Guest g : guestlist){
			if(g.isProfessor == true){
				g.sendMsg(msg);
			}
		}
	}
	
	//�����ͺ��̽� ���� �� ���̺� ����
	private void createDatabase(){
		new DBConnection();
		new DBTable();
	}
	
	//�����ϴ� �ο��� ���� ��������Ʈ�� �߰� �� ���� ��ȭâ�� ����޽��� ����ϴ� �޼���
	public void addGuest(Guest g){
		guestlist.add(g);
		broadcast("��������/" + g.alias);
	}
	
	//���� ��������� ���Ͽ� �� �߰�
	public void addRoom(String roomname){
		roomForGuest.put(roomname, new Vector<Guest>());
	}
	
	//�濡 �������� �� ���� ��������Ʈ�� ���� �߰� �� �� ���� ���� �ο� üũ�ϴ� �޼���
	public boolean addRoomguest(Guest g, String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		String[] r = roomInfo.get(roomname);

		int limit = Integer.parseInt(r[1]);
		if(rguest.size() >= limit){
			g.sendMsg("JoinRoom/������ �� á���ϴ�.");
			return true;
		}
		else{
			rguest.add(g);
			return false;
		}
	}
	
	//���� ��ȭâ�� �޽��� ����ϴ� �޼���
	public void broadcast(String msg){
		System.out.println("broadcast : " + msg);
		for(Guest g : guestlist){
			g.sendMsg(msg);
		}
	}
	
	//���� ��������� ���� ����Ʈ�� ���
	public void broadcastGuestlist(){
		String s = "���ǼմԸ��/";
		for(Guest g : guestlist){
			s += g.alias + "/";
		}
		broadcast(s);
	}
	
	//������ �ִ� Ư���� �������� �޽��� ����ϴ� �޼���
	public void broadcastProfessor(String msg){	
		System.out.println("���� Ŭ���̾�Ʈ���� ���� : " + msg);	
		for(Guest g : guestlist){
			if(g.isProfessor == true){
				g.sendMsg(msg);
			}
		}
	}
	
	//�ڽ��� ������ ����������鿡�� �޽��� ���
	public void broadcastExceptMe(String msg){	//��ü����/�޽���/�������
		String[] temp = msg.split("/");
		System.out.println("��ü���� ���� : " + msg);	
		for(Guest g : guestlist){
			if(g.alias.equals(temp[2])){}
			else{ g.sendMsg(msg); }
		}
	}
	
	//������ �ִ� Ư���� �������� �޽��� ����ϴ� �޼���
	public void broadcastonlysomeone(String msg){	// ���ǱӼӸ�/msg/sender/receiver, ����/jtp_NoteForm_text.getText()/sender/receiver
		String[] temp = msg.split("/");				// 1:1��ȭ/msg/sender/receiver,  �������ۼ���/senderIPaddr/sender/receiver
		System.out.println("boradcastwhisper : " + msg);	// 1:1��ȭ��û/���� 1:1��ȭ�� ��û�ϼ̽��ϴ�.\n�����Ͻðڽ��ϱ�?/sender/receiver
		for(Guest g : guestlist){		
			if(g.alias.equals(temp[3])){
				g.sendMsg(msg + "/ ");
			}
			else if(temp[0].equals("���ǱӼӸ�") && g.alias.equals(temp[2])){
				g.sendMsg(msg + "/�ڱ��ڽ�");
			}
		}
		
		Iterator<Vector<Guest>> iterator = roomForGuest.values().iterator();
		while(iterator.hasNext()){
			for(Guest g : iterator.next()){
				if(g.alias.equals(temp[3])){
					g.sendMsg(msg);
				}
			}
		}
	}
	
	//���� ��ȭâ�� �޽��� ����ϴ� �޼���
	public void broadcastRoom(String msg, String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		if(rguest == null){
			return;
		}
		for(Guest g : rguest){
			g.sendMsg(msg);
		}
	}
	
	//�濡 ������ �ִ� ���� ����� ���� ��������Ʈ�� ����ϴ� �޼���
	public void broadcastRoomguestlist(String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		if(rguest == null){
			return;
		}
		String s = "��մԸ��/";
		for(Guest g : rguest){
			s += g.alias + "/";
		}
		broadcastRoom(s, roomname);
	}
	
	//������ �ִ� ���� ����Ʈ�� ���� �� ����Ʈ�� ����ϴ� �޼���(������̸� ���̸� �տ� [���]ǥ��)
	public void broadcastRoomlist(){
		String s = "����/";
		Iterator<String> iterator = roomForGuest.keySet().iterator();
		
		while(iterator.hasNext()){
			String room = iterator.next();
			Vector<Guest> currentguest = roomForGuest.get(room);
			String[] info = roomInfo.get(room); // captain/limit/roompw
			if(info[2] != null){
				s += "[���] " + room;
			}
			else{
				s += room;
			}
			s += "(" + currentguest.size() + "\\" + info[1] + ")/";
		}
		broadcast(s);	// ����/([���])roomname(currentguest/personlimit)
	}
	
	//�濡 �ִ� Ư�� �������Ը� �޽��� ����ϴ� �޼���
	public void broadcastRoomonlysomeone(String msg, String roomname){ // flag/msg/something/selectedId
		String[] temp = msg.split("/");				
		Vector<Guest> rguest = roomForGuest.get(roomname);
		
		for(Guest g : rguest){
			if(g.alias.equals(temp[3])){
				g.sendMsg(msg);
			}
		}
	}
	
	//ä�� ���α׷��� ������ �ִ� ��� ������ �г����� �����ϴ� �޼���
	public String getallconnectedguest(){
		String s = "";
		for(Guest g : guestlist){
			s += g.alias + "/";
		}
		Iterator<Vector<Guest>> iterator = roomForGuest.values().iterator();
		while(iterator.hasNext()){
			for(Guest g : iterator.next()){
				s += g.alias + "/";
			}
		}
		return s;
	}
	
	//������ ������ �ѱ��� �ʰ� ���� ���������� ��� ���� ������ �� ���� �������� �ѱ�� ���ؼ� �� ������ �����ϴ� �޼���
	public String getNextGuest(String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		return rguest.get(1).alias;
	}
	
	//���� �濡 ������ �ִ� �ο����� �����ϴ� �޼���
	public int getRoomguestNumber(String roomname){
		return roomForGuest.get(roomname).size();
	}
	
	//Ư�� ���� ����, ���� �ο�, ��й�ȣ���� ������ �����ϴ� �޼���
	public String[] getRoomInfo(String roomname){
		return roomInfo.get(roomname);
	}

	//������ ������ �濡 �ʴ��ϱ� ���� ���� ���� ����� ����ϴ� �޼���
	public void guestListForInvite(Guest guest, String roomname){
		String s = "�����������/" + roomname + "/";
		for(Guest g : guestlist){
			s += g.alias + "/";
		}
		guest.sendMsg(s);
	}

	//������ ������ �ѱ�� �޼���
	public void giveGrant(String alias, String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		for(Guest g : rguest){
			g.isCaptain = false;
			if(g.alias.equals(alias)){
				g.isCaptain = true;
			}
			g.sendMsg("����/" + g.isCaptain);
		}
	}
	
	//�ڽ��� �̹� ������ �ִ��� üũ�ϴ� �޼���
	public boolean isConnected(String alias){
		boolean isConnected = false;
		for(Guest g : guestlist){
			if(alias.equals(g.alias))
				isConnected = true;
		}
		Iterator<String> iterator = roomForGuest.keySet().iterator();
		while(iterator.hasNext()){
			Vector<Guest> roomguest = roomForGuest.get(iterator.next());
			for(Guest g : roomguest){
				if(alias.equals(g.alias))
					isConnected = true;
			}
		}
		return isConnected;
	}
	
	//���α׷� ����� ������ ���� ���� ��Ͽ��� �����ϴ� �޼��� �� ���� ��ȭâ�� ���� �޽��� ����ϴ� �޼���
	public void removeGuest(Guest g){
		guestlist.remove(g);
		broadcast("��������/" + g.alias);
	}
	
	//���� �������� ��� ���� �� ��Ͽ��� �����ϴ� �޼���
	public void removeRoom(String roomname){
		roomForGuest.remove(roomname);
	}
	
	//�濡�� ������ �������� �� �� ���� ���� ��Ͽ��� ������ �����ϴ� �޼���
	public void removeRoomguest(Guest g, String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		rguest.remove(g);
	}
	
	//���� �̸��� ���� �����ϴ��� üũ�ϴ� �޼���
	public boolean roomCheck(String roomname){
		Iterator<String> roomlist = roomForGuest.keySet().iterator();
		boolean isRoomExist = false;
		while(roomlist.hasNext()){
			if(roomname.equals(roomlist.next())){
				isRoomExist = true;
				break;
			}
		}
		return isRoomExist;
	}

	//�濡 ������ �ִ� �������� ����� ����ϴ� �޼���
	public void roomGuestList(Guest guest, String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		String s = "������/";
		for(Guest g : rguest){
			s += g.alias + "/";
		}
		guest.sendMsg(s);
	}
	
	//�� ����� �������� ���� ������ �ο��ϴ� �޼���
	public void setCaptain(String roomname){
		Vector<Guest> rguest = roomForGuest.get(roomname);
		for(Guest g : rguest){
			g.sendMsg("����/" + g.isCaptain);
		}
	}
	
	//�� ���鶧 �� ���� ������ �����ϴ� �޼���
	public void setRoomInfo(String roomname, String captain, String limit, String roompw){
		roomInfo.put(roomname, new String[]{captain, limit, roompw});
	}

	//�濡 �����ϰų� ������ ������ ���� ������ �����ϴ� �޼���
	public void updateRoomInfo(String roomname, String captain, String limit, int currentguest){
		String s = "UpdateRoomInfo/" + roomname + "/" + captain + "/" + limit + "/" + currentguest;
		this.broadcastRoom(s, roomname);
	}
}