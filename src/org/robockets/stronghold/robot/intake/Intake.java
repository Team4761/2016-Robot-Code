package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	

	public final PIDController encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoder, new DummyPIDOutput());
	
	public Intake() {
		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(false);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void spin(double speed) {
    	RobotMap.intakeMotor.set(speed);
    }
    
    public void spinIn() {
    	RobotMap.intakeMotor.set(0.5);
    }
    
    public void spinOut() {
    	RobotMap.intakeMotor.set(-0.5);
    }
    
    public void spinAssisted() {
    	encoderPID.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
    	
    	RobotMap.intakeMotor.set(encoderPID.get());
    }
    
    public void setIntakeAngle(double angle) {
    	 encoderPID.setSetpoint(angle);
    }
    
    public void enablePID() {
    	encoderPID.enable();
    	encoderPID.reset();
    	encoderPID.setSetpoint(RobotMap.intakeEncoder.get());
    }
    
    public void stop() {
    	RobotMap.intakeMotor.set(0);
    }
}

