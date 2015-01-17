package org.usfirst.frc.team4068.robot.lib;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class References {
    public static final int PWM_MOTOR = 0;
    public static final int PWM_MOTOR2 = 1;
    public static final int USB_DRIVER = 0;
    public static final int RELAY_SOLENOID1 = 0;
    public static final int RELAY_SOLENOID2 = 1;
    //public static final Talon TALON1 = new Talon(1);
    public static final Motor FL = new Motor(0, false);
    public static final Motor FR = new Motor(1, true);
    public static final Motor BL = new Motor(2, true);
    public static final Motor BR = new Motor(3, false);
    //public static final Victor lights = new Victor(5);
}
