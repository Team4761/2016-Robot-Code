package org.robockets.stronghold.robot.shootingwheel;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveShootingWheelSmartDashboard extends Command {

    public MoveShootingWheelSmartDashboard() {
        requires(Robot.shootingWheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Shoot RPM Aligned", false);
    	Robot.shootingWheel.setSpeed(SmartDashboard.getNumber("New RPM", 0));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shootingWheel.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Shoot RPM Aligned", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
