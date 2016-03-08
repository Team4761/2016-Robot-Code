package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.robockets.stronghold.robot.Robot;

/**
 * 
 */
public class FireShooter extends Command {

    public FireShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    	setTimeout(0.7);
    }

    protected void execute() {
    	Robot.shooter.setShooterFlipper(0.3);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	Robot.shooter.setShooterFlipper(1);
    }

    protected void interrupted() {
    	end();
    }
}
