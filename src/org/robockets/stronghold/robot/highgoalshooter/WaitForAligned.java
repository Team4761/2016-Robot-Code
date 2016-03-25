package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Wait until horizontal and vertical and rpm have been aligned.
 */
public class WaitForAligned extends Command {

    public WaitForAligned() {
    }

    protected void initialize() {
    }

    boolean aligned = false;
    
    protected void execute() {
    	if (SmartDashboard.getBoolean("Shoot RPM Aligned", false) && SmartDashboard.getBoolean("Shoot Horizontally Aligned", false)) {
    		if (aligned == false) {
    			setTimeout(1);
    		}
    		aligned = true;
    	} else {
    		aligned = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return SmartDashboard.getBoolean("Shoot RPM Aligned", false) && SmartDashboard.getBoolean("Shoot Horizontally Aligned", false)
        		&& aligned && isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
