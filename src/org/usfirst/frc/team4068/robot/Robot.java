
package org.usfirst.frc.team4068.robot;

import org.usfirst.frc.team4068.robot.code.Autonomous;
import org.usfirst.frc.team4068.robot.code.Teleop;
import org.usfirst.frc.team4068.robot.code.Test;
import org.usfirst.frc.team4068.robot.lib.References;

import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    //Declarations
    //Threads -- When adding a new thread, be sure to add a *thread*.stop(); to disabledInit() corresponding to the new thread
    public static Teleop teleDrive;
    public static Teleop tele2;
    public static Teleop tele3;
    public static Autonomous auto1;
    public static Autonomous auto2;
    public static Autonomous auto3;
    public static Test test1;
    public static Test test2;
    public static Test test3;
    
    SendableChooser autoThread1;
    SendableChooser autoThread2;
    SendableChooser autoThread3;
    /**
     * Initialization code - runs when the robot is turned on (after the rRIO boots up)
     */
    public void robotInit() {
        System.out.println("It's Robot Time!");
        
        autoThread1 = new SendableChooser();
        autoThread1.addDefault("Yes", new Autonomous("thread1"));
        autoThread1.addObject("No", null);
        
        autoThread2 = new SendableChooser();
        autoThread2.addDefault("Yes", new Autonomous("thread2"));
        autoThread2.addObject("No", null);
        
        autoThread3 = new SendableChooser();
        autoThread3.addDefault("Yes", new Autonomous("thread3"));
        autoThread3.addObject("No", null);
        
        //SmartDashboard.putData("Run Autonomous Thread 1?", autoThread1);
        //SmartDashboard.putData("Run Autonomous Thread 2?", autoThread2);
        //SmartDashboard.putData("Run Autonomous Thread 3?", autoThread3);
    }

    /**
     * Autonomous section -- Runs for 15 seconds
     */
    
    public void autonomousPeriodic() {
        auto1 = new Autonomous("thread1"); //Initialize thread
        Timer time = new Timer();
        time.start();
        while (isAutonomous() && isEnabled() && (time.get() < 15)){
            Scheduler.getInstance().run();
            if (true){
                //start
            }
        }
        time.stop();
        System.out.printf("Autonomous Disabled, ran for: %n", time.get());
        auto1 = null;
    }

    /**
     * Teleop section -- Runs for 135 seconds (2 minutes, 15 seconds)
     */
    public void teleopPeriodic() {
        teleDrive = new Teleop("thread1");
        //tele2 = new Teleop("thread2");
        Timer time = new Timer();
        time.start();
        while (isOperatorControl() && isEnabled() && (time.get() <= 135)){
            if (!teleDrive.getRun()){ //If a thread is not running, start it again
                teleDrive.start();
            }
            //if (!tele2.getRun()){
                //tele2.start();
            //}
        }
        time.stop();
        System.out.printf("Teleop Disabled, ran for: %n", time.get());
        teleDrive.free();
        teleDrive = null;
    }
    
    /**
     * Test mode
     */
    public void testPeriodic() {
        //LiveWindow.run();
        test1 = new Test("thread1"); //Initialize thread
        Timer time = new Timer();
        time.start();
        while (isTest() && isEnabled()){
            if (!test1.getRun()){
                test1.start();
            }
        }
        time.stop();
        System.out.printf("Test Disabled, ran for: %n", time.get());
        test1.free();
        test1 = null;
    }
    
    public void disabledInit(){
        teleDrive = null;
        tele2 =  null;
        tele3 = null;
        auto1 = null;
        auto2 = null;
        auto3 = null;
        test1 = null;
        test2 = null;
        test3 = null;
    }
    
}
