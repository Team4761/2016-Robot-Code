package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRelative extends Command {

	double amount;
	boolean finished;
	
    public TurnRelative(double amount) {
        requires(Robot.intake);
        this.amount = amount;
        finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.gyroPID.setSetpoint(RobotMap.navX.getAccumulatedYaw() + amount);
    	Robot.driveTrain.gyroPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.driveTrain.gyroPID.get() == RobotMap.navX.getAccumulatedYaw() + amount) {
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.gyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
