package org.usfirst.frc.team4068.robot.subsystems;

import java.nio.ByteBuffer;

import org.usfirst.frc.team4068.robot.Robot.Scores;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.MeasurementType;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision extends AxisCamera{
	public Vision(String cameraHost) {
		super(cameraHost);
		// TODO Auto-generated constructor stub
	}

	/*AxisCamera camera = new AxisCamera("10.40.68.11");
	
	Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);*/
	
	public class Scores {
		double Area;
		double Aspect;
	};
	
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(101, 64);	//Default hue range for yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(88, 255);	//Default saturation range for yellow tote
	NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(134, 255);	//Default value range for yellow tote
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Scores scores = new Scores();

	Image frame;
	Image binaryFrame;
	
	public void imageRetrieval() {
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);

		ColorImage image = null;
		BinaryImage thresholdImage = null;
		BinaryImage bigObjectsImage = null;
		BinaryImage convexHullImage = null;
		BinaryImage filteredImage = null;
		try{
			image = this.getImage();
			thresholdImage = image.thresholdRGB(0, 255, 0, 255, 0, 255); //Filters out most of the non-green. We use green lights, so the green should  be what we need to see.
			bigObjectsImage = thresholdImage.removeSmallObjects(false, 2); //Filters small particles.
			convexHullImage = bigObjectsImage.convexHull(false); //The video said that this fills the rectangles or something like that. I don't understand why.
			filteredImage = convexHullImage.particleFilter(criteria); //Looks for rectangular particles I think.
			ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();
			for(int i = 0; i < reports.length +1; i++){
				
			}
		}catch(Exception ex){
			
		}finally{
			
		}
		
		try {
			filteredImage.free();
			convexHullImage.free();
			bigObjectsImage.free();
			thresholdImage.free();
			image.free();
		} catch(NIVisionException ex) {
			
		} finally {
			
		}
		
		/*if(filteredImage != null) {
			return true;
		} else {
			return false;
		}*/
	}
	
	public boolean isToteInView() {
		return false;
	}
}
