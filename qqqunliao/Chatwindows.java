package qqqunliao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.*;

public class Chatwindows extends JFrame implements Runnable{
	Chatwindows ct=this;
	String message;JTextArea jtar;
	private Socket sk;
	BufferedReader br=null;
	private Severice s;
	private ActionListener actionListener = null;
	private JTextArea jr;
	private JTextField jf;
	private JButton jb;
	private String name;
	public Chatwindows(String name,Socket sk) {
		this.sk=sk;
		this.name=name;
		try {
			br=new BufferedReader(new InputStreamReader(sk.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		init();
	}
	public  synchronized void init() {
		setTitle(name);
		s=new Severice();
		jr=new JTextArea(50,50);
		jf=new JTextField(20);
		jb=new JButton("·¢ËÍ");
		this.setLayout(null);
		jr.setEditable(false);
		jr.setBounds(0, 0, 400, 400);
		jf.setBounds(0, 420, 300, 40);
		jb.setBounds(340, 420, 60, 40);
		add(jr);
		add(jf);
		add(jb);
		jb.addActionListener(getActionListener());
		setVisible(true);
		setBounds(480,400,420,520);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String content=null;
		try {
			while((content=br.readLine()).length()>0) {
				jr.append(name+"Ëµ: "+content+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener() {
		
				public void actionPerformed(ActionEvent e) {
					
					ct.sentMessage2();
				}
			};
		}
		return actionListener;
	}
//	public void setmessage(String message,JTextArea jtar,String name) {
//		this.message=message;
//		this.jtar=jtar;
//		
//	}
	public void sentMessage2() {
		try {
		
			PrintStream ps=new PrintStream(sk.getOutputStream());
			String line=null;
			while((line=jf.getText()).length()>0) {
				ps.println(line);
				jf.setText("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		//Chatwindows c=new Chatwindows();
	}
}
