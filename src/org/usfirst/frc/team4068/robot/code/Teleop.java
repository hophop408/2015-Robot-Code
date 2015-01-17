/*
 * This class should be used for code that will run during teleop (the last 135 seconds of the match)
 */
package org.usfirst.frc.team4068.robot.code;

import org.usfirst.frc.team4068.robot.Robot;
import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;
import org.usfirst.frc.team4068.robot.lib.XboxController;
import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Teleop implements Runnable{
    private String thread;
    private boolean run = false;
    
    //private Talon motor0 = new Talon(References.PWM_MOTOR);
    //private Talon motor1 = new Talon(1);
    //private Talon motor2 = new Talon(2);
    //private Talon motor3 = new Talon(3);
    //private Talon motor4 = new Talon(4);
    //private Victor motor5 = new Victor(5);
    private XboxController driver = new XboxController(References.USB_DRIVER);
    
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
    
    public void free(){
        //motor0.free();
        //motor1.free();
        //motor2.free();
        //motor3.free();
        //motor4.free();
        //motor5.free();
        driver = null;
    }
    
    //RobotDrive drive = new RobotDrive(References.FL, References.BL, References.FR, References.BR);
    Motor FL = References.FL;
    Motor FR = References.FL;
    Motor BL = References.FL;
    Motor BR = References.FL;
    Victor jarvis = new Victor(5);
    private void drive(){
        //drive.arcadeDrive(driver.getLeftY(), driver.getLeftX());
        
        //double x = driver.getLeftX();
        //double y = driver.getLeftY();
        //double r = driver.getRightX();
        
        //double fl = y -x +r;
        //double fr = -y -x +r;
        //double bl = y +x +r;
        //double br = -y +x +r;
        
        jarvis.set(.1);
        
        //FL.set(1);
        ///FR.set(1);
        //BL.set(1);
        //BR.set(1);
        
    }
    //DriveTrain d = new DriveTrain();
    private void thread2(){
        //d.drive();
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
