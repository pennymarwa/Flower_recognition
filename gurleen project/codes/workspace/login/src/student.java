import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


public class student extends JFrame implements ActionListener{
	JButton b1,b2,b4,b5,b6;
	TextField t1,t3;
	JLabel l4;
	String s1,s2,s3,s4;
	JPasswordField t8;
	ImagePanel l12=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/1234a.jpg");

	public student()
	{   
		this.setVisible(true);
		//this.setLayout(null);
		this.setSize(2000,2000);
		 l12.setBounds(0, 0, 2000, 2000);
			getContentPane().add(l12);	
			/*JLabel l1=new JLabel("WELCOME TO GNDEC");
			l1.setBounds(280, 50,1000, 100);
			Font f=new Font("times new roman",Font.ITALIC,75);
			l1.setFont(f);
			l12.add(l1);
			l1.setForeground(Color.red);*/
		JLabel l2=new JLabel("Username:");
		l2.setBounds(500, 570, 160, 20);
		Font f2=new Font("times new roman",Font.PLAIN,30);
		l2.setFont(f2);
		l2.setForeground(Color.black);
		l12.add(l2);
		JLabel l3=new JLabel("Password:");
		l3.setBounds(500,600, 160, 20);
		Font f3=new Font("times new roman",Font.PLAIN,30);
		l3.setFont(f3);
		l3.setForeground(Color.black);
		l12.add(l3);
		 t3=new TextField();
		 t3.setBounds(700,570,200,25);
		 l12.add(t3);
		 t8=new JPasswordField();
		 t8.setBounds(700,600,200,25);
		 l12.add(t8);
		 b1=new JButton("Sign in");
		 b1.setBounds(500,630,150,30);
		 Font f5=new Font("times new roman",Font.PLAIN,15);
			b1.setFont(f5);
			l12.add(b1);
			b1.addActionListener(this);
			/* l4=new JLabel("In case you lost your login id or password please contact your Department.");
			 l4.setForeground(Color.magenta);
			 l4.setBounds(380,420,800,35);
			 Font f9=new Font("times new roman",Font.HANGING_BASELINE,25);
				l4.setFont(f9);
				l12.add(l4);*/
				b4=new JButton("Sign up");
				b4.setBounds(685, 630,150, 30);
				Font f8=new Font("times new roman",Font.PLAIN,15);
				b4.setFont(f8);
				l12.add(b4);
				b4.addActionListener(this);
				b5=new JButton("Admin Login");
				b5.setBounds(860, 630,150, 30);
				//Font f8=new Font("times new roman",Font.PLAIN,25);
				b5.setFont(f8);
				l12.add(b5);
				b5.addActionListener(this);
				b6=new JButton("Forgot Password");
				b6.setBounds(330, 630,150, 30);
				//Font f8=new Font("times new roman",Font.PLAIN,25);
				b6.setFont(f8);
				l12.add(b6);
				b6.addActionListener(this);
				
			 
	}
public static void main(String args[])
{
	
	new student();
	
	
}
public void actionPerformed(ActionEvent arg0) {
	
	if(arg0.getSource()==b1)
	{
		Connection con;
		Statement smt;
	    ResultSet rs;
	    if(t3.getText().equals("") ||t8.getText().equals(""))
	    {
	    	JOptionPane.showMessageDialog(null,"Fields are necessary to fill");
	    }
	    else{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
			smt=con.createStatement();
		    rs=smt.executeQuery("Select * from rgstudent where Username='"+t3.getText()+"'and Password='"+t8.getText()+"'");
 if(rs.next())
		    {
	 
	 dispose();
	new Welcome(t3.getText());
		
			}
			else
				 JOptionPane.showMessageDialog(null, "Incorrect username and Password");
			t3.setText("");
			t8.setText("");
			}	
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	}
	if(arg0.getSource()==b5)
	{
		Connection con;
		Statement smt;
	    ResultSet rs;
	    if(t3.getText().equals("") ||t8.getText().equals(""))
	    {
	    	JOptionPane.showMessageDialog(null,"Fields are necessary to fill");
	    }
	    else{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
			smt=con.createStatement();
		    rs=smt.executeQuery("Select * from admin where username='"+t3.getText()+"'and password='"+t8.getText()+"'");
 if(rs.next())
		    {
	 
	 dispose();
	new admin(t3.getText());
		
			}
			else
				 JOptionPane.showMessageDialog(null, "Incorrect username and Password for admin login");
			t3.setText("");
			t8.setText("");
			}	
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	}
	if(arg0.getSource()==b4)
	{
		dispose();
		new reg_student();
	}
	if(arg0.getSource()==b6)
	{
		Connection con;
		Statement smt;
	    ResultSet rs;
	    if(t3.getText().equals(""))
	    {
	    	JOptionPane.showMessageDialog(null,"Please fill your username to know your password");
	    }
	    else{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
			smt=con.createStatement();
		    rs=smt.executeQuery("Select * from rgstudent where Username='"+t3.getText()+"'");
 if(rs.next())
		    {
	 
	 dispose();
	//new Welcome(t3.getText());
	new passlost();	
			}
			else
				 JOptionPane.showMessageDialog(null, "Incorrect username");
			t3.setText("");
			t8.setText("");
			}	
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
}
		 
}


