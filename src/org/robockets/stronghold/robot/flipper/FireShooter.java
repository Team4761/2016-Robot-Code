package org.robockets.stronghold.robot.flipper;

import edu.wpi.first.wpilibj.command.Command;

import org.robockets.stronghold.robot.Robot;

/**
 * 
 */
public class FireShooter extends Command {

    public FireShooter() {
        requires(Robot.flipper);
    }

    protected void initialize() {
    	setTimeout(0.7);
    }

    protected void execute() {
    	Robot.flipper.setAngle(0.3);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	Robot.flipper.setAngle(1);
    }

    protected void interrupted() {
    	end();
    }
}
