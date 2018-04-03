package qqqunliao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mysever {
	
	public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(30001);
			while(true) {
				Socket s=ss.accept();
				socketList.add(s);
				new Thread(new ServerThread(s)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器启动失败，端口已经被占用");
		}
		
	}
	
}
