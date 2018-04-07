package test2;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
public class FuncImage {
public static void bufferedImageShow(Mat mat) {
FuncImage.bufferedImageShow(mat, null);
}
public static void bufferedImageShow(Mat mat, String title) {
BufferedImage bufferedImage = FuncImage.mat2BufferedImage(mat);
ImageIcon icon = new ImageIcon(bufferedImage);
JFrame frame = new JFrame(title);
frame.setLayout(new FlowLayout());
frame.setSize(bufferedImage.getWidth(null) + 30,
bufferedImage.getHeight(null) + 50);
JLabel lbl = new JLabel();
lbl.setIcon(icon);
frame.add(lbl);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static BufferedImage mat2BufferedImage(Mat mat) {
int type = BufferedImage.TYPE_BYTE_GRAY;
if (mat.channels() > 1)
	type = BufferedImage.TYPE_3BYTE_BGR;
	int bufferSize = mat.channels() * mat.cols() * mat.rows();
	byte[] b = new byte[bufferSize];
	mat.get(0, 0, b);
	BufferedImage bufferedImage = new BufferedImage(mat.cols(),
	mat.rows(), type);
	final byte[] targetPixels =((DataBufferByte)bufferedImage.getRaster()
	.getDataBuffer()).getData();
	System.arraycopy(b, 0, targetPixels, 0, b.length);
	return bufferedImage;
	}
	}
