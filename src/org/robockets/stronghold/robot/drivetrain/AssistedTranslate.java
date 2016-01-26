package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedTranslate extends Command {
	private AssistedTranslateType pidType;
	private double relativeAngle;

    public AssistedTranslate(AssistedTranslateType pidType, double relativeAngle) {
    	requires(Robot.driveTrain);
    	this.pidType = pidType;
    	this.relativeAngle = relativeAngle;
    	
    	if (pidType == AssistedTranslateType.COMPASS) {
    		Robot.driveTrain.enableCompassPID();
    		Robot.driveTrain.setAngle(relativeAngle, true);
    	} else if (pidType == AssistedTranslateType.GYRO) {
    		Robot.driveTrain.enableGyroPID();
    		Robot.driveTrain.setAngle(relativeAngle, false);
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (pidType == AssistedTranslateType.COMPASS) {
    		Robot.driveTrain.driveAssisted(0, true);
    	} else if (pidType == AssistedTranslateType.GYRO) {
    		Robot.driveTrain.driveAssisted(0, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (pidType == AssistedTranslateType.COMPASS) {
    		return Robot.driveTrain.compassPID.onTarget();
    	} else if (pidType == AssistedTranslateType.GYRO) {
    		return Robot.driveTrain.gyroPID.onTarget();
    	} else { // WHAT?!
    		return true;
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
