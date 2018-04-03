package qqqunliao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DB {
	private static final String URL="jdbc:mysql://localhost:3306/imooc?useSSL=false";
	private static final String USER="root";
	private static final String PASSWORD="123";
	private static Connection conn=null;
	
	static {
		//1.������������
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		//2.������ݿ������
		 try {
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		
	}
	public static Connection getConnection() {
		return conn;
	}
	public static void main(String[] args) throws SQLException {
		Statement s=DB.getConnection().createStatement();
		
	}
	
	
}
