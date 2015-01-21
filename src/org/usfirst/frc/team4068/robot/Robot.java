
package org.usfirst.frc.team4068.robot;

import org.usfirst.frc.team4068.robot.code.Autonomous;
import org.usfirst.frc.team4068.robot.code.Teleop;
import org.usfirst.frc.team4068.robot.code.Test;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
    public static Teleop teleDrive = new Teleop("thread1");
    public static Teleop tele2 = new Teleop("thread2");
    public static Teleop tele3 = new Teleop("thread3");
    public static Autonomous auto1 = new Autonomous("thread1");
    public static Autonomous auto2 = new Autonomous("thread2");
    public static Autonomous auto3 = new Autonomous("thread3");
    public static Test test1 = new Test("thread1");
    public static Test test2 = new Test("thread2");
    public static Test test3 = new Test("thread3");
    
    /**
     * Initialization code - runs once as soon as the robot is turned on (after the roboRIO boots up)
     */
    public void robotInit() {
        System.out.println("It's Robot Time!");
    }

    /**
     * Autonomous section -- Runs for 15 seconds
     */
    public void autonomousPeriodic() {
        Timer time = new Timer();
        time.start();
        while (isAutonomous() && isEnabled() && (time.get() < 15)){
            if (!auto1.getRun()){
                auto1.start();
            }
        }
        time.stop();
        System.out.printf("Autonomous Disabled, ran for: %f", time.get());
    }

    /**
     * Teleop section -- Runs for 135 seconds (2 minutes, 15 seconds)
     */
    public void teleopPeriodic() {
        Timer time = new Timer();
        time.start();
        while (isOperatorControl() && isEnabled() && (time.get() <= 135)){
            if (!teleDrive.getRun()){ //If a thread is not running, start it again
                teleDrive.start();
            }
            if (!tele2.getRun()){
                tele2.start();
            }
        }
        time.stop();
        System.out.printf("Teleop Disabled, ran for: %f", time.get());
    }
    
    /**
     * Test mode
     */
    public void testPeriodic() {
    }
    
    public void disabledInit(){
        
    }
    
}
