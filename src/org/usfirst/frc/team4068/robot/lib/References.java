package org.usfirst.frc.team4068.robot.lib;

import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class References {
    
    public static final XboxController DRIVER = new XboxController(USB.DRIVER);
    
    public static final XboxController CODRIVER = new XboxController(USB.CODRIVER);
    
    public static final DriveTrain DRIVE = new DriveTrain(MOTOR.DRIVE_FL, 
                                                          MOTOR.DRIVE_FR, 
                                                          MOTOR.DRIVE_BL, 
                                                          MOTOR.DRIVE_BR);
    
    public static final Motor LIGHTS = new Motor(PWM.LIGHTS);
    
    public static final BuiltInAccelerometer ACCELEROMETER = new BuiltInAccelerometer();
    public static final SerialPort ARDUINO_SERIAL = new SerialPort(9600, SerialPort.Port.kUSB);
    public static final AnalogInput ULTRASONIC = new AnalogInput(0);
    
    public static final Encoder ENCODER1 = new Encoder(DIO.ENCODER_CHA, DIO.ENCODER_CHB, false, Encoder.EncodingType.k4X);
    
    public static class PWM {
        public static final int DRIVE_FL = 0;
        public static final int DRIVE_FR = 1;
        public static final int DRIVE_BL = 2;
        public static final int DRIVE_BR = 3;
        
        public static final int ENCODER_MOTOR = 4;
        
        public static final int LIGHTS = 5;
    }
    
    public static class DIO{
        public static final int ENCODER_CHA = 0;
        public static final int ENCODER_CHB = 1;
    }
    
    public static class USB {
        public static final int DRIVER = 0;
        public static final int CODRIVER = 1;
    }
    
    public static class MOTOR {
        public static final Motor DRIVE_FL = new Motor(PWM.DRIVE_FL);
        public static final Motor DRIVE_FR = new Motor(PWM.DRIVE_FR);
        public static final Motor DRIVE_BL = new Motor(PWM.DRIVE_BL);
        public static final Motor DRIVE_BR = new Motor(PWM.DRIVE_BR);
        
        public static final Motor ENCODER = new Motor(4);
    }
    
}
