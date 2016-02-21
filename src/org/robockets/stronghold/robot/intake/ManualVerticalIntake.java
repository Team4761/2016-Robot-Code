package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualVerticalIntake extends Command {

	public Direction direction; // Object for the Direction enum
	Intake intake;

	int time; // Used to set timeout

	int speed; // Used to set speed manually

	/**
	 * Initalizes some variables
	 * 
	 * @param direction
	 *            Used to initalize Direction enum
	 * @param time
	 *            Takes input for time
	 */
	public ManualVerticalIntake(Direction direction, int time, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
		}
		this.direction = direction;
		this.time = time;
	}

	public ManualVerticalIntake(int speed, int time, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
		}
		this.speed = speed;
		this.direction = Direction.MANUAL;
		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(time);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction == Direction.UP) {
			intake.setIntakeAngle(intake.getIntakeSetpointAngle() - 1);
		} else if (direction == Direction.DOWN) {
			intake.setIntakeAngle(intake.getIntakeSetpointAngle() + 1);
		} else if (direction == Direction.MANUAL) {
			intake.move(speed);
		} else {
			intake.stopVertical();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (time == 0) {
			return false;
		} else {
			return isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		intake.stopVertical();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
