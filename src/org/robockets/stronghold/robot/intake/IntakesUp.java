package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakesUp extends Command {
	
	boolean end;
	
    public IntakesUp() {
    	end = false;
    }

    protected void initialize() {
    	Robot.intakeFront.setIntakeAngle(90); //TEMP
    	Robot.intakeBack.setIntakeAngle(90); //TEMP
    }

    protected void execute() {
    	if (RobotMap.intakeEncoderFront.get() == Robot.intakeFront.encoderPID.getSetpoint() && RobotMap.intakeEncoderBack.get() == Robot.intakeBack.encoderPID.getSetpoint()) {
    		end = true;
    	}
    }

    protected boolean isFinished() {
    	return end;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
