/*
 * This class should be used for code that will run during teleop (the last 135 seconds of the match)
 */
package org.usfirst.frc.team4068.robot.code;

import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;
import org.usfirst.frc.team4068.robot.lib.Controller;
import org.usfirst.frc.team4068.robot.subsystems.BeltDrive;
import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop implements Runnable{
    private String thread;
    private boolean run = false;
    
    DriveTrain drive = References.DRIVE;
    
    Motor lights = References.LIGHTS;
    
    Controller driver = References.DRIVER;
    Controller codriver = References.CODRIVER;
    
    public Teleop(String thread){
        this.thread = thread;
        //SmartDashboard.putBoolean("Xbox", false);
    }

    @Override
    public void run() {
        run = true;
        if (thread.equals("thread1")){
            drive();
        }else if (thread.equals("thread2")){
            //thread2();
        }else if (thread.equals("thread3")){
            thread3();
        }
        run = false;
    }
    
    public boolean getRun(){
        return run;
    }
    
    public void start(){
        Thread teleop = new Thread(this);
        teleop.start();
    }
    
    public void stop(){
        drive.drive(0, 0, 0);
        lights.set(0);
        belt.run(0);
    }
    
    private void drive(){
        
        double x = 0;
        double y = 0;
        double r = 0;
        
        double exp = 3;
        
        if (SmartDashboard.getBoolean("Xbox")){
            x = -(driver.getXAxis()>=.15||driver.getXAxis()<=-.18 ? driver.getXAxis() : 0);
            y = -(driver.getYAxis()>=.15||driver.getYAxis()<=-.15 ? driver.getYAxis() : 0);
            r = (driver.getRawAxis(3) > 0? driver.getRawAxis(3): (driver.getRawAxis(2) > 0? -driver.getRawAxis(2) : 0));
        }else{
            x = -driver.getRawAxis(0);
            y = -driver.getRawAxis(1);
            r = driver.getRawAxis(2);
        }
        
        x = Math.pow(x, 3);
        y = Math.pow(y, 3);
        r = Math.pow(r, 3);
        
        drive.drive(x, y, r);
        
        //System.out.println("test");
    }
    
    //AnalogInput ultrasonic = References.ULTRASONIC;
    //Encoder encoder1 = References.ENCODER1;
    //Motor encoder_motor = References.MOTOR.ENCODER;
    int count = 0;
    private void thread2(){
        count++;
        System.out.println("test");
        //double volts = ultrasonic.getAverageVoltage();
        //double cm = volts/1024;
        //String out = String.format("Distance: %f cm", cm);
        //System.out.println(out);
        //SmartDashboard.putNumber("Ultrasonic Distance", cm);
        /*System.out.println(encoder1.getRaw());
        if (count > 0 && count < 2000){
            encoder_motor.set(.2);
        }else if(count > 2000 && count < 4000){
            encoder_motor.set(0);
        }else if(count > 4000 && count < 6000){
            encoder_motor.set(.2);
        }else{
            encoder_motor.set(0);
        }
        */
    }
    
    //DigitalInput limit = References.LIMIT1;
    
    BeltDrive belt = References.BELT;
    
    DoubleSolenoid kicker = References.KICKER;
    
    private void thread3(){
        //if (limit.get()){
            //lights.set(.2);
        //}else{
            //lights.set(0);
        //}
        
        //lights.set(driver.getRawAxis(3));
        
        //References.vert.set(driver.getRightY());
        //References.hor.set(driver.getRightX());
        
        double beltSpeed = (codriver.getRightY() > .15 || codriver.getRightY() < -.15? -codriver.getRightY(): 0);
        
        belt.run(beltSpeed);
        
        SmartDashboard.putBoolean("Limit 1 (tote)", References.LIMIT1.get());
        
        if (codriver.getRawAxis(3) > .5){
            kicker.set(Value.kForward);
        }else{
            kicker.set(Value.kReverse);
        }
        
        /*
        if (driver.getButtonA()){
            belt.set(.5);
        }else if(driver.getButtonB()){
            belt.set(-.5);
        }else{
            belt.set(0);
        }
        */
    }
    
}
