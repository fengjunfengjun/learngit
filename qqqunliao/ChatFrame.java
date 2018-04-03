package qqqunliao;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

import javax.swing.*;

public class ChatFrame extends JFrame implements Runnable{
	public static HashSet<String> hs=new HashSet<>();
	ChatFrame ct=this;
	private WindowListener windowlistener=null;
	String message;JTextArea jtar;
	private Socket sk;
	BufferedReader br=null;
	private Severice s;
	private ActionListener actionListener = null;
	private JTextArea jr;
	private JTextField jf;
	private JButton jb;
	private String name;
	public ChatFrame(String name,Socket sk) {
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
		jb=new JButton("发送");
//		this.setLayout(null);
//		jr.setBounds(0, 0, 400, 400);
//		jf.setBounds(0, 420, 300, 40);
//		jb.setBounds(340, 420, 60, 40);
		add(new JScrollPane(jr));
		JPanel p1=new JPanel();
		p1.add(new JLabel("发送消息"));
		p1.add(jf);
		p1.add(jb);
		add(p1,BorderLayout.SOUTH);
//		add(jf);
//		add(jb);
		jb.addActionListener(getActionListener());
		//jb.registerKeyboardAction(getActionListener(), KeyEvent.VK_ENTER, JComponent.WHEN_IN_FOCUSED_WINDOW);
		setVisible(true);
		setBounds(480,400,420,520);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(getWindowListener());
//		// 将Ctrl+Enter键和"send"关联
//				jf.getInputMap().put(KeyStroke.getKeyStroke('\n'
//					, java.awt.event.InputEvent.CTRL_MASK) , "send");
//				// 将"send"与sendAction关联
//				jf.getActionMap().put("send", actionListener);
		
		jf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int KeyCode=e.getKeyCode();
				if(KeyCode==KeyEvent.VK_ENTER) {
					sentMessage2();
									}
				
			}
		});
		String content=null;
		try {
			while((content=br.readLine()).length()>0) {
				jr.append(content+"\n");
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
				ps.println(name+"说: "+line);
				jf.setText("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public WindowListener getWindowListener() {
		windowlistener=new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
					Connection con=DB.getConnection();
					String sql="update qq set loginCheck =0 where userName=?";
				try {
					PreparedStatement ptmt=con.prepareStatement(sql);
					ptmt.setString(1, name);
					ptmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return windowlistener;
	}
	
	 
	public static void main(String[] args) {
		//Chatwindows c=new Chatwindows();
	}
}

