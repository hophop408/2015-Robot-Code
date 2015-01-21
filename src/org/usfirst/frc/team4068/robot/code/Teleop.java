/*
 * This class should be used for code that will run during teleop (the last 135 seconds of the match)
 */
package org.usfirst.frc.team4068.robot.code;

import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;
import org.usfirst.frc.team4068.robot.lib.XboxController;
import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop implements Runnable{
    private String thread;
    private boolean run = false;
    
    DriveTrain drive = References.DRIVE;
    
    Motor lights = References.LIGHTS;
    
    XboxController driver = References.DRIVER;
    
    public Teleop(String thread){
        this.thread = thread;
    }

    @Override
    public void run() {
        run = true;
        if (thread.equals("thread1")){
            drive();
        }else if (thread.equals("thread2")){
            thread2();
        }else if (thread.equals("thread3")){
            thread3();
        }
        run = false;
    }
    
    private void drive(){
        double x = (driver.getLeftX()>=.15||driver.getLeftX()<=-.18 ? driver.getLeftX() : 0);
        double y = (driver.getLeftY()>=.15||driver.getLeftY()<=-.15 ? driver.getLeftY() : 0);
        double r = (driver.getRightX()>=.15||driver.getRightX()<=-.2 ? driver.getRightX() : 0);
        
        drive.drive(x, y, r);
        
        //System.out.println("test");
    }
    
    AnalogInput ultrasonic = References.ULTRASONIC;
    Encoder encoder1 = References.ENCODER1;
    Motor encoder_motor = References.MOTOR.ENCODER;
    int count = 0;
    private void thread2(){
        count++;
        System.out.println("test");
        double volts = ultrasonic.getAverageVoltage();
        double cm = volts/1024;
        String out = String.format("Distance: %f cm", cm);
        System.out.println(out);
        SmartDashboard.putNumber("Ultrasonic Distance", cm);
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
    
    private void thread3(){
        
    }
    
    public boolean getRun(){
        return run;
    }
    
    public void start(){
        Thread teleop = new Thread(this);
        teleop.start();
    }
    
}
