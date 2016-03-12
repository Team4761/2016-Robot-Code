package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Kill extends Command {

    public Kill() {
        requires(Robot.driveTrain);
        requires(Robot.intakeFront);
        requires(Robot.intakeBack);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.stop();
    	
    	Robot.intakeFront.stopIntake();
    	Robot.intakeFront.stopVertical();
    	Robot.intakeBack.stopIntake();
    	Robot.intakeBack.stopVertical();
    	Robot.intakeBack.setIntakeAngle(Robot.intakeBack.getIntakeAngle());
    	Robot.intakeFront.setIntakeAngle(Robot.intakeFront.getIntakeAngle());
    	
    	Robot.shooter.setHoodAngle(Robot.shooter.getHoodAngle());
    	Robot.shooter.setShootingWheelSpeed(0);
    	Robot.shooter.setTurnTableAngle(Robot.shooter.getTurnTableAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
