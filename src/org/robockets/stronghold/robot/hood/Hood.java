package org.robockets.stronghold.robot.hood;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The part of the shooter subsystem that aims the cannonballs at a specified angle.
 */
public class Hood extends Subsystem {
	public final double HOOD_ERROR = 2;
	
	public final PIDController pidController;
	
	public Hood(){
		pidController = new PIDController(0.075, 0.0001, 0, RobotMap.hoodPIDSource, RobotMap.hoodMotor);
		pidController.disable();
		pidController.setSetpoint(0);
		pidController.setContinuous(true);
		pidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public void setAngle(double angle) {
    	pidController.setSetpoint(angle);
    }
    
    public double getSetpoint() {
    	return pidController.getSetpoint();
    }
    
    public double getAngle() {
    	return RobotMap.hoodPIDSource.pidGet();
    }
    
    public boolean onTarget() {
    	return Math.abs(Math.abs(pidController.getSetpoint()) - Math.abs(getAngle())) < HOOD_ERROR;
    }

    public void enablePID() {
		pidController.enable();
		pidController.reset();
		pidController.setSetpoint(RobotMap.hoodEncoder.get());
    }
}

