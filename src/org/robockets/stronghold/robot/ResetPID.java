package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetPID extends Command {

	Encoder encoder;
	PIDController pidController;
	
    public ResetPID(Encoder encoder, PIDController pidController) {
    	this.encoder = encoder;
    	this.pidController = pidController;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	encoder.reset();
    	pidController.reset();
    	pidController.setSetpoint(0);
    	pidController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    }
}
