package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Set the exact position of the hood part of the high goal shooter subsystem.
 */
public class SetTurnTable extends Command {

	double angle;
	
	/**
	 * Set the angle of the turn table part of the highgoal shooter.
	 * @param angle		The angle to set the hood at.
	 */
    public SetTurnTable(double angle) {
    	requires(Robot.shooter);
    }
    
    protected void initialize() {
    	Robot.shooter.enableTurnTablePID();
    	Robot.shooter.setTurnTableAngle(angle);
    	setTimeout(20);
    }

    protected void execute() {
    	Robot.shooter.spinTurnTableAssisted();
    }

    protected boolean isFinished() {
        return Robot.shooter.turnTablePidController.onTarget() || isTimedOut();
    }

    protected void end() {
    	Robot.shooter.turnTablePidController.disable();
    	Robot.shooter.spinTurnTable(0);
    }

    protected void interrupted() {
    	end();
    }
}
