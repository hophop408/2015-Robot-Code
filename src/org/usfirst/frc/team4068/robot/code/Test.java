/*
 * This class should be used for code meant to run in test mode. None of this code will run
 * during a game, but if we want to test a subsystem, for example, without running the rest
 * of the robot with it, this will be the best place to do it.
 */
package org.usfirst.frc.team4068.robot.code;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Test implements Runnable{
    private String thread;
    private boolean run = false;
    
    //DoubleSolenoid solenoid1 = new DoubleSolenoid(0, 0, 1);
    
    public Test(String thread){
        this.thread = thread;
        //solenoid1.stopLiveWindowMode();
    }
    
    public void run(){
        run = true;
        if (thread.equals("thread1")){
            test1();
        }else if (thread.equals("thread2")){
            test2();
        }
        run = false;
    }
    
    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
    
    public boolean getRun(){
        return run;
    }
    
    public void free(){
        //solenoid1.free();
        //lights.free();
    }
   Victor jarvis = new Victor(5);
    private void test1(){
        //solenoid1.set(DoubleSolenoid.Value.kForward);
        //Timer.delay(3);
        //solenoid1.set(DoubleSolenoid.Value.kReverse);
        jarvis.set(.1);
        Timer.delay(.1);
        jarvis.set(.2);
        Timer.delay(.1);
        jarvis.set(.3);
        Timer.delay(.1);
        jarvis.set(.4);
        Timer.delay(.1);
        jarvis.set(.5);
        Timer.delay(.1);
        jarvis.set(.6);
        Timer.delay(.1);
        jarvis.set(.7);
        Timer.delay(.1);
        jarvis.set(.8);
        Timer.delay(.1);
        jarvis.set(.9);
        Timer.delay(.1);
        jarvis.set(1);
        Timer.delay(.1);
        jarvis.set(0);
        Timer.delay(.1);
    }
    
    private void test2(){
        
    }
}
