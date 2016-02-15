package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualVerticalIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	public IntakeSide intakeSide;
	
	int time; //Used to set timeout
	
	int speed; //Used to set speed manually
	
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
    	this.direction = direction;
    	this.intakeSide = intakeSide;
    	this.time = time;
    }
    
    public ManualVerticalIntake(int speed, int time, IntakeSide intakeSide) {
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

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (intakeSide == IntakeSide.FRONT) {
    		if (direction == Direction.UP) {
    			Robot.intakeFront.moveUp();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeFront.moveDown();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeFront.move(speed);
    		} else {
    			Robot.intakeFront.stopVertical();
    		}
    	} else {
    		if (direction == Direction.UP) {
    			Robot.intakeBack.moveUp();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeBack.moveDown();
    		} else if (direction == Direction.DOWN){
    			Robot.intakeBack.move(speed);
    		} else {
    			Robot.intakeBack.stopVertical();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
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
