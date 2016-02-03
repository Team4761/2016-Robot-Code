package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.stronghold.robot.Robot;

/**
 * Roll the jeff rollers to fire a cannonball.
 */
public class FireShooter extends Command {

    public FireShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.7);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.spinJeffRollers(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.spinJeffRollers(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
