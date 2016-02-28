package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.CompassPIDSource;
import org.robockets.stronghold.robot.pidsources.DualEncoderPIDSource;
import org.robockets.stronghold.robot.pidsources.GyroPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public final PIDController compassPID;
	public final PIDController gyroPID;
	public final PIDController distancePID;
	public final PIDController encodersPID;
	
	public Drivetrain() {
		compassPID = new PIDController(0.1, 0, 0, new CompassPIDSource(), new DummyPIDOutput());
		gyroPID = new PIDController(0.01, 0.0001, 0.00001, new GyroPIDSource(), new DummyPIDOutput());
		distancePID = new PIDController(0.0018, 0.000024, 0.0005, RobotMap.driveEncoder, new DummyPIDOutput());
		encodersPID = new PIDController(0.0019, 0.0003, 0, new DualEncoderPIDSource(), new DummyPIDOutput());
		
		compassPID.disable();
		compassPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		compassPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		gyroPID.disable();
		gyroPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		gyroPID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		distancePID.disable();
		distancePID.setOutputRange(-1.0, 1.0); // Set turning speed range
		distancePID.setPercentTolerance(5.0); // Set tolerance of 5%
		
		encodersPID.disable();
		encodersPID.setOutputRange(-1.0, 1.0); // Set turning speed range
		encodersPID.setAbsoluteTolerance(5.0); // Set tolerance of 5%
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
    	if (compassAssist) { // Use compass for PID
    		driveArcade(moveValue * scalar, compassPID.get());
    	} else if (!compassAssist && !encoder){
    		driveArcade(moveValue * scalar, -gyroPID.get());
    	} else {
    		driveArcade(moveValue * scalar, -encodersPID.get());
    	}
    }
    
    /**
     * Move the robot with both rotation and distance pid
     * @param compassAssist whether the robot should use compass pid or gyro pid
     */
    public void driveAssisted(boolean compassAssist, boolean encoder, double scalar) {
    	driveAssisted(distancePID.get(), compassAssist, encoder, scalar);
    }
    
    public void setAngle(double angle, boolean compassAssist) {
    	if (compassAssist) {
    		compassPID.setSetpoint(angle);
    	} else {
    		gyroPID.setSetpoint(angle);
    	}
    }
    
    /**
     * Set the offset for a particular angle.
     * @param angle Actual degrees.
     */
    public void setOffsetAngle(double angle) {
    	//2.464 degrees per 1 inch of arclength
    	encodersPID.setSetpoint(encodersPID.getSetpoint() + ((14 / 2.464) * angle));
    }
    
    public void setDistance(double distance) {
    	distancePID.setSetpoint(distance);
    }
    
    public void setDistanceInInches(double distance) {
    	distancePID.setSetpoint(distance * 14);
    }
    
    public double getEncodersOffset() {
    	return -RobotMap.driveEncoder.get() - RobotMap.driveEncoder2.get();
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
    
    public void enableDistancePID() {
    	distancePID.reset();
    	distancePID.setSetpoint(RobotMap.driveEncoder.get());
    	distancePID.enable();
    }
    
    public void enableEncodersPID() {
    	encodersPID.enable();
    	encodersPID.reset();
    	encodersPID.setSetpoint(getEncodersOffset());
    }
}

