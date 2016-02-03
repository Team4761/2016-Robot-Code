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
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.shooter.spinShootingWheel(0);
    }

    protected void interrupted() {
    	end();
    }
}
