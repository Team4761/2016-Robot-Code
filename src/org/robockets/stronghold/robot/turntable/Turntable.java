package org.robockets.stronghold.robot.turntable;

import java.util.HashMap;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The plate on which other shooter components rest. Capable of turning 360 degrees.
 */
public class Turntable extends Subsystem {
	
	public final double MIDPOINT = 2;
	public final double MOTOR_SIDE_LIMIT = -15;
	public final double OPEN_SIDE_LIMIT = 25;
	
	public boolean atLimit = false;
	
	public double negativeLimit = MOTOR_SIDE_LIMIT;
	public double positiveLimit = OPEN_SIDE_LIMIT;
    
	public final PIDController pidController;
	public final EncoderPIDSource encoder;
	
	public final double factor = 1.0; //0.5;
	public final double TARGET_OFFSET = 4; // Bigger means <- left
	
	
    public final double CALIBRATION_START = -20;
    public final double CALIBRATION_INCREMENT = 0.5;
    
	private HashMap<Double, Double> visionConversion = new HashMap<Double, Double>();
	
	public Turntable(){
		encoder = new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement);
		pidController = new PIDController(75.0 / 1000.0, 4.0 / 1000.0, 30.0 / 1000.0, encoder, RobotMap.turnTableMotor);
		
		pidController.disable();
		
		pidController.setSetpoint(0);
		pidController.setPercentTolerance(0.05);
		pidController.setContinuous(true);
		
		pidController.enable();
		
		visionConversion.put(-20.0, 0.0);
		visionConversion.put(-19.5, 0.0);
		visionConversion.put(-19.0, 0.0);
		visionConversion.put(-18.5, 0.0);
		visionConversion.put(-18.0, 0.0);
		visionConversion.put(-17.5, 0.0);
		visionConversion.put(-17.0, 0.0);
		visionConversion.put(-16.5, 0.0);
		visionConversion.put(-16.0, 0.0);
		visionConversion.put(-15.5, 0.0);
		visionConversion.put(-15.0, 0.0);
		visionConversion.put(-14.5, 0.0);
		visionConversion.put(-14.0, 0.0);
		visionConversion.put(-13.5, 0.0);
		visionConversion.put(-13.0, 0.0);
		visionConversion.put(-12.5, 0.0);
		visionConversion.put(-12.0, 0.0);
		visionConversion.put(-11.5, 0.0);
		visionConversion.put(-11.0, 0.0);
		visionConversion.put(-10.5, 0.0);
		visionConversion.put(-10.0, 0.0);
		visionConversion.put(-9.5, 0.0);
		visionConversion.put(-9.0, 0.0);
		visionConversion.put(-8.5, 0.0);
		visionConversion.put(-8.0, 0.0);
		visionConversion.put(-7.5, 0.0);
		visionConversion.put(-7.0, 0.0);
		visionConversion.put(-6.5, 0.0);
		visionConversion.put(-6.0, 0.0);
		visionConversion.put(-5.5, 0.0);
		visionConversion.put(-5.0, 0.0);
		visionConversion.put(-4.5, 0.0);
		visionConversion.put(-4.0, 0.0);
		visionConversion.put(-3.5, 0.0);
		visionConversion.put(-3.0, 0.0);
		visionConversion.put(-2.5, 0.0);
		visionConversion.put(-2.0, 0.0);
		visionConversion.put(-1.5, 0.0);
		visionConversion.put(-1.0, 0.0);
		visionConversion.put(-0.5, 0.0);
		visionConversion.put(0.0, 0.0);
		visionConversion.put(0.5, 0.0);
		visionConversion.put(1.0, 0.0);
		visionConversion.put(1.5, 0.0);
		visionConversion.put(2.0, 0.0);
		visionConversion.put(2.5, 0.0);
		visionConversion.put(3.0, 0.0);
		visionConversion.put(3.5, 0.0);
		visionConversion.put(4.0, 0.0);
		visionConversion.put(4.5, 0.0);
		visionConversion.put(5.0, 0.0);
		visionConversion.put(5.5, 0.0);
		visionConversion.put(6.0, 0.0);
		visionConversion.put(6.5, 0.0);
		visionConversion.put(7.0, 0.0);
		visionConversion.put(7.5, 0.0);
		visionConversion.put(8.0, 0.0);
		visionConversion.put(8.5, 0.0);
		visionConversion.put(9.0, 0.0);
		visionConversion.put(9.5, 0.0);
		visionConversion.put(10.0, 0.0);
		visionConversion.put(10.5, 0.0);
		visionConversion.put(11.0, 0.0);
		visionConversion.put(11.5, 0.0);
		visionConversion.put(12.0, 0.0);
		visionConversion.put(12.5, 0.0);
		visionConversion.put(13.0, 0.0);
		visionConversion.put(13.5, 0.0);
		visionConversion.put(14.0, 0.0);
		visionConversion.put(14.5, 0.0);
		visionConversion.put(15.0, 0.0);
		visionConversion.put(15.5, 0.0);
		visionConversion.put(16.0, 0.0);
		visionConversion.put(16.5, 0.0);
		visionConversion.put(17.0, 0.0);
		visionConversion.put(17.5, 0.0);
		visionConversion.put(18.0, 0.0);
		visionConversion.put(18.5, 0.0);
		visionConversion.put(19.0, 0.0);
		visionConversion.put(19.5, 0.0);
		visionConversion.put(20.0, 0.0);
	}
	
    public void initDefaultCommand() {
    }
    
    public double getAngle() {
    	return encoder.pidGet();
    }
    
    public void setAngle(double angle) {
    	//pidController.setSetpoint(SmartDashboard.getNumber("New Turntable"));
    	
    	atLimit = false;
    	if (angle > positiveLimit) {
    		angle = positiveLimit;
    		atLimit = true;
    	} else if (angle < negativeLimit) {
    		angle = negativeLimit;
    		atLimit = true;
    	}
    	
    	if (angle < MIDPOINT) {
    		Robot.hood.negativeLimit = Robot.hood.HOOD_MIN;
    	} else {
    		Robot.hood.negativeLimit = Robot.hood.HOOD_MIN_NO_MOTOR;
    	}
    	
    	pidController.setSetpoint(angle);
    }
    
    public double getSetpoint() {
    	return pidController.getSetpoint();
    }
    
    public boolean onTarget(){
    	return Math.abs(pidController.getSetpoint() - encoder.pidGet()) < 0.5;
    }
    
    public void enablePID() {
    	pidController.enable();
    }
    
    public void disablePID() {
    	pidController.disable();
    }
    
    public double convertVisionAngle(double visionAngle) {    	
    	// Round vision angle to 0.5
    	return visionConversion.get(visionAngle);
    }
}

