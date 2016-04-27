package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class MenzieTargetFinder extends Command {

	NetworkTable table;
	
	boolean firstTurn;
	boolean shouldSetTimeout;
	boolean waiting;
	
    public MenzieTargetFinder() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	firstTurn = true;
    	shouldSetTimeout = true;
    	waiting = false;
    	
		table = NetworkTable.getTable("vision");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (firstTurn) {
    		if (shouldSetTimeout) {
    			setTimeout(46.0 / Robot.driveTrain.DEGREES_PER_SECOND);
    			shouldSetTimeout = false;
    		}
    		
    		if (!waiting) {
    			Robot.driveTrain.driveArcade(0, 0.5);
    		}
    	} else {
    		if (shouldSetTimeout) {
    			setTimeout(72.0 / Robot.driveTrain.DEGREES_PER_SECOND); // Not negative angle because that would be negative time
    			shouldSetTimeout = false;
    		}
    		
    		if (!waiting) {
    			Robot.driveTrain.driveArcade(0, -0.5);
    		}
    	}
    	
    	if (isTimedOut() && !waiting) {
    		waiting = true;
    		setTimeout(0.5); // Wait for vision to catch up
    	}
    	
    	if (isTimedOut() && firstTurn && waiting) {
    		firstTurn = false;
    		waiting = false;
    		shouldSetTimeout = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return table.getNumber("can_see_target", 0) == 1 || (!firstTurn && isTimedOut() && waiting);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
