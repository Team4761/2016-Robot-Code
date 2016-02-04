package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.Rotation;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualDrive extends Command {
	Rotation rotation;
	Direction direction;
	double translateSpeed;
	double rotateSpeed;
	Double time;

    public ManualDrive(Direction direction, Rotation rotation, double translateSpeed, double rotateSpeed, double time) {
        requires(Robot.driveTrain);
        this.direction = direction;
        this.rotation = rotation;
        this.translateSpeed = translateSpeed;
        this.rotateSpeed = rotateSpeed;
        this.time = time;
    }

    public ManualDrive(Direction direction, Rotation rotation, double translateSpeed, double rotateSpeed) {
        requires(Robot.driveTrain);
        this.direction = direction;
        this.rotation = rotation;
        this.translateSpeed = translateSpeed;
        this.rotateSpeed = rotateSpeed;
    }
    
    public ManualDrive(double translateSpeed, double rotateSpeed, double time) {
        requires(Robot.driveTrain);
        this.translateSpeed = translateSpeed;
        this.rotateSpeed = rotateSpeed;
        this.time = time;
    }
    
    public ManualDrive(double translateSpeed, double rotateSpeed) {
        requires(Robot.driveTrain);
        this.translateSpeed = translateSpeed;
        this.rotateSpeed = rotateSpeed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (direction == Direction.BACKWARD) translateSpeed *= -1;
    	if (rotation == Rotation.COUNTERCLOCKWISE) rotateSpeed *= -1;
    	Robot.driveTrain.driveArcade(translateSpeed, rotateSpeed);
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
