package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the hood upwards or downwards.
 */
public class MoveHood extends Command {

	Double angle; // Note that this is intentionally not the primitive angle so it can be compared to null.
	Double speed;
	
	/**
	 * Move the hood upwards or downwards continuously.
	 * @param rate		The speed to move at. Negative values will go downwards.
	 */
    public MoveHood(double rate) {
        requires(Robot.shooter);
    }

    /**
	 * Move the hood upwards or downwards continuously. Note you have to enable the PID for this.
	 * @param angle			The angle to move the hood by. This is added to the current angle. Negative values will go downwards.
	 */
    public MoveHood(float ang) {
        requires(Robot.shooter);
        
        angle = Robot.shooter.hoodPidController.getSetpoint() + ang;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (angle != null){
    		Robot.shooter.hoodPidController.setSetpoint(angle);
    	}
    	
    	setTimeout(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle == null) Robot.shooter.spinHood(speed);
    	else {
    		Robot.shooter.spinHoodAssisted();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (angle != null) return Robot.shooter.hoodPidController.onTarget();
        return false || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.spinHood(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.spinHood(0);
    }
}
