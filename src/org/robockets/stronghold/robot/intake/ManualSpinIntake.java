package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualSpinIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	
	int time; //Used to set timeout
	
	double speed; //Used to set speed manually
	
	/**
	 * Initalizes some variables
	 * 
	 * @param direction  Used to initalize Direction enum
	 * @param time  Takes input for time
	 * */
    public ManualSpinIntake(Direction directionEnum, int time) {
    	requires(Robot.intake);
    	this.direction = directionEnum;
    	this.time = time;
    }
    
    public ManualSpinIntake(double speed, int time) {
    	requires(Robot.intake);
    	this.speed = speed;
    	this.direction = Direction.MANUAL;
    	this.time = time;
    }
    
    public ManualSpinIntake() {
    	requires(Robot.intake);
    	this.speed = 0.5;
    	this.direction = Direction.MANUAL;
    	this.time = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (direction == Direction.FORWARD) {
    		Robot.intake.spinIn();
    	} else if (direction == Direction.BACKWARD){
    		Robot.intake.spinOut();
    	} else if (direction == Direction.MANUAL) {
    		Robot.intake.spin(speed);
    	} else {
    		Robot.intake.stopIntake();
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
    	Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
