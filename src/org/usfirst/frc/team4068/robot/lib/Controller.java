package org.usfirst.frc.team4068.robot.lib;

import edu.wpi.first.wpilibj.Joystick;

public class Controller extends Joystick{
    
    Types type;
    
    public Controller(int port, Types type) {
        super(port);
        this.type = type;
    }
    
    public Controller(int port){
        super(port);
        type = Types.Xbox;
    }
    
    public double getXAxis() {
        return getX();
    }
    
    public double getYAxis() {
        return getY();
    }
    
    public double getRotation() {
        if (type.getType()==0){
            return getRawAxis(4);
        }else if (type.getType()==1){
            return getRawAxis(2);
        }else{
            return 0.0;
        }
    }
    
    public double getRightY() {
        return getRawAxis(5);
    }
    
    public boolean getButtonA() {
        return getRawButton(1);
    }
    
    public boolean getButtonB() {
        return getRawButton(2);
    }
    
    public boolean getButtonX() {
        return getRawButton(3);
    }
    
    public boolean getButtonY() {
        return getRawButton(4);
    }
    
    public boolean getLeftBumper() {
        return getRawButton(5);
    }
    
    public boolean getRightBumper() {
        return getRawButton(6);
    }
    
    public enum Types {
        Xbox("Xbox 360 For Windows (Controller)", 0),
        X3D("Logitech Extreme 3D", 1);
        
        private String controllerName;
        private int controllerType;
        
        Types(String name, int type){
            controllerName = name;
            controllerType = type;
        }
        
        public String getName(){
            return controllerName;
        }
        
        public int getType(){
            return controllerType;
        }
    }
}
