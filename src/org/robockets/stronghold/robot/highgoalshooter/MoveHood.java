package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the hood upwards or downwards.
 */
public class MoveHood extends Command {

	Double angle; // Note that this is intentionally not the primitive angle so it can be compared to null.
	Double speed;
	Double time;
	
	/**
	 * Move the hood upwards or downwards continuously.
	 * @param rate		The speed to move at. Negative values will go downwards.
	 */
    public MoveHood(double speed, double time) {
        requires(Robot.shooter);
        this.speed = speed;
        this.time = time;
    }

    /**
	 * Move the hood upwards or downwards continuously. Note you have to enable the PID for this.
	 * @param angle			The angle to move the hood to
	 */
    public MoveHood(double angle) {
        requires(Robot.shooter);
        this.angle = angle;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (time != null) {
    		setTimeout(time);
    		Robot.shooter.hoodPidController.disable();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle != null) {
    		Robot.shooter.setHoodAngle(angle);
    	} else {
    		Robot.shooter.spinHood(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (angle != null) return true;
        if (time != 0) return isTimedOut();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (time != null) {
    		Robot.shooter.spinHood(0);
    		Robot.shooter.hoodPidController.enable();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
