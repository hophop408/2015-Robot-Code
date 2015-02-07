package org.usfirst.frc.team4068.robot.subsystems;
import org.usfirst.frc.team4068.robot.lib.Motor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
*
*
* @author brandonpfoff
*
*/
public class BeltDrive {
    Motor beltMotor;
    
    public BeltDrive(Motor belt, DoubleSolenoid brakes){
        System.out.println("New instance of Belt Drive created");
        beltMotor = belt;
        this.brakes = brakes;
    }
    
    public void run(double speed)
    {
        beltMotor.set(speed);
    }
    /**
    * ^
    * Belt |
    *
    *
    * This is the start of the pneumantics.
    *
    *
    * Pneumatics |
    * v
    **/
    
    DoubleSolenoid brakes;
    
    public enum Mode{
        TELEOP(1),
        AUTO(2),
        TEST(3);
        private int value;
        private Mode(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
    
    public void Brakes(Mode state)
    {
        switch(state.getValue())
        {
            case 1:
                brakes.set(DoubleSolenoid.Value.kForward);
                break;
            case -1:
                brakes.set(DoubleSolenoid.Value.kReverse);
                break;
            case 0:
                brakes.set(DoubleSolenoid.Value.kOff);
                break;
            default:
                break;
        }
    }
}