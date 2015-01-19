package org.usfirst.frc.team4068.robot.lib;

import org.usfirst.frc.team4068.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SerialPort;

public class References {
    
    public static final XboxController DRIVER = new XboxController(USB.DRIVER);
    
    public static final XboxController CODRIVER = new XboxController(USB.CODRIVER);
    
    public static final Motor DRIVE_FL = new Motor(PWM.DRIVE_FL);
    public static final Motor DRIVE_FR = new Motor(PWM.DRIVE_FR);
    public static final Motor DRIVE_BL = new Motor(PWM.DRIVE_BL);
    public static final Motor DRIVE_BR = new Motor(PWM.DRIVE_BR);
    public static final DriveTrain DRIVE = new DriveTrain(DRIVE_FL, DRIVE_FR, DRIVE_BL, DRIVE_BR);
    
    public static final Motor LIGHTS = new Motor(PWM.LIGHTS);
    
    public static final BuiltInAccelerometer ACCELEROMETER = new BuiltInAccelerometer();
    public static final SerialPort ARDUINO_SERIAL = new SerialPort(9600, SerialPort.Port.kUSB);
    
    public class PWM {
        public static final int DRIVE_FL = 0;
        public static final int DRIVE_FR = 1;
        public static final int DRIVE_BL = 2;
        public static final int DRIVE_BR = 3;
        
        public static final int LIGHTS = 5;
    }
    
    public class USB {
        public static final int DRIVER = 0;
        public static final int CODRIVER = 1;
    }
    
}
