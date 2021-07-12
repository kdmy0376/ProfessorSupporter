package Main;

import javax.swing.*;

import Communication.Transit;
import Controller.Manager;

public class ClientMain{
	//����� ����
	private static void applyLookAndFeel(){
		//����� �ҷ�����
		try{
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			System.out.println("����� ���� ����");
		}catch(Exception e){
			System.out.println("����� ���� ����");
			e.printStackTrace();
		}
	} //����� ���� �Լ� ��
	
	//����
	public static void main(String[] args){
		applyLookAndFeel();	//����� ����
		try{
			new Transit().start();						//������ ����ϴ� ������ ����
			Manager.SPLASHSCREEN.setVisible(true);		//�ε� ȭ�� ���̱�
			Manager.SPLASHSCREEN.createLoginWindow();	//�α��� â ǥ��
		}catch(Exception e){
			JOptionPane.showMessageDialog(Manager.LOGINFORM, "������ ������ ���� �ʽ��ϴ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}