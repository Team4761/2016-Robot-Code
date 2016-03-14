package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class FireShooter extends Command {
	
	boolean goBack = false;
	
    public FireShooter() {
        //requires(Robot.shooter);
    }

    protected void initialize() {
    	setTimeout(0.7);
    }

    protected void execute() {
    	Robot.shooter.setShooterFlipper(0.5);
    	//RobotMap.newServo.set(1);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	Robot.shooter.setShooterFlipper(0.1);
    	//RobotMap.newServo.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
