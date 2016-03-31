package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClampIntake extends Command {

	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.intakeVerticalFront.setIntakeAngle(-5);
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.intakeVerticalFront.setIntakeAngle(0);
	}
	
	protected void interrupted() {
		end();
	}
}
