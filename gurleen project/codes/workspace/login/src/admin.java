import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import swing_project.mySwingProject;


public class admin extends JFrame implements ActionListener
{
	String s4;
	JLabel l1;
	JTextField t1;
	JButton b1,b3,b2,ba;
	ImagePanel l12=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/123a.jpg");	
	public admin (String s)
	{
		/*this.setLayout(null);
		this.setVisible(true);
		*/
		
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
		b1=new JButton("Edit this Profile");
		b1.setBounds(230, 290,300, 30);
		Font f2=new Font("times new roman",Font.PLAIN,15);
		Font f3=new Font("times new roman",Font.PLAIN,25);
		b1.setFont(f2);
		l12.add(b1);
		b1.addActionListener(this);
		b2=new JButton("Add Flower");
		b2.setBounds(530, 290,300, 30);
		b2.setFont(f2);
		l12.add(b2);
		b2.addActionListener(this);
		b3=new JButton("Delete this Profile");
		b3.setBounds(830, 290,300, 30);
		b3.setFont(f2);
		l12.add(b3);
		b3.addActionListener(this);
		ba=new JButton("Logout");
		ba.setBounds(530, 500,300, 100);
		ba.setFont(f3);
		l12.add(ba);
		ba.addActionListener(this);
		t1=new JTextField();
		t1.setBounds(680, 250,120, 30);
		l12.add(t1);
		l1=new JLabel("username");
		l1.setBounds(530, 250, 120, 30);
		l1.setFont(f3);
		l12.add(l1);
		s4=t1.getText();
}

public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource()==b3)
	{
		Connection con;
		Statement smt;
		ResultSet rs;
		 if(t1.getText().equals(""))
		    {
		    	JOptionPane.showMessageDialog(null,"Fields are necessary to fill");
		    }
		 else{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
			smt=con.createStatement();
			smt.executeUpdate("Delete from rgstudent where Username='"+s4+"'");
			JOptionPane.showMessageDialog(null,"given username account is deleted");	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage()); 
		}

		}
	}
	else if(arg0.getSource()==b2)
	{
		JOptionPane.showMessageDialog(null,"To add new flowers contact software developers");
		
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