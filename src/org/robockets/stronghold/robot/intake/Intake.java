package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem having to do with inputting cannonballs from the floor.
 */
public class Intake extends Subsystem {
	

	public final PIDController encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoder, new DummyPIDOutput());
	
	public Intake() {
		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);
		SmartDashboard.putNumber("Intake P", encoderPID.getP());
		SmartDashboard.putNumber("Intake I", encoderPID.getI());
		SmartDashboard.putNumber("Intake D", encoderPID.getD());
	}
	
    public void initDefaultCommand() {
    	
    }
    
	public void spin(double speed) {
		RobotMap.intakeMotor.set(speed);
	}
	 
    public void spinRoller(double speed) {
    	RobotMap.intakeRollerMotor.set(speed);
    }
    
    public void spinRollersIn() {
    	RobotMap.intakeRollerMotor.set(0.5);
    }
    
    
    public void moveUp() {
    	RobotMap.intakeMotor.set(0.5);
    }
    
    public void moveDown() {
    	RobotMap.intakeMotor.set(-0.5);
    }
    
    public void move(int speed) {
    	RobotMap.intakeMotor.set(speed);
    }
    
    public void spinAssisted() {
    	encoderPID.setPID(SmartDashboard.getNumber("Intake P"), SmartDashboard.getNumber("Intake I"), SmartDashboard.getNumber("Intake D"));
    	
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
    
    public void stopIntake() {
    	RobotMap.intakeMotor.set(0);
    }
    
    public void stopVertical() {
    	RobotMap.intakeMotor.set(0);
    }
}

