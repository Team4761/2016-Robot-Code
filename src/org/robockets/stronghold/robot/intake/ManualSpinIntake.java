package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualSpinIntake extends Command {
	
	boolean spinForward = true; //TEMP
	public Direction direction;
	int time;
	
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
    		Robot.intake.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
