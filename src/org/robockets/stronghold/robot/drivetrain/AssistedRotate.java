package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedRotate extends Command {
	private AssistedRotateType pidType;

    public AssistedRotate(AssistedRotateType pidType, double relativeAngle) {
    	requires(Robot.driveTrain);
    	this.pidType = pidType;
    	
    	if (pidType == AssistedRotateType.COMPASS) {
    		Robot.driveTrain.enableCompassPID();
    		Robot.driveTrain.setAngle(relativeAngle, true);
    	} else { // Default to gyro
    		Robot.driveTrain.enableGyroPID();
    		Robot.driveTrain.setAngle(relativeAngle, false);
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (pidType == AssistedRotateType.COMPASS) {
    		Robot.driveTrain.driveAssisted(0, true);
    	} else { // Default to gyro
    		Robot.driveTrain.driveAssisted(0, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (pidType == AssistedRotateType.COMPASS) {
    		return Robot.driveTrain.compassPID.onTarget();
    	} else { // Default to gyro
    		return Robot.driveTrain.gyroPID.onTarget();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
