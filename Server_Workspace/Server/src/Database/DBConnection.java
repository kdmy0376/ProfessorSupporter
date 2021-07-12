package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnection {
	//MySQL DBMS
	private static final String URL = "jdbc:mysql://localhost/Guest";	//�����ͺ��̽� �̸� : Guest(3306)
	private static final String USERNAME = "root";              	    //����ڸ� root
	private static final String PASSWORD = "2001";                      //��й�ȣ 2001

	//�����ͺ��̽� ������ �����ϴ� Connection ���� ����
	static Connection connection = null;

	//������
	public DBConnection(){
		this.findDriver();				//Driver ã�� �Լ� ȣ��
		this.createConnectionObject();	//Connection ��ü ���� �Լ� ȣ��
	} //������ ��

	//Driver ã��
	private void findDriver(){
		//����̹� Ŭ������ �˻�
		try{ 
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("[DB] �����ͺ��̽� ����̹� �˻� ����");
		}catch(ClassNotFoundException e){
			//����̹� ã�� ���� �޽��� �ڽ� ����
			System.out.println("[DB] �����ͺ��̽� ����̹� �˻� ����.");
			JOptionPane.showMessageDialog(null, "����̹��� ã�� �� �����ϴ�.", "���α׷� �޽���", JOptionPane.WARNING_MESSAGE);
		}
	} //Driver ã�� �Լ� ��

	//Connection ��ü ����
	private void createConnectionObject(){
		//DB������ �����ϴ� ��ü ����
		try{ 
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("[DB] �����ͺ��̽��� ���� ����");
		}catch(SQLException sqlException){
			//DB���� ���� �޽��� �ڽ� ����
			System.out.println("[DB] �����ͺ��̽��� ���� ����");
			JOptionPane.showMessageDialog(null, "�����ͺ��̽��� ������ �� �����ϴ�.", "���α׷� �޽���", JOptionPane.WARNING_MESSAGE);
		}
	} //Connection ��ü ���� �Լ� ��
}