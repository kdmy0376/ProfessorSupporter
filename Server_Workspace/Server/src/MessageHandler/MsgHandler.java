package MessageHandler;
import java.util.*;

import Database.DBWork;
import Server.Guest;
import Server.Server;

public class MsgHandler{
	private Server server;
	private Guest guest;

	public MsgHandler(Server server, Guest guest){
		this.server = server;
		this.guest = guest;
	}

	private String getAllAlias(){
		String alias = server.getallconnectedguest();
		return alias;
	}

	//Ŭ���̾�Ʈ���� ���� �޽����� ó���ϴ� �޼���
	public void manageMsg(String msg){
		System.out.println("Server MsgHandler : " + msg);

		//���� �޽����� �Ľ���
		StringTokenizer st = new StringTokenizer(msg, "/");
		String[] arr = new String[st.countTokens()];
		for(int i = 0; st.hasMoreTokens(); i++){
			arr[i] = st.nextToken();
		}

		//ȸ������ â���� �ߺ�Ȯ�� ��ư
		if(arr[0].equals("�ߺ�Ȯ��")){	
			//�ش� ���̵� �̹� �ִ��� ������ �˻�
			boolean isDuplicated = DBWork.isIdInTheExistence(arr[1]);
			//���̵� �ߺ�
			if(isDuplicated){
				guest.sendMsg("�ߺ�Ȯ��ó����/�ߺ�");
			}
			//���̵� �ߺ��ƴ�
			else{
				guest.sendMsg("�ߺ�Ȯ��ó����/�ߺ��ƴ�");
			}
		}
		//ȸ������
		else if(arr[0].equals("ȸ������")){
			//�ش� ���̵� �̹� �ִ��� ������ �˻�
			boolean isDuplicated = DBWork.isIdInTheExistence(arr[1]);
			//���̵� �ߺ�
			if(isDuplicated){
				guest.sendMsg("�ߺ�Ȯ��ó����/�ߺ�");
			}
			//���̵� �ߺ��ƴ�
			else{
				if(DBWork.addPeople(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10])){
					guest.sendMsg("ȸ������ó����/����");
				}
				else{
					guest.sendMsg("ȸ������ó����/����");
				}
			}
		}
		//����ȸ������ â���� �ߺ�Ȯ�� ��ư
		if(arr[0].equals("�����ߺ�Ȯ��")){	
			//�ش� ���̵� �̹� �ִ��� ������ �˻�
			boolean isDuplicated = DBWork.isIdInTheExistence(arr[1]);
			//���̵� �ߺ�
			if(isDuplicated){
				guest.sendMsg("�����ߺ�Ȯ��ó����/�ߺ�");
			}
			//���̵� �ߺ��ƴ�
			else{
				guest.sendMsg("�����ߺ�Ȯ��ó����/�ߺ��ƴ�");
			}
		}
		//����ȸ������
		else if(arr[0].equals("����ȸ������")){
			//�ش� ���̵� �̹� �ִ��� ������ �˻�
			boolean isDuplicated = DBWork.isIdInTheExistence(arr[1]);
			//���̵� �ߺ�
			if(isDuplicated){
				guest.sendMsg("�����ߺ�Ȯ��ó����/�ߺ�");
			}
			//���̵� �ߺ��ƴ�
			else{
				if(DBWork.addPeople(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10])){
					guest.sendMsg("����ȸ������ó����/����");
				}
				else{
					guest.sendMsg("����ȸ������ó����/����");
				}
			}
		}
		//���α׷��� �α����ϴ� �۾�
		else if(arr[0].equals("Login")){	// Login/id/pw/pcNumber/��������
			boolean isNull = false;
			System.out.println("Login");

			//�Է����� ���� ������ �ִ��� üũ
			if(arr.length != 5)
				isNull = true;

			//�Է����� ���� ������ ���� ��
			if(isNull){
				guest.sendMsg(arr[0] + "/�Է����� ���� ������ �ֽ��ϴ�.");
			}
			//���̵�� ��й�ȣ�� ��� �Է��Ͽ��� ��
			else{
				//String pw = "";
				String alias = "";

				alias = arr[1];
				String[] all = getAllAlias().split("/");
				boolean isConn = false;
				for(String s: all){
					if(s.equals(alias)){
						isConn = true;
						break;
					}
				}
				if(isConn){
					guest.sendMsg(arr[0] + "/�̹� ���ӵǾ� �ֽ��ϴ�.");
				}
				else{
					boolean isMatch = DBWork.isEqualIdAndPassword(arr[1], arr[2]);
					//���̵�� ��й�ȣ�� ��ġ�Ǹ�
					if(isMatch){
						System.out.println("[����] ����ȭ�� ȣ���� ���� �غ�");
						//Ŭ���̾�Ʈ�� �������ο� ����
						guest.isProfessor = Boolean.parseBoolean(arr[4]);
						//Ŭ���̾�Ʈ�� �ǽ� ��ȣ�� ��ȣ�� ����
						guest.pcNumber = arr[3];
						//Ŭ���̾�Ʈ�� ���̵� ���̵� ����
						guest.id = arr[1];
						//Ŭ���̾�Ʈ�� �г��ӿ� �г����� ����
						if(guest.isProfessor == true){
							guest.alias = alias + "(����)";
						}else{
							guest.alias = alias;
						}
						//���� ���� ���� ��Ͽ� Ŭ���̾�Ʈ �߰�
						server.addGuest(guest);
						//���� ���� ������ ������ ��������Ʈ�� ���
						server.broadcastGuestlist();
						//������ �� ����Ʈ�� ���
						server.broadcastRoomlist();

						if(guest.isProfessor == false){
							//���� Ŭ���̾�Ʈ�� ��ǻ�� �ڸ�ȭ�鿡 ǥ��
							String name = DBWork.getName(guest.id);
							// ��ǻ��ǥ��/id/pw/pcNumber/��������/�̸�
							server.occupyComputer("��ǻ��ǥ��" + "/" + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4] + "/" + name); 
							System.out.println("��ǻ�� �ڸ� ȭ�鿡 ǥ��!!!");
						}

						//���Ƿ� �Ѿ�� ���� �޽��� ����
						guest.sendMsg(arr[0] + "/true");

						System.out.println("[����] ����ȭ�� ȣ���� ���� �غ�");
					}
					//���̵�� ��й�ȣ�� ��ġ���� ������
					else{
						guest.sendMsg(arr[0] + "/false");
					}
				}
			}
		}
		//�α׾ƿ����� �� �α��� ���� ���� ��
		else if(arr[0].equals("Logout")){
			//�α��� ���� ���� ���� �޽��� ����
			guest.sendMsg(arr[0]);

			//���� Ŭ���̾�Ʈ���� ��ǻ�� �ڸ� ����
			server.broadcastProfessor("��ǻ���ڸ�����/"+ guest.pcNumber);

			//������ ���� ��Ͽ��� Ŭ���̾�Ʈ�� ����
			server.removeGuest(guest);
			//������ ���� ����� ������ ���� ����Ʈ�� ���
			server.broadcastGuestlist();
		}
		//���� ��ȭ�� ó���ϴ� �۾�
		else if(arr[0].equals("���Ǵ�ȭ")){ // ���Ǵ�ȭ/jtf_waitRoom_write.getText()/fontName/fontSize/fontColor/toUser
			System.out.println("Server ���Ǵ�ȭ : " + msg);
			//���ǿ� ������ ��� ������� �޽����� ���
			if(arr[5].equals("��ο���")){
				server.broadcast(arr[0] + "/" + "[" + guest.alias + "] " + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4]);
			}
			//�ӼӸ��� �� ����� �ڽ��̸� �����޽��� ���
			else if(arr[5].equals(guest.alias)){
				guest.sendMsg("Error/�ڽſ��� �ӼӸ��� �� �� �����ϴ�.");
			}
			//������ Ư���� �������Ը� �޽��� ���(�Ӹ� ���)
			else
				server.broadcastonlysomeone("���ǱӼӸ�/" + arr[1] + "/" + guest.alias + "/" + arr[5]);
		}
		//�� ����� �۾�
		else if(arr[0].equals("MakeRoom")){     //	MakeRoom/roomname/personlimit/roompw
			//������� �ϴ� �� �̸��� �̹� �����ϴ��� üũ
			if(server.roomCheck(arr[1])){
				guest.sendMsg("RoomError/�̹� �����ϴ� ���Դϴ�.");
			}
			else{
				//Ŭ���̾�Ʈ�� �����̶�� ����
				guest.isCaptain = true;
				//���� ���� �ؽ��ʿ� �߰�
				server.addRoom(arr[1]);
				//��й�ȣ�� �ִ� ���� ����� �۾�
				if(arr.length == 5){
					//�� ������ �ؽ��ʿ� �߰�
					server.setRoomInfo(arr[1], guest.alias, arr[2], arr[4]);
				}
				//��й�ȣ�� ���� ���� ����� �۾�
				else{
					//�� ������ �ؽ��ʿ� �߰�
					server.setRoomInfo(arr[1], guest.alias, arr[2], null);
				}
				//Ŭ���̾�Ʈ�� ���� ���� ��Ͽ��� ����
				server.removeGuest(guest);

				//�� ���� ��Ͽ� �ڽ��� �߰�
				server.addRoomguest(guest, arr[1]);
				//�濡 ���� ������ �ο��� ��� �۾�
				int num = server.getRoomguestNumber(arr[1]);
				//�ڽſ��� �游��µ� �ʿ��� �޽��� ����
				guest.sendMsg("MakeRoom/" + guest.alias + "/" + arr[1] + "/" + arr[2] + "/" + num);

				//������ �濡 ����޽��� ���
				server.broadcastRoom("������/" + "[" + guest.alias + "]", arr[1]);
				//�ڽ��� �������� ����
				server.setCaptain(arr[1]);
				//�� ���� ����� ���� ��������Ʈ�� ���
				server.broadcastRoomguestlist(arr[1]);
				//���� ���� ����� ���� ��������Ʈ�� ���
				server.broadcastGuestlist();
				//���� �� ��ϸ���Ʈ�� �� ����� ���
				server.broadcastRoomlist();
			}
		}
		//�濡 �����ϴ� �۾�
		else if(arr[0].equals("JoinRoom")){ //JoinRoom/roomname/roompw
			//��й�ȣ�� �ִ� �濡 �����ϴ� �۾�
			if(!arr[2].equals("null")){
				//������ �濡 ���� ������ ��� �۾�
				String[] roomInfo = server.getRoomInfo(arr[1]); //roomname/captain/limit/roompw
				//���� ��й�ȣ�� �Է��� ��й�ȣ�� ��ġ�ϴ��� üũ
				if(roomInfo[2].equals(arr[2])){
					//�����ϴ� �۾� ó��
					joinRoom(arr[1]);
				}
				else{
					guest.sendMsg("JoinRoom/��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				}
			}
			//��й�ȣ�� ���� �濡 �����ϴ� �۾�
			else{
				joinRoom(arr[1]);
			}
		}
		//�г��� ���� �۾�
		else if(arr[0].equals("ChangeAlias")){	// ChangeAlias/changedalias/roomname

			String[] allalias = getAllAlias().split("/");

			boolean isaliasexist = false;
			//�̹� ������ �ִ� �г������� üũ
			for(String s : allalias){
				if(s.equals(arr[1])){
					isaliasexist = true;
					break;
				}
			}
			if(isaliasexist){
				guest.sendMsg(arr[0] + "/�̹� ��ϵ� �г����Դϴ�.");
			}
			else{
				try{
					//���ǿ��� �г��� ���� �۾��� �̷����� �� ����
					if(arr[2].equals("WaitingRoomForm")){
						//������ ��ȭâ�� �޼��� ���
						server.broadcast("���Ǵ�ȭ/[" + guest.alias + "]���� �г����� [" + arr[1] + "]�� ����Ǿ����ϴ�.");
						guest.alias = arr[1];
						//���� ��������� ���� ��������Ʈ�� ���
						server.broadcastGuestlist();
					}
					//�� �ȿ��� �г��� ���� �۾��� �̷����� �� ����
					else{
						//���� ��ȭâ�� �޼��� ���
						server.broadcastRoom("���ȭ/[" + guest.alias + "]���� �г����� [" + arr[1] + "]�� ����Ǿ����ϴ�.", arr[2]);
						guest.alias = arr[1];
						//�濡 ������ ���� ����� �� ��������Ʈ�� ���
						server.broadcastRoomguestlist(arr[2]);
					}
					//Ŭ���̾�Ʈ�� �г��ӿ� ����� �г��� ����
					guest.sendMsg(arr[0] + "/�г����� ����Ǿ����ϴ�.");
				}catch(Exception e){
					System.out.println("�г��� ���� ����");
				}
			}
		}
		//���� ������ �۾� ����
		else if(arr[0].equals("����")){	// ����/msg/receiver
			//�ڽſ��� ���� ������ �� ����
			if(arr[2].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� ������ ���� �� �����ϴ�.");
			else
				//������ �������� ���� ������ �޼��� ����
				server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
		}
		else if(arr[0].equals("��ü����")){	//��ü����/�޽���
			server.broadcastExceptMe("��ü����/" + arr[1] + "/" + guest.alias);	//��ü����/�޽���/�������
		}
		//��ũ�� �� ��û
		else if(arr[0].equals("��ũ������û")){	//��ũ������û/receiver
			//�ڽſ��� ��ũ������û ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� ��ũ���� ��û�� �� �� �����ϴ�.");
			else
				//������ �������� ��ũ������û ������ �޼��� ����
				System.out.println("[����] ������ �����ڿ��� ��ũ���� ��û ����");
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[1]);
		}
		//����ȭ�� ��û
		else if(arr[0].equals("����ȭ���û")){	//����ȭ���û/receiver
			//�ڽſ��� ����ȭ���û ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� ����ȭ�� ��û�� �� �� �����ϴ�.");
			else
				//������ �������� ����ȭ���û ������ �޼��� ����
				System.out.println("[����] ������ �����ڿ��� ����ȭ�� ��û ����");
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[1]);
		}
		//�������� ��û
		else if(arr[0].equals("���������û")){	//���������û/receiver
			//�ڽſ��� ���������û ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� �������� ��û�� �� �� �����ϴ�.");
			else
				//������ �������� ���������û ������ �޼��� ����
				System.out.println("[����] ������ �����ڿ��� �������� ��û ����");
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[1]);
		}
		//��������
		else if(arr[0].equals("��������")){	//��������/receiver
			//�ڽſ��� ���������û ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� �������� ��û�� �� �� �����ϴ�.");
			else
				//������ �������� ���������û ������ �޼��� ����
				System.out.println("[����] ������ �����ڿ��� �������� ��û ����");
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[1]);
		}
		//1:1��ȭ��û �۾� ����
		else if(arr[0].equals("1:1��ȭ��û")){	// 1:1��ȭ/receiver
			//�ڽſ��� ���� ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� 1:1��ȭ ��û�� �� �� �����ϴ�.");
			else
				//���õ� �������� 1:1 ��ȭ �������� ���� �޽��� ����
				server.broadcastonlysomeone(arr[0] + "/���� 1:1��ȭ�� ��û�ϼ̽��ϴ�. �����Ͻðڽ��ϱ�?/"  + guest.alias + "/" + arr[1]);
		}
		//1:1 ��ȭ���� �۾� ����
		else if(arr[0].equals("1:1��ȭ����")){	// 1:1��ȭ����/boolean/receiver/sender or 1:1��ȭ����/false/sender
			//1:1��ȭ ���� ���� �� ����
			if(Boolean.parseBoolean(arr[1]))
				server.broadcastonlysomeone(arr[0] + "/true/" + arr[2] + "/" + arr[3]);
			//1:1��ȭ �ź����� �� �ź� �޽��� ����
			else{
				server.broadcastonlysomeone(arr[0] + "/false/������ �����Ͽ����ϴ�./" + arr[2]);
			}
		}
		//1:1��ȭ �۾� ����
		else if(arr[0].equals("1:1��ȭ")){	// 1:1��ȭ/msg/receiver
			//�ڽſ��� �ڽ��� ���� �޽��� ���
			guest.sendMsg(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
			//���濡�� �ڽ��� ���� �޽��� ����
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
		}
		//�濡�� ��ȭ�ϴ� �۾� ����
		else if(arr[0].equals("���ȭ")){		// ���ȭ/msg/fontName/fontSize/fontColor/toUser/roomname
			//�濡 ������ ��� �������� �޽��� ����
			if(arr[5].equals("��ο���")){
				server.broadcastRoom(arr[0] + "/" + "[" + guest.alias + "] " + arr[1] + "/" + arr[2] + "/" + 
						arr[3] + "/" + arr[4], arr[6]);
			}
			//�ڽſ��� �ӼӸ��� ���� �� ��
			else if(arr[5].equals(guest.alias)){
				guest.sendMsg("Error/�ڽſ��� �ӼӸ��� �� �� �����ϴ�.");
			}
			else
				//������ �������� �ӼӸ� ����
				server.broadcastRoomonlysomeone("��ӼӸ�/" + arr[1] + "/" + guest.alias + "/" + arr[5], arr[6]);
		}
		//�ʴ��ϱ� ���� ���� ���� ����Ʈ ��� �۾�
		else if(arr[0].equals("�����������")){
			server.guestListForInvite(guest, arr[1]);
		}
		//�ʴ����� ��� ���� ���� ���� �۾�
		else if(arr[0].equals("�ʴ�")){	// �ʴ�/roomname/selectedId
			server.broadcastonlysomeone(arr[0] + "/�ʴ밡 �Խ��ϴ�. �����Ͻðڽ��ϱ�?/" + arr[1] + "/" + arr[2]);
		}
		//�ʴ� �������� ��� �濡 �����ϴ� �۾� ����
		else if(arr[0].equals("�ʴ����")){	// �ʴ����/roomname/selectedId
			joinRoom(arr[1]);
		}
		//�濡�� �������� �� �ϴ� �۾�
		else if(arr[0].equals("Out")){ // out/roomname/null
			//������ ������ ������ ��� ����
			if(guest.isCaptain){
				//�մ����� ����
				guest.isCaptain = false;
				//�濡 ������ �����ִ� ��� �۾�
				if(server.getRoomguestNumber(arr[1]) > 1){
					//���� ���� ������ ����
					String nextcaptain = server.getNextGuest(arr[1]);
					//���� �������� ���� ���� �ο�
					server.giveGrant(nextcaptain, arr[1]);
					//���� ���  �������� �޽��� ����
					server.broadcastRoom("���ȭ/[" + nextcaptain + "]���� ������ �Ǿ����ϴ�.", arr[1]);
				}
			}
			//�ڽ��� �� ���� ��Ͽ��� ���� 
			server.removeRoomguest(guest, arr[1]);
			//���� �ִ� ������ ���� ��� ����
			if(server.getRoomguestNumber(arr[1]) == 0){
				//�� ��Ͽ��� �� ����
				server.removeRoom(arr[1]);
			}
			else{
				//������ ���� ���� ����
				String[] roomInfo = server.getRoomInfo(arr[1]);
				//����� ������ �����ϴ� �۾�
				server.updateRoomInfo(arr[1], roomInfo[0], roomInfo[1], server.getRoomguestNumber(arr[1]));
			}
			//���� ���� ��Ͽ� �ڽ� �߰�
			server.addGuest(guest);
			//���� ��� �������� ���� �޽��� ����
			server.broadcastRoom("������/" + guest.alias, arr[1]);
			//���� ��������Ʈ�� �� ���� ��� ���
			server.broadcastRoomguestlist(arr[1]);
			//���� ���� ����� ���� ��������Ʈ�� ���
			server.broadcastGuestlist();
			//�� ����� ���� �� ����Ʈ�� ���
			server.broadcastRoomlist();
			//������ �����ֱ����� Flag���� ����
			guest.sendMsg("������Ϸ�/");
		}
		//����ѱ�� �۾� ����
		else if(arr[0].equals("����ѱ��")){	// ����ѱ��/roomname/selectedId
			//������ ������ �ڽ��� ��� ����
			if(guest.alias.equals(arr[2])){
				guest.sendMsg("Error/�ڽſ��� ������ �ѱ� �� �����ϴ�.");
			}
			else{
				//������ �������� ���� ���� �ο�
				server.giveGrant(arr[2], arr[1]);
				//���� ��� �������� �޽��� ����
				server.broadcastRoom("���ȭ/[" + arr[2] + "]���� ������ �Ǿ����ϴ�.", arr[1]);
			}
		}
		//���� �۾� ����
		else if(arr[0].equals("����")){	// ����/roomname/selectedId
			//������ ������ �ڽ��� ��� ����
			if(guest.alias.equals(arr[2]))
				guest.sendMsg("Error/�ڽ��� ���� �� �� �����ϴ�.");
			else
				//������� �������� �޽��� ����
				server.broadcastRoomonlysomeone(arr[0] + "/����Ǿ����ϴ�./" + arr[1] + "/" + arr[2], arr[1]);
		}
		//���� �� ����Ʈ���� �濡 ������ ���� ���� ���� �۾�
		else if(arr[0].equals("������")){
			server.roomGuestList(guest, arr[1]);
		}
		//���α׷��� ������ ��� ������ ������ �۾�
		else if(arr[0].equals("��ü����")){ // ��ü����/Ÿ��
			//������ ��� ���� ����
			String s = server.getallconnectedguest();
			//�޽��� ����
			guest.sendMsg(msg + s);
		}
		//���� �����ϱ� ���� �۾�
		else if(arr[0].equals("��������")){	// ��������/selectedId
			String IPaddr = guest.s.getInetAddress().getHostAddress();
			server.broadcastonlysomeone("�������ۼ���/" + IPaddr + "/" + guest.alias + "/" + arr[1]);
		}
		else if(arr[0].equals("�������ۼ�������")){ // �������ۼ�������/false/sender/receiver
			server.broadcastonlysomeone("�������ۼ�������/" + arr[1] + "/" + arr[3] + "/" + arr[2]);
		}
		//�л�����ȭ�� �޺��ڽ� ������ ���� �۾�
		else if(arr[0].equals("�л�����ȭ���޺��ڽ�")){
			String idAndName = DBWork.getIdAndName();
			guest.sendMsg("�л�����ȭ���޺��ڽ�����" + idAndName);
		}
		//���õ� ��� ���� ǥ�� ----- �������ǥ��/" + id + "/" + userName + "/" + pcNumber
		else if(arr[0].equals("�������ǥ��")){
			String pcNumber = "";
			for(int i=0; i<server.guestlist.size(); i++){
				if((server.guestlist.get(i).id).equals(arr[1])){
					pcNumber = server.guestlist.get(i).pcNumber;
				}
			}
			if(arr[2].equals("����")){
				String name = DBWork.getName(arr[1]);
				String result = DBWork.getOneManProperty(arr[1], name);
				guest.sendMsg("�����������Ʈǥ��" + "/" + "PC ��ȣ : " + pcNumber + result);
			}
			else{
				String result = DBWork.getOneManProperty(arr[1], arr[2]);
				guest.sendMsg("�����������Ʈǥ��" + "/" + "PC ��ȣ : " +pcNumber + result);
			}
		}
		//���� Ŭ���̾�Ʈ ���� �޺��ڽ� ����
		else if(arr[0].equals("�л�����ȭ���޺��ڽ�")){
			String result = DBWork.getAllIdAndName();
			guest.sendMsg("�л�����ȭ���޺��ڽ�����" + result);
		}
		//���� Ŭ���̾�Ʈ ���� �޺��ڽ� ����
		else if(arr[0].equals("�л�����ȭ���޺��ڽ�")){
			String result = DBWork.getAllIdAndName();
			guest.sendMsg("�л�����ȭ���޺��ڽ�����" + result);
		}
		//���� Ŭ���̾�Ʈ �޺��ڽ� Ŭ����				"ȸ������������������/id-name
		else if(arr[0].equals("ȸ������������������")){
			StringTokenizer stz = new StringTokenizer(arr[1], "-");
			String[] temp = new String[stz.countTokens()];
			for(int i = 0; stz.hasMoreTokens(); i++){
				temp[i] = stz.nextToken();
			}
			String result = DBWork.getAllProperty(temp[0], temp[1]);

			guest.sendMsg("ȸ������������������Ϸ�" + result);
		}
		////���� Ŭ���̾�Ʈ �޺��ڽ� Ŭ����				"ȸ������������������/id-name
		else if(arr[0].equals("ȸ������������������")){
			StringTokenizer stz = new StringTokenizer(arr[1], "-");
			String[] temp = new String[stz.countTokens()];
			for(int i = 0; stz.hasMoreTokens(); i++){
				temp[i] = stz.nextToken();
			}
			String result = DBWork.getAllProperty(temp[0], temp[1]);

			guest.sendMsg("ȸ������������������Ϸ�" + result);
		}
		//ȸ���������� Ȯ�ι�ư
		else if(arr[0].equals("ȸ����������")){
			boolean isDelete = DBWork.updateInformation(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], 
					arr[7], arr[8], arr[9], arr[10]);
			if(isDelete){
				guest.sendMsg("ȸ�����������Ϸ�/����");
			}
			else{
				guest.sendMsg("ȸ�����������Ϸ�/����");
			}
		}
		//ȸ���������� Ȯ�ι�ư
		else if(arr[0].equals("ȸ����������")){
			boolean isDelete = DBWork.deleteInformation(arr[1]);
			if(isDelete){
				guest.sendMsg("ȸ�����������Ϸ�/����");
			}
			else{
				guest.sendMsg("ȸ�����������Ϸ�/����");
			}
		}
		//�л��˻�
		else if(arr[0].equals("�л��˻�")){
			String result = null; 
			result = DBWork.getAllPropertyByName(arr[1]);

			if(result != null){
				guest.sendMsg("�л��˻��Ϸ�" + result + "/����");
			}
			else{
				guest.sendMsg("�л��˻��Ϸ�" + result + "/����");
			}
		}
		//�л��˻��޴�������
		else if(arr[0].equals("�л��˻��޴�������")){
			String result = null; 
			result = DBWork.getAllPropertyByName(arr[1]);

			if(result != null){
				guest.sendMsg("�л��˻��޴������ۿϷ�" + result + "/����");
			}
			else{
				guest.sendMsg("�л��˻��޴������ۿϷ�" + result + "/����");
			}
		}
		//����ȭ��������û		����ȭ��������û / ���õȻ��
		else if(arr[0].equals("����ȭ��������û")){
			//�ڽſ��� ����ȭ��������û ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� ����ȭ������ ��û�� �� �� �����ϴ�.");
			else
				//������ �������� ����ȭ��������û ������ �޼��� ����
				System.out.println("[����] ������ �����ڿ��� �������� ��û ����");
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[1]);
		}
		//������â ���̱�
		else if(arr[0].equals("������â���̱�")){
			server.broadcastProfessor("������â���̱�/");
		}
	}

	//�濡 �����ϴ� �۾� ����
	private void joinRoom(String roomname){
		//�� ���� ��Ͽ� �ڽ��� �߰�, ���� ���� �ο��� �Ѿ��� ��� ����
		if(server.addRoomguest(guest, roomname)){
			return;
		}
		//���� ���� ��Ͽ��� �ڽ� ����
		server.removeGuest(guest);
		//������ ���� ���� ����
		String[] roomInfo = server.getRoomInfo(roomname);
		//�����ϴµ� �ʿ��� ���� ����
		guest.sendMsg("JoinRoom/" + roomInfo[0] + "/" + roomname + "/" + server.getRoomguestNumber(roomname) + "/" + roomInfo[1]); 
		//����� ������ �����ϴ� �۾�
		server.updateRoomInfo(roomname, roomInfo[0], roomInfo[1], server.getRoomguestNumber(roomname));
		//���� ��� �������� ���� �޽��� ���
		server.broadcastRoom("������/" + "[" + guest.alias + "]", roomname);
		//������ �� ����Ʈ�� �� ��� ���
		server.broadcastRoomlist();
		//�� ���� ����� �� ���� ����Ʈ�� ���
		server.broadcastRoomguestlist(roomname);
		//���� ���� ����� ���� ���� ����Ʈ�� ��
		server.broadcastGuestlist();
	}
}