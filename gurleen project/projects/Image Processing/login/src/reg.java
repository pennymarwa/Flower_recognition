
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


public class reg extends JFrame implements ActionListener{
	JPasswordField t2;
	JLabel t,l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JButton b1,b2;
	Choice  c,c6,c9,c91,c92,c7,c94,ca;
	TextField t1,t8,ta;
	//Checkbox c2,c3;
	//CheckboxGroup c1;
	String s1,s2,s3,s4,s7,s9,s10,s8,sa,sb;
	TextArea a;
	ImagePanel l31=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/123456.jpg");
		public reg(String s){
	/*this.setVisible(true);
		this.setLayout(null);
		this.setSize(1500,1500);
		this.getContentPane().setBackground(Color.black);*/
			sb=s;
		  this.setVisible(true);
			//this.setLayout(null);
			this.setSize(2000,2000);
			 l31.setBounds(0, 0, 2000, 2000);
				getContentPane().add(l31);
		l1=new JLabel("Editing form");
		l1.setBounds(580, 10, 600, 50);
		l1.setForeground(Color.gray);
		Font f1=new Font("times new roman",Font.PLAIN,45);
		l1.setFont(f1);
		l31.add(l1);
		 l2=new JLabel("Name:");
		l2.setBounds(520, 100, 100, 40);
		l2.setForeground(Color.black);
		Font f2=new Font("times new roman",Font.HANGING_BASELINE,25);
		l2.setFont(f2);
		l31.add(l2);
		 l3=new JLabel("Username:");
		l3.setBounds(520, 150, 130, 40);
		l3.setForeground(Color.black);

		
		l3.setFont(f2);
		l31.add(l3);
		t=new JLabel(sb);
		t.setBounds(650, 160,200, 25);
		l31.add(t);
		 t1=new TextField();
		t1.setBounds(650, 110,200, 25);
		l31.add(t1);
		 l4=new JLabel("Password:");
		l4.setBounds(520, 200, 130, 40);
		l4.setForeground(Color.black);

		
		l4.setFont(f2);
		l31.add(l4);
		t2=new JPasswordField();
		t2.setBounds(650, 210,200, 25);
		l31.add(t2);
		 l5=new JLabel("D.O.B:");
		l5.setBounds(520, 250, 90, 40);
		l5.setForeground(Color.black);

		
		l5.setFont(f2);
		l31.add(l5);
		c6=new Choice();
		c6.setBounds(700,260,70,50);
		c6.add("Month");
		c6.add("jan");
		c6.add("feb");
		c6.add("March");
		c6.add("April");
		c6.add("May");
		c6.add("june");
		c6.add("july");
		c6.add("August");
		c6.add("sept");
		c6.add("Oct");
		c6.add("Nov");
		c6.add("Dec");
		l31.add (c6);
	
		 c7=new Choice();
		c7.setBounds(770,260,70,50);
		c7.add("year");
		for(int j=1991;j<=1994;j++)
		   {
			   c7.add(String.valueOf(j));
		   }
		l31.add (c7);
		c9=new Choice();
		c9.setBounds(650,260,50,50);
		c9.add("DAY");
		for(int j=1;j<=31;j++)
		   {
			   c9.add(String.valueOf(j));
		   }
		l31.add(c9);
	    l6=new JLabel("Gender:");
		l6.setBounds(520, 300, 100, 40);
		l6.setForeground(Color.black);

		
		l6.setFont(f2);
		l31.add(l6);
	/*	 c1=new CheckboxGroup();
		 c2=new Checkbox("Male",c1,false);
		c3=new Checkbox("Female",c1,false);
		c2.setBounds(650,305,150,40);
		c3.setBounds(800,305,170,40);
		c2.setForeground(Color.black);
		c3.setForeground(Color.black);
		l31.add(c2);
		l31.add(c3);*/
		 ca=new Choice();
			ca.setBounds(650,305,200, 25);
			ca.add("I am... ");
			ca.add("Male");
			ca.add("Female");
			l31.add(ca);
		 l7=new JLabel("Address:");
		l7.setBounds(520, 350, 100, 40);
		l7.setForeground(Color.black);

		
		l7.setFont(f2);
		l31.add(l7);
		 a=new TextArea();
		a.setBounds(650,360,250,90);
		l31.add(a);
		 l8=new JLabel("Reason:");
		l8.setBounds(520, 470, 130, 40);
		l8.setForeground(Color.black);
		l8.setFont(f2);
		l31.add(l8);
		 c=new Choice();
		c.setBounds(650, 480,200, 25);
		c.add("Personal ");
		c.add("Professional");
		l31.add(c);
		 l9=new JLabel("Mobile No:");
		l9.setBounds(520, 520, 130, 40);
		l9.setForeground(Color.black);
		l9.setFont(f2);
		l31.add(l9);
		 b2=new JButton("BACK");
			b2.setBounds(550,620,130,35);
			b2.setForeground(Color.BLUE);
			b2.setBackground(Color.YELLOW);
			b2.setFont(f2);
			l31.add(b2);
			b2.addActionListener(this);
		/* c10=new Choice();
			c10.setBounds(650, 530,200, 25);
			c10.add("Semester");
			c10.add("3");
			c10.add("5");
			c10.add("7");
			c10.add("8");
			l31.add(c10);*/
			ta=new TextField();
			ta.setBounds(650, 530,200, 25);
			l31.add(ta);
			b1=new JButton("Submit");
		b1.setBounds(720,620,130,35);
		b1.setForeground(Color.BLUE);
		b1.setBackground(Color.YELLOW);
		b1.setFont(f2);
		l31.add(b1);
		b1.addActionListener(this);
		
				
}

public static void main(String args[])
{
	new reg("");
}


public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource()==b1){
		
	 s1=t.getText();
	sa=ta.getText();
			s2=t1.getText();

				s3=t2.getText();
		 s4=c6.getSelectedItem().concat("/").concat(c9.getSelectedItem()).concat("/").concat(c7.getSelectedItem());
			 s7=ca.getSelectedItem();
			 s8=a.getText();
			
			 s9=c.getSelectedItem();
			//s10=c10.getSelectedItem();
			Connection con;
			Statement smt;
			ResultSet rs;
			if(a.getText().equals("")||ca.getSelectedItem().equals("I am... ")||t.getText().equals("") ||t1.getText().equals("")||t2.getText().equals("")||c6.getSelectedItem().equals("Month")||c7.getSelectedItem().equals("year")||c9.getSelectedItem().equals("DAY")||ta.getText().equals(""))
       {
				JOptionPane.showMessageDialog(null,"All fields are necessary to fill"); 
				
			}

			
		else	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
				smt=con.createStatement();
				smt.executeUpdate("Update rgstudent Set Name='"+s2+"', Password='"+t2.getText()+"',Gender='"+s7+"',Address='"+s8+"',Course='"+s9+"',Semester='"+sa+"' where Username='"+t.getText()+"'");
				
				
				
					//,D.O.B='"+s4+"'
				 JOptionPane.showMessageDialog(null,"Profile updated succesfully");
				dispose();
				new Welcome(""+sb+""); 
		//		new adminhome();

			
			}
			
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,ex.getMessage()); 
			}


		
		}					
			}
	if(arg0.getSource()==b2)
	{   
		dispose();
		new Welcome(""+sb+"");
		//new Welcome();
	}
}
	
}
