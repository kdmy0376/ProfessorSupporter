package Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBWork {

	//���̵� ���� ���� �˻�
	public static boolean isIdInTheExistence(String id){
		Statement findIdStatement = null;
		ResultSet findIdResultSet = null;
		boolean valueToReturn = false;

		//�ش� ���̵� �ش��ϴ� ��� �ʵ� �˻�
		String findPropertyByServiceNameQuery = "select count(*) from Guest where id= '" + id + "'";
		int numberOfId = 0;

		try{
			findIdStatement = DBConnection.connection.createStatement();
			findIdResultSet = findIdStatement.executeQuery(findPropertyByServiceNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdResultSet.next()){ 
				//���̵� �ش��ϴ� Ʃ�� ������ ����
				numberOfId = findIdResultSet.getInt(1);
				//Ʃ�� ������ 1�� �̻��̸�
				if(numberOfId > 0){
					System.out.println("[DB] �ش� ���̵� �̹� ����");
					valueToReturn = true;
				}else{
					System.out.println("[DB] �ش� ���̵� ��� ����");
				}
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return valueToReturn;
	} //���̵� ���� ���� �˻� �Լ� ��

	//���ο� ��� ���
	public static boolean addPeople(String id, String password, String name, String email, String sex,
			String homepage, String phone, String cellPhone, String address, String introduction)
	{
		boolean valueToReturn = false;
		//��� ���� ������
		String insertNewPeopleQuery = "INSERT INTO Guest VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			PreparedStatement insertNewService = DBConnection.connection.prepareStatement(insertNewPeopleQuery);
			insertNewService.setString(1, id);		
			insertNewService.setString(2, password);
			insertNewService.setString(3, name);
			insertNewService.setString(4, email);
			insertNewService.setString(5, sex);
			insertNewService.setString(6, homepage);
			insertNewService.setString(7, phone);
			insertNewService.setString(8, cellPhone);
			insertNewService.setString(9, address);
			insertNewService.setString(10, introduction);
			insertNewService.executeUpdate();
			System.out.println("[DB] ȸ�� ��� �Ϸ�");
			valueToReturn = true;
		}
		catch(SQLException e){
			System.out.println("[DB] ȸ�� ��� ����");
			return false;
		}
		return valueToReturn;
	} //���ο� ��� ��� �Լ� ��

	//���̵�� ��й�ȣ�� ���εǴ��� �˻�
	public static boolean isEqualIdAndPassword(String id, String password){
		Statement findPasswordStatement = null;
		ResultSet findPasswordResultSet = null;
		boolean valueToReturn = false;

		//�ش� ���̵� �ش��ϴ� ��й�ȣ �˻�
		String findPasswordByIdQuery = "select Password from Guest where id= '" + id + "'";
		String resultOfQuery = null;

		try{
			findPasswordStatement = DBConnection.connection.createStatement();
			findPasswordResultSet = findPasswordStatement.executeQuery(findPasswordByIdQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findPasswordResultSet.next()){ 
				//���̵� �ش��ϴ� ��й�ȣ�� ����
				resultOfQuery = findPasswordResultSet.getString(1);
				//ã�� ��й�ȣ�� �α���â�� �Է��� ��й�ȣ�� ������ �˻�
				if(resultOfQuery.equals(password)){
					System.out.println("���̵�� ��й�ȣ�� ��ġ�մϴ�.");
					valueToReturn = true;
				}
			}
			System.out.println("���̵�� ��й�ȣ�� ����ġ�մϴ�.");
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return valueToReturn;
	} //���̵�� ��й�ȣ�� ���εǴ��� �˻��ϴ� �Լ� ��

	//���̵� �ش��ϴ� �̸� ���
	public static String getName(String id){
		Statement findNameStatement = null;
		ResultSet findNameResultSet = null;

		//�ش� ���̵� �ش��ϴ� �̸� �˻�
		String findNameByIdQuery = "select Name from Guest where id= '" + id + "'";
		String resultOfQuery = null;

		try{
			findNameStatement = DBConnection.connection.createStatement();
			findNameResultSet = findNameStatement.executeQuery(findNameByIdQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findNameResultSet.next()){ 
				//���̵� �ش��ϴ� ��й�ȣ�� ����
				resultOfQuery = findNameResultSet.getString(1);
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}

	//���̵�� �̸� ���
	public static String getIdAndName(){
		Statement findIdNameStatement = null;
		ResultSet findIdNameResultSet = null;

		//���̵�� �̸� �˻�
		String findIdNameQuery = "select Id, Name from Guest";
		String resultOfQuery = "";

		try{
			findIdNameStatement = DBConnection.connection.createStatement();
			findIdNameResultSet = findIdNameStatement.executeQuery(findIdNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdNameResultSet.next()){ 
				resultOfQuery += "/" + findIdNameResultSet.getString(1) + "/";
				resultOfQuery += findIdNameResultSet.getString(2);
			}
			System.out.println(resultOfQuery);
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}
	//���̵�� �̸��� �ش��ϴ� ����� �Ӽ�
	public static String getOneManProperty(String id, String name){
		Statement findIdNameStatement = null;
		ResultSet findIdNameResultSet = null;

		//���̵�� �̸� �˻� 6�� �Ӽ� 1,3,4,7,8,9
		String findIdNameQuery = "select Id, Name, Email, Phone, CellPhone, Address from Guest where Id="+ 
				"'" + id + "'" + " and " + "Name=" + "'" + name + "'";
		String resultOfQuery = "";

		try{
			findIdNameStatement = DBConnection.connection.createStatement();
			findIdNameResultSet = findIdNameStatement.executeQuery(findIdNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdNameResultSet.next()){ 
				resultOfQuery += "/���̵� : " + findIdNameResultSet.getString(1) + "/�̸� : ";
				resultOfQuery += findIdNameResultSet.getString(2) + "/�̸��� �ּ� : ";
				resultOfQuery += findIdNameResultSet.getString(3) + "/��ȭ��ȣ : ";
				resultOfQuery += findIdNameResultSet.getString(4) + "/�޴��� ��ȣ : ";
				resultOfQuery += findIdNameResultSet.getString(5) + "/�ּ� : ";
				resultOfQuery += findIdNameResultSet.getString(6);
			}
			System.out.println(resultOfQuery);
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}

	//��� ���̵�� ��������
	public static String getAllIdAndName(){
		Statement findIdNameStatement = null;
		ResultSet findIdNameResultSet = null;

		//���̵�
		String findIdNameQuery = "select Id, Name from Guest";
		String resultOfQuery = "";

		try{
			findIdNameStatement = DBConnection.connection.createStatement();
			findIdNameResultSet = findIdNameStatement.executeQuery(findIdNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdNameResultSet.next()){ 
				resultOfQuery += "/"+findIdNameResultSet.getString(1)+"/";
				resultOfQuery += findIdNameResultSet.getString(2);
			}
			System.out.println("������� : " + resultOfQuery);
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}

	//���̵�� �̸��� �ش��ϴ� ����� �Ӽ�
	public static String getAllProperty(String id, String name){
		Statement findIdNameStatement = null;
		ResultSet findIdNameResultSet = null;

		//10�� �Ӽ�
		String findIdNameQuery = "select * from Guest where Id="+ 
				"'" + id + "'" + " and " + "Name=" + "'" + name + "'";
		String resultOfQuery = "";

		try{
			findIdNameStatement = DBConnection.connection.createStatement();
			findIdNameResultSet = findIdNameStatement.executeQuery(findIdNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdNameResultSet.next()){ 
				resultOfQuery += "/" + findIdNameResultSet.getString(1) + "/";
				resultOfQuery += findIdNameResultSet.getString(2) + "/";
				resultOfQuery += findIdNameResultSet.getString(3) + "/";
				resultOfQuery += findIdNameResultSet.getString(4) + "/";
				resultOfQuery += findIdNameResultSet.getString(5) + "/";
				resultOfQuery += findIdNameResultSet.getString(6) + "/";
				resultOfQuery += findIdNameResultSet.getString(7) + "/";
				resultOfQuery += findIdNameResultSet.getString(8) + "/";
				resultOfQuery += findIdNameResultSet.getString(9) + "/";
				resultOfQuery += findIdNameResultSet.getString(10);
			}
			System.out.println(resultOfQuery);
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}

	//����
	public static boolean deleteInformation(String id){

		String deleteQuery = "delete from guest where Id= '" + id + "'";
		try{
			PreparedStatement deleteStatement = DBConnection.connection.prepareStatement(deleteQuery);
			deleteStatement.executeUpdate();
			return true;
		}
		catch(SQLException sqlException){
			System.out.println("�л����� ���� ����");
		}
		return false;
	} //���õ� ���� ���� �Լ� ��

	//����
	public static boolean updateInformation(String id, String password, String name,	
			String email, String sex, String homepage, String phone, String cellphone,	
			String address, String introduction){

		//Update ������
		String updateQuery = "update guest set Id = ?, Password = ?, Name = ?, Email = ?, Sex = ?," +
				" Homepage = ?, Phone = ?, CellPhone = ?, Address = ?," +
				" Introduction = ? " + "where Id = ?";
		try{
			PreparedStatement updateStatement = DBConnection.connection.prepareStatement(updateQuery);
			updateStatement.setString(1, id);		
			updateStatement.setString(2, password);
			updateStatement.setString(3, name);
			updateStatement.setString(4, email);
			updateStatement.setString(5, sex);
			updateStatement.setString(6, homepage);
			updateStatement.setString(7, phone);
			updateStatement.setString(8, cellphone);
			updateStatement.setString(9, address);
			updateStatement.setString(10, introduction);
			updateStatement.setString(11, id);
			updateStatement.executeUpdate();
			return true;
		}
		catch(SQLException sqlException){
			System.out.println("�л��������� ����");
		}
		return false;
	} //���� �Ӽ��� ���� �Լ� ��

	//���̵�� �̸��� �ش��ϴ� ����� �Ӽ�
	public static String getAllPropertyByName(String name){
		Statement findIdNameStatement = null;
		ResultSet findIdNameResultSet = null;

		//10�� �Ӽ�
		String findIdNameQuery = "select * from Guest where Name=" + "'" + name + "'";
		String resultOfQuery = "";

		try{
			findIdNameStatement = DBConnection.connection.createStatement();
			findIdNameResultSet = findIdNameStatement.executeQuery(findIdNameQuery);
			//ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
			while(findIdNameResultSet.next()){ 
				resultOfQuery += "/" + findIdNameResultSet.getString(1) + "-";
				resultOfQuery += findIdNameResultSet.getString(2) + "-";
				resultOfQuery += findIdNameResultSet.getString(3) + "-";
				resultOfQuery += findIdNameResultSet.getString(4) + "-";
				resultOfQuery += findIdNameResultSet.getString(5) + "-";
				resultOfQuery += findIdNameResultSet.getString(6) + "-";
				resultOfQuery += findIdNameResultSet.getString(7) + "-";
				resultOfQuery += findIdNameResultSet.getString(8) + "-";
				resultOfQuery += findIdNameResultSet.getString(9) + "-";
				resultOfQuery += findIdNameResultSet.getString(10);
			}
			System.out.println(resultOfQuery);
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return resultOfQuery;
	}

}