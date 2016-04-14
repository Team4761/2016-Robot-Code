package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnstickBall extends Command {
	boolean stop = false;

    public UnstickBall() {
        requires(Robot.shootingWheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shootingWheel.shootingWheelPIDController.setOutputRange(-0.7, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shootingWheel.setSpeed(-1500);
    	System.out.println(RobotMap.shootingWheelMotor.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shootingWheel.getSpeed() < -400;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shootingWheel.shootingWheelPIDController.setOutputRange(0, 1);
    	Robot.shootingWheel.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
