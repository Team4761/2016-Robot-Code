package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Put the highgoal shooter into shooting position.
 */
public class ShootingPosition extends Command {

    public ShootingPosition() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	Robot.shooter.setHoodAngle(45);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.shooter.hoodOnTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
