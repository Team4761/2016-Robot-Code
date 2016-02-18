package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set the exact position of the hood part of the high goal shooter subsystem.
 */
public class SetTurnTable extends Command {

	float angle;
	
	/**
	 * Set the angle of the hood part of the highgoal shooter.
	 * @param angle		The angle to set the hood at.
	 */
    public SetTurnTable(float angle) {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	Robot.shooter.turnTablePidController.setSetpoint(angle);
    	setTimeout(5);
    }

    protected void execute() {
    	Robot.shooter.spinTurnTableAssisted();
    }

    protected boolean isFinished() {
        return Robot.shooter.turnTablePidController.onTarget() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
