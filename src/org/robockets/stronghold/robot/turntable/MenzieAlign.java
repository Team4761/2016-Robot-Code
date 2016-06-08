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
	boolean firstTime;
	boolean aligned = false;
	boolean turnRobot = false;
	double targetTime, startTime;
	
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
		firstTime = true;
		turnRobot = false;
	}
	
	protected void execute() {
		//factor = SmartDashboard.getNumber("Factor");
		
		//if (!turnRobot) {
			SmartDashboard.putNumber("Simon's Angle", table.getNumber("horiz_offset", 0));
			
			if (table.getNumber("can_see_target", 0) == 1) {
				double angleError = table.getNumber("horiz_offset", 0); // Camera 2 degrees off
				
				if ((Timer.getFPGATimestamp() - startTime) >= 0.5 && table.getNumber("heartbeat", 0) == 1 && firstTime) {
					table.putNumber("heartbeat", 0);	
					
					//double output = Robot.turntable.getAngle() + (Robot.turntable.convertVisionAngle(Robot.turntable.factor * angleError));
					double output = Robot.turntable.getAngle() + (Robot.turntable.factor * angleError) + Robot.turntable.TARGET_OFFSET;
					Robot.turntable.setAngle(output);
									
					SmartDashboard.putNumber("output for turntable", output);
					firstTime = false;
				}
				
				if ((Timer.getFPGATimestamp() - startTime) >= 0.5  && Robot.turntable.onTarget()) {
					if (!aligned) {
						aligned = true;
					}
				} else {
					targetTime = Timer.getFPGATimestamp() + 0.5;
					aligned = false;
				}
				
				SmartDashboard.putNumber("Turntable Setpoint", Robot.turntable.getSetpoint());
			}
		/*} else {
			System.out.println(targetTime);
			
			if (Timer.getFPGATimestamp() < targetTime) {
				Robot.driveTrain.driveArcade(0, -0.5);
			} else {
				Robot.driveTrain.driveArcade(0, 0);
				turnRobot = false;
			}
		}*/
	}

	protected boolean isFinished() {
		//if (aligned && (Timer.getFPGATimestamp() >= targetTime) && Robot.turntable.onTarget()) {
		if (aligned && Robot.turntable.onTarget() && !(Robot.hood.atLimit || Robot.turntable.atLimit)) {
		//if (Robot.turntable.onTarget()) {
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", true);
			if (continuous == false) { return true; }
		} else {
			SmartDashboard.putBoolean("Shoot Horizontally Aligned", false);
		}
		
		/*if ((Robot.hood.atLimit || Robot.turntable.atLimit) && !turnRobot && !firstTime) {
			initialize();
			turnRobot = true;
			targetTime = Timer.getFPGATimestamp() + 0.5;
		}*/
		
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
