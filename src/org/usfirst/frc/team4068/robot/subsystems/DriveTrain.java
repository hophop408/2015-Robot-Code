package org.usfirst.frc.team4068.robot.subsystems;

import org.usfirst.frc.team4068.robot.lib.Motor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain {
    
    private Motor FL;
    private Motor FR;
    private Motor BL;
    private Motor BR;
    
    private Encoder e = new Encoder(1, 2);
    
    Timer time = new Timer();
    
    public DriveTrain(Motor fl, Motor fr, Motor bl, Motor br){
        FL = fl;
        FR = fr;
        BL = bl;
        BR = br;
        time.start();
    }
    
    public void drive(double movx, double movy, double rot){
        int speeds[] = getMotorSpeeds(1);
        
        double fl = (movy -movx +rot);
        double fr = (-movy -movx +rot);
        double bl = (movy +movx +rot);
        double br = (-movy +movx +rot);
        
        FL.set(fl);
        FR.set(fr);
        BL.set(bl);
        BR.set(br);
    }
    
    
    double time_start = time.get();
    private int[] getMotorSpeeds(double sample_time){
        int speeds[] = {1, 1, 1, 1};
        
        int start_count = e.get();
        if (time.get() - time_start > sample_time){
            int end_count = e.get();
            speeds[0] = end_count-start_count;
        }
        return speeds;
    }
}
