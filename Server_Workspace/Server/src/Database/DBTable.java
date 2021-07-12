package Database;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTable {
	private Statement createTableStatement = null;

	//������
	public DBTable(){
		this.createTable();	//���̺� ���� �Լ� ȣ��
	} //������ ��

	//���̺� ����
	private void createTable(){
		////////////////////////////////////////////////////////////////////////////////////
		//Table ����
		String tableStructure = 	
				"(ID varchar(100) primary key, " +	//1
				"Password varchar(100), " +			//2
				"Name varchar(100), " +				//3
				"Email varchar(2000), " +			//4
				"Sex varchar(100), " +				//5
				"Homepage varchar(2000), " +		//6
				"Phone varchar(200), " +			//7
				"CellPhone varchar(200), " +		//8
				"Address TEXT, " +					//9
				"Introduction TEXT)";				//10
		////////////////////////////////////////////////////////////////////////////////////

		//���̺� ������ ���� ������		
		String createTableQuery = "create table if not exists Guest";	//Guest ���̺� �������� �ʴٸ� ����        						 

		//���̺� ���� ó��
		try{
			createTableStatement = DBConnection.connection.createStatement();
			createTableStatement.executeUpdate(createTableQuery + tableStructure);	//������ + ���̺� ���� ����
			System.out.println("[DB] ���̺� ����");
		}catch(SQLException e){	
			System.out.println("[DB] ���̺� ���� ����"); 
		}
		//Close �۾�
		try{
			createTableStatement.close();
		}catch(SQLException e){	
			e.printStackTrace(); 
		}
	} //���̺� ���� �Լ� ��
}