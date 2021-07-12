package MessageHandler;


import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;

import Chat.ChatRoom;
import Chat.ListForm;
import Chat.PrivateChatForm;
import Communication.Transit;
import Controller.Manager;
import FileTransfer.ReceiveFile;
import MainScreen.MainFrame;
import Note.NoteForm;
import Note.NoteReceiveForm;
import ScreenShot.CaptureScreen;
import ScreenShot.ImageClient;

//�����κ��� ���۵� �޽��� ó���ϴ� Ŭ����
public class MsgHandler{
	//������ �� �ּ�
	private static ChatRoom ROOM;
	//1:1��ȭâ �ּ�
	private static PrivateChatForm pcf;
	//��Ʈ�ѷ� Ŭ����
	private Manager manager;
	
	public ImageClient imgClient =null;

	public MsgHandler(String msg){
		manager = new Manager();
		executeMsg(msg);
	}

	private void executeMsg(String msg){
		System.out.println("Client MsgHandler : " + msg);
		//������ �޽����� �Ľ���
		StringTokenizer st = new StringTokenizer(msg, "/");
		//�Ľ��� �͵��� �迭�� ��
		String[] arr = new String[st.countTokens()];
		for(int i = 0; st.hasMoreTokens(); i++){
			arr[i] = st.nextToken();
		}
		//�ߺ�Ȯ�� ó�� ��
		if(arr[0].equals("�ߺ�Ȯ��ó����")){
			if(arr[1].equals("�ߺ�")){
				JOptionPane.showMessageDialog(Manager.JOINFORM, "�ش� ���̵� �̹� �����մϴ�.", "���̵� �ߺ�", JOptionPane.ERROR_MESSAGE);
			}
			else if(arr[1].equals("�ߺ��ƴ�")){
				JOptionPane.showMessageDialog(Manager.JOINFORM, "�ش� ���̵�� ��� �����մϴ�.", "��� ������ ���̵�", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		//ȸ������ ó�� ��
		else if(arr[0].equals("ȸ������ó����")){
			if(arr[1].equals("����")){
				JOptionPane.showMessageDialog(Manager.JOINFORM, "ȸ�� ���Կ� �����Ͽ����ϴ�.", "ȸ�� ���� ����", JOptionPane.INFORMATION_MESSAGE);
				Manager.JOINFORM.dispose();
				Manager.JOINFORM.initializeRegisterForm();
				Manager.JOINFORM.cardLayout.show(Manager.JOINFORM.contentPane, "JoinPolicy");
			}
			else if(arr[1].equals("����")){
				JOptionPane.showMessageDialog(Manager.JOINFORM, "ȸ�� ���Կ� �����Ͽ����ϴ�.", "ȸ�� ���� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
		//Flag������ Login�ΰ�� ����
		else if(arr[0].equals("Login")){
			//���̵�� ��� ��ȣ�� ��ġ�ϴ��� üũ
			System.out.println("��ġüũ");
			boolean isSuccess = false;
			try{	
				isSuccess = Boolean.parseBoolean(arr[1]);
			}catch(Exception e){
				isSuccess = false;
			}
			//��ġ�ϸ� ����
			if(isSuccess){
				System.out.println("��ġ");
				//�α��� â �Ⱥ��̰� �ϱ�
				Manager.LOGINFORM.setVisible(false);
				//�α���â �ڿ�����
				Manager.LOGINFORM.dispose();
				//�ε�ȭ�� ���α׷��� �� ����
				Manager.SPLASHSCREEN.startProgressBar();
				//				//���� �� ����
				//				manager.showNextForm(arr[0]);
			}
			//��� ��ȣ Ʋ���� ��� ���̾�α� ���
			else
				JOptionPane.showMessageDialog(Manager.LOGINFORM, "���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�α��� ����", 	JOptionPane.WARNING_MESSAGE);
		}
		//Flag������ Logout�� ��� ����
		else if(arr[0].equals("Logout")){
			//���� �� ���
			manager.showNextForm(arr[0]);
		}
		//Flag������ ���ǼմԸ���� ��� ����
		else if(arr[0].equals("���ǼմԸ��")){
			System.out.println(msg);
			//���� �� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;

			//���ǿ� ������ �ִ� ���� ��� ����
			String[] waitroomuserlist = userlist(msg);

			//���� �޺� �ڽ��� ���� ��� ���̱�
			for(int i = 1;  i < arr.length; i++){
				frame.getConnectedStudentComboBox().insertItemAt(waitroomuserlist[i-1], i);
			}
			//���� ��� ������ �� �ߺ� �Ŵ� ���� ��� ���� 
			if(frame.getConnectedStudentComboBox().getItemCount() > arr.length){
				for(int i = frame.getConnectedStudentComboBox().getItemCount(); i > arr.length; i--){
					frame.getConnectedStudentComboBox().removeItemAt(i - 1);
				}
			}

			//���� ���� ����Ʈ�� ���� ��� ����
			frame.connectedStudentList.setListData(waitroomuserlist);
		}
		//Flag������ ���Ǵ�ȭ�� ��� ����
		else if(arr[0].equals("���Ǵ�ȭ")){  // ���Ǵ�ȭ/jtf_waitRoom_write.getText()/fontName/fontSize/fontColor
			//���� �� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			System.out.println("���Ǵ�ȭ : " + arr[1]);
			//Flag������ ���� ����
			int templength = frame.getChatContent().getText().length();

			//�޽��� ���� ��ȭâ�� ���
			try{
				frame.getChatContent().getDocument().insertString(templength, arr[1] + "\n", 
						frame.getChatContent().getCharacterAttributes());
			}catch(BadLocationException e){}

			//��Ʈ �̸�, ��Ʈ ũ��, ������� ������ ������ ��������
			if(arr.length != 5){
				frame.getChatContent().setCaretPosition(frame.getChatContent().getText().length());
				return;
			}

			//��Ʈ �̸�, ��Ʈ ũ��, ������� ������ ������ ���� ��ȭâ�� ����� ���� ��Ʈ ������ �°� �ٲ���
			final Style textStyle = frame.getChatContent().getStyledDocument().addStyle("font", null);
			StyleConstants.setFontFamily(textStyle, arr[2]);
			StyleConstants.setFontSize(textStyle, Integer.parseInt(arr[3]));
			frame.getChatContent().getStyledDocument().setCharacterAttributes(templength, 
					arr[1].length(), textStyle, false);

			final Style textColor = frame.getChatContent().getStyledDocument().addStyle("color", null);
			StyleConstants.setForeground(textColor, new Color(Integer.parseInt(arr[4])));
			frame.getChatContent().getStyledDocument().setCharacterAttributes(templength,
					arr[1].length(), textColor, false);
			frame.getChatContent().setCaretPosition(frame.getChatContent().getText().length());
		}
		//Flag������ RoomError�� ��� ����
		else if(arr[0].equals("RoomError")){
			//���̾�α� ���
			JOptionPane.showMessageDialog(Manager.MAKEROOMFORM, arr[1], 
					"MakeRoom Error", JOptionPane.WARNING_MESSAGE);
		}
		//Flag������ ��մԸ���� ��� ����
		else if(arr[0].equals("��մԸ��")){
			System.out.println(msg);
			//�濡 ������ �ִ� ���� ��� ����
			String[] roomguestlist = userlist(msg);
			//���� ���� ����Ʈ�� ���� ��� ����
			ROOM.jlt_Room_ids.setListData(roomguestlist);

			//�� �޺��ڽ��� ���� ��� ����
			for(int i = 0;  i < roomguestlist.length; i++){
				ROOM.jcb_Room_userlist.insertItemAt(roomguestlist[i], i + 1);
			}
			//�� �޺��ڽ��� �ߺ��Ǵ� ���� ����� ������ ���� 
			if(ROOM.jcb_Room_userlist.getItemCount() > arr.length){
				for(int i = ROOM.jcb_Room_userlist.getItemCount(); i > arr.length; i--){
					ROOM.jcb_Room_userlist.removeItemAt(i - 1);
				}
			}			
		}
		//Flag������ MakeRoom�� ��� ����
		else if(arr[0].equals("MakeRoom")){  //	MakeRoom/alias/roomname/personlimit/currentnum
			//�游��� �� �Ⱥ��̰� ��
			Manager.MAKEROOMFORM.setVisible(false);
			//�游��� �� �ڿ�����
			Manager.MAKEROOMFORM.dispose();
			//���� �� �Ⱥ��̰� ��
			//Manager.MAINFRAME.setVisible(false);
			//���� ��ü ����
			ROOM = new ChatRoom(arr[1], arr[2], arr[3], arr[4]);
		}
		//Flag������ ������ ��� ����
		else if(arr[0].equals("����")){  // ����/([���])roomname(currentguest/personlimit)
			//������ �迭�� ����
			String[] temp = new String[arr.length - 1];

			for(int i = 0 ; i < temp.length; i++){
				temp[i] = arr[i+1];
				System.out.println("temp[" + i + "] : " + temp[i]);
			}

			//���� �� ��� ����Ʈ�� �� ��� ����
			Manager.MAINFRAME.getChatRoomList().setListData(temp);
		}
		//Flag������ MakeRoom�� ��� ����
		else if(arr[0].equals("JoinRoom")){	// JoinRoom/captain/roomname/personlimit/currentnum �� �;ߵ�
			//������ �� ���� �� ����
			if(arr.length == 2){
				JOptionPane.showMessageDialog(Manager.MAINFRAME, arr[1], 
						"Join Error", JOptionPane.WARNING_MESSAGE);
			}
			//������ �� ���� �� ����
			else{
				//���� â �Ⱥ��̰� ��
				//Manager.MAINFRAME.setVisible(false);
				//�� ��ü ����
				ROOM = new ChatRoom(arr[1], arr[2], arr[3], arr[4]);
			}
		}
		//Flag������ ���������� ��� ����
		else if(arr[0].equals("��������")){
			//���� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			//���ʷ� ���� ����� ��� ����޽��� ���� ��ȭâ�� ���( ���� ��ȭâ�� �ƹ��� ���� ���ٸ� insertString�� ������ ����)
			if(frame.getChatContent().getText().length() == 0){
				frame.getChatContent().setText("[" + arr[1] + "]���� ���α׷��� �����ϼ̽��ϴ�.\n");
			}
			//�� ���� ��� ���� ���� �۾� 
			else{
				try{
					frame.getChatContent().getDocument().insertString(frame.getChatContent().getText().length(), 
							"[" + arr[1] + "]���� ���α׷��� �����ϼ̽��ϴ�.\n", frame.getChatContent().getCharacterAttributes());
				}catch(BadLocationException e){}
			}
			System.out.println("�������� ó��");
			frame.getChatContent().setCaretPosition(frame.getChatContent().getText().length());
		}
		//Flag������ ���������� ��� ����
		else if(arr[0].equals("��������")){
			//���� �� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			//���� �޽����� ���� ��ȭâ�� ���
			try{
				frame.getChatContent().getDocument().insertString(frame.getChatContent().getText().length(), 
						"[" + arr[1] + "]���� �����ϼ̽��ϴ�.\n", frame.getChatContent().getCharacterAttributes());
			}catch(BadLocationException e){}
			//���� ��ȭâ ��ũ���� �ڵ����� �������� �ϱ� ���ؼ� ĳ�� ��ġ�� �� ���������� ����
			frame.getChatContent().setCaretPosition(frame.getChatContent().getText().length());
		}
		//Flag������ �������� ��� ����
		else if(arr[0].equals("������")){
			//���ʷ� �������� ��� �� ��ȭâ�� ���� �޽��� ���(�� ��ȭâ�� �ƹ��� ���� ���ٸ� insertString�� ������ ����)
			if(ROOM.jtp_Room_chat.getText().length() == 0){
				ROOM.jtp_Room_chat.setText(arr[1] + "���� ���α׷��� �����ϼ̽��ϴ�.\n");
			}
			//�� ���� ��� ���� ���� �۾�
			else{
				try{
					ROOM.jtp_Room_chat.getDocument().insertString(ROOM.jtp_Room_chat.getText().length(), 
							arr[1] + "���� ���α׷��� �����ϼ̽��ϴ�.\n", ROOM.jtp_Room_chat.getCharacterAttributes());
				}catch(BadLocationException e){}
			}
			//�� ��ȭâ ��ũ���� �ڵ����� �������� �ϱ� ���ؼ� ĳ�� ��ġ�� �� ���������� ����
			ROOM.jtp_Room_chat.setCaretPosition(ROOM.jtp_Room_chat.getText().length());
		}
		//Flag������ �������� ��� ����
		else if(arr[0].equals("������")){
			//�� ��ȭâ�� ���� �޽��� ���
			try{
				ROOM.jtp_Room_chat.getDocument().insertString(ROOM.jtp_Room_chat.getText().length(), 
						"[" + arr[1] + "]���� �����ϼ̽��ϴ�.\n", ROOM.jtp_Room_chat.getCharacterAttributes());
			}catch(BadLocationException e){}

			ROOM.jtp_Room_chat.setCaretPosition(ROOM.jtp_Room_chat.getText().length());
		}
		//Flag������ ChangeAlias�� ��� ����
		else if(arr[0].equals("ChangeAlias")){
			JOptionPane.showMessageDialog(Manager.MAINFRAME, arr[1], "Change Alias", JOptionPane.INFORMATION_MESSAGE);
		}
		//Flag������ UpdateRoomInfo�� ��� ����
		else if(arr[0].equals("UpdateRoomInfo")){  //UpdateRoomInfo/roomname/captain/limit/currentguest
			//�� ������ �ٲ𶧸��� title ����
			ROOM.setTitle(arr[2] + "�� ä��â ( ���� : " + arr[1] + " ) " + arr[4] + "/" + arr[3]);
		}
		//Flag������ ���ǱӼӸ��� ��� ����
		else if(arr[0].equals("���ǱӼӸ�")){	// ���ǱӼӸ�/msg/sender/receiver     ���ǱӼӸ�/msg/sender/receiver/�ڱ��ڽ�
			//���� �� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			//�ӼӸ��� �޴� ������ ��ȭâ���� ���
			String whisperString = "[" + arr[2] + "���� ���� �ӼӸ�] ";
			if(arr[4].equals("�ڱ��ڽ�")){
				whisperString = "[" + arr[3] + "���� ������ �ӼӸ�] ";
			}
			try{
				frame.getChatContent().getDocument().insertString(frame.getChatContent().getText().length(), 
						whisperString + arr[1] + "\n", frame.getChatContent().getCharacterAttributes());
			}catch(BadLocationException e){}
			//�ӼӸ��� �޴� ������ ��ȭâ�� ��ũ�ѹٸ� �ڵ����� �����ִ� �۾�
			frame.getChatContent().setCaretPosition(frame.getChatContent().getText().length());
		}
		//Flag������ Error�� ��� ����
		else if(arr[0].equals("Error")){
			JOptionPane.showMessageDialog(Manager.MAINFRAME, arr[1], "Error", JOptionPane.WARNING_MESSAGE);
		}
		//Flag������ ������ ��� ����
		else if(arr[0].equals("����")){	// ����/jtp_NoteForm_text.getText()/sender/receiver
			System.out.println(msg);
			//��Ʈ�� ��ü ����
			NoteForm nf = new NoteForm();
			//Ÿ��Ʋ�� ������ ��� �г��� ����
			nf.setTitle("[" + arr[2] + "]���� ���� ����");
			//��Ʈ���� JTextPane�� �޽��� ���
			nf.jtp_NoteForm_text.setText(arr[1]);
		}
		//Flag������ 1:1��ȭ��û�� ��� ����
		else if(arr[0].equals("1:1��ȭ��û")){	// 1:1��ȭ��û/���� 1:1��ȭ�� ��û�ϼ̽��ϴ�.\n�����Ͻðڽ��ϱ�?/sender/receiver
			//������ ���� ���� ���̾�α� ���
			int x = JOptionPane.showConfirmDialog(Manager.MAINFRAME, "[" + arr[2] + "]" + arr[1], 
					arr[0], JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//Ȯ�� ��ư ���� ��� ����
			if(x == JOptionPane.OK_OPTION){
				//������ �����ߴٴ� �޽��� ����
				Transit.sendMsg("1:1��ȭ����/true/" + arr[3] + "/" + arr[2]);
				//1:1��ȭâ ���
				pcf = new PrivateChatForm(arr[2]);
			}
			//���̿� ��ư ���� ��� ����
			else{
				//������ �ź��ߴٴ� �޽��� ����
				Transit.sendMsg("1:1��ȭ����/false/" + arr[2]);
			}
		}
		//Flag������ 1:1��ȭ������ ��� ����
		else if(arr[0].equals("1:1��ȭ����")){	// 1:1��ȭ����/boolen/receiver/sender
			System.out.println("Client 1:1��ȭ���� : " + msg);
			//��ȭ ������ ��� ����
			if(Boolean.parseBoolean(arr[1])){
				//1:1��ȭ �� ��ü ����
				pcf = new PrivateChatForm(arr[2]);
			}
			//��ȭ �ź��� ��� ���̾�α� ���
			else{
				JOptionPane.showMessageDialog(Manager.MAINFRAME, arr[2] + "���� �����ϼ̽��ϴ�.", 
						"Private Chat Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		//Flag������ 1:1��ȭ�� ��� ����
		else if(arr[0].equals("1:1��ȭ")){	// 1:1��ȭ/msg/sender/receiver
			//1:1��ȭ�� 1:1��ȭâ�� ���
			pcf.jtp_PrivateChatForm_text.setText(pcf.jtp_PrivateChatForm_text.getText() + 
					"[" + arr[2] + "]" + arr[1] + "\n");
			//��ȭâ ��ũ���� �ڵ����� ����
			pcf.jtp_PrivateChatForm_text.setCaretPosition(pcf.jtp_PrivateChatForm_text.getText().length());
		}
		//Flag������ ���ȭ�� ��� ����
		else if(arr[0].equals("���ȭ")){	// ���ȭ/msg/fontName/fontSize/fontColor
			System.out.println("���ȭ : " + arr[1]);
			//�� ��ȭâ�� �ؽ�Ʈ�� ������ ��ġ�� �˾ƿ�
			int templength = ROOM.jtp_Room_chat.getText().length();
			//�޽����� �ؽ�Ʈ�� ������ ��ġ ������ �̾� ���̴� �۾�
			try{
				ROOM.jtp_Room_chat.getDocument().insertString(templength, arr[1] + "\n", 
						ROOM.jtp_Room_chat.getCharacterAttributes());
			}catch(BadLocationException e){}

			//��Ʈ ũ��, ��Ʈ �̸�, ���� �������� ���� ��� ����
			if(arr.length != 5){
				ROOM.jtp_Room_chat.setCaretPosition(ROOM.jtp_Room_chat.getText().length());
				return;
			}

			//��Ʈ ũ��, ��Ʈ �̸�, ���� �������� �ִ� ��� ������ �°� ����� �޽����� �ٲ���
			final Style textStyle = ROOM.jtp_Room_chat.getStyledDocument().addStyle("font", null);
			StyleConstants.setFontFamily(textStyle, arr[2]);
			StyleConstants.setFontSize(textStyle, Integer.parseInt(arr[3]));
			ROOM.jtp_Room_chat.getStyledDocument().setCharacterAttributes(templength, 
					arr[1].length(), textStyle, false);

			final Style textColor = ROOM.jtp_Room_chat.getStyledDocument().addStyle("color", null);
			StyleConstants.setForeground(textColor, new Color(Integer.parseInt(arr[4])));
			ROOM.jtp_Room_chat.getStyledDocument().setCharacterAttributes(templength,
					arr[1].length(), textColor, false);

			//�� ��ȭâ�� ��ũ���� �ڵ����� �������� �ϴ� �۾�
			ROOM.jtp_Room_chat.setCaretPosition(ROOM.jtp_Room_chat.getText().length());
		}
		//Flag������ ��ӼӸ��� ��� ����
		else if(arr[0].equals("��ӼӸ�")){	// ��ӼӸ�/msg/sender/receiver
			System.out.println("Client ��ӼӸ� : " + msg);
			//�޽����� �� ��ȭâ�� ��� 
			try{
				ROOM.jtp_Room_chat.getDocument().insertString(ROOM.jtp_Room_chat.getText().length(), 
						"[" + arr[2] + "���� ���� �ӼӸ�] " + arr[1] + "\n", ROOM.jtp_Room_chat.getCharacterAttributes());
			}catch(BadLocationException e){}
			//�� ��ȭâ�� ��ũ���� �ڵ����� �������� �ϴ� �۾�
			ROOM.jtp_Room_chat.setCaretPosition(ROOM.jtp_Room_chat.getText().length());
		}
		//Flag������ ������ ��� ����
		else if(arr[0].equals("����")){
			//�ڽ��� �������� ������ ����
			ROOM.isCaptain = Boolean.parseBoolean(arr[1]);
		}
		//Flag������ ������������� ��� ����
		else if(arr[0].equals("�����������")){
			//���ǿ� �ִ� �������� ����� ����
			String[] guestlist = new String[arr.length -2];
			for(int i = 1; i < arr.length - 1; i++){
				guestlist[i-1] = arr[i+1];
			}
			//Ÿ��Ʋ�� �ʴ��� ����Ʈ �� ��ü�� ����
			new ListForm(arr[1], "�ʴ�", guestlist);
		}
		//Flag������ �ʴ��� ��� ����
		else if(arr[0].equals("�ʴ�")){	// �ʴ�/msg/roomname/selectedId
			//�ʴ뿡 ���� ������ ���� ���̾�α� ���
			int x = JOptionPane.showConfirmDialog(Manager.MAINFRAME, arr[1], "�ʴ�", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//�ʴ븦 ������ ��� ����
			if(x == JOptionPane.OK_OPTION){
				//Flag������ ������ ����
				Transit.sendMsg("�ʴ����/" + arr[2] + "/" + arr[3]);
			}
		}
		//Flag������ ������ ��� ����
		else if(arr[0].equals("����")){ // ����/msg/roomname/seletedId
			//������ߴٴ� ���̾�α� ���
			JOptionPane.showMessageDialog(Manager.MAINFRAME, arr[1], "����", JOptionPane.INFORMATION_MESSAGE);
			//Flag������ ������ ����(�濡�� �ڽ��� �� �������� �Ͱ� ���� ���)
			Transit.sendMsg("Out/" + arr[2] + "/null");
			//���� ���� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			//���� ���� ��ȭâ ����
			frame.getChatContent().setText("");
			//�� �Ⱥ��̰� ��
			ROOM.setVisible(false);
			//�� �ڿ�����
			ROOM.dispose();
			//���� ���̰� ��
			frame.setVisible(true);
		}
		//Flag������ �������� ��� ����
		else if(arr[0].equals("������")){ // ������/ids
			//�濡 �������ִ� ���� ��� ����
			String[] roomguest = userlist(msg);

			//Ÿ��Ʋ�� �������� ����Ʈ�� ��ü�� ����
			new ListForm(null, "������", roomguest);
		}
		//Flag������ ��ü������ ��� ����
		else if(arr[0].equals("��ü����")){ // ��ü����/Ÿ��/ids
			//���ǿ� �ִ� ������ ����� ����
			String[] allconnectedguest = new String[arr.length-2];
			for(int i = 2; i < allconnectedguest.length + 2; i++){
				allconnectedguest[i-2] = arr[i];
			}
			//Ÿ�Կ� ���� ����Ʈ ���� �̺�Ʈ�� �����ϱ� ���� �޼��� ����
			this.msgType(allconnectedguest, arr[1]);
		}
		//Flag������ ������Ϸ��� ��� ����
		else if(arr[0].equals("������Ϸ�")){
			//���� �� �ּ� ����
			MainFrame frame = Manager.MAINFRAME;
			//���� �� �Ⱥ��̰� ��
			frame.setVisible(true);
		}
		else if(arr[0].equals("�������ۼ���")){	// �������ۼ���/senderIPaddr/sender/receiver
			int x = JOptionPane.showConfirmDialog(null, arr[2] + "���� ������ �������� �մϴ�.\n�����Ͻðڽ��ϱ�?", 
					"Receive File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(x == JOptionPane.YES_OPTION){
				new ReceiveFile(Manager.MAINFRAME, arr[1]);
			}
			else{
				Transit.sendMsg("�������ۼ�������/false/" + arr[2] + "/" + arr[3]);
			}
		}
		//���� �ޱ⸦ �ź� ���� �� ����
		else if(arr[0].equals("�������ۼ�������")){ // �������ۼ�������/false/receiver/sender
			JOptionPane.showMessageDialog(null, arr[2] + "���� �����ϼ̽��ϴ�.", "Send File Error", JOptionPane.WARNING_MESSAGE);
		}
		//��ũ���� ��û�� �޾��� ��
		else if(arr[0].equals("��ũ������û")){
			System.out.println("��ũ�� �� ��û ó��");
			imgClient = new ImageClient("ScreenShot");
		}
		//����ȭ�� ��û�� �޾��� ��
		else if(arr[0].equals("����ȭ���û")){
			System.out.println("����ȭ�� ��û ó��");
			imgClient = new ImageClient("NonStop");
			imgClient.start();
		}
		//����ȭ��������û�� �޾��� ��
		else if(arr[0].equals("����ȭ��������û")){
			System.out.println("����ȭ��������û ó��");
			imgClient.stop();
		}
		//�������� ��û�� �޾��� ��
		else if(arr[0].equals("���������û")){
			System.out.println("�������� ��û ó��");
			Manager.captureScreen.start();
		}
		//�������� ��û�� �޾��� ��
		else if(arr[0].equals("��������")){
			System.out.println("�������� ��û ó��");
			Manager.captureScreen.stop();
		}
		//��ü���� �ޱ�
		else if(arr[0].equals("��ü����")){	//��ü����/�޽���/�������
			System.out.println("��ü���� �ޱ�");
			new NoteReceiveForm(arr[1]).setVisible(true);
		}
		//�����������Ʈǥ��		�����������Ʈǥ��" + "/" + pc��ȣ + result/���� 6��
		else if(arr[0].equals("�����������Ʈǥ��")){	
			System.out.println("�����������Ʈ�� ǥ��");
			//���ǿ� ������ �ִ� ���� ��� ����
			String[] userInformation = userlist(msg);
			//���� ���� ����Ʈ�� ���� ��� ����
			MainFrame frame = Manager.MAINFRAME;
			frame.getInformationOfStudentList().setListData(userInformation);
		}
	}	


	//�迭�� ���� ��� ����
	private String[] userlist(String msg){
		String[] arr = msg.split("/");

		String[] roomguestlist = new String[arr.length - 1];

		for(int i = 1; i < arr.length; i++){
			roomguestlist[i - 1] = arr[i];
		}

		return roomguestlist;
	}

	//Ÿ�Կ� ���� ����Ʈ ���� �̺�Ʈ�� �����ϱ� ���� �޼��� ����
	private void msgType(String[] allconnectedguest, String type){
		if(type.equals("����")){
			new ListForm(null, "����", allconnectedguest);
		}
		else if(type.equals("1:1��ȭ")){
			new ListForm(null, "1:1��ȭ", allconnectedguest);
		}
		else if(type.equals("���� ������")){
			new ListForm(null, "���� ������", allconnectedguest);
		}
	}
}