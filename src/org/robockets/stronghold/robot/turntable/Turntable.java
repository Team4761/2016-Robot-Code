package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The plate on which other shooter components rest. Capable of turning 360 degrees.
 */
public class Turntable extends Subsystem {
    
	public final PIDController pidController;
	public final EncoderPIDSource encoder;
	
	public Turntable(){
		encoder = new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement);
		pidController = new PIDController(0.05, 0.002, 0, encoder, RobotMap.turnTableMotor);
		
		pidController.disable();
		
		pidController.setSetpoint(0);
		pidController.setPercentTolerance(0.05);
		pidController.setContinuous(true);
		
		pidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public double getAngle() {
    	return encoder.pidGet();
    }
    
    public void setAngle(double angle) {
    	pidController.setSetpoint(angle);
    	//pidController.setSetpoint(SmartDashboard.getNumber("New Turntable"));
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
}

