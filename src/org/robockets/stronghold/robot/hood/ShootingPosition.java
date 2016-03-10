package org.robockets.stronghold.robot.hood;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Put the highgoal shooter into shooting position.
 */
public class ShootingPosition extends Command {

    public ShootingPosition() {
    	requires(Robot.hood);
    }

    protected void initialize() {
    	Robot.hood.setAngle(45);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.hood.onTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
