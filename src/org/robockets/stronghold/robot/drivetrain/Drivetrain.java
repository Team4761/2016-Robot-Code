package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.CompassPIDSource;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;
import org.robockets.stronghold.robot.pidsources.GyroPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public final PIDController compassPID;
	public final PIDController gyroPID;
	public final PIDController leftWheelsPID;
	public final PIDController rightWheelsPID;

	
	public Drivetrain() {	
		EncoderPIDSource leftWheelPIDSource = new EncoderPIDSource(RobotMap.driveEncoderLeft, -14, PIDSourceType.kDisplacement);
		EncoderPIDSource rightWheelPIDSource = new EncoderPIDSource(RobotMap.driveEncoderRight, 14, PIDSourceType.kDisplacement);
		
		compassPID = new PIDController(0.1, 0, 0, new CompassPIDSource(), new DummyPIDOutput());
		gyroPID = new PIDController(0.01, 0.0001, 0.00001, new GyroPIDSource(), new DummyPIDOutput());
		leftWheelsPID = new PIDController(0.1, 0, 0, leftWheelPIDSource, new DummyPIDOutput());
		rightWheelsPID = new PIDController(0.1, 0, 0, rightWheelPIDSource, new DummyPIDOutput());

		compassPID.disable();
		compassPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		compassPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		gyroPID.disable();
		gyroPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		gyroPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		leftWheelsPID.disable();
		leftWheelsPID.setOutputRange(-1.0, 1.0);
		
		rightWheelsPID.disable();
		rightWheelsPID.setOutputRange(-1.0, 1.0);
	}
	
    public void initDefaultCommand() {

    }
    
    public void driveTank(double leftValue, double rightValue) {
    	RobotMap.robotDrive.tankDrive(leftValue, rightValue);
    }
    
    public void driveArcade(double moveValue, double rotateValue) {
    	RobotMap.robotDrive.arcadeDrive(moveValue, rotateValue);
    }
    
    /**
     * Move the robot with rotation pid
     * @param moveValue the amount to constantly move the robot by
     * @param compassAssist whether the robot should use compass pid or gyro pid
     * @param scalar	The maximum speed the robot should be traveling (0-1).
     */
    public void driveAssisted(double moveValue, boolean compassAssist, boolean encoder, double scalar) {
    	//moveValue = moveValue * -1;
    	if (compassAssist) { // Use compass for PID
    		driveArcade(moveValue * scalar, compassPID.get());
    	} else if (!compassAssist && !encoder) {
    		driveArcade(moveValue * scalar, -gyroPID.get());
    	} else {
    		RobotMap.leftDriveMotor.set(leftWheelsPID.get() * scalar);
    		RobotMap.rightDriveMotor.set(rightWheelsPID.get() * scalar);
    	}
    }
    
    /**
     * Move the robot with both rotation and distance pid
     * @param compassAssist whether the robot should use compass pid or gyro pid
     */
    public void driveAssisted(boolean compassAssist, boolean encoder, double scalar) {
    	driveAssisted(0, compassAssist, encoder, scalar);
    }
    
    public void setAngle(double angle, boolean compassAssist) {
    	if (compassAssist) {
    		compassPID.setSetpoint(angle);
    	} else {
    		gyroPID.setSetpoint(angle);
    	}
    }
    
    //2.464 degrees per 1 inch of arclength

    public void setDistanceInInches(double distance) {
    	leftWheelsPID.setSetpoint(distance * 14);
    	rightWheelsPID.setSetpoint(distance * 14);
    }
    
    public double getLeftDistanceInInches() {
    	return RobotMap.driveEncoderLeft.get() / 14;
    }
    
    public double getRightDistanceInInches() {
    	return RobotMap.driveEncoderRight.get() / 14;
    }
    
    public double getLeftDistanceSetpointInInches() {
    	return leftWheelsPID.getSetpoint() / 14;
    }
    
    public double getRightDistanceSetpointInInches() {
    	return rightWheelsPID.getSetpoint() / 14;
    }
    
    public boolean encodersOnTarget() {
    	return Math.abs(leftWheelsPID.getSetpoint() - RobotMap.driveEncoderLeft.get()) < 84 && 
    			Math.abs(rightWheelsPID.getSetpoint() - RobotMap.driveEncoderRight.get()) < 84; //84 is about 6 inches of error.
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
    
    public void enableGyroPID() {
    	compassPID.disable();
    	
    	gyroPID.enable();
    	gyroPID.reset();
    	gyroPID.setSetpoint(RobotMap.navX.getYaw());
    }
    
    public void enableWheelPID() {
    	leftWheelsPID.enable();
    	rightWheelsPID.enable();
    }
}

