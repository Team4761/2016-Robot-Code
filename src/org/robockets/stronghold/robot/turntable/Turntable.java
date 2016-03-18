package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The plate on which other shooter components rest. Capable of turning 360 degrees.
 */
public class Turntable extends Subsystem {
    
	public final PIDController pidController;
	
	public Turntable(){
		pidController = new PIDController(0.06, 0.0005, 0, RobotMap.turntablePIDSource, RobotMap.turnTableMotor);
		
		pidController.disable();
		
		pidController.setSetpoint(0);
		pidController.setPercentTolerance(0.05);
		pidController.setContinuous(true);
		
		pidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public double getAngle() {
    	return RobotMap.turntablePIDSource.pidGet();
    }
    
    public void setAngle(double angle) {
    	if (angle > 270) {
    		System.out.println(angle % 270 + 90);
    	} else if (angle < 270) {
    		System.out.println(angle % -270 - 90);
    	} else {
    		System.out.println(angle);
    	}
    	pidController.setSetpoint(angle);
    }
    
    public double getSetpoint() {
    	return pidController.getSetpoint();
    }
    
    public boolean onTarget(){
    	return Math.abs(pidController.getSetpoint() - RobotMap.turntablePIDSource.pidGet()) < 0.5;
    }
    
    public void enablePID() {
    	pidController.enable();
    }
    
    public void disablePID() {
    	pidController.disable();
    }
}

