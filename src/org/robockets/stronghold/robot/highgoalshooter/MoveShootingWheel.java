package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set the shooting wheel at a desired speed.
 */
public class MoveShootingWheel extends Command {

	double speed;
	
    public MoveShootingWheel(double speed) {
    	requires(Robot.shooter);
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.shooter.setShootingWheelSpeed(speed);
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.shooter.shootingWheelOnTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
