/*
 *This class should be used for code that will run during the autonomous period of the match
 *(first 15 seconds)
 */

package org.usfirst.frc.team4068.robot.code;
import java.awt.Robot;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.AnalogChannel;
import edu.wpi.first.wpilibj.smartdashboard.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.Talon;
import edu.wpi.first.wpilibj.smartdashboard.Victor;
import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous implements Runnable{
    private String thread;
    private boolean run = false;
    
public class SmartDashboard extends Object {{
	
	    Talon drivetrainLeft = new Talon(1);
        
	    LiveWindow.addActuator("Drive train Left", (Talon) drivetrainLeft);
        
	    Talon drivetrainRight = new Talon(1);
    	
	    LiveWindow.addActuator("Drive train Right", (Talon) drivetrainRight);
        
	    RobotDrive drivetrainRobotDrive = new RobotDrive(drivetrainLeft, drivetrainRight);
        
	    RobotDrive.drivetrainRobotDrive.setSafetyEnabled(false);
        
	    RobotDrive.drivetrainRobotDrive.setExpiration(0.1);
        
	    RobotDrive.drivetrainRobotDrive.setSensitivity(0.5);
        
	    RobotDrive.drivetrainRobotDrive.setMaxOutput(1.0);
        
	    Victor elevatorMotor = new Victor(1);
	    
	    LiveWindow.addActuator("Elevator", (Victor) elevatorMotor);
        
	    AnalogChannel elevatorHeight = new AnalogChannel(1);
	    
	    LiveWindow.addSensor("Elevator", elevatorHeight);
        
	    Victor gripMotor = new Victor(1);
	    
	    LiveWindow.addActuator("Grip", "Motor", (Victor) gripMotor);

}}

    
    public Autonomous(String thread){
        this.thread = thread;
    }
    
    public void run(){
        run = true;
        if (thread.equals("thread1")){
            test1();
        }else if (thread.equals("thread2")){
            test2();
        }else if (thread.equals("thread3")){
            test3();
        }else if (thread.equals("program1")){
            test1();
            Timer.delay(2);
            test2();
        }
        run = false;
    }
    
    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
    
    public boolean getRun(){
        return run;
    }
    
    Encoder encoder1 = References.ENCODER1;
    Motor encoder_motor = References.MOTOR.ENCODER;
    int count;
    
    private void test1(){
        count++;
        encoder1.setDistancePerPulse(7);
        
        
        if (count == 1){
            encoder1.reset();
        }
        int rotations = (encoder1.get())/497;
        //if (count > 0 && count < 2000){
            //encoder_motor.set(.5);
        //}else if(count > 2000 && count < 4000){
            //encoder_motor.set(0);
        //}else if(count > 4000 && count < 6000){
            //encoder_motor.set(.5);
        //}else{
            //encoder_motor.set(0);
        //}
        if (rotations >= 20){
            encoder_motor.set(0);
        }else{
            encoder_motor.set(1);
        }
    }
    
    private void test2(){
        
    }
    
    private void test3(){
        
    }
}
