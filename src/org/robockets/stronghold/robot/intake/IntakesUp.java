package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakesUp extends Command {
		
    public IntakesUp() {
    	requires(Robot.intakeVerticalFront);
    	requires(Robot.intakeVerticalBack);
    }

    protected void initialize() {
    	Robot.intakeVerticalFront.setIntakeAngle(0);
    	Robot.intakeVerticalBack.setIntakeAngle(0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return Robot.intakeVerticalBack.intakeOnTarget() && Robot.intakeVerticalFront.intakeOnTarget();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
