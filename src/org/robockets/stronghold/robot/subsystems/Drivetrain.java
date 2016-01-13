package org.robockets.stronghold.robot.subsystems;

import org.robockets.stronghold.robot.CompassPIDSource;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.drivetrain.DrivePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private final PIDController compassPID = new PIDController(0.1, 0, 0, new CompassPIDSource(), new DrivePIDOutput());

	public Drivetrain() {
		compassPID.disable();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveTank(double leftValue, double rightValue) {
    	RobotMap.robotDrive.tankDrive(leftValue, rightValue);
    }
    
    public void driveArcade(double moveValue, double rotateValue) {
    	RobotMap.robotDrive.arcadeDrive(moveValue, rotateValue);
    }
    
    /*
     * Drive with PID. First must enable the correct PID
     */
    public void driveAssisted(double moveValue, double angle, boolean compassAssist) {
    	if (compassAssist) { // Use compass for PID
    		driveArcade(moveValue, compassPID.get());
    	} else {
    		// TODO Implement gyro PID logic
    	}
    }
    
    public void setAngle(double angle, boolean compassAssist) {
    	if (compassAssist) {
    		compassPID.setSetpoint(angle);
    	} else {
    		// TODO Implement gyro PID logic
    	}
    }
    
    public void stop() {
    	driveTank(0, 0);
    }
    
    public void enableCompassPID() {
    	// TODO Once gyro PID is implemented, first disable gyro PID
    	
    	compassPID.enable();
    	compassPID.reset();
    	compassPID.setSetpoint(RobotMap.navX.getCompassHeading()); // Make sure setpoint starts as current position
    }
}

