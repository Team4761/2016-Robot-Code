package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class SetVerticalIntake extends Command {

	Direction direction; // Object for the Direction enum
	IntakeVertical intake;

	Integer time; // Used to set timeout
	double speed; // Used to set speed manually
	double angle;
	

	/**
	 * Initalizes some variables
	 * @param direction Used to initalize Direction enum
	 * @param time Takes input for time
	 */
	public SetVerticalIntake(Direction direction, int time, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeVerticalFront);
			intake = Robot.intakeVerticalFront;
		} else {
			requires(Robot.intakeVerticalBack);
			intake = Robot.intakeVerticalBack;
		}
		
		this.direction = direction;
		this.time = time;
	}

	public SetVerticalIntake(int speed, int time, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeVerticalFront);
			intake = Robot.intakeVerticalFront;
		} else {
			requires(Robot.intakeVerticalBack);
			intake = Robot.intakeVerticalBack;
		}
		
		this.speed = speed;
		this.direction = Direction.MANUAL;
		
		this.time = time;
	}
	
	// angle is absolute
	public SetVerticalIntake(double angle, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeVerticalFront);
			intake = Robot.intakeVerticalFront;
		} else {
			requires(Robot.intakeVerticalBack);
			intake = Robot.intakeVerticalBack;
		}
		
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (time != null) {
			setTimeout(time);
		} else {
			setTimeout(3); // Set backup timeout
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction == Direction.UP) {
			intake.setIntakeAngle(intake.getIntakeSetpointAngle() - 1);
		} else if (direction == Direction.DOWN) {
			intake.setIntakeAngle(intake.getIntakeSetpointAngle() + 1);
		} else if (direction == Direction.MANUAL) {
			intake.move(speed);
		} else { // Must be pid
			intake.setIntakeAngle(angle);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (time != null && time == 0) {
			return false;
		} else if (time == null) {
			return intake.intakeOnTarget() || isTimedOut();
		} else {
			return isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		intake.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
