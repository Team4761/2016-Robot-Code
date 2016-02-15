package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	if (speed == 0) {
    		Robot.shooter.setShootingWheelSpeed(0);
    	} else {
    		Robot.shooter.setShootingWheelSpeed(SmartDashboard.getNumber("New Spin Speed"));
    	}
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
