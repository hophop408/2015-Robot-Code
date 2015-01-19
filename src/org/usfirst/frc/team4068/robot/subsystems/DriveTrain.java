package org.usfirst.frc.team4068.robot.subsystems;

import org.usfirst.frc.team4068.robot.lib.Motor;

public class DriveTrain {
    
    private Motor FL;
    private Motor FR;
    private Motor BL;
    private Motor BR;
    
    public DriveTrain(Motor fl, Motor fr, Motor bl, Motor br){
        FL = fl;
        FR = fr;
        BL = bl;
        BR = br;
    }
    
    public void drive(double movx, double movy, double rot){
        double fl = movy -movx +rot;
        double fr = -movy -movx +rot;
        double bl = movy +movx +rot;
        double br = -movy +movx +rot;
        
        FL.set(fl);
        FR.set(fr);
        BL.set(bl);
        BR.set(br);
    }
}
