package org.robockets.stronghold.robot.flipper;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class FireShooter extends Command {
	
	boolean goBack = false;
	
    public FireShooter() {
        //requires(Robot.flipper);
    }

    protected void initialize() {
    	setTimeout(0.7);
    }

    protected void execute() {
    	Robot.flipper.setAngle(0.1);
    	//Robot.flipper.setAngle(0.5);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	Robot.flipper.setAngle(0.5);
    	//Robot.flipper.setAngle(0.1);
    }

    protected void interrupted() {
    	end();
    }
}
