package Flowerprocessing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import sun.print.resources.serviceui;
public class FuncImageFinal {
	static int multiplierFactor=0;
	static int returnIndex =0;
	static boolean returnFlag = false;
	private static Semaphore s = new Semaphore(0);
	public static void bufferedImageShow(ArrayList<ArrayList<HashMap>> imageList,HashMap imgNames,final int outputIndex) {
		JFrame fBack = new JFrame("Output images");
		ArrayList<HashMap> h = imageList.get(0);
		multiplierFactor=h.size();
		fBack.setSize(((220*multiplierFactor)+100)+100, 840);
		fBack.setResizable(false);
		fBack.setLayout(null);
		fBack.setBounds(25,25,(220*multiplierFactor)+100, 840);
		ImagePanel imp=new ImagePanel("C:/Users/Marwa's/Downloads/gurleen project/suns.jpg");
		fBack.getContentPane().add(imp);
		JPanel frame = new JPanel();
		frame.setBackground(new Color(0,0,0,0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		multiplierFactor=h.size();
		frame.setSize((220*multiplierFactor)+100, 840);
		frame.setLayout(gridBagLayout);
		h = imageList.get(0);
		multiplierFactor=h.size();

		JPanel panel1 =  new JPanel();
		panel1.setBackground(new Color(0,0,0,0));
		GridBagLayout gbpT = new GridBagLayout();
		panel1.setSize((220*multiplierFactor)+100, 280);
		panel1.setLayout(gbpT);

		JPanel tpanel0 =  new JPanel();
		tpanel0.setBackground(new Color(0,0,0,0));
		GridBagLayout tGbp1 = new GridBagLayout();
		tpanel0.setSize((220*multiplierFactor)+100, 60);
		tpanel0.setLayout(tGbp1);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel title0 = new JLabel();
		gbc.gridx = 0;

		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.ipady = 1;

		title0.setHorizontalAlignment(JLabel.CENTER);
		title0.setText("Results on the basis of color");
		title0.setFont((new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)));
		tpanel0.add(title0,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(tpanel0,gbc);
		for(int x=0;x<h.size();x++)
		{
			HashMap temp = h.get(x);
			Mat mat=new Mat();
			mat=Highgui.imread(temp.get("name").toString());
			ImageScaler myObj = new ImageScaler();
			BufferedImage bufferedImage = FuncImageFinal.mat2BufferedImage(mat);
			Image dimg = bufferedImage.getScaledInstance(150, 150,Image.SCALE_SMOOTH);
			bufferedImage = myObj.toBufferedImage(dimg);
			ImageIcon icon = new ImageIcon(bufferedImage);

			JLabel lbl = new JLabel();
			gbc.gridx = x;
			gbc.gridy = 0;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lbl.setSize(150,150);
			lbl.setIcon(icon);
			panel1.add(lbl,gbc);
			JLabel lblName = new JLabel();
			gbc.gridx = x;
			gbc.gridy = 1;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lblName.setSize(150,50);
			lblName.setHorizontalAlignment(JLabel.CENTER);

			lblName.setText(imgNames.get(temp.get("label")).toString());

			panel1.add(lblName,gbc);
		}
		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.add(panel1,gbc);
		
		h = imageList.get(1);
		multiplierFactor=h.size();

		JPanel panel2 =  new JPanel();
		panel2.setBackground(new Color(0,0,0,0));
		GridBagLayout gbp2 = new GridBagLayout();
		panel2.setSize((220*multiplierFactor)+100, 280);
		panel2.setLayout(gbp2);

		JPanel tPanel2 =  new JPanel();
		tPanel2.setBackground(new Color(0,0,0,0));
		tGbp1 = new GridBagLayout();
		tPanel2.setSize((220*multiplierFactor)+100, 60);
		tPanel2.setLayout(tGbp1);

		JLabel title2 = new JLabel();
		gbc.gridx = 0;

		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.ipady = 1;

		title2.setHorizontalAlignment(JLabel.CENTER);
		title2.setText("Results on the basis of color & shape");
		title2.setFont((new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)));
		tPanel2.add(title2,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.add(tPanel2,gbc);
		for(int x=0;x<h.size();x++)
		{
			HashMap temp = h.get(x);
			Mat mat=new Mat();
			mat=Highgui.imread(temp.get("name").toString());
			ImageScaler myObj = new ImageScaler();
			BufferedImage bufferedImage = FuncImageFinal.mat2BufferedImage(mat);
			Image dimg = bufferedImage.getScaledInstance(150, 150,Image.SCALE_SMOOTH);
			bufferedImage = myObj.toBufferedImage(dimg);
			ImageIcon icon = new ImageIcon(bufferedImage);

			JLabel lbl = new JLabel();
			gbc.gridx = x;
			gbc.gridy = 0;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lbl.setSize(150,150);
			lbl.setIcon(icon);
			panel2.add(lbl,gbc);
			JLabel lblName = new JLabel();
			gbc.gridx = x;
			gbc.gridy = 1;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lblName.setSize(150,50);
			lblName.setHorizontalAlignment(JLabel.CENTER);

			lblName.setText(imgNames.get(temp.get("label")).toString());

			panel2.add(lblName,gbc);




		}
		gbc.gridx = 0;
		gbc.gridy = 4;
		frame.add(panel2,gbc);

		h = imageList.get(1);
		multiplierFactor=h.size();

		JPanel panel3 =  new JPanel();
		panel3.setBackground(new Color(0,0,0,0));
		GridBagLayout gbp3 = new GridBagLayout();
		panel3.setSize((220*multiplierFactor)+100, 280);
		panel3.setLayout(gbp3);

		JPanel tpanel3 =  new JPanel();
		tpanel3.setBackground(new Color(0,0,0,0));
		tGbp1 = new GridBagLayout();
		tpanel3.setSize((220*multiplierFactor)+100, 60);
		tpanel3.setLayout(tGbp1);

		JLabel title3 = new JLabel();
		gbc.gridx = 0;

		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.ipady = 1;

		title3.setHorizontalAlignment(JLabel.CENTER);
		title3.setText("Final output");
		title3.setFont((new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)));
		tpanel3.add(title3,gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		frame.add(tpanel3,gbc);
		
			HashMap temp = h.get(outputIndex);
			Mat mat=new Mat();
			mat=Highgui.imread(temp.get("name").toString());
			ImageScaler myObj = new ImageScaler();
			BufferedImage bufferedImage = FuncImageFinal.mat2BufferedImage(mat);
			Image dimg = bufferedImage.getScaledInstance(150, 150,Image.SCALE_SMOOTH);
			bufferedImage = myObj.toBufferedImage(dimg);
			ImageIcon icon = new ImageIcon(bufferedImage);

			final JLabel lbl = new JLabel();
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lbl.setSize(150,150);
			lbl.setIcon(icon);
			panel3.add(lbl,gbc);
			JLabel lblName = new JLabel();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.ipadx = 10;
			gbc.ipady = 1;
			lblName.setSize(150,50);
			lblName.setHorizontalAlignment(JLabel.CENTER);

			lblName.setText(imgNames.get(temp.get("label")).toString());

			panel3.add(lblName,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		frame.add(panel3,gbc);
		
		frame.setVisible(true);
		fBack.setVisible(true);
		fBack.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fBack.add(frame);
		System.out.println("Before semaphore");
		
		System.out.println(returnIndex);
	}
	public static BufferedImage mat2BufferedImage(Mat mat) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (mat.channels() > 1)
			type = BufferedImage.TYPE_3BYTE_BGR;
		int bufferSize = mat.channels() * mat.cols() * mat.rows();
		byte[] b = new byte[bufferSize];
		mat.get(0, 0, b);
		BufferedImage bufferedImage = new BufferedImage(mat.cols(),	mat.rows(), type);
		final byte[] targetPixels =((DataBufferByte)bufferedImage.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return bufferedImage;
	}
}
