package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SuperIntakeReset extends Command {

    public SuperIntakeReset() {
        requires(Robot.intakeVerticalFront);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeVerticalFront.encoderPID.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeVerticalFront.move(0.4); // Move intake up
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.intakeFrontUp.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeVerticalFront.stop();
    	Robot.intakeVerticalFront.resetEncoder();
    	Robot.intakeVerticalFront.enablePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
