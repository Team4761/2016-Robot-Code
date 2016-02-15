package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move the turntable left or right.
 */
public class MoveTurnTable extends Command {

	Double speed = null;
	Double time = null;
	
	/**
	 * Move the hood upwards or downwards continuously.
	 * @param rate		The speed to move at.
	 */
    public MoveTurnTable(double rate) {
        requires(Robot.shooter);
        speed = rate;
    }
    
    /**
     * Move the hood upwards or downwards for a desired time at a set speed.
     * @param rate		The speed to move at.
     * @param time		The time to wait until stopping the set speed.
     */
    public MoveTurnTable(double rate, double time) {
        requires(Robot.shooter);
        speed = rate;
        this.time = time;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(time!=null) setTimeout(time);
    	Robot.shooter.disableTurnTablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.spinTurnTable(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time != null) return isTimedOut(); // I didn't know what isTimedOut would return instead.
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    	Robot.shooter.enableTurnTablePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}