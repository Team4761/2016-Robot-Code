package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualSpinIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	public IntakeSide intakeSide;
	
	int time; //Used to set timeout
	
	double speed; //Used to set speed manually
	
	/**
	 * Initalizes some variables
	 * 
	 * @param direction  Used to initalize Direction enum
	 * @param time  Takes input for time
	 * */
    public ManualSpinIntake(Direction directionEnum, int time, IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    	} else {
    		requires(Robot.intakeBack);
    	}
    	this.direction = directionEnum;
    	this.intakeSide = intakeSide;
    	this.time = time;
    }
    
    public ManualSpinIntake(double speed, int time, IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    	} else {
    		requires(Robot.intakeBack);
    	}
    	this.speed = speed;
    	this.direction = Direction.MANUAL;
    	this.intakeSide = intakeSide;
    	this.time = time;
    }
    
    public ManualSpinIntake(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    	} else {
    		requires(Robot.intakeBack);
    	}
    	this.speed = 0.5;
    	this.direction = Direction.MANUAL;
    	this.intakeSide = intakeSide;
    	this.time = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (intakeSide == IntakeSide.FRONT) {
    		if (direction == Direction.FORWARD) {
    			Robot.intakeFront.spinIn();
    		} else if (direction == Direction.BACKWARD){
    			Robot.intakeFront.spinOut();
    		} else if (direction == Direction.MANUAL) {
    			Robot.intakeFront.spin(speed);
    		} else {
    			Robot.intakeFront.stopIntake();
    		}
    	} else {
    		if (direction == Direction.FORWARD) {
        		Robot.intakeBack.spinIn();
        	} else if (direction == Direction.BACKWARD){
        		Robot.intakeBack.spinOut();
        	} else if (direction == Direction.MANUAL) {
        		Robot.intakeBack.spin(speed);
        	} else {
        		Robot.intakeBack.stopIntake();
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
    		Robot.intakeFront.stopIntake();
    	} else {
    		Robot.intakeBack.stopIntake();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
