package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A Command to spin the intake motor forward or backward
 */
public class SpinIntake extends Command {

	public Direction direction; // Object for the Direction enum
	Intake intake;

	int time; // Used to set timeout

	double speed; // Used to set speed manually

	/**
	 * Initalizes some variables
	 * @param direction Used to initalize Direction enum
	 * @param time Takes input for time
	 */
	public SpinIntake(Direction directionEnum, int time, IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
		}
		
		this.direction = directionEnum;
		this.time = time;
	}

	public SpinIntake(double speed, int time, IntakeSide intakeSide) {
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
		if (direction == Direction.FORWARD) {
			intake.spinIn();
		} else if (direction == Direction.BACKWARD) {
			intake.spinOut();
		} else if (direction == Direction.MANUAL) {
			intake.spin(speed);
		} else {
			intake.stopIntake();
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
		intake.stopIntake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
