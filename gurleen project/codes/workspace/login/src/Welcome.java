import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import swing_project.mySwingProject;


public class Welcome extends JFrame implements ActionListener
{
	String s4;
	JButton b1,b3,b2,ba;
	ImagePanel l12=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/123a.jpg");	
	public Welcome (String s)
	{
		/*this.setLayout(null);
		this.setVisible(true);
		*/
		s4=s;
		this.setSize(1500,1500);
	
		
		 this.setVisible(true);
			//this.setLayout(null)
			this.setSize(2000,2000);
			 l12.setBounds(0, 0, 2000, 2000);
				getContentPane().add(l12);			
				JLabel l1=new JLabel("Welcome  "+s);
		l1.setBounds(380, 50,1000, 100);
		Font f=new Font("times new roman",Font.PLAIN,75);
		l1.setFont(f);
		l12.add(l1);
		l1.setForeground(Color.black);
		b1=new JButton("Update Profile");
		b1.setBounds(530, 290,300, 30);
		Font f2=new Font("times new roman",Font.PLAIN,25);
		b1.setFont(f2);
		l12.add(b1);
		b1.addActionListener(this);
		b2=new JButton("Test Software");
		b2.setBounds(230, 290,300, 30);
		b2.setFont(f2);
		l12.add(b2);
		b2.addActionListener(this);
		b3=new JButton("Delete Profile");
		b3.setBounds(830, 290,300, 30);
		b3.setFont(f2);
		l12.add(b3);
		b3.addActionListener(this);
		ba=new JButton("Logout");
		ba.setBounds(530, 500,300, 100);
		ba.setFont(f2);
		l12.add(ba);
		ba.addActionListener(this);

}

public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource()==b3)
	{
		Connection con;
		Statement smt;
		ResultSet rs;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
			smt=con.createStatement();
			smt.executeUpdate("Delete from rgstudent where Username='"+s4+"'");}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage()); 
		}

		dispose();
		new student();
	}
	else if(arg0.getSource()==b2)
	{
		System.out.println("Action val caught");
		mySwingProject m = new mySwingProject(this);
		
	}
	
	else if(arg0.getSource()==b1)
	{
		dispose();
		new reg(""+s4+""); 
	//	new login();
	}
	else if(arg0.getSource()==ba)
	{
		dispose();
	//	new login();
		new student();
	}
	
}
}