package qqqunliao;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class loginWindows extends JFrame{
	File f=new File("info.txt");
	//FileWriter out=new FileWriter("");
	
	JCheckBox remeberPassword=null;
	ItemListener itemListener=null;
	private ActionListener actionListener = null;
	Severice sevice;
	Object ob=this;
	BufferedImage login;
	JTextField userName;
	JPasswordField password;
	JButton lg;
	public loginWindows() {
		super();
		init();
		isRemberPassword();
		setSize(530,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void init() {
		sevice=new Severice();
		lg=new JButton("登录");
		this.setLayout(null);
		JPanel p1=new JPanel();
		p1.setLayout(null);
		userName=new JTextField(10);
		password=new JPasswordField(10);
		loginImage li=new loginImage();
		li.setBounds(0, 0, 550, 200);
		userName.setBounds(200, 230, 200, 25);
		password.setBounds(200, 260, 200, 25);
		lg.setBounds(new Rectangle(200, 340, 100, 30));
		Label a=new Label("账户");
		Label b=new Label("密码");
		a.setBounds(130, 230, 30, 20);
		b.setBounds(130, 260, 30, 20);
		
		 remeberPassword=new JCheckBox();
		JLabel remeberPassword2=new JLabel("记住密码");
		JCheckBox loginAtomic=new JCheckBox();
		JLabel loginAtomic2=new JLabel("自动登录");
		remeberPassword.setBounds(130, 290,30,30);
		remeberPassword2.setBounds(180,290,70,30);
		loginAtomic.setBounds(280, 290, 30, 30);
		loginAtomic2.setBounds(330, 290, 70, 30);
		remeberPassword.addItemListener(getItemListener());
		add(remeberPassword);
		add(remeberPassword2);
		add(loginAtomic);
		add(loginAtomic2);
		//p1.add(new Label("UserName"));
		p1.add(userName);
		//p1.add(new Label("Password"));
		p1.add(password);
		add(li);
		
		//add(p1,BorderLayout.SOUTH);
		add(userName);
		add(password);
		add(lg);
		add(a);
		add(b);
		setResizable(false);
		lg.addActionListener(getActionListener());
	}
	private void closeFrame() {
		this.dispose();
	}
	class loginImage extends JPanel{
		public void paint(Graphics g) {
			try {
				login=ImageIO.read(new File("login.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(login, 0,0, this);
		}
	}
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener() {
				/**
				 * 实现接口中的actionPerformed方法
				 * 
				 * @param e
				 *            ActionEvent
				 * @return void
				 */
				public void actionPerformed(ActionEvent e) {
					sevice.loginCheck(userName.getText(), new String(password.getPassword()),ob);//(userName.getText(),new String(password.getPassword()),this);
					
				}
			};
		}
		return actionListener;
	}
	public ItemListener getItemListener() {
		if(itemListener==null) {
			itemListener=new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					BufferedWriter out=null;
					if(remeberPassword.isSelected()) {
					
					try {
						out=new BufferedWriter(new FileWriter("info.txt"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						out.write(userName.getText()+"-"+new String(password.getPassword()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						out.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					}
					else {
						try {
							out=new BufferedWriter(new FileWriter("info.txt"));
							out.write("-");
							out.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			};
		}
		return itemListener;
	}
	public void isRemberPassword()  {
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("info.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("系统找不到指定的文件");
		}
		String[] a=null;
//		a[0]="";
//		a[1]="";
		String line=null;
		try {
			if((line=in.readLine())!=null) {
				 a=line.split("-");
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			if(a!=null&a.length>=2) {
				remeberPassword.setSelected(true);
				userName.setText(a[0]);
				password.setText(a[1]);
			}
			
		
	}
	public static void main(String[] args) {
		//System.out.println(Mysever.a);
		loginWindows a=new loginWindows();
		//a.isRemberPassword();
		a.setLocation(250, 100);
	}
}

