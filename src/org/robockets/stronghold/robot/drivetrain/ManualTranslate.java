package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to manually translate the robot with an Enum and other parameters.
 * @author Brian Shin
 * @version v.1.disgruntled.0.1.crunch.1454030395.7
 */
public class ManualTranslate extends Command {
	Direction direction;
	double speed;
	Double time;
	
	/**
	 * @param rotation Enum parameter for translation direction. Enums are FORWARD and BACKWARD.
	 * @param speed A double that gives the desired speed of the translation.
	 * @param time An optional parameter that lets the robot run for a specified amount of time.
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
    	if (direction == Direction.BACKWARD) speed *= -1;
    	Robot.driveTrain.driveArcade(speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (time != null) {
        	return isTimedOut();
        } else {
        	return false;
        }
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
