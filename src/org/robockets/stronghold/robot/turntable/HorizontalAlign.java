package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

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
		requires(Robot.turntable);
		this.continuous = continuous;
	}

	protected void initialize() {
		table = NetworkTable.getTable("vision");
		table.putNumber("heartbeat", 1);
		SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
	}

	protected void execute() {
		double pixelError = table.getNumber("horiz_offset", 0);
		SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.0305));
		double factor = 1; // It's now just an angle. SmartDashboard.getNumber("factorz", 53/1204);

		// In eclipse use Ctrl+I to indent multiple selected lines.

		if (table.getNumber("heartbeat", 0) == 1) {

			double output = Robot.turntable.getAngle() + (factor * pixelError);
			Robot.turntable.setAngle(output);
			SmartDashboard.putNumber("pid error", Robot.turntable.getSetpoint() - Robot.turntable.getAngle());
			
			
			/*if (!continuous && Robot.turntable.onTarget()) {
				if (!continuous && Math.abs(pixelError) < 20) {
					if (!onTargetForReal) {
						setTimeout(1);
					}

					onTargetForReal = true;
				} else {
					onTargetForReal = false;
				}
			}*/
			
			Robot.turntable.setAngle(output);
			SmartDashboard.putNumber("output for turntable", output);
		}	
	}

	protected boolean isFinished() {
		if (table.getNumber("heartbeat",0)==1) {
			table.putNumber("heartbeat",0);
			if (Robot.turntable.onTarget() && isTimedOut() && Math.abs(table.getNumber("horiz_offset", 3)) == 2) {
				SmartDashboard.putBoolean("Shoot Horizontally Aligned", true);
				if (continuous == false) { return true; }
			} else {
				SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
			}
		}
		return false;
	}

	protected void end() {
		SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
	}

	protected void interrupted() {
		end();
	}
}
