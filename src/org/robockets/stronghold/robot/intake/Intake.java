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
	
	IntakeSide intakeSide;
	
	
	public final PIDController encoderPIDFront = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoderFront, new DummyPIDOutput());
	public final PIDController encoderPIDBack = new PIDController(0, 0, 0, RobotMap.intakeEncoderBack, new DummyPIDOutput());
	
	public Intake(IntakeSide intakeSide) {
		this.intakeSide = intakeSide;
		encoderPIDFront.disable();
		encoderPIDFront.setSetpoint(0);
		encoderPIDFront.setPercentTolerance(0.05);
		encoderPIDFront.setContinuous(true);
		
		encoderPIDBack.disable();
		encoderPIDBack.setSetpoint(0);
		encoderPIDBack.setPercentTolerance(0.05);
		encoderPIDBack.setContinuous(true);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void spin(double speed) {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(speed);
    	} else {
    		RobotMap.intakeMotorBack.set(speed);
    	}
    }
    
    public void spinIn() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(0.5);
    	} else {
    		RobotMap.intakeMotorBack.set(0.5);
    	}
    }
    
    public void spinOut() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(-0.5);
    	} else {
    		RobotMap.intakeMotorBack.set(-0.5);
    	}
    }
    
    public void moveUp() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeVerticalMotorFront.set(0.5);
    	} else {
    		RobotMap.intakeVerticalMotorBack.set(0.5);
    	}
    }
    
    public void moveDown() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeVerticalMotorFront.set(-0.5);
    	} else {
    		RobotMap.intakeVerticalMotorBack.set(-0.5);
    	}
    }
    
    public void move(int speed) {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeVerticalMotorFront.set(speed);
    	} else {
    		RobotMap.intakeVerticalMotorBack.set(speed);
    	}
    }
    
    public void spinAssisted() {
    	encoderPIDFront.setPID(SmartDashboard.getNumber("P Front"), SmartDashboard.getNumber("I Front"), SmartDashboard.getNumber("D Front"));
    	encoderPIDBack.setPID(SmartDashboard.getNumber("P Back"), SmartDashboard.getNumber("I Back"), SmartDashboard.getNumber("D Back"));
    	
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(encoderPIDFront.get());
    	} else {
    		RobotMap.intakeMotorBack.set(encoderPIDBack.get());
    	}
    }
    
    public void setIntakeAngle(double angle) {
    	if (intakeSide == IntakeSide.FRONT) {
    		encoderPIDFront.setSetpoint(angle);
    	} else {
    		encoderPIDBack.setSetpoint(angle);
    	}
    }
    
    public void enablePID() {
    	if (intakeSide == IntakeSide.FRONT) {
    		encoderPIDFront.enable();
    		encoderPIDFront.reset();
    		encoderPIDFront.setSetpoint(RobotMap.intakeEncoderFront.get());
    	} else {
    		encoderPIDBack.enable();
    		encoderPIDBack.reset();
    		encoderPIDBack.setSetpoint(RobotMap.intakeEncoderFront.get());
    	}
    }
    
    public void stopIntake() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(0);
    	} else {
    		RobotMap.intakeMotorBack.set(0);
    	}
    }
    
    public void stopVertical() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeVerticalMotorFront.set(0);
    	} else {
    		RobotMap.intakeVerticalMotorBack.set(0);
    	}
    }
}

