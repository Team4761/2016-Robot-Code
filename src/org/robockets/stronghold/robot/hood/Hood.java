package org.robockets.stronghold.robot.hood;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The part of the shooter subsystem that aims the cannonballs at a specified angle.
 */
public class Hood extends Subsystem {
	public final double HOOD_ERROR = 2;
	public final double HOOD_START = -75;
	public final double HOOD_MIN = -45;
	
	public final PIDController pidController;
	
	public double encoderOffset = 0;
	
	public Hood() {
		pidController = new PIDController(0.075, 0.0001, 0, RobotMap.hoodPIDSource, RobotMap.hoodMotor);
		pidController.disable();
		pidController.setSetpoint(0);
		pidController.setContinuous(true);
		pidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public void setAngle(double angle) {
    	//setSetpoint(SmartDashboard.getNumber("New Hood Angle"));
    	setSetpoint(angle);
    }
    
    public void setSetpoint(double angle) {
    	pidController.setSetpoint(angle - encoderOffset);
    }
    
    public double getSetpoint() {
    	return pidController.getSetpoint() + encoderOffset;
    }
    
    public double getAngle() {
    	return RobotMap.hoodPIDSource.pidGet() + encoderOffset;
    }
    
    public void resetEncoder(double newAngle) {
    	RobotMap.hoodEncoder.reset();
    	encoderOffset = newAngle;
    	pidController.setSetpoint(0);
    	SmartDashboard.putNumber("New Hood Angle", encoderOffset);
    }
        
    public boolean onTarget() {
    	return Math.abs(Math.abs(getSetpoint()) - Math.abs(getAngle())) < HOOD_ERROR;
    }

    public void enablePID() {
		pidController.enable();
		pidController.reset();
		pidController.setSetpoint(getAngle());
    }
}

