package org.robockets.stronghold.robot.flipper;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterFlipper extends Command {
	
	double angle;

    public SetShooterFlipper(double angle) {
    	requires(Robot.flipper);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flipper.setAngle(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
