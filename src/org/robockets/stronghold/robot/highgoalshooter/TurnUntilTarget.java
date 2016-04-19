package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class TurnUntilTarget extends Command {

	NetworkTable visionTable;
	double speed;

    public TurnUntilTarget(double speed) {
        requires(Robot.driveTrain);
        
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	visionTable = NetworkTable.getTable("vision");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveArcade(0, speed);
    	System.out.println("TEST!");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return visionTable.getNumber("can_see_target", 0) == 1.0 && Math.abs(visionTable.getNumber("horiz_offset", 10)) + HorizontalAlign.TARGET_OFFSET < 2;    	
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
