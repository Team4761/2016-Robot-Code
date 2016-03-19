package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.CompassPIDSource;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;
import org.robockets.stronghold.robot.pidsources.GyroPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private final EncoderPIDSource leftWheelsPIDSource;
	private final EncoderPIDSource rightWheelsPIDSource;
	public final PIDController compassPID;
	public final PIDController gyroPID;
	public final PIDController leftWheelsPID;
	public final PIDController rightWheelsPID;

	
	public Drivetrain() {	
		leftWheelsPIDSource = new EncoderPIDSource(RobotMap.driveEncoderLeft, 1.0 / -14.0, PIDSourceType.kDisplacement);
		//EncoderPIDSource rightWheelPIDSource = new EncoderPIDSource(RobotMap.driveEncoderRight, 14.0, PIDSourceType.kDisplacement);
		rightWheelsPIDSource = new EncoderPIDSource(RobotMap.driveEncoderRight, ((1.0 / 360.0) * 250.0) * (1.0 / 14.0), PIDSourceType.kDisplacement);
		
		compassPID = new PIDController(0.1, 0, 0, new CompassPIDSource(), new DummyPIDOutput());
		gyroPID = new PIDController(0.01, 0.0001, 0.00001, new GyroPIDSource(), new DummyPIDOutput());
		leftWheelsPID = new PIDController(0.003, 0.00003, 0, leftWheelsPIDSource, new DummyPIDOutput());
		rightWheelsPID = new PIDController(0.003, 0, 0.00004, rightWheelsPIDSource, new DummyPIDOutput());

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
     * @param moveValue the amount to constantly move the robot by (this ignored when using encoders)
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
    		leftWheelsPID.setPID(SmartDashboard.getNumber("Left P"), SmartDashboard.getNumber("Left I"), SmartDashboard.getNumber("Left D"));
    		rightWheelsPID.setPID(SmartDashboard.getNumber("Right P"), SmartDashboard.getNumber("Right I"), SmartDashboard.getNumber("Right D"));
    		SmartDashboard.putNumber("Left Setpoint", Robot.driveTrain.getLeftDistanceSetpointInInches());
        	SmartDashboard.putNumber("Right Side Setpoint", Robot.driveTrain.getRightDistanceSetpointInInches());
        	SmartDashboard.putNumber("Drive Encoder Left", Robot.driveTrain.getLeftDistanceInInches());
        	SmartDashboard.putNumber("Drive Encoder Right", Robot.driveTrain.getRightDistanceInInches());
        	SmartDashboard.putNumber("Left PID", Robot.driveTrain.leftWheelsPID.get());
        	SmartDashboard.putNumber("Right PID", Robot.driveTrain.rightWheelsPID.get());
    		
    		RobotMap.leftDriveMotor.set(leftWheelsPID.get() * scalar);
    		//RobotMap.rightDriveMotor.set(rightWheelsPID.get() * scalar);
    		RobotMap.rightDriveMotor.set(-rightWheelsPID.get() * scalar);

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
    	SmartDashboard.putNumber("distance setpoint", distance);
    	leftWheelsPID.setSetpoint(distance);
    	rightWheelsPID.setSetpoint(distance);
    }
    
    public double getLeftDistanceInInches() {
    	return leftWheelsPIDSource.pidGet();
    }
    
    public double getRightDistanceInInches() {
    	return rightWheelsPIDSource.pidGet();
    }
    
    public double getLeftDistanceSetpointInInches() {
    	return leftWheelsPID.getSetpoint();
    }
    
    public double getRightDistanceSetpointInInches() {
    	return rightWheelsPID.getSetpoint();
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
    	leftWheelsPID.reset();
    	rightWheelsPID.reset();
    	leftWheelsPID.enable();
    	rightWheelsPID.enable();
    }
    
    public void disableWheelPID() {
    	leftWheelsPID.reset();
    	rightWheelsPID.reset();
    	setDistanceInInches(getLeftDistanceInInches());
    }
}

