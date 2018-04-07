package test2;
import java.util.Arrays;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;



public class withOutBlurring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary( "opencv_java2410" );
		Mat rgbImage = Highgui.imread("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (23).jpg");
		   
		//mat gray image holder
		Mat imageGray = new Mat();
		//mat canny image
		Mat imageCny = new Mat();
		
		int[] DB_dominantColor={4,4,4,2,2,3,3,0,2,2,2,2,2,4,4,3,3,3,2,3,3,4};
		int[] DB1=new int[9];
		//System.out.println("DB_dominantColor[22]="+DB_dominantColor[3]);
		
		//resize input image
		
		Imgproc.resize( rgbImage, rgbImage, new Size(760,540));
		
		//Show the RGB Image
		FuncImage.bufferedImageShow(rgbImage, "RGB Image");

		//Convert the image in to gray image single channel image
		Imgproc.cvtColor(rgbImage, imageGray, Imgproc.COLOR_BGR2GRAY);
		//Show the gray image
		FuncImage.bufferedImageShow(imageGray, "Gray Image");

		//Canny Edge Detection
		Imgproc.Canny(imageGray, imageCny, 10, 100, 3, true);
		//Show the Canny Edge detector image
		FuncImage.bufferedImageShow(imageCny, "Canny Edge Detection Image");
		
		//int histSize=256;		
		//Mat histImg=new Mat();
		//List<Mat> imagesList=new ArrayList<>();
		//Imgproc.calcHist();
		
		///calculate histogram for b,g and r planes
		Vector<Mat> bgr_planes = new Vector<>();
		Core.split(rgbImage, bgr_planes);
		MatOfInt histSize = new MatOfInt(256);
		final MatOfFloat histRange = new MatOfFloat(0f, 256f);
		boolean accumulate = false;
		Mat b_hist = new Mat();
		Mat g_hist = new Mat();
		Mat r_hist = new Mat();
		Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist,histSize, histRange, accumulate);
		Imgproc.calcHist(bgr_planes, new MatOfInt(1), new Mat(), g_hist,histSize, histRange, accumulate);
		Imgproc.calcHist(bgr_planes, new MatOfInt(2), new Mat(), r_hist,histSize, histRange, accumulate);
		
		/*int hist_w = 512;
		int hist_h = 600;
		long bin_w;
		bin_w = Math.round((double) (hist_w / 256));
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);
		Core.normalize(b_hist, b_hist, 3, histImage.rows(), Core.NORM_MINMAX);
		for (int i = 1; i < 256; i++) {
		Core.line(
		histImage,
		new Point(bin_w * (i - 1), hist_h
		- Math.round(b_hist.get(i - 1, 0)[0])),
		new Point(bin_w * (i), hist_h
		- Math.round(Math.round(b_hist.get(i, 0)[0]))),
		new Scalar(255, 0, 0), 2, 8, 0);
		}
		FuncImage.bufferedImageShow(histImage, "Histogram");*/
		//Scalar normFactor((double)255);
		
		//b_hist=b_hist/normFactor;
		//Core.divide(b_hist, Scalar(255.0), b_hist);
		for(int i=0;i<b_hist.rows();i=i+1)
		{
			double[] x=b_hist.get(i,0);
			//double y=x[0]/255;
			//System.out.println("b_hist["+i+"]"+b_hist.get(i,0));
			System.out.println("b_hist["+i+"]"+x[0]);
		}
		//find maxPos for b,g and r histogram
		int START_POS=0;			//MACRO
		int END_POS=230;			//MACRO
		int maxPosB=0;
		int maxPosG=0;
		int maxPosR=0;
		double[] x;
		double[] y;
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=b_hist.get(maxPosB,0);
			y=b_hist.get(i,0);
			//System.out.println("x[0]"+x[0]+"\t"+"y[0]"+y[0]);
			if(x[0]<y[0])
			{
				maxPosB=i;
			}
		}
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=g_hist.get(maxPosG,0);
			y=g_hist.get(i,0);
			//System.out.println("x[0]"+x[0]+"\t"+"y[0]"+y[0]);
			if(x[0]<y[0])
			{
				maxPosG=i;
			}
		}
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=r_hist.get(maxPosR,0);
			y=r_hist.get(i,0);
			//System.out.println("x[0]"+x[0]+"\t"+"y[0]"+y[0]);
			if(x[0]<y[0])
			{
				maxPosR=i;
			}
		}
		System.out.println("maxPosB="+maxPosB);
		System.out.println("maxPosG="+maxPosG);
		System.out.println("maxPosR="+maxPosR);
		
		///decide dominant color in the flower
		int dominantColor=-1;
		int WHITE_TH=226;		//MACRO
		int YELLOW__COLOR_TH=7;		//MACRO
		int YELLOW_SEGMENT_TH=50;	//MACRO
		if(maxPosB>WHITE_TH && maxPosG>WHITE_TH && maxPosR>WHITE_TH)
		{
			dominantColor=4;
		}
		else if(Math.abs(maxPosG-maxPosR)<YELLOW__COLOR_TH && Math.abs(maxPosB-maxPosG)>YELLOW__COLOR_TH && Math.abs(maxPosB-maxPosR)>YELLOW__COLOR_TH)
		{
			dominantColor=3;
		}
		else if(maxPosB>maxPosG && maxPosB>maxPosR)
		{
			dominantColor=0;
		}
		else if(maxPosG>maxPosB && maxPosG>maxPosR)
		{
			dominantColor=1;
		}
		else if(maxPosR>maxPosB && maxPosR>maxPosG)
		{
			dominantColor=2;
		}
		
		
		
		System.out.println("dominantColor="+dominantColor);
		
		/////create DB1 based on dominant color
		int DB1_cnt=0;
		for(int index=0;index<22;index=index+1)
		{
			if(dominantColor==DB_dominantColor[index])
			{
				DB1[DB1_cnt]=index+1;
				DB1_cnt++;
			}
		}

		for(int index=0;index<DB1_cnt;++index)
		{
			System.out.println("DB1["+index+"]"+DB1[index]);
		}
		/////DB1 is ready
		/////give one color to all pixels of flower
		//Imgproc.GaussianBlur(rgbImage, rgbImage, new Size(0.8, 0.8), 10.0);
		//FuncImage.bufferedImageShow(rgbImage, "Gaussian Blur");

		Mat segmentedImage = new Mat();
		segmentedImage.create(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
		for(int row=0;row<rgbImage.rows();++row)
		{
			for(int col=0;col<rgbImage.cols();++col)
			{
				x=rgbImage.get(row,col);
				if(dominantColor==0)
				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
					if(x[0]>x[1] && x[0]>x[2])
						segmentedImage.put(row,col,255);
				}
				else if(dominantColor==1)
				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
					if(x[1]>x[0] && x[1]>x[2])
						segmentedImage.put(row,col,255);
				}
				else if(dominantColor==2)
				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
					if(x[2]>x[1] && x[2]>x[0])
						segmentedImage.put(row,col,255);
				}
				else if(dominantColor==3)
				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
					if((Math.abs(x[1]-x[2])<YELLOW_SEGMENT_TH && Math.abs(x[0]-x[1])>YELLOW_SEGMENT_TH && Math.abs(x[0]-x[2])>YELLOW_SEGMENT_TH))
					{
						segmentedImage.put(row,col,255);
					}
				}
			}
		}
		FuncImage.bufferedImageShow(segmentedImage, "segmentedImage");
		
		///find contours in segmented image
		Mat contourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
		Mat largestContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);

		Vector<MatOfPoint> contours = new Vector<>();
		Imgproc.findContours(segmentedImage, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = -1;
        int maxAreaIdx = -1;
        MatOfPoint temp_contour = contours.get(0); //the largest is at the index 0 for starting point
        MatOfPoint largest_contour = contours.get(0);
        Imgproc.drawContours(contourImage,contours, -1, new Scalar(255, 255, 255), -1);
        FuncImage.bufferedImageShow(contourImage, "countourImage");
        
        for (int idx = 0; idx < contours.size(); idx++) 
        {
        	double contourarea = Imgproc.contourArea(contours.get(idx));
            //compare this contour to the previous largest contour found
            if (contourarea > maxArea)
            {
            	maxArea = contourarea;
                maxAreaIdx = idx;
            }

        }
        Imgproc.drawContours(largestContourImage,contours, maxAreaIdx, new Scalar(255, 255, 255), -1);
        FuncImage.bufferedImageShow(largestContourImage, "largestCountourImage");
        
        ////compare shape of largest contour with all images in DB1
        double[] matchFactor=new double[DB1_cnt];
        for(int index=0;index<DB1_cnt;++index)
        {
        	String formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (%d).jpg",DB1[index]);
        	Mat refRgbImage=Highgui.imread(formattedString);
        	Imgproc.resize( refRgbImage, refRgbImage, new Size(760,540));
        	FuncImage.bufferedImageShow(refRgbImage, "refRgbImage");
        	//Imgproc.GaussianBlur(refRgbImage, refRgbImage, new Size(0.8,0.8), 10.0);
    		//FuncImage.bufferedImageShow(refRgbImage, "Gaussian Blur");

        	//segment the ref image
    		Mat refSegmentedImage = new Mat();
    		refSegmentedImage.create(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
    		for(int row=0;row<rgbImage.rows();++row)
    		{
    			for(int col=0;col<rgbImage.cols();++col)
    			{
    				x=refRgbImage.get(row,col);
    				if(dominantColor==0)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[0]>x[1] && x[0]>x[2])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==1)
    				{
    					if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[1]>x[0] && x[1]>x[2])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==2)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[2]>x[1] && x[2]>x[0])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==3)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if((Math.abs(x[1]-x[2])<YELLOW_SEGMENT_TH && Math.abs(x[0]-x[1])>YELLOW_SEGMENT_TH && Math.abs(x[0]-x[2])>YELLOW_SEGMENT_TH))
    					{
    						refSegmentedImage.put(row,col,255);
    					}
    				}
    			}
    		}
        	//find contours
        	Mat refContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
    		Mat refLargestContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);

    		Vector<MatOfPoint> refContours = new Vector<>();
    		Imgproc.findContours(refSegmentedImage, refContours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            double refMaxArea = -1;
            int refMaxAreaIdx=-1;
            for (int idx = 0; idx < refContours.size(); idx++) 
            {
            	double contourarea = Imgproc.contourArea(refContours.get(idx));
                //compare this contour to the previous largest contour found
                if (contourarea > refMaxArea)
                {
                	refMaxArea = contourarea;
                	refMaxAreaIdx = idx;
                }
            }
            Imgproc.drawContours(refLargestContourImage,refContours, refMaxAreaIdx, new Scalar(255, 255, 255), -1);
            FuncImage.bufferedImageShow(refLargestContourImage, "refLargestCountourImage");
            
            //match shapes
            //for(int cnt=0;cnt<500;cnt++);
            matchFactor[index]=Imgproc.matchShapes(contours.get(maxAreaIdx),refContours.get(refMaxAreaIdx),Imgproc.CV_CONTOURS_MATCH_I3,0);
            System.out.println("matchFactor["+index+"]"+matchFactor[index]);
        	
        }
        ///create DB2
        //sort matchFactor array and take lowest 3 values
        int[] DB2=new int[9];
        int DB2_cnt=0;
        double[] sortedMatchFactor=Arrays.copyOf(matchFactor,DB1_cnt);
        double temp;
        for(int cnt=0;cnt<DB1_cnt;++cnt)
        {
        	for(int cnt1=cnt+1;cnt1<DB1_cnt;++cnt1)
        	{
        		if(sortedMatchFactor[cnt]>sortedMatchFactor[cnt1])
        		{
        			temp=sortedMatchFactor[cnt];
        			sortedMatchFactor[cnt]=sortedMatchFactor[cnt1];
        			sortedMatchFactor[cnt1]=temp;
        		}
        	}
        	//Arrays.sort(sortedMatchFactor);
        }
        for(int cnt1=0;cnt1<DB1_cnt;++cnt1)
        {
        	System.out.println("sortedMatchFactor["+cnt1+"]="+sortedMatchFactor[cnt1]);
        }
        //check for zeros
        for(int cnt=0;cnt<DB1_cnt;++cnt)
        {
        	if(matchFactor[cnt]==0)
        	{
        		DB2[DB2_cnt]=DB1[cnt];
        		System.out.println("DB2["+DB2_cnt+"]="+DB2[DB2_cnt]);    			
        		DB2_cnt++;
        	}
        }
        if(DB2_cnt<3)
        {
	        for(int cnt=DB2_cnt;DB2_cnt<3;++cnt)
	        {
	        	for(int cnt1=0;cnt1<DB1_cnt;++cnt1)
	        	{	
	        		if(sortedMatchFactor[cnt]==matchFactor[cnt1])
	        		{
	        			DB2[DB2_cnt]=DB1[cnt1];
	        			System.out.println("DB2["+DB2_cnt+"]="+DB2[DB2_cnt]);
	        			DB2_cnt++;
	        			break;
	        		}
	        	}
	        }
        
        }
        ///DB2 is ready
        ////compare pistal/stamen for DB2
        for(int DB2_index=0;DB2_index<DB2_cnt;++DB2_index)
        {
        	String formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (%d).jpg",DB2[DB2_index]);
        	Mat refRgbImage=Highgui.imread(formattedString);
        	Imgproc.resize( refRgbImage, refRgbImage, new Size(760,540));
        	FuncImage.bufferedImageShow(refRgbImage, "refRgbImage");
        	//Imgproc.GaussianBlur(refRgbImage, refRgbImage, new Size(0.8,0.8), 10.0);
    		//FuncImage.bufferedImageShow(refRgbImage, "Gaussian Blur");

        	//segment the ref image
    		Mat refSegmentedImage = new Mat();
    		refSegmentedImage.create(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
    		for(int row=0;row<rgbImage.rows();++row)
    		{
    			for(int col=0;col<rgbImage.cols();++col)
    			{
    				x=refRgbImage.get(row,col);
    				if(dominantColor==0)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[0]>x[1] && x[0]>x[2])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==1)
    				{
    					if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[1]>x[0] && x[1]>x[2])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==2)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if(x[2]>x[1] && x[2]>x[0])
    						refSegmentedImage.put(row,col,255);
    				}
    				else if(dominantColor==3)
    				{	if(x[0]<END_POS && x[1]<END_POS && x[2]<END_POS)
    					if((Math.abs(x[1]-x[2])<YELLOW_SEGMENT_TH && Math.abs(x[0]-x[1])>YELLOW_SEGMENT_TH && Math.abs(x[0]-x[2])>YELLOW_SEGMENT_TH))
    					{
    						refSegmentedImage.put(row,col,255);
    					}
    				}
    			}
    		}
        	//find contours
        	Mat refContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
    		Mat refLargestContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);

    		Vector<MatOfPoint> refContours = new Vector<>();
    		Imgproc.findContours(refSegmentedImage, refContours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            double refMaxArea = -1;
            int refMaxAreaIdx=-1;
            for (int idx = 0; idx < refContours.size(); idx++) 
            {
            	double contourarea = Imgproc.contourArea(refContours.get(idx));
                //compare this contour to the previous largest contour found
                if (contourarea > refMaxArea)
                {
                	refMaxArea = contourarea;
                	refMaxAreaIdx = idx;
                }
            }
            Imgproc.drawContours(refLargestContourImage,refContours, refMaxAreaIdx, new Scalar(255, 255, 255), -1);
            Rect rect = Imgproc.boundingRect(refContours.get(refMaxAreaIdx));

            // draw enclosing rectangle (all same color, but you could use variable i to make them unique)
            Core.rectangle(refLargestContourImage, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(255, 255, 255), 3); 
            Rect RectPistal=new Rect((int)(rect.x+rect.width*(0.34)),(int)(rect.y+rect.height*(0.34)),rect.width/3,rect.height/3);
            
            Mat pistalRgbImage = new Mat(refRgbImage,RectPistal);
            FuncImage.bufferedImageShow(pistalRgbImage, "pistalRgbImage");
            
            
            
            
            FuncImage.bufferedImageShow(refLargestContourImage, "rectRefLargestCountourImage");
            
        	
        }




		
		
		
		
		}
	}



