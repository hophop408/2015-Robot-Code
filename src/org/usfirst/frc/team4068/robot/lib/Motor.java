package org.usfirst.frc.team4068.robot.lib;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;

public class Motor extends Talon{
    
    boolean invert;
    
    private Encoder encoder;
    
    int signalsPerRotation;
    
    public Motor(int pwm, boolean invert) {
        super(pwm);
        this.invert = invert;
        this.encoder = null;
    }
    
    public Motor(int pwm, boolean invert, Encoder encoder, boolean reverseDirection, int signalsPerRotation) {
        super(pwm);
        this.invert = invert;
        this.encoder = encoder;
        this.encoder.setReverseDirection(reverseDirection);
        this.signalsPerRotation = signalsPerRotation;
    }
    
    public Motor(int pwm, boolean invert, Encoder encoder, double dpp) {
        super(pwm);
        this.invert = invert;
        this.encoder = encoder;
        this.encoder.setDistancePerPulse(dpp);
        this.encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        //this.signalsPerRotation = signalsPerRotation;
    }
    
    public Motor(int pwm) {
        super(pwm);
        invert = false;
    }
    
    public Motor(int pwm, Encoder encoder, boolean reverseDirection, int signalsPerRotation) {
        super(pwm);
        invert = false;
        this.encoder = encoder;
        this.encoder.setReverseDirection(reverseDirection);
        this.signalsPerRotation = signalsPerRotation;
    }
    
    public Motor(int pwm, Encoder encoder, int signalsPerRotation) {
        super(pwm);
        invert = false;
        this.encoder = encoder;
        this.signalsPerRotation = signalsPerRotation;
    }
    
    public void set(double speed){
        System.out.println("Setting motor speed");
        super.set(invert ? -speed : speed);
    }
    
    public Encoder getEncoder(){
        return encoder;
    }
    
    public int getRotations(){
        int rotations = Math.floorDiv(encoder.getRaw(), signalsPerRotation);
        return rotations;
    }
    
    public int getRaw(){
        return encoder.getRaw();
    }
}
