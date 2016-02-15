package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move the turntable left or right.
 */
public class MoveTurnTable extends Command {

	Double speed = null;
	
	/**
	 * Move the hood upwards or downwards continuously.
	 * @param rate		The speed to move at.
	 */
    public MoveTurnTable(double rate) {
        requires(Robot.shooter);
        speed = rate;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.spinTurnTable(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.spinTurnTable(0);
    }
}