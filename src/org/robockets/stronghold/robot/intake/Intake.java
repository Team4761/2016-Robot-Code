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
	
	public final PIDController encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoder, new DummyPIDOutput());
	
	public Intake(IntakeSide intakeSide) {
		this.intakeSide = intakeSide;
		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);
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
    	encoderPID.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeMotorFront.set(encoderPID.get());
    	} else {
    		RobotMap.intakeMotorBack.set(encoderPID.get());
    	}
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

