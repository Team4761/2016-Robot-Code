package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualTranslate extends Command {
	
    public ManualTranslate(Direction dir, double rate) {
    	requires(Robot.driveTrain);
    	direction = dir;
    	speed = rate;
    }
    Direction direction;
    double speed;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (direction == Direction.BACKWARD) {
    		speed *= -1;
    	}
    	Robot.driveTrain.driveArcade(speed, 0);
    	//doesn't need to be called every 20ms 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
