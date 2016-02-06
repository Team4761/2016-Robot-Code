package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set the robot into being a position that is able to go beneath the low bar and interact with other bits of the robot.
 */
public class Limbo extends Command {

    public Limbo() {
    	requires(Robot.shooter);
    	requires(Robot.intake);
    }

    protected void initialize() {
    	setTimeout(5);
    	Robot.shooter.setHoodAngle(-90);
    }

    protected void execute() {
    	Robot.shooter.spinHoodAssisted();
    	Robot.intake.spinOut(); // Spin out or in, idk which one is more compact.
    }

    protected boolean isFinished() {
        return Robot.shooter.hoodPidController.onTarget() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
