package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.stronghold.robot.Robot;

/**
 * Roll the jeff rollers to fire a cannonball.
 */
public class FireShooter extends Command {

    public FireShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    	setTimeout(0.7);
    }

    protected void execute() {
    	Robot.shooter.setShooterFlipper(20);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooter.setShooterFlipper(80);
    }

    protected void interrupted() {
    	end();
    }
}
