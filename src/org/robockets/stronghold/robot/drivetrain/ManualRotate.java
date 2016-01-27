package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.Rotation;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Brian Shin
 */
public class ManualRotate extends Command {
	Rotation rotation;
	double speed;
	double time;
	
	/**
	 * @param rotation An enum for the direction in which to rotate. Options are CLOCKWISE and COUNTERCLOCKWISE.
	 * @param speed The desired rotation speed for the robot. Negative
	 * @param time Optional parameter for a set amount of time for the robot to rotate.
	 */
    public ManualRotate(Rotation rotation, double speed, double time) {
        requires(Robot.driveTrain);
        this.rotation = rotation;
        this.speed = speed;
        this.time = time;
    }
    
    public ManualRotate(Rotation rotation, double speed) {
        requires(Robot.driveTrain);
        this.rotation = rotation;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (rotation == Rotation.CLOCKWISE) {
    		Robot.driveTrain.driveArcade(0, speed);
    	} else {
    		Robot.driveTrain.driveArcade(0, -speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time != null) {
    		return isTimedOut();
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
