package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set both intakes up
 */
public class IntakesUp extends Command {
		
    public IntakesUp() {
    	requires(Robot.intakeFront);
    	requires(Robot.intakeBack);
    }

    protected void initialize() {
    	Robot.intakeFront.setIntakeAngle(0);
    	Robot.intakeBack.setIntakeAngle(0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return Robot.intakeBack.intakeOnTarget() && Robot.intakeFront.intakeOnTarget();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
