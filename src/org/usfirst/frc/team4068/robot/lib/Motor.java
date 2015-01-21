package org.usfirst.frc.team4068.robot.lib;

import edu.wpi.first.wpilibj.Talon;

public class Motor extends Talon{
    
    boolean invert;
    
    public Motor(int pwm, boolean invert) {
        super(pwm);
        this.invert = invert;
    }
    public Motor(int pwm) {
        super(pwm);
        invert = false;
    }
    
    public void set(double speed){
        super.set(invert ? -speed : speed);
    }
}
