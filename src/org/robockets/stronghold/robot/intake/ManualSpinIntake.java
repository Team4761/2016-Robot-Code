package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualSpinIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	
	int time; //Used to set timeout
	
	/**
	 * Initalizes some variables
	 * 
	 * @param direction  Used to initalize Direction enum
	 * @param time  Takes input for time
	 * */
    public ManualSpinIntake(Direction direction, int time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.direction = direction;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction == Direction.FORWARD) {
    		Robot.intake.spinIn();
    	} else if(direction == Direction.BACKWARD){
    		Robot.intake.spinOut();
    	} else {
    		Robot.intake.stopIntake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}