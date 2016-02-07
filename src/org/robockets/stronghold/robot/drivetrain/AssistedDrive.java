package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedDrive extends Command {
	private AssistedTranslateType translatePidType;
	private AssistedRotateType rotationPidType;

    public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, double distance, double relativeAngle) {
        requires(Robot.driveTrain);
        
        this.translatePidType = translatePidType;
        this.rotationPidType = rotationPidType;
        
        if (translatePidType == AssistedTranslateType.ENCODER) {
        	Robot.driveTrain.enableEncoderPID();
        	Robot.driveTrain.setDistance(distance);
        }
        
        if (rotationPidType == AssistedRotateType.COMPASS) {
    		Robot.driveTrain.enableCompassPID();
    		Robot.driveTrain.setAngle(relativeAngle, true);
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		Robot.driveTrain.enableGyroPID();
    		Robot.driveTrain.setAngle(relativeAngle, false);
    	}
    }
    
    public AssistedDrive(AssistedRotateType rotatePidType, double relativeAngle) {
    	this(AssistedTranslateType.NONE, rotatePidType, 0.0, relativeAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean compassAssist = true;
    	if (rotationPidType == AssistedRotateType.COMPASS) {
    		compassAssist = true;
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		compassAssist = false;
    	}
    	
    	if (translatePidType == AssistedTranslateType.ENCODER) {
    		Robot.driveTrain.driveAssisted(compassAssist);
    	} else {
    		Robot.driveTrain.driveAssisted(0, compassAssist);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean encoderOnTarget = true;
    	if (translatePidType == AssistedTranslateType.ENCODER) {
    		encoderOnTarget = Robot.driveTrain.encoderPID.onTarget();
    	}
    	
    	if (rotationPidType == AssistedRotateType.COMPASS) {
    		return Robot.driveTrain.compassPID.onTarget() && encoderOnTarget;
    	} else { // Default to gyro
    		return Robot.driveTrain.gyroPID.onTarget() && encoderOnTarget;
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
