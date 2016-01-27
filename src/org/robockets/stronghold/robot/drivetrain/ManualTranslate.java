package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Brian Shin
 */
public class ManualTranslate extends Command {
	Direction direction;
    double speed;
    double time;

    /** 
      * @param direction An enum for the direction in which to translate. Options are FORWARD and BACKWARD.
      * @param speed The desired translation speed for the robot.
      * @param time Optional parameter for a set amount of time for the robot to run.
     */
    public ManualTranslate(Direction direction, double speed, double time) {
    	requires(Robot.driveTrain);
    	this.direction = direction;
    	this.speed = speed;
    	this.time = time;
    }
    
    public ManualTranslate(Direction direction, double speed) {
    	requires(Robot.driveTrain);
    	this.direction = direction;
    	this.speed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction == Direction.BACKWARD) speed *= -1;
    	Robot.driveTrain.driveArcade(speed, 0);
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
