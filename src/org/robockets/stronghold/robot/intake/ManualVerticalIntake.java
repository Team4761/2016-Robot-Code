package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualVerticalIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	public IntakeSide intakeSide;
	
	int time; //Used to set timeout
	
	double speed; //Used to set speed manually
	
	boolean encoderPID = false;
	
	/**
	 * Initalizes some variables
	 * 
	 * @param direction  Used to initalize Direction enum
	 * @param time  Takes input for time
	 * */
    public ManualVerticalIntake(Direction direction, int time, IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    	} else {
    		requires(Robot.intakeBack);
    	}
    	this.intakeSide = intakeSide;
    	this.direction = direction;
    	this.time = time;
    }
    
    public ManualVerticalIntake(double speed, int time, IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    	} else {
    		requires(Robot.intakeBack);
    	}
    	this.intakeSide = intakeSide;
    	this.speed = speed;
    	this.direction = Direction.MANUAL;
    	this.time = time;
    }
    
    public ManualVerticalIntake(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    		SmartDashboard.putNumber("Intake P", Robot.intakeFront.encoderPID.getP());
        	SmartDashboard.putNumber("Intake I", Robot.intakeFront.encoderPID.getI());
        	SmartDashboard.putNumber("Intake D", Robot.intakeFront.encoderPID.getD());
    	} else {
    		requires(Robot.intakeBack);
    		SmartDashboard.putNumber("Intake P", Robot.intakeBack.encoderPID.getP());
        	SmartDashboard.putNumber("Intake I", Robot.intakeBack.encoderPID.getI());
        	SmartDashboard.putNumber("Intake D", Robot.intakeBack.encoderPID.getD());
    	}
    	this.intakeSide = intakeSide;
    	this.speed = 0.5;
    	this.direction = Direction.MANUAL;
    	this.time = 0;
    	
    	SmartDashboard.putNumber("Intake Start", 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.intake.encoderPID.setPID(SmartDashboard.getNumber("Intake P"), SmartDashboard.getNumber("Intake I"), SmartDashboard.getNumber("Intake D"));
    	
    	if (SmartDashboard.getNumber("Intake Start") == 1) {
    		if (intakeSide == IntakeSide.FRONT) {
    			Robot.intakeFront.encoderPID.enable();
    		} else {
    			Robot.intakeBack.encoderPID.enable();
    		}
    		encoderPID = true;
    	} else {
    		if (encoderPID) {
    			encoderPID = false;
    			if (intakeSide == IntakeSide.FRONT) {
    				Robot.intakeFront.encoderPID.disable();
    			} else {
    				Robot.intakeBack.encoderPID.disable();
    			}
    		}
    		
    	}
    	
    	if (intakeSide == IntakeSide.FRONT) {
    		if (direction == Direction.UP) {
    			Robot.intakeFront.moveUp();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeFront.moveDown();
    		} else if (direction == Direction.MANUAL){
    			Robot.intakeFront.move(speed);
    		} else {
    			Robot.intakeFront.stopVertical();
    		}
    	} else {
    		if (direction == Direction.UP) {
    			Robot.intakeBack.moveUp();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeBack.moveDown();
    		} else if (direction == Direction.MANUAL){
    			Robot.intakeBack.move(speed);
    		} else {
    			Robot.intakeBack.stopVertical();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time == 0) {
    		return false;
    	} else {
    		return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (intakeSide == IntakeSide.FRONT) {
    		Robot.intakeFront.stopVertical();
    	} else {
    		Robot.intakeBack.stopVertical();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
