package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Move intake to a desired angle.
 */
public class SetIntake extends Command {

	double angle;
	
	/**
	 * Move intake to the specified angle.
	 * @param angle		The angle at which the intake will move to.
	 */
    public SetIntake(double angle) {
    	requires(Robot.intakeFront);
    	this.angle = angle;
    }

    protected void initialize() {
    	Robot.intakeFront.setIntakeAngle(angle);
    	setTimeout(10);
    }

    protected void execute() {
    	Robot.intakeFront.moveAssisted();
    }

    protected boolean isFinished() {
        return Robot.intakeFront.encoderPID.onTarget() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
