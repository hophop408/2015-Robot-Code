 package org.usfirst.frc.team4068.robot;
import org.usfirst.frc.team4068.robot.code.Autonomous;
import org.usfirst.frc.team4068.robot.code.Teleop;
import org.usfirst.frc.team4068.robot.code.Test;
import org.usfirst.frc.team4068.robot.lib.References;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    //Declarations
    References refs = new References(); // This will never be used, but is necessary to pre-load contents of References.java
    //Threads
    public static Teleop teleDrive = new Teleop("thread1");
    public static Teleop tele2 = new Teleop("thread2");
    public static Teleop tele3 = new Teleop("thread3");
    public static Autonomous auto1 = new Autonomous("thread1");
    public static Autonomous auto2 = new Autonomous("thread2");
    public static Autonomous auto3 = new Autonomous("thread3");
    public static Test test1 = new Test("thread1");
    public static Test test2 = new Test("thread2");
    public static Test  test3 = new Test("thread3");

    /**
     * Initialization code - runs once as soon as the robot is turned on (after the roboRIO boots up)
     */
    
    //I2C arduino = new I2C(I2C.Port.kOnboard, 168);
    //byte toSend[] = new byte[1];
    
    //SerialPort serial = new SerialPort(115200, SerialPort.Port.kUSB);
    public void robotInit() {
        //References.TIME.start();
        System.out.println("It's Robot Time!");
        //toSend[0] = 76;
        //arduino.transaction(toSend, 1, null, 0);
        //serial.writeString("Comm:ping");
        SmartDashboard.putNumber("Belt Time 1 (up tote)", 0);
        SmartDashboard.putNumber("Drive Back 1", 0);
        SmartDashboard.putNumber("Belt Time 2 (down tote)", 0);
        SmartDashboard.putNumber("Drive Back 2", 0);
        SmartDashboard.putNumber("Rotation Offset", 0);
        SmartDashboard.putBoolean("Xbox", false);
    }

    /**
     * Autonomous section -- Runs for 15 seconds
     */
    public void autonomousInit() {
        /*
        //init
        //this.m_ds.InDisabled(false);
        //this.m_ds.InAutonomous(true);
        Timer time = new Timer();
        time.start();
        
        //meat
        while (isAutonomous() && isEnabled() && (this.m_ds.isFMSAttached()? (time.get() < 15) : true)){
            if (!auto1.getRun()){
                auto1.start();
            }
            if (!auto2.getRun()){
                auto2.start();
            }
            if (!auto3.getRun()){
                auto3.start();
            }
        }
        
        //end
        auto1.stop();
        auto2.stop();
        auto3.stop();
        time.stop();
        System.out.printf("Autonomous Disabled, ran for: %f", time.get());
        //this.m_ds.InAutonomous(false);
         * 
         */
        auto1.program3();
    }

    /**
     * Teleop section -- Runs for 135 seconds (2 minutes, 15 seconds)
     */
    public void teleopInit() {
        //init
        //this.m_ds.InDisabled(false);
        //this.m_ds.InOperatorControl(true);
        Timer time = new Timer();
        time.start();
        
        //Loops while Teleop is enabled, and if we are attached to an FMS makes sure we stop after 135 seconds (2:15 - the teleop period)
        while (isOperatorControl() && isEnabled() && (this.m_ds.isFMSAttached()? (time.get() < 135) : true)){
            if (!teleDrive.getRun()){ //If a thread is not running, start it again
                teleDrive.start();
            }
            if (!tele2.getRun()){
                tele2.start();
            }
            if (!tele3.getRun()){
                tele3.start();
            }
        }
        
        //end
        time.stop();
        System.out.printf("Teleop Disabled, ran for: %f", time.get());
        //this.m_ds.InOperatorControl(false);
    }
    
    public void disabledInit(){
        //this.m_ds.InDisabled(true);
    }
    
}
