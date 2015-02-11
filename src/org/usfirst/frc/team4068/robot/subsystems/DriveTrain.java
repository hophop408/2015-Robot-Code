package org.usfirst.frc.team4068.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team4068.robot.lib.Motor;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    
    private double Kp = .3;
    private double Ki = 0.0;
    private double Kd = 0.0;
    
    private Motor FL;
    private Motor FR;
    private Motor BL;
    private Motor BR;
    
    private PIDController frontLeft;
    private PIDController frontRight;
    private PIDController backLeft;
    private PIDController backRight;
    
    private double rotationOffset = .1;
    
    Timer time = new Timer();
    
    public DriveTrain(Motor fl, Motor fr, Motor bl, Motor br){
        System.out.println("New instance of Drive Train being created");
        FL = fl;
        FR = fr;
        BL = bl;
        BR = br;
        
        
        
        frontLeft = new PIDController(Kp, Ki, Kd, FL.getEncoder(), FL);
        frontRight = new PIDController(Kp, Ki, Kd, FR.getEncoder(), FR);
        backLeft = new PIDController(Kp, Ki, Kd, BL.getEncoder(), BL);
        backRight = new PIDController(Kp, Ki, Kd, BR.getEncoder(), BR);
        
        frontLeft.setOutputRange(.2, 1);
        frontLeft.setInputRange(0, 5);
        frontRight.setOutputRange(.2, 1);
        frontRight.setInputRange(0, 5);
        backLeft.setOutputRange(.2, 1);
        backLeft.setInputRange(0, 5);
        backRight.setOutputRange(.2, 1);
        backRight.setInputRange(0, 5);
        
        time.start();
        System.out.println("Drive Train created");
    }
    
    int count = 0;
    double lastTime = 0;
    
    public void drive(double movx, double movy, double rot){
        count++;
        rotationOffset = SmartDashboard.getNumber("Rotation Offset");
        rot = rot + (rotationOffset * movx); //causes robot to rotate based on strafing value
        
        double fl = (movy -movx +rot);
        double fr = (-movy -movx +rot);
        double bl = (movy +movx +rot);
        double br = (-movy +movx +rot); //-1 - 1 values sent to motors
        
        double flact = FL.getEncoder().getRate()*60; //actual RPM of motor
        double fract = FR.getEncoder().getRate()*60; //actual RPM of motor
        double blact = BL.getEncoder().getRate()*60; //actual RPM of motor
        double bract = BR.getEncoder().getRate()*60; //actual RPM of motor
        
        SmartDashboard.putNumber("FL PWM value", fl);
        SmartDashboard.putNumber("FR PWM value", fr);
        SmartDashboard.putNumber("BL PWM value", bl);
        SmartDashboard.putNumber("BR PWM value", br);
        
        //if (time.get() - lastTime > .02){
            //lastTime = time.get();
            //cal(fl, fr, bl, br);
        //}
        
        SmartDashboard.putNumber("FL RPM", flact);
        SmartDashboard.putNumber("FR RPM", fract);
        SmartDashboard.putNumber("BL RPM", blact);
        SmartDashboard.putNumber("BR RPM", bract);
        
        FL.set((fl > .2 || fl < -.2)? (fl * flmult) : 0);
        FR.set((fr > .2 || fr < -.2)? (fr * frmult) : 0);
        BL.set((bl > .2 || bl < -.2)? (bl * blmult) : 0);
        BR.set((br > .2 || br < -.2)? (br * brmult) : 0);
        
    }
    
    double flmult = 1;
    double frmult = 1;
    double blmult = 1;
    double brmult = 1;
    
    public void cal(double fl, double fr, double bl, double br){
        double flest = fl * 600; //The estimated RPM of the motor - max rpm is about 600
        double frest = fr * 600; //The estimated RPM of the motor - max rpm is about 600
        double blest = bl * 600; //The estimated RPM of the motor - max rpm is about 600
        double brest = br * 600; //The estimated RPM of the motor - max rpm is about 600
        
        double flact = FL.getEncoder().getRate()*60; //actual RPM of motor
        double fract = FR.getEncoder().getRate()*60; //actual RPM of motor
        double blact = BL.getEncoder().getRate()*60; //actual RPM of motor
        double bract = BR.getEncoder().getRate()*60; //actual RPM of motor
        
        double flerr = flest / flact;
        double frerr = frest / fract;
        double blerr = blest / blact;
        double brerr = brest / bract;
        
        double[] array = {flerr, frerr, blerr, brerr};
        
        double smallest = Double.MAX_VALUE;
        
        for (int i = 0; i < array.length; i++){ // puts the smallest error value in the largest variable
            if (array[i] > smallest){
                smallest = array [i];
            }
        }
        
        flmult = flerr / smallest; // gets a value from 0 - 1 to modify the pwm being sent
        frmult = frerr / smallest; // gets a value from 0 - 1 to modify the pwm being sent
        blmult = blerr / smallest; // gets a value from 0 - 1 to modify the pwm being sent
        brmult = brerr / smallest; // gets a value from 0 - 1 to modify the pwm being sent
        
        SmartDashboard.putNumber("FL RPM", flact);
        SmartDashboard.putNumber("FR RPM", fract);
        SmartDashboard.putNumber("BL RPM", blact);
        SmartDashboard.putNumber("BR RPM", bract);
    }
    
}
