package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    public void initDefaultCommand() {
    	
    }
    
    public void spin(double speed){
    	RobotMap.intakeMotor.set(speed);
    }
    
    public void spinIn(){
    	RobotMap.intakeMotor.set(0.5);
    }
    
    public void spinOut(){
    	RobotMap.intakeMotor.set(-0.5);
    }
    
    public void stop(){
    	RobotMap.intakeMotor.set(0);
    }
}

