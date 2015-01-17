/*
 *This class should be used for code that will run during the autonomous period of the match
 *(first 15 seconds)
 */

package org.usfirst.frc.team4068.robot.code;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous implements Runnable{
    private String thread;
    private boolean run = false;
    
    DoubleSolenoid solenoid1 = new DoubleSolenoid(0, 0, 1);
    
    public Autonomous(String thread){
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
        }else if (thread.equals("program1")){
            test1();
            Timer.delay(2);
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
        solenoid1.free();
    }
    
    private void test1(){
        
    }
    
    private void test2(){
        
    }
    
    private void test3(){
        
    }
}
