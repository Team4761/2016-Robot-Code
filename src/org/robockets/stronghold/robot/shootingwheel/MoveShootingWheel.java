package org.robockets.stronghold.robot.shootingwheel;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Set the shooting wheel at a desired speed.
 */
public class MoveShootingWheel extends Command {

	double speed;
	
    public MoveShootingWheel(double speed) {
    	requires(Robot.shootingWheel);
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.shootingWheel.setSpeed(speed);
    	if (speed == 0) { 
    		SmartDashboard.putBoolean("Wheels Spinning", false);
    	} else {
    		SmartDashboard.putBoolean("Wheels Spinning", true);
    	}
    }

    protected void execute() {
    	SmartDashboard.putBoolean("Shoot RPM Aligned", false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.shootingWheel.onTarget();
    }

    protected void end() {
    	SmartDashboard.putBoolean("Shoot RPM Aligned", true);
    }

    protected void interrupted() {
    	end();
    }
}
