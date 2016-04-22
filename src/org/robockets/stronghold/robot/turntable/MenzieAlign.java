package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MenzieAlign extends Command {

	NetworkTable table;
	boolean continuous;
	double factor = 0.5; // It's now just an angle. SmartDashboard.getNumber("factorz", 53/1204);
	boolean aligned = false;
	double targetTime, startTime;
	
	public static final double TARGET_OFFSET = 3; // Bigger means <- left

	/**
	 * * @param continuous		If it should stop when on target.
	 */
	public MenzieAlign(boolean continuous) {
		requires(Robot.turntable);
		this.continuous = continuous;
	}

	protected void initialize() {
		table = NetworkTable.getTable("vision");
		table.putNumber("heartbeat", 1);
		SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
		aligned = false;
		targetTime = Timer.getFPGATimestamp();
		startTime = Timer.getFPGATimestamp();
	}
	
	protected void execute() {
		//factor = SmartDashboard.getNumber("Factor");
		
		SmartDashboard.putNumber("Simon's Angle", table.getNumber("horiz_offset", 0));
		
		if (table.getNumber("can_see_target", 0) == 1) {
			double angleError = table.getNumber("horiz_offset", 0); // Camera 2 degrees off
			
			if ((Timer.getFPGATimestamp() - startTime) >= 0.5 && table.getNumber("heartbeat", 0) == 1) {
				table.putNumber("heartbeat", 0);	
				
				double output = Robot.turntable.getAngle() + (factor * angleError);
				Robot.turntable.setAngle(output + TARGET_OFFSET);
				
				SmartDashboard.putNumber("output for turntable", output);
			}
			
			if (Math.abs((table.getNumber("horiz_offset", 3) * factor) + TARGET_OFFSET) <= 1.5) {
				if (!aligned) {
					aligned = true;
				}
			} else {
				targetTime = Timer.getFPGATimestamp() + 0.5;
				aligned = false;
			}
			
			SmartDashboard.putNumber("Turntable Setpoint", Robot.turntable.getSetpoint());
		}
	}

	protected boolean isFinished() {
		if (aligned && (Timer.getFPGATimestamp() >= targetTime) && Robot.turntable.onTarget()) {
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", true);
			if (continuous == false) { return true; }
		} else {
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
		}

		return false;
	}

	protected void end() {
		SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
		
		if (continuous) {
			Robot.shootingWheel.setSpeed(0); // Stop the wheel when the button is let go
		}
	}

	protected void interrupted() {
		end();
	}
}
