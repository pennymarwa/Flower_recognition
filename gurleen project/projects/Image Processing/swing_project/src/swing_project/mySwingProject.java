package swing_project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Flowerprocessing.MyProcessor;

public class mySwingProject {

	private JFrame frame;
	private JTextField textField;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnProceed;
	HashMap imgNames = new HashMap();
	String Name = "";
	
	ActionListener a = new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			MyProcessor i = new MyProcessor();
			i.imageProcessor(Name,frame);
			
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
		ImagePanel imp=new ImagePanel("C:/Users/A/Downloads/Image Processing/swing_project/background.jpg");
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
