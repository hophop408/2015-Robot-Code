package org.usfirst.frc.team4068.robot.lib;

import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class References {
    
    public References(){
        //should be called in Robot.java
        //This will instance the code in this class, so it (the objects and variables)
        //can be called later.
    }
    
    //objects should be marked public static
    public static Servo vert = new Servo(6);
    public static Servo hor = new Servo(7);
    
    public static XboxController DRIVER = new XboxController(USB.DRIVER);
    
    public static XboxController CODRIVER = new XboxController(USB.CODRIVER);
    
    public static DriveTrain DRIVE = new DriveTrain(MOTOR.DRIVE_FL, 
                                                    MOTOR.DRIVE_FR, 
                                                    MOTOR.DRIVE_BL, 
                                                    MOTOR.DRIVE_BR);
    
    public static Motor LIGHTS = new Motor(PWM.LIGHTS);
    
    public static BuiltInAccelerometer ACCELEROMETER = new BuiltInAccelerometer();
    //public static SerialPort ARDUINO_SERIAL = new SerialPort(9600, SerialPort.Port.kUSB);
    public static AnalogInput ULTRASONIC = new AnalogInput(0);
    
    public static DigitalInput LIMIT1 = new DigitalInput(6);
    
    public static Encoder ENCODER1;
    
    //public static Timer TIME = new Timer(); //global timer that starts when the robot is turned on - used to run code that needs to wait without using the wait function and halting other code in the thread
    
    //Unchanging values (PWMs, mathematical constants, etc) should be marked public static final
    public static class PWM {
        public static final int DRIVE_FL = 0;
        public static final int DRIVE_FR = 1;
        public static final int DRIVE_BL = 2;
        public static final int DRIVE_BR = 3;
        
        public static final int ENCODER_MOTOR = 4;
        
        public static final int LIGHTS = 5;
    }
    
    public static class DIO{
        public static final int ENCODER_CHA = 2;
        public static final int ENCODER_CHB = 3;
    }
    
    public static class USB {
        public static final int DRIVER = 0;
        public static final int CODRIVER = 1;
    }
    
    public static class MOTOR {
        public static Motor DRIVE_FL = new Motor(PWM.DRIVE_FL);
        public static Motor DRIVE_FR = new Motor(PWM.DRIVE_FR);
        public static Motor DRIVE_BL = new Motor(PWM.DRIVE_BL);
        public static Motor DRIVE_BR = new Motor(PWM.DRIVE_BR);
        
        public static Motor ENCODER = new Motor(4);
        
    }
    
}
