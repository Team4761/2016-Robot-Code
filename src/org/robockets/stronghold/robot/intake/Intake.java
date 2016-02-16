package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem having to do with inputting cannonballs from the floor.
 */
public class Intake extends Subsystem {
	

	public final PIDController encoderPID;
	IntakeSide intakeSide;
	
	public Intake(IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			encoderPID  = new PIDController(0.0, 0.0, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
		} else {
			encoderPID = new PIDController(0.0, 0.0, 0, RobotMap.intakeEncoderBack, RobotMap.intakeVerticalMotorBack);
		}
		
		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);
	}
	
    public void initDefaultCommand() {
    	
    }
    
	public void move(double speed) {
		encoderPID.disable();
		if (intakeSide == IntakeSide.FRONT) {
			RobotMap.intakeVerticalMotorFront.set(speed);
		} else {
			RobotMap.intakeVerticalMotorBack.set(speed);
		}
		encoderPID.setSetpoint(encoderPID.get());
	}
	
	public void move(int setpoint) {
		encoderPID.setSetpoint((double) setpoint); //TEMP
	}
	 
    public void spinRoller(double speed, int time) {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeRollerMotorFront.set(speed);
    	} else {
    		RobotMap.intakeRollerMotorBack.set(speed);
    	}
    }
    
    public void spinRollersIn() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeRollerMotorFront.set(0.5);
    	} else {
    		RobotMap.intakeRollerMotorBack.set(0.5);
    	}
    }
    
    public void spinRollersOut() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeRollerMotorFront.set(-0.5);
    	} else {
    		RobotMap.intakeRollerMotorBack.set(-0.5);
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
    
    
    public void moveAssisted() {
    	encoderPID.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeVerticalMotorFront.set(encoderPID.get());
    	} else {
    		RobotMap.intakeVerticalMotorBack.set(encoderPID.get());
    	}
    }
    
    public void setIntakeAngle(double angle) {
    	 encoderPID.setSetpoint(angle);
    }
    
    public void enablePID() {
    	encoderPID.enable();
    	encoderPID.reset();
    	if (intakeSide == IntakeSide.FRONT) {
    		encoderPID.setSetpoint(RobotMap.intakeEncoderFront.get());
    	} else {
    		encoderPID.setSetpoint(RobotMap.intakeEncoderBack.get());
    	}
    }
    
    public void stopIntake() {
    	if (intakeSide == IntakeSide.FRONT) {
    		RobotMap.intakeRollerMotorFront.set(0);
    	} else {
    		RobotMap.intakeRollerMotorBack.set(0);
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

