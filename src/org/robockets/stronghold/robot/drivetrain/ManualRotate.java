package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.Rotation;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to manually rotate the robot with an Enum and other parameters.
 * @author Brian Shin
 * @version v.1.disgruntled.0.1.crunch.1454030395.7
 */
public class ManualRotate extends Command {
	Rotation rotation;
	double speed;
	Double time;
	
	/**
	 * @param rotation Enum parameter for rotation direction. Enums are CLOCKWISE and COUNTERCLOCKWISE.
	 * @param speed A double that gives the desired speed of the rotation.
	 * @param time An optional parameter that lets the robot turn for a specified amount of time.
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
    	if (rotation == Rotation.COUNTERCLOCKWISE) speed *= -1;
    	Robot.driveTrain.driveArcade(0, speed);
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
