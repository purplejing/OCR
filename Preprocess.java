/*  This program aims at preprocessing the image before the contour analysis
 *  Version 1: 
 *  1. Supporting only the final bill number characters on the image
 *  2. Pure color background
 *  3. Ideally no noise, nothing blurred
 */

import java.io.File;
import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.imgproc.*;
import org.opencv.calib3d.*;
import org.opencv.contrib.*;
import org.opencv.ml.*;
import org.opencv.objdetect.*;
import org.opencv.photo.*;
import org.opencv.*;
import org.opencv.features2d.*;

public class Preprocess {
	// right now we are using definite threshold value for edge detection which later on should be trained to be adaptive
	int kernel_size = 3;
	int edgethreshold = 5;
	int lowthreshold = 10;
	int upthreshold = 100;
	
	Mat img, preprocessedimg;
	
	public void extract_contours ( String[] filename )  {
		
		// open the file we are going to preprocess
		Mat img = imread(filename, 0);
		
		// convert the image to gray scale
		cvtColor(img, preprocessedimg, CV_BGR2GRAY);
		
		// apply Gaussian filter to smooth edges
		Size applied_size(3,3);
		GaussianBlur(preprocessedimg, preprocessedimg, applied_size, 0);
		
		//Canny Edge Detector
		Canny(edgethreshold, edgethreshold, lowthreshold, upthreshold, kernel_size);
		
		// apply adaptive threshold to detect edges
		adptiveThreshold(img, img, 255, CV_ADAPTIVE_THRESH_MEAN_C, CV_THRESH_BINARY, upthreshold, lowthreshold);
		bitwise_not(img, img);
		
		
		
	   }

}
