package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.stronghold.robot.Robot;

/**
 * Reset the highgoal shooter after aiming.
 */
public class RevDownShooter extends Command {

    public RevDownShooter() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.setShootingWheelSpeed(0);
    }

    protected boolean isFinished() {
        return Robot.shooter.shootingWheelOnTarget(0);
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
