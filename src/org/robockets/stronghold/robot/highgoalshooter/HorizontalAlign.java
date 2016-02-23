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
    	SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.02));
	double factor = SmartDashboard.getNumber("factorz", 0.02); // Or something.
    	
	SmartDashboard.putNumber("Setpoint delta", factor * pixelError);
	if (table.getNumber("heartbeat", 0) == 1) {
		Robot.shooter.setTurnTableAngle(Robot.shooter.turnTableSource.pidGet() + (factor * pixelError));
		table.putNumber("heartbeat", 1);
	}
    }

    protected boolean isFinished() {
    	if (continuous) return Robot.shooter.turnTablePidController.onTarget();
    	return false;
    }

    protected void end() {
    	Robot.shooter.setTurnTableAngle(Robot.shooter.getTurnTableSetpoint());
    }

    protected void interrupted() {
    	end();
    }
}
