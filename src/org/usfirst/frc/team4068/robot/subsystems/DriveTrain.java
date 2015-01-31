package org.usfirst.frc.team4068.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team4068.robot.lib.Motor;

import edu.wpi.first.wpilibj.Timer;

public class DriveTrain {
    
    private Motor FL;
    private Motor FR;
    private Motor BL;
    private Motor BR;
    
    private Map<Motor, Double> speeds = new HashMap<Motor, Double>(); //Holds conversions for PWMs so that all motors spin at the same speed
    
    Timer time = new Timer();
    
    public DriveTrain(Motor fl, Motor fr, Motor bl, Motor br){
        FL = fl;
        FR = fr;
        BL = bl;
        BR = br;
        
        speeds.put(FL, 1.0);
        speeds.put(FR, 1.0);
        speeds.put(BL, 1.0);
        speeds.put(BR, 1.0); //Initializes speed map by giving each motor the default speed of 1x (no change to the PWM value passed)
        
        time.start();
    }
    
    public void drive(double movx, double movy, double rot){
        double fl = (movy -movx +rot);
        double fr = (-movy -movx +rot);
        double bl = (movy +movx +rot);
        double br = (-movy +movx +rot);
        
        double[] pwmValues = {fl, fr, bl, br};
        
        calibrate(.02, pwmValues);
        
        FL.set(fl);
        FR.set(fr);
        BL.set(bl);
        BR.set(br);
    }
    
    private void calibrate(double sample_time, double[] pwmValues){
        int[] speeds = getMotorSpeeds(sample_time);
        
        double[] multipliers = {1, 1, 1, 1};
        
        int lowestSpeed = Integer.MAX_VALUE;
        
        for (int i = 0; i < 4; i++){ //Finds and stores the lowest value
            speeds[i] = Math.round((float)(pwmValues[i] * speeds[i])); //Weights speeds based on pwm value sent to that motor
            if (speeds[i] < lowestSpeed){
                lowestSpeed = speeds[i];
            }
        }
        
        for (int i = 0; i < 4; i++){ //Sets multipliers so that each motor spins at the same speed 
                                     //based on the lowest speed motor
            multipliers[i] = speeds[i] / lowestSpeed;
        }
        
        this.speeds.replace(FL, multipliers[0]);
        this.speeds.replace(FR, multipliers[1]);
        this.speeds.replace(BL, multipliers[2]);
        this.speeds.replace(BR, multipliers[3]);
    }
    
    //Samples encoders for current speed in rpm (should be sampled over 5ms)
    double time_start;
    boolean startSample = true;
    int[] startCount;
    int[] speeds1;
    private int[] getMotorSpeeds(double sample_time){
        if (startSample){
            speeds1[0] = 0;
            speeds1[1] = 0;
            speeds1[2] = 0;
            speeds1[3] = 0;
            
            startCount[0] = FL.getRaw();
            startCount[1] = FR.getRaw();
            startCount[2] = BL.getRaw();
            startCount[3] = BR.getRaw();
            
            time_start = time.get();
        }else if (time.get() - time_start > sample_time){
            int end_count[] = {FL.getRaw(), FR.getRaw(), BL.getRaw(), BR.getRaw()};
            for (int i = 0; i < 4; i++){
                speeds1[0] = end_count[i] - startCount[i];
            }
        }
        return speeds1;
    }
}
