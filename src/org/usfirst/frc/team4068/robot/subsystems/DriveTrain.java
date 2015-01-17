package org.usfirst.frc.team4068.robot.subsystems;

import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;

public class DriveTrain {
    Motor frontRight = new Motor(References.PWM_MOTOR2);
    public void drive(){
        frontRight.set(1);
    }
}
