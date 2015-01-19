/*
 * This class should be used for code that will run throughout the game
 * Example: If we end up using the accelerometer to determine where we are on the field,
 *          we would want that to run as soon as the robot is on, regardless if it's
 *          in auto, teleop, or disabled.
 */

package org.usfirst.frc.team4068.robot.code;

import org.usfirst.frc.team4068.robot.lib.References;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class Global implements Runnable{
    
    BuiltInAccelerometer accel = References.ACCELEROMETER;
    
    public Global(){
        
    }

    public void run() {
        while(true){
            
        }
    }
    
    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
}
