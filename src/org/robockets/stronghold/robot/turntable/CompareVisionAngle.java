package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class CompareVisionAngle extends Command {

	NetworkTable table;
	
    public CompareVisionAngle() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		table = NetworkTable.getTable("vision");
		setTimeout(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Turntable Angle: " + Robot.turntable.getAngle() + ", Vision Angle: " + (table.getNumber("horiz_offset", 0)));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
