import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


public class passlost extends JFrame implements ActionListener{
	JButton b1,b2,b4,b5,b6;
	TextField t1,t3,t8;
	JLabel l4;
	String s1,s2,s3,s4;
	
	ImagePanel l12=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/1234.jpg");

	public passlost()
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
		JLabel l3=new JLabel("Phone no:");
		l3.setBounds(500,600, 160, 20);
		Font f3=new Font("times new roman",Font.PLAIN,30);
		l3.setFont(f3);
		l3.setForeground(Color.black);
		l12.add(l3);
		 t3=new TextField();
		 t3.setBounds(700,570,200,25);
		 l12.add(t3);
		 t8=new TextField();
		 t8.setBounds(700,600,200,25);
		 l12.add(t8);
		 b1=new JButton("Sign in");
		 b1.setBounds(600,630,150,30);
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
		    rs=smt.executeQuery("Select * from rgstudent where Username='"+t3.getText()+"'and Semester='"+t8.getText()+"'");
 if(rs.next())
		    {
	 
	 dispose();
	new Welcome(t3.getText());
		
			}
			else{
				 JOptionPane.showMessageDialog(null, "Incorrect username and phone number");
			new student();}
			}	
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	}

}
		 
}



