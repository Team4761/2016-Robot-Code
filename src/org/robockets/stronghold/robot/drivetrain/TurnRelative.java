package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRelative extends Command {

	double amount;
	
    public TurnRelative(double amount) {
        requires(Robot.driveTrain);
        this.amount = amount;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveTrain.encodersPID.enable();
    	//Robot.driveTrain.setOffsetAngle(amount);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveAssisted(false, true, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveTrain.encodersOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.driveTrain.encodersPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
