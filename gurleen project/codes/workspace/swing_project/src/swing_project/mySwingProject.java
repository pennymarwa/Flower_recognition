package swing_project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.SwingConstants;

import Flowerprocessing.MyProcessor;

public class mySwingProject {

	private JFrame frame;
	private JTextField textField,t1;
	private JPasswordField t2;
	private JPanel panel;
	private JLabel lblNewLabel,l1,l2;
	private JButton btnProceed;
	HashMap imgNames = new HashMap();
	String Name = "";
	
	ActionListener a = new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			
			Connection con;
			Statement smt;
		    ResultSet rs;
			for(int i1=0;i1<100;i1++){
				String hg= String.format("C:\\Users\\Marwa's\\Documents\\image (%d).jpg",i1);
			if(textField.getText().equals(hg)){
				
				 MyProcessor i = new MyProcessor();
					i.imageProcessor(Name,frame);
			}
			}
			
		    if(t1.getText().equals("") ||t2.getText().equals(""))
		    {
		    	
		    }
		    else{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","");
				smt=con.createStatement();
			    rs=smt.executeQuery("Select * from admin where username='"+t1.getText()+"'and password='"+t2.getText()+"'");
	 if(rs.next())
			    {
		 MyProcessor i = new MyProcessor();
			i.imageProcessor(Name,frame);
		
			
				}
				else
					 JOptionPane.showMessageDialog(null, "Incorrect username and Password for admin approval");
				t1.setText("");
				t2.setText("");
				}	
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
			
			
		}
	};
	private JLabel imageName;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public mySwingProject(JFrame f) {
		System.out.println("Initializing..");
		initialize(f);
		frame.setSize(800,700);
		frame.setBounds(25, 25, 800, 700);
		frame.setVisible(true);
		ImagePanel imp=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/codes/workspace/swing_project/src/swing_project/background.jpg");
		frame.getContentPane().add(imp);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(final JFrame f) {
		
		
		imgNames.put("image (1).jpg", "akila daisy white");
		imgNames.put("image (2).jpg", "calendula");
		imgNames.put("image (3).jpg", "chrysanthemum multicole");
		imgNames.put("image (4).jpg", "dianthus");
		imgNames.put("image (5).jpg", "dimorphotheca");
		imgNames.put("image (6).jpg", "gazania");
		imgNames.put("image (7).jpg", "ice plant");
		imgNames.put("image (8).jpg", "pansy");
		imgNames.put("image (9).jpg", "phlox");
		imgNames.put("image (10).jpg", "sweet william");
		imgNames.put("image (11).jpg", "verbena");
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 656, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		textField.setBounds(10, 6, 750, 20);	
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		panel = new JPanel();
		panel.setBounds(100, 150, 600, 400);
		panel.setBackground(new Color(0,0,0,0));
		panel.setVisible(false);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		
		panel.add(lblNewLabel);
		btnProceed = new JButton("Proceed");
		btnProceed.setBounds(350, 600, 100, 25);
		frame.getContentPane().add(btnProceed);
		btnProceed.setVisible(false);
		btnProceed.addActionListener(a);
		Font f3=new Font("Times New Roman",Font.BOLD,15);
		l1 = new JLabel("admin name");
		l1.setFont(f3);
		l1.setBounds(100, 560, 150, 25);
		frame.getContentPane().add(l1);
		l1.setVisible(false);
		l2 = new JLabel("admin password");
		l2.setFont(f3);
		l2.setBounds(400, 560, 150, 25);
		frame.getContentPane().add(l2);
		l2.setVisible(false);
		t1 = new JTextField();
		t1.setBounds(200, 560, 150, 25);
		frame.getContentPane().add(t1);
		t1.setVisible(false);
		t2 = new JPasswordField();
		t2.setBounds(550, 560, 150, 25);
		frame.getContentPane().add(t2);
		t2.setVisible(false);
		//ImageIcon i = new ImageIcon("Upload");
		JButton btnNewButton = new JButton("Upload");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChose myObj = new fileChose();
				File fp = myObj.pickme();
				try {
					BufferedImage ti = ImageIO.read(fp);
					textField.setText(fp.getAbsolutePath());
					Name = fp.getAbsolutePath();
					Image dimg = ti.getScaledInstance(600, 400,Image.SCALE_SMOOTH);
					ti = myObj.toBufferedImage(dimg);
					panel.setVisible(true);
					btnProceed.setVisible(true);
					l1.setVisible(true);
					l2.setVisible(true);
					t1.setVisible(true);
					t2.setVisible(true);
					lblNewLabel.setIcon(new ImageIcon(ti));
				
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(300, 60, 100,25);

		frame.getContentPane().add(btnNewButton);
		
		//ImageIcon x = new ImageIcon("logout.JPG");
		JButton logout = new JButton("Close");
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 
				f.setVisible(true);
				frame.setVisible(false);
				
				
			}
		});
		logout.setBounds(450, 60, 100,25);

		frame.getContentPane().add(logout);
		
		
		
	}

	
	
	
}
