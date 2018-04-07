
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class front extends JFrame implements WindowListener
{   JProgressBar pb=new JProgressBar();
    JLabel l=new JLabel();
	JButton b2,b3;
	ImagePanel l1=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/projects/Image Processing/backgroundimages/12345.jpg");
	public front()
	{
		
		this.setVisible(true);
		this.setSize(2000,2000);
		l1.setSize(2000,2000);
		add (l1);
		 l1.setBounds(0, 0, 2000, 2000);
			getContentPane().add(l1);

		
		
		this.setVisible(true);
        this.setLayout(null);
		this.setSize(1500,1500);
		this.getContentPane().setBackground(Color.black);
		
		Font f=new Font("Times New Roman",Font.BOLD,50);
		Font f2=new Font("Times New Roman",Font.BOLD,40);
		/*JLabel l2=new JLabel("PUNJAB AGRICULTURAL UNIVERSITY");
		l2.setForeground(Color.black);
		l2.setFont(f);
		l2.setBounds(200, 50,950,70);
		add(l2);
		l1.add(l2);
		JLabel l3=new JLabel("School of Elect. Engg. & Information Technology ");
		l3.setForeground(Color.black);
		l3.setFont(f);
		l3.setBounds(150, 120,1250,70);
		add(l3);
		l1.add(l3);
		JLabel l4=new JLabel("Software for flower species Recognition ");
		l4.setForeground(Color.black);
		l4.setFont(f2);
		l4.setBounds(330, 200,850,70);
		add(l4);
		l1.add(l4);*/
		
		this.setTitle("My Application");
		this.addWindowListener(this);
		pb.setMaximum(100);
		pb.setBounds(250,600,800,20);
		add(pb);
		//b2=new JButton();
		//b2.setBounds(40, 40,100, 100);
	//	l1.add(b2);
		//ImageIcon i1=new ImageIcon(getClass().getResource("C:\\Users\\Marwa's\\Pictures\\logo.jpg"));
	//	b2.setIcon(i1);
		l.setBounds(1100,580,300,50);
		Font f1=new Font("Times New Roman",Font.ITALIC,30);
		l.setFont(f1);
		l.setForeground(Color.black);
		add(l);
		for(int i=0;i<=100;i++)
			
		{
			pb.setValue(i);
			l.setText("Loading "+i+"%");
			l1.add(l);
			if(pb.getValue()>99)
			{   
				
				dispose();
				new student();
			
			}
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				
			}
		
	
		
		
		}
		
	}
	
	public static void main(String args[])
	{
		new front();
		
		
		
	}
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


