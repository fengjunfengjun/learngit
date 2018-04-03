package qqqunliao;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Severice {
	private static final String URL="jdbc:mysql://localhost:3306/imooc";
	private static final String USER="root";
	private static final String PASSWORD="123";
	private static Connection conn=null;
	
	static {
		//1.加载驱动程序
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		//2.获得数据库的连接
		 try {
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void loginCheck(String name,String password,Object ob) {
		String sql="select password from qq where userName=?";
		String sql2="select loginCheck from qq where userName=?";
		String sql3="update qq set loginCheck =1 where userName=?";
		try {
			PreparedStatement ptmt=conn.prepareStatement(sql);
			PreparedStatement ptmt2=conn.prepareStatement(sql2);
			PreparedStatement ptmt3=conn.prepareStatement(sql3);
			ptmt.setString(1, name);
			ptmt2.setString(1, name);
			ptmt3.setString(1, name);
			ResultSet rs=ptmt.executeQuery();
			if(rs.next()) {
			
				if(rs.getString("password").equals(password)) {
					rs=ptmt2.executeQuery();
					rs.next();
					if(rs.getString("loginCheck").equals("0")) {
					Socket s;
					try {
						s = new Socket("127.0.0.1",30001);
						//new Thread(new Chatwindows(name,s)).start();
						new Thread(new ChatFrame(name,s)).start();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					ptmt3.executeUpdate();
				loginWindows ls=(loginWindows) ob;
				ls.dispose();}
					else {
						JOptionPane.showMessageDialog(null, "用户已经登录", null,JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "密码错误", null,JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "没有该用户", null,JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void sendMessage(String message,JTextArea jtar,String name) {
//		jtar.append(name+"说: "+message+"\n");
//	}
	
	
}
