package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Align the robot horizontally with the target.
 */
public class HorizontalAlign extends Command {

	NetworkTable table;
	boolean continuous;
	boolean firstTime;
	final double factor = 0.6; // It's now just an angle. SmartDashboard.getNumber("factorz", 53/1204);
	boolean aligned = false;
	double targetTime;

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
		firstTime = true;
		aligned = false;
		targetTime = Timer.getFPGATimestamp();
	}
	
	protected void execute() {
		if (table.getNumber("can_see_target", 0) == 1){
			double pixelError = table.getNumber("horiz_offset", 0); // Camera 2 degrees off
			//SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.0305));

			//SmartDashboard.putBoolean("heartbeat", table.getNumber("heartbeat", 0) == 1);

			// In eclipse use Ctrl+I to indent multiple selected lines.

			if (firstTime || (table.getNumber("heartbeat", 0) == 1)) {
				//if (Robot.turntable.onTarget()) {
				table.putNumber("heartbeat", 0);
				double output = Robot.turntable.getAngle() + (factor * pixelError);
				Robot.turntable.setAngle(output + 1);
				SmartDashboard.putNumber("output for turntable", output);
				firstTime = false;
			}
			
			System.out.println(Math.abs(table.getNumber("horiz_offset", 3) * factor + 1));
			if (Math.abs(table.getNumber("horiz_offset", 3) * factor + 1) <= 2) {
				if (!aligned) {
					aligned = true;
				}
			} else {
				targetTime = Timer.getFPGATimestamp() + 3;
				aligned = false;
			}
		}
	}

	protected boolean isFinished() {
		//if (table.getNumber("can_see_target", 0)==1){
		//if (table.getNumber("heartbeat", 0) == 1) {
		if (aligned && (Timer.getFPGATimestamp() >= targetTime) && Robot.turntable.onTarget()) {
			System.out.println("Finished horizontally aligning");
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", true);
			if (continuous == false) { return true; }
		} else {
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
		}
		//}
		//}
		return false;
	}

	protected void end() {
		SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
	}

	protected void interrupted() {
		end();
	}
}
