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
	 * * @param continuous		If it should stop when on target.
	 */
    public HorizontalAlign(boolean continuous) {
    	requires(Robot.shooter);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    	table.putNumber("heartbeat", 1);
    }

    boolean holdUp = false;
    
    protected void execute() {
    	double pixelError = table.getNumber("horiz_offset", 0);
    	//SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.0354));
    	//resolution: 1024 px wide
    	//fov:
    	double factor = 0.0305;
    	
    	if (holdUp){
    		if (Robot.shooter.turnTableOnTarget()) { holdUp = false; }
    	} else {
    		if (table.getNumber("heartbeat", 0) == 1) {
    			double output = Robot.shooter.turnTableSource.pidGet() + (factor * pixelError);
    			if (Math.abs(output) > 270) {
    				holdUp = true; // We need to spin back around to not twist the wires.
    			}
    			Robot.shooter.setTurnTableAngle(output);
    			table.putNumber("heartbeat", 1);
    		}
    	}
    }
    
    protected boolean isFinished() {
    	if (continuous == false) return Robot.shooter.turnTableOnTarget();
    	return false;
    }

    protected void end() {
    	Robot.shooter.setTurnTableAngle(Robot.shooter.getTurnTableSetpoint()); // Stopping it.
    }

    protected void interrupted() {
    	end();
    }
}
