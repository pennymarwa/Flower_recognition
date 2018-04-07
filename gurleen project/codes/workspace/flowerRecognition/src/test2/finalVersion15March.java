package test2;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.*;

import java.util.Arrays;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class finalVersion15March{
	static void displayImage(String imageName,String windowName)
	{	Mat displayImg=new Mat();
		displayImg=Highgui.imread(imageName);
		FuncImage.bufferedImageShow(displayImg, windowName);
	}

	public static void main(String[] args) {
		System.loadLibrary( "opencv_java2410" );
		Mat rgbInpImage = Highgui.imread("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (24).jpg");
		      
		//mat gray image holder
		Mat imageGray = new Mat();
		Mat rgbImage=new Mat();
			
		//int[] DB_dominantColor={4,4,4,2,2,3,3,0,2,2,2,2,2,4,4,5,5,5,2,3,3,4};
		int[] DB_dominantColor={0,5,3,2,0,3,6,6,2,2,6,0,6,6,4,3,6,2,2,4,0,5,3,3};
		String[] flowerName={"akila daisy white","calendula","chrysanthemum multicole","dianthus","dimorphotheca","gazania","ice plant","pansy","phlox","sweet william","verbena","acroclinium-roseum","antwerp_hollyhock"
							,"aster","cineraria","Coreopsis_lanceolata","cosmos_flower","dahlia","helichrysum_bracteatum","Linum_perenne","matricaria_recutita","nasturtium","sunflower","Xerochrysum_bracteatum"};
		int[] DB1=new int[9];
		String formattedString,formattedDisplayString;
		//Show the RGB Image
		FuncImage.bufferedImageShow(rgbInpImage, "rgbInpImage");
		//convert to HSV color model
		Imgproc.cvtColor(rgbInpImage, rgbInpImage, Imgproc.COLOR_BGR2HSV);
		//Convert the image in to gray image single channel image
		Imgproc.cvtColor(rgbInpImage, imageGray, Imgproc.COLOR_BGR2GRAY);
		//Show the gray image
		//FuncImage.bufferedImageShow(imageGray, "Gray Image");
		
		///calculate histogram for h=b,s=g and v=r planes
		Vector<Mat> bgr_planes = new Vector<>();
		Core.split(rgbInpImage, bgr_planes);
		MatOfInt histSize = new MatOfInt(256);
		final MatOfFloat histRange = new MatOfFloat(0f, 256f);
		boolean accumulate = false;
		Mat b_hist = new Mat();
		Mat g_hist = new Mat();
		Mat r_hist = new Mat();
		Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist,histSize, histRange, accumulate);
		Imgproc.calcHist(bgr_planes, new MatOfInt(1), new Mat(), g_hist,histSize, histRange, accumulate);
		Imgproc.calcHist(bgr_planes, new MatOfInt(2), new Mat(), r_hist,histSize, histRange, accumulate);
		
		//find maxPos for b,g and r histogram
		int START_POS=1;			//MACRO
		int END_POS=180;			//MACRO
		int maxPosB=START_POS;
		int maxPosG=START_POS;
		int maxPosR=START_POS;
		double[] x;
		double[] y;
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=b_hist.get(maxPosB,0);
			y=b_hist.get(i,0);
			if(x[0]<y[0])
			{
				maxPosB=i;
			}
		}
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=g_hist.get(maxPosG,0);
			y=g_hist.get(i,0);
			if(x[0]<y[0])
			{
				maxPosG=i;
			}
		}
		for(int i=START_POS;i<END_POS;i=i+1)
		{
			x=r_hist.get(maxPosR,0);
			y=r_hist.get(i,0);
			if(x[0]<y[0])
			{
				maxPosR=i;
			}
		}
		//System.out.println("maxPosB="+maxPosB);
		//System.out.println("maxPosG="+maxPosG);
		//System.out.println("maxPosR="+maxPosR);
		
		///decide dominant color in the flower
		int dominantColor=-1;
		int R_RANGE_LOW1=1;		//MACRO
		int R_RANGE_HIGH1=20;	//MACRO
		int R_RANGE_LOW2=325;	//MACRO
		int R_RANGE_HIGH2=360;	//MACRO
		int Y_RANGE_LOW=21;		//MACRO
		int Y_RANGE_HIGH=67;	//MACRO
		int G_RANGE_LOW=68;		//MACRO
		int G_RANGE_HIGH=142;	//MACRO
		int W_RANGE_LOW_DOMINANT_COLOR=143;		//MACRO	//use this to find the dominant color	
		int W_RANGE_LOW=20;						//MACRO	//use this as lower cut-off for segmentation to ensure segmentation of dark part of flower
		int W_RANGE_HIGH=225;	//MACRO
		int B_RANGE_LOW=226;	//MACRO
		int B_RANGE_HIGH=262;	//MACRO
		int O_RANGE_LOW=21;		//MACRO
		int O_RANGE_HIGH=37;	//MACRO
		int V_RANGE_LOW=263;	//MACRO
		int V_RANGE_HIGH=324;	//MACRO
	
		if((maxPosB*2>=R_RANGE_LOW1 && maxPosB*2<=R_RANGE_HIGH1) || (maxPosB*2>=R_RANGE_LOW2 && maxPosB*2<=R_RANGE_HIGH2))
		{
			dominantColor=2;
		}
		else if((maxPosB*2>=O_RANGE_LOW && maxPosB*2<=O_RANGE_HIGH))
		{
			dominantColor=5;
		}
		else if((maxPosB*2>=Y_RANGE_LOW && maxPosB*2<=Y_RANGE_HIGH))
		{
			dominantColor=3;
		}
		
		else if((maxPosB*2>=W_RANGE_LOW_DOMINANT_COLOR && maxPosB*2<=W_RANGE_HIGH))
		{
			dominantColor=0;
		}
		else if(maxPosB*2>=G_RANGE_LOW && maxPosB*2<=G_RANGE_HIGH)
		{
			dominantColor=1;
		}
		else if((maxPosB*2>=V_RANGE_LOW && maxPosB*2<=V_RANGE_HIGH))
		{
			dominantColor=6;
		}
		else if((maxPosB*2>=B_RANGE_LOW && maxPosB*2<=B_RANGE_HIGH))
		{
			dominantColor=4;
		}
		
			
		
		
		//System.out.println("dominantColor="+dominantColor);
		
		/////create DB1 based on dominant color
		int DB1_cnt=0;
		for(int index=0;index<DB_dominantColor.length;index=index+1)
		{
			if(dominantColor==DB_dominantColor[index])
			{
				DB1[DB1_cnt]=index+1;
				System.out.println("DB1["+DB1_cnt+"]"+DB1[DB1_cnt]);
				DB1_cnt++;
			}
		}
		//to show db1
		for(int index=0;index<DB1_cnt;++index)
		{
			formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (%d).jpg",DB1[index]);
        	formattedDisplayString = String.format("DB1(%d).jpg",index);
			displayImage(formattedString,"Related by color-"+formattedDisplayString);
		}
		/////DB1 is ready
		/////color segmentation to segment out the flower
		rgbImage.create(rgbInpImage.rows(),rgbInpImage.cols(),rgbInpImage.type());
		rgbInpImage.copyTo(rgbImage);
		Mat segmentedImage = new Mat();
		segmentedImage.create(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
		if(dominantColor==0)
		{	
			Core.inRange(rgbImage,new Scalar(W_RANGE_LOW/2,0,0),new Scalar(W_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		if(dominantColor==1)
		{	
			Core.inRange(rgbImage,new Scalar(G_RANGE_LOW/2,0,0),new Scalar(G_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		else if(dominantColor==2)
		{	
			if(maxPosB*2>=R_RANGE_LOW1 && maxPosB*2<=R_RANGE_HIGH1)
			Core.inRange(rgbImage,new Scalar(R_RANGE_LOW1/2,0,0),new Scalar(R_RANGE_HIGH1/2,255,255),segmentedImage);
			else
			Core.inRange(rgbImage,new Scalar(R_RANGE_LOW2/2,0,0),new Scalar(R_RANGE_HIGH2/2,255,255),segmentedImage);
			
		}
		else if(dominantColor==3)
		{	
			Core.inRange(rgbImage,new Scalar(Y_RANGE_LOW/2,0,0),new Scalar(Y_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		else if(dominantColor==4)
		{	
			Core.inRange(rgbImage,new Scalar(B_RANGE_LOW/2,0,0),new Scalar(B_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		else if(dominantColor==5)
		{	
			Core.inRange(rgbImage,new Scalar(O_RANGE_LOW/2,0,0),new Scalar(O_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		else if(dominantColor==6)
		{	
			Core.inRange(rgbImage,new Scalar(V_RANGE_LOW/2,0,0),new Scalar(V_RANGE_HIGH/2,255,255),segmentedImage);
			
		}
		//FuncImage.bufferedImageShow(segmentedImage, "segmentedImage");
		
		///find contours in segmented image
		Mat contourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
		Mat largestContourImage=new Mat(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);

		Vector<MatOfPoint> contours = new Vector<>();
		Imgproc.findContours(segmentedImage, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = -1;
        int maxAreaIdx = -1;
        Imgproc.drawContours(contourImage,contours, -1, new Scalar(255, 255, 255), -1);
        //FuncImage.bufferedImageShow(contourImage, "countourImage");
        
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
        //Imgproc.drawContours(largestContourImage,contours, maxAreaIdx, new Scalar(255, 255, 255), 8);
        //FuncImage.bufferedImageShow(largestContourImage, "largestCountourImage");
////////////////////////////////////////////////////////////////////
	Rect rect = Imgproc.boundingRect(contours.get(maxAreaIdx));
	
	// draw enclosing rectangle (all same color, but you could use variable i to make them unique)
	Core.rectangle(largestContourImage, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(255, 255, 255), 3); 
	Rect RectPistal=new Rect((int)(rect.x+rect.width*(0.34)),(int)(rect.y+rect.height*(0.34)),rect.width/3,rect.height/3);
	
	Mat pistalRgbImage = new Mat(rgbInpImage,RectPistal);
	Mat pistalGrayImage=new Mat();
	Mat pistalStrechedGrayImage=new Mat();
	Mat pistalHistImage=new Mat();
	Mat pistalLowerSegmentedImg=new Mat();
	Mat pistalHigherSegmentedImg=new Mat();
	
	//Convert the image in to gray image single channel image
	Imgproc.cvtColor(pistalRgbImage, pistalGrayImage, Imgproc.COLOR_BGR2GRAY);
	// Normalize function call to apply linear stretch(contrast enhancement)
	Core.normalize(pistalGrayImage, pistalStrechedGrayImage, 0,255,Core.NORM_MINMAX);
	
	//FuncImage.bufferedImageShow(largestContourImage, "rectRefLargestCountourImage");
	//FuncImage.bufferedImageShow(pistalRgbImage, "pistalRgbImage");
	//FuncImage.bufferedImageShow(pistalGrayImage, "pistalGrayImage");
	//FuncImage.bufferedImageShow(pistalStrechedGrayImage, "pistalStrechedGrayImage");
	
	//calculate histogram
	Vector<Mat> pistalStrechedGrayImagePlanes = new Vector<>();
	Core.split(pistalStrechedGrayImage, pistalStrechedGrayImagePlanes);
	Imgproc.calcHist(pistalStrechedGrayImagePlanes, new MatOfInt(0), new Mat(), pistalHistImage,histSize, histRange, accumulate);
	//find lower maxima and higher maxima in hist
	int HIST_PARTITION=100;		//MACRO
	int lowerMaxPos=0,higherMaxPos=HIST_PARTITION;
	for(int histIdx=0;histIdx<HIST_PARTITION;++histIdx)
	{
	x=pistalHistImage.get(lowerMaxPos,0);
	y=pistalHistImage.get(histIdx,0);
	if(x[0]<y[0])
	{
	lowerMaxPos=histIdx;
	}
	}
	for(int histIdx=HIST_PARTITION;histIdx<256;++histIdx)
	{
	x=pistalHistImage.get(higherMaxPos,0);
	y=pistalHistImage.get(histIdx,0);
	if(x[0]<y[0])
	{
	higherMaxPos=histIdx;
	}
	}
	//System.out.println("lowerMaxPos="+lowerMaxPos);
	//System.out.println("higherMaxPos="+higherMaxPos);
	//segmentation
	Core.inRange(pistalStrechedGrayImage,new Scalar(lowerMaxPos-40,0,0),new Scalar(lowerMaxPos+40,0,0),pistalLowerSegmentedImg);
	//FuncImage.bufferedImageShow(pistalLowerSegmentedImg, "pistalLowerSegmentedImg");
	Core.inRange(pistalStrechedGrayImage,new Scalar(higherMaxPos-40,0,0),new Scalar(higherMaxPos+40,0,0),pistalHigherSegmentedImg);
	//FuncImage.bufferedImageShow(pistalHigherSegmentedImg, "pistalHigherSegmentedImg");
	//find Area in number of pixels
	int lowerArea=0,higherArea=0;
	for(int row=0;row<pistalLowerSegmentedImg.rows();++row)
	{
	for(int col=0;col<pistalLowerSegmentedImg.cols();++col)
	{
	x=pistalLowerSegmentedImg.get(row, col);
	if(x[0]!=0)
	lowerArea++;
	}
	
	}
	for(int row=0;row<pistalHigherSegmentedImg.rows();++row)
	{
	for(int col=0;col<pistalHigherSegmentedImg.cols();++col)
	{
	x=pistalHigherSegmentedImg.get(row, col);
	if(x[0]!=0)
	higherArea++;
	}
	
	}
	//System.out.println("lowerArea="+lowerArea);
	//System.out.println("higherArea="+higherArea);
	////find relative area for pistal
	double relLowerArea=((double)lowerArea/maxArea)*100;
	double relHigherArea=(double)higherArea/maxArea*100;
	//System.out.println("relLowerArea="+relLowerArea);
	//System.out.println("relHigherArea="+relHigherArea);
	////////////////////////////////////////////////////////////////////////////
        
        ////compare shape of largest contour with all images in DB1
        double[] matchFactor=new double[DB1_cnt];
        double MATCH_FACTOR_TH=	0.014;	//0.020;		//MACRO
        for(int index=0;index<DB1_cnt;++index)
        {
        	//formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (%d).jpg",DB1[index]);
        	formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (%d).jpg",DB1[index]);

        	Mat refRgbImage=Highgui.imread(formattedString);
        	//FuncImage.bufferedImageShow(refRgbImage, "refRgbImage");
           	//convert to HSV color model
    		Imgproc.cvtColor(refRgbImage, refRgbImage, Imgproc.COLOR_BGR2HSV);
        	//segment the ref image
    		Mat refSegmentedImage = new Mat();
    		refSegmentedImage.create(rgbImage.rows(),rgbImage.cols(),CvType.CV_8UC1);
    		if(dominantColor==0)
    		{	
    			Core.inRange(refRgbImage,new Scalar(W_RANGE_LOW/2,0,0),new Scalar(W_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		if(dominantColor==1)
    		{	
    			Core.inRange(refRgbImage,new Scalar(G_RANGE_LOW/2,0,0),new Scalar(G_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==2)
    		{	
    			if(maxPosB*2>=R_RANGE_LOW1 && maxPosB*2<=R_RANGE_HIGH1)
    			Core.inRange(refRgbImage,new Scalar(R_RANGE_LOW1/2,0,0),new Scalar(R_RANGE_HIGH1/2,255,255),refSegmentedImage);
    			else
    			Core.inRange(refRgbImage,new Scalar(R_RANGE_LOW2/2,0,0),new Scalar(R_RANGE_HIGH2/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==3)
    		{	
    			Core.inRange(refRgbImage,new Scalar(Y_RANGE_LOW/2,0,0),new Scalar(Y_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==4)
    		{	
    			Core.inRange(refRgbImage,new Scalar(B_RANGE_LOW/2,0,0),new Scalar(B_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==5)
    		{	
    			Core.inRange(refRgbImage,new Scalar(O_RANGE_LOW/2,0,0),new Scalar(O_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==6)
    		{	
    			Core.inRange(refRgbImage,new Scalar(V_RANGE_LOW/2,0,0),new Scalar(V_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
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
            //Imgproc.drawContours(refLargestContourImage,refContours, refMaxAreaIdx, new Scalar(255, 255, 255), -1);
            //FuncImage.bufferedImageShow(refLargestContourImage, "refLargestCountourImage");
            
            //match shapes
            matchFactor[index]=Imgproc.matchShapes(contours.get(maxAreaIdx),refContours.get(refMaxAreaIdx),Imgproc.CV_CONTOURS_MATCH_I3,0);
        }
        ///create DB2
        //sort matchFactor array and take 0.0 and <MATCH_FACTOR_TH OR lowest two
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
        if(DB2_cnt<3 && DB2_cnt<DB1_cnt)
        {
	        for(int cnt=DB2_cnt;cnt<DB1_cnt;++cnt)
	        {
	        	for(int cnt1=0;cnt1<DB1_cnt;++cnt1)
	        	{	
	        		if((sortedMatchFactor[cnt]==matchFactor[cnt1]) && sortedMatchFactor[cnt]<=MATCH_FACTOR_TH)
	        		{
	        			DB2[DB2_cnt]=DB1[cnt1];
	        			System.out.println("DB2["+DB2_cnt+"]="+DB2[DB2_cnt]);
	        			DB2_cnt++;
	        			break;
	        		}
	        	}
	        }
        }
        if(DB2_cnt==0)
        {
        	 for(int cnt=DB2_cnt;DB2_cnt<2;++cnt)
 	        {
 	        	for(int cnt1=0;cnt1<DB1_cnt;++cnt1)
 	        	{	
 	        		if((sortedMatchFactor[cnt]==matchFactor[cnt1]))
 	        		{
 	        			DB2[DB2_cnt]=DB1[cnt1];
 	        			System.out.println("DB2["+DB2_cnt+"]="+DB2[DB2_cnt]);
 	        			DB2_cnt++;
 	        			break;
 	        		}
 	        	}
 	        }
        }
        
        //show DB2
        for(int cnt=0;cnt<DB2_cnt;++cnt)
        {
        	formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (%d).jpg",DB2[cnt]);
        	formattedDisplayString = String.format("DB2(%d).jpg",cnt);
        	displayImage(formattedString,"Related by color and shape"+formattedDisplayString);
        }
        ///DB2 is ready
        ////compare pistal/stamen for DB2 and create DB3 (possible matches)
        int[] DB3=new int[3];
        int DB3_cnt=0;
        int REL_AREA_TH=2;			//MACRO
        for(int DB2_index=0;DB2_index<DB2_cnt;++DB2_index)
        {
        	//formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (%d).jpg",DB2[DB2_index]);
        	formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (%d).jpg",DB2[DB2_index]);

        	Mat refRgbImage=Highgui.imread(formattedString);
        	//FuncImage.bufferedImageShow(refRgbImage, "refRgbImage");
        	//convert to HSV color model
    		Imgproc.cvtColor(refRgbImage, refRgbImage, Imgproc.COLOR_BGR2HSV);
        	//segment the ref image
    		Mat refSegmentedImage = new Mat();
    		refSegmentedImage.create(refRgbImage.rows(),refRgbImage.cols(),CvType.CV_8UC1);
    		if(dominantColor==0)
    		{	
    			Core.inRange(refRgbImage,new Scalar(W_RANGE_LOW/2,0,0),new Scalar(W_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		if(dominantColor==1)
    		{	
    			Core.inRange(refRgbImage,new Scalar(G_RANGE_LOW/2,0,0),new Scalar(G_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==2)
    		{	
    			if(maxPosB*2>=R_RANGE_LOW1 && maxPosB*2<=R_RANGE_HIGH1)
    			Core.inRange(refRgbImage,new Scalar(R_RANGE_LOW1/2,0,0),new Scalar(R_RANGE_HIGH1/2,255,255),refSegmentedImage);
    			else
    			Core.inRange(refRgbImage,new Scalar(R_RANGE_LOW2/2,0,0),new Scalar(R_RANGE_HIGH2/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==3)
    		{	
    			Core.inRange(refRgbImage,new Scalar(Y_RANGE_LOW/2,0,0),new Scalar(Y_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==4)
    		{	
    			Core.inRange(refRgbImage,new Scalar(B_RANGE_LOW/2,0,0),new Scalar(B_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==5)
    		{	
    			Core.inRange(refRgbImage,new Scalar(O_RANGE_LOW/2,0,0),new Scalar(O_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
    		}
    		else if(dominantColor==6)
    		{	
    			Core.inRange(refRgbImage,new Scalar(V_RANGE_LOW/2,0,0),new Scalar(V_RANGE_HIGH/2,255,255),refSegmentedImage);
    			
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
            ////////////////////////////////////////////////////////////////////
            
            // draw enclosing rectangle (all same color, but you could use variable i to make them unique)
            Rect refRect = Imgproc.boundingRect(refContours.get(refMaxAreaIdx));
            Core.rectangle(refLargestContourImage, new Point(refRect.x,refRect.y), new Point(refRect.x+refRect.width,refRect.y+refRect.height), new Scalar(255, 255, 255), 3); 
            Rect refRectPistal=new Rect((int)(refRect.x+refRect.width*(0.34)),(int)(refRect.y+refRect.height*(0.34)),refRect.width/3,refRect.height/3);
            
            Mat refPistalRgbImage = new Mat(refRgbImage,refRectPistal);
            Mat refPistalGrayImage=new Mat();
            Mat refPistalStrechedGrayImage=new Mat();
            Mat refPistalHistImage=new Mat();
            Mat refPistalLowerSegmentedImg=new Mat();
            Mat refPistalHigherSegmentedImg=new Mat();
            
            //Convert the image in to gray image single channel image
    		Imgproc.cvtColor(refPistalRgbImage, refPistalGrayImage, Imgproc.COLOR_BGR2GRAY);
    		// Normalize function call to apply linear stretch(contrast enhancement)
    		Core.normalize(refPistalGrayImage, refPistalStrechedGrayImage, 0,255,Core.NORM_MINMAX);
    	
    		//FuncImage.bufferedImageShow(refLargestContourImage, "rectRefLargestCountourImage");
            //FuncImage.bufferedImageShow(refPistalRgbImage, "refPistalRgbImage");
            //FuncImage.bufferedImageShow(refPistalGrayImage, "refPistalGrayImage");
            //FuncImage.bufferedImageShow(refPistalStrechedGrayImage, "refPistalStrechedGrayImage");
            
            //calculate histogram
    		Vector<Mat> refPistalStrechedGrayImagePlanes = new Vector<>();
    		Core.split(refPistalStrechedGrayImage, refPistalStrechedGrayImagePlanes);
    		Imgproc.calcHist(refPistalStrechedGrayImagePlanes, new MatOfInt(0), new Mat(), refPistalHistImage,histSize, histRange, accumulate);
    		//find lower maxima and higher maxima in hist
    		int refLowerMaxPos=0,refHigherMaxPos=HIST_PARTITION;
    		for(int histIdx=0;histIdx<HIST_PARTITION;++histIdx)
    		{
    			x=refPistalHistImage.get(refLowerMaxPos,0);
    			y=refPistalHistImage.get(histIdx,0);
    			if(x[0]<y[0])
    			{
    				refLowerMaxPos=histIdx;
    			}
    		}
    		for(int histIdx=HIST_PARTITION;histIdx<256;++histIdx)
    		{
    			x=refPistalHistImage.get(refHigherMaxPos,0);
    			y=refPistalHistImage.get(histIdx,0);
    			if(x[0]<y[0])
    			{
    				refHigherMaxPos=histIdx;
    			}
    		}
    		//System.out.println("refLowerMaxPos="+refLowerMaxPos);
    		//System.out.println("refHigherMaxPos="+refHigherMaxPos);
    		//segmentation
    		Core.inRange(refPistalStrechedGrayImage,new Scalar(refLowerMaxPos-40,0,0),new Scalar(refLowerMaxPos+40,0,0),refPistalLowerSegmentedImg);
    		//FuncImage.bufferedImageShow(refPistalLowerSegmentedImg, "refPistalLowerSegmentedImg");
    		Core.inRange(refPistalStrechedGrayImage,new Scalar(refHigherMaxPos-40,0,0),new Scalar(refHigherMaxPos+40,0,0),refPistalHigherSegmentedImg);
    		//FuncImage.bufferedImageShow(refPistalHigherSegmentedImg, "refPistalHigherSegmentedImg");
    		//find Area in number of pixels
    		int refLowerArea=0,refHigherArea=0;
    		for(int row=0;row<refPistalLowerSegmentedImg.rows();++row)
    		{
    			for(int col=0;col<refPistalLowerSegmentedImg.cols();++col)
    			{
    			x=refPistalLowerSegmentedImg.get(row, col);
    			if(x[0]!=0)
    				refLowerArea++;
    			}
    			
    		}
    		for(int row=0;row<refPistalHigherSegmentedImg.rows();++row)
    		{
    			for(int col=0;col<refPistalHigherSegmentedImg.cols();++col)
    			{
    			x=refPistalHigherSegmentedImg.get(row, col);
    			if(x[0]!=0)
    				refHigherArea++;
    			}
    			
    		}
    		//System.out.println("refLowerArea="+refLowerArea);
    		//System.out.println("refHigherArea="+refHigherArea);
    		////find relative area for pistal
    		double refRelLowerArea=((double)refLowerArea/refMaxArea)*100;
    		double refRelHigherArea=((double)refHigherArea/refMaxArea)*100;
    		//System.out.println("refRelLowerArea="+refRelLowerArea);
    		//System.out.println("refRelHigherArea="+refRelHigherArea);
////////////////////////////////////////////////////////////////////////////
    		////create DB3 if relative areas are comparable
    		if(Math.abs(refRelLowerArea-relLowerArea)<=REL_AREA_TH && Math.abs(refRelHigherArea-relHigherArea)<=REL_AREA_TH )
    		{
    			DB3[DB3_cnt]=DB2[DB2_index];
    			DB3_cnt++;
    		}

        }
        //to show DB3
        for(int cnt=0;cnt<DB3_cnt;++cnt)
        {
        	System.out.println("DB3["+cnt+"]="+DB3[cnt]);
            //formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamed\\image (%d).jpg",DB3[cnt]);
            formattedString = String.format("D:\\flowerRecognitionProject\\dataBase\\clientDataBase\\renamedFlowerWithName\\image (%d).jpg",DB3[cnt]);
            formattedDisplayString = String.format("DB3(%d).jpg",cnt);
            displayImage(formattedString,"output-Related by color,shape and pistal/stamen area-"+flowerName[DB3[cnt]-1]);
        }
		}
	}



