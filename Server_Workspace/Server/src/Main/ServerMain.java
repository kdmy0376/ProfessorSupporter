package Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Server.Server;

public class ServerMain {
	//����� ����
	private static void applyLookAndFeel(){
		//����� �ҷ�����
		try{
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			System.out.println("[����] ����� ���� ����");
		}catch(Exception e){
			System.out.println("[����] ����� ���� ����");
			e.printStackTrace();
		}
	} //����� ���� �Լ� ��

	//����
	public static void main(String[] args){
		applyLookAndFeel();	//����� ����
		try{
			new Server().start();						//���� ������ ����
		}catch(Exception e){
			System.out.println("[����] ���� ���ۿ� �����Ͽ����ϴ�.");
			JOptionPane.showMessageDialog(null, "���� ���ۿ� �����Ͽ����ϴ�..", "���� ���� ����", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}