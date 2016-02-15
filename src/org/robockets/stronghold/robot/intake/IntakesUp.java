package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakesUp extends Command {
	
    public IntakesUp() {
    	
    }

    protected void initialize() {
    	Robot.intakeFront.setIntakeAngle(90); //TEMP
    	Robot.intakeBack.setIntakeAngle(90); //TEMP
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
