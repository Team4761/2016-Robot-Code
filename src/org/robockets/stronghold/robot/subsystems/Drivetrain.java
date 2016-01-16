package org.robockets.stronghold.robot.subsystems;

import org.robockets.stronghold.robot.CompassPIDSource;
import org.robockets.stronghold.robot.GyroPIDSource;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.drivetrain.DrivePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public final PIDController compassPID = new PIDController(0.1, 0, 0, new CompassPIDSource(), new DrivePIDOutput());
	public final PIDController gyroPID = new PIDController(0.1, 0, 0, new GyroPIDSource(), new DrivePIDOutput());
	
	public Drivetrain() {
		compassPID.disable();
		compassPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		compassPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		gyroPID.disable();
		gyroPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		gyroPID.setPercentTolerance(5.0); // Set tolerance of 5%
	}
	
    public void initDefaultCommand() {

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
    		driveArcade(moveValue, gyroPID.get());
    	}
    }
    
    public void setAngle(double angle, boolean compassAssist) {
    	if (compassAssist) {
    		compassPID.setSetpoint(angle);
    	} else {
    		gyroPID.setSetpoint(angle);
    	}
    }
    
    public void stop() {
    	driveTank(0, 0);
    }
    
    public void enableCompassPID() {
    	gyroPID.disable();
    	
    	compassPID.enable();
    	compassPID.reset();
    	compassPID.setSetpoint(RobotMap.navX.getCompassHeading()); // Make sure setpoint starts as current position
    }
    
    public void enableGyroPID(){
    	compassPID.disable();
    	
    	gyroPID.enable();
    	gyroPID.reset();
    	gyroPID.setSetpoint(RobotMap.navX.getYaw());
    }
}

