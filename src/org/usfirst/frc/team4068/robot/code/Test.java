/*
 * This class should be used for code meant to run in test mode. None of this code will run
 * during a game, but if we want to test a subsystem, for example, without running the rest
 * of the robot with it, this will be the best place to do it.
 */
package org.usfirst.frc.team4068.robot.code;

import org.usfirst.frc.team4068.robot.lib.Motor;
import org.usfirst.frc.team4068.robot.lib.References;

import edu.wpi.first.wpilibj.Timer;

public class Test implements Runnable{
    private String thread;
    private boolean run = false;
    
    public Test(String thread){
        this.thread = thread;
    }
    
    public void run(){
        run = true;
        if (thread.equals("thread1")){
            test1();
        }else if (thread.equals("thread2")){
            test2();
        }else if (thread.equals("thread3")){
            test3();
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
    
    Motor jarvis = References.LIGHTS;
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
    
    private void test3(){
        
    }
}
