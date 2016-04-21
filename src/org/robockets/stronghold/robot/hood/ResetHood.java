package org.robockets.stronghold.robot.hood;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetHood extends Command {

    public ResetHood() {
        requires(Robot.hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hood.resetEncoder(Robot.hood.HOOD_START);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
