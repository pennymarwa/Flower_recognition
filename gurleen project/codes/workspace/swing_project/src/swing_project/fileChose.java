package swing_project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;

public class fileChose {
	File pickme()
	{
		JFileChooser f = new JFileChooser();
		if(f.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			try{
				File fl = f.getSelectedFile();
				//String x= fl.getAbsolutePath();
				return fl;
			}
			catch(Exception e)
			{
				System.err.println("Error:"+e);
			}
		}
		return null;		
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
}
