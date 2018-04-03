package qqqunliao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServerThread implements Runnable{
	Socket s=null;
	BufferedReader br=null;
	public  ServerThread(Socket s) {
		this.s=s;
		try {
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		String content=null;
		
		while((content=readFromClient())!=null) {
			for(Socket s:Mysever.socketList) {
				try {
					PrintStream ps=new PrintStream(s.getOutputStream());
					ps.println(content);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private String readFromClient() {
		// TODO Auto-generated method stub
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Mysever.socketList.remove(s);	
		}
		return null;
	}
}
