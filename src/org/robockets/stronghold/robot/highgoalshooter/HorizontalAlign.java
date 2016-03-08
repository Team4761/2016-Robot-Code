package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Align the robot horizontally with the target.
 */
public class HorizontalAlign extends Command {

	NetworkTable table;
	boolean continuous;
	
	/**
	 * * @param continuos		If it should stop when on target.
	 */
    public HorizontalAlign(boolean continuous) {
    	requires(Robot.shooter);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    }

    protected void execute() {
    	double pixelError = table.getNumber("horiz_offset", 0);
    	//SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.0354));
    	//resolution: 1024 px wide
    	//fov:
    	double factor = 0.0305;

    	if (table.getNumber("heartbeat", 0) == 1) {
    		double output = Robot.shooter.turnTableSource.pidGet() + (factor * pixelError);
    		Robot.shooter.setTurnTableAngle(output);
    	}
   }
    
    protected boolean isFinished() {
    	if (continuous == false && table.getNumber("heartbeat", 0) == 1) return Robot.shooter.turnTableOnTarget();
    	table.putNumber("heartbeat", 0); // Assuming this is called right after execute.
    	return false;
    }

    protected void end() {
    	Robot.shooter.setTurnTableAngle(Robot.shooter.getTurnTableSetpoint()); // Stopping it.
    }

    protected void interrupted() {
    	end();
    }
}
