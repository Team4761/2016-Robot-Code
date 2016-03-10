package org.robockets.stronghold.robot.hood;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The part of the shooter subsystem that aims the cannonballs at a specified angle.
 */
public class Hood extends Subsystem {
	public final double HOOD_ERROR = 2;
	
	public final PIDController hoodPidController;
	
	public Hood(){
		hoodPidController = new PIDController(0.075, 0.0001, 0, RobotMap.hoodPIDSource, RobotMap.hoodMotor);
		hoodPidController.disable();
		hoodPidController.setSetpoint(0);
		hoodPidController.setContinuous(true);
		hoodPidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public void setAngle(double angle) {
    	hoodPidController.setSetpoint(angle);
    }
    
    public double getSetpoint() {
    	return hoodPidController.getSetpoint();
    }
    
    public double getAngle() {
    	return RobotMap.hoodPIDSource.pidGet();
    }
    
    public boolean onTarget() {
    	return Math.abs(Math.abs(hoodPidController.getSetpoint()) - Math.abs(getAngle())) < HOOD_ERROR;
    }

    public void enablePID() {
		hoodPidController.enable();
		hoodPidController.reset();
		hoodPidController.setSetpoint(RobotMap.hoodEncoder.get());
    }
}

