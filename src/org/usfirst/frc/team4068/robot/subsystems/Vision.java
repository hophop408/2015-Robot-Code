package org.usfirst.frc.team4068.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision extends AxisCamera{
	public Vision(String cameraHost) {
		super(cameraHost);
		// TODO Auto-generated constructor stub
	}
	Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	//Need to find correct IP address for the camera.
	//I'm pretty sure that this just sets the camera to the name of camera.
	
	public void imageRetrieval() {
		ColorImage image = null;
		BinaryImage thresholdImage = null;
		try{
			image = this.getImage();//Why is this throwing a warning?
			thresholdImage = image.thresholdRGB(0, 45, 25, 255, 0, 45); //Filters out most of the non-green. We use green lights, so the green should  be what we need to see.
		}catch(Exception ex){
			
		}finally{
			
		}
	}
	
	//Commented out this whole section because I don't think we'll be using it. 
	/*public void thing(){
		
		//NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100); Only important if drawShapeOnImage pans out.
		
		//While statement appears to check if robot is enabled and operator controlled. Operator Control might need to be removed.
		
		//This part only happens if something? I don't know. Work to resolve.
		ColorImage image = camera.getImage();
		BinaryImage thresholdImage = image.thresholdRGB(0, 45, 25, 255, 0, 45); //Filters out most of the non-green. We use green lights, so the green should  be what we need to see.
		//camera.getImage(frame); //Gets the image? Not sure if this or the above is correct
		
		//NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
		//No clue if above is even necessary or useful.
		//Can use setImage() to send this image with the rectangle to places.
	}*/
}
