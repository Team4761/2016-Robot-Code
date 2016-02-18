package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Move intake to a desired angle.
 */
public class SetIntake extends Command {

	double angle;
	Intake intake;
	
	/**
	 * Move intake to the specified angle.
	 * @param angle		The angle at which the intake will move to.
	 */
    public SetIntake(double angle, IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    		intake = Robot.intakeFront;
    	} else {
    		requires(Robot.intakeBack);
    		intake = Robot.intakeBack;
    	}
    	
    	this.angle = angle;
    }

    protected void initialize() {
    	intake.setIntakeAngle(angle);
    	setTimeout(10);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return intake.encoderPID.onTarget() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
