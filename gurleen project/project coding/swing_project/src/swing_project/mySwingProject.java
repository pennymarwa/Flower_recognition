package swing_project;

import java.awt.EventQueue;
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


import test2.MyProcessor;

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
			i.imageProcessor(Name);
			
		}
	};
	private JLabel imageName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mySwingProject window = new mySwingProject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mySwingProject() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		textField.setBounds(10, 6, 530, 20);	
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		panel = new JPanel();
		panel.setBounds(26, 42, 600, 350);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		imageName = new JLabel("");
		imageName.setHorizontalAlignment(SwingConstants.CENTER);
		imageName.setBounds(289, 403, 113, 38);
		frame.getContentPane().add(imageName);
		panel.add(lblNewLabel);
		btnProceed = new JButton("Proceed");
		btnProceed.setBounds(300, 445, 89, 23);
		frame.getContentPane().add(btnProceed);
		btnProceed.setVisible(false);
		btnProceed.addActionListener(a);
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChose myObj = new fileChose();
				File fp = myObj.pickme();
				try {
					BufferedImage ti = ImageIO.read(fp);
					textField.setText(fp.getAbsolutePath());
					Name = fp.getAbsolutePath();
					String fName = Name.split("\\\\")[Name.split("\\\\").length-1];
					imageName.setText(imgNames.get(fName).toString());
					Image dimg = ti.getScaledInstance(350, 350,Image.SCALE_SMOOTH);
					ti = myObj.toBufferedImage(dimg);
					btnProceed.setVisible(true);
					lblNewLabel.setIcon(new ImageIcon(ti));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(550, 5, 89, 23);

		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
	}

	
	
	
}