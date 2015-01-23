package org.usfirst.frc.team4068.robot.subsystems;

import java.nio.ByteBuffer;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.MeasurementType;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision extends AxisCamera{
	public Vision(String cameraHost) {
		super(cameraHost);
		// TODO Auto-generated constructor stub
	}

	/*AxisCamera camera = new AxisCamera("10.40.68.11");
	
	Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	
	ParticleFilterCriteria2[] criteria = {null};*/

	
	//Need to find correct IP address for the camera.
	//I'm pretty sure that this just sets the camera to the name of camera.
	
	public boolean imageRetrieval() {
		ColorImage image = null;
		BinaryImage thresholdImage = null;
		BinaryImage bigObjectsImage = null;
		BinaryImage convexHullImage = null;
		BinaryImage filteredImage = null;
		try{
			image = this.getImage();
			thresholdImage = image.thresholdRGB(0, 45, 25, 255, 0, 45); //Filters out most of the non-green. We use green lights, so the green should  be what we need to see.
			bigObjectsImage = thresholdImage.removeSmallObjects(false, 2); //Filters small particles.
			convexHullImage = bigObjectsImage.convexHull(false); //The video said that this fills the rectangles or something like that. I don't understand why.
			filteredImage = convexHullImage.particleFilter(criteria); //Looks for rectangular particles I think.
		}catch(Exception ex){
			
		}finally{
			
		}
		if(filteredImage != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isToteInView() {
		return false;
	}
}
