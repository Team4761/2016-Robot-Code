package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Enable the PIDController for the hood part of the high goal shooter subsystem.
 */
public class EnableTurnTablePID extends Command {

    public EnableTurnTablePID() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.turnTablePidController.enable();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shooter.turnTablePidController.disable();
    }

    protected void interrupted() {
    }
}
