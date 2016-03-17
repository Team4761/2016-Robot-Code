package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * AssistedDrive is a command for PID driving.
 */
public class AssistedDrive extends Command {
	private AssistedTranslateType translatePidType;
	private AssistedRotateType rotationPidType;
	double speed;
	double distance;
	double relativeAngle;
	
	public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, double distance, double relativeAngle) {
		this(translatePidType, rotationPidType, distance, relativeAngle, 1.0); // Got to go fast!
	}
	
	/**
	 * Reccommended constructor.
	 * @param translatePidType Choose from a few Enums on how you want to move. Theres ENCODER which uses encoders to track distance and NONE.
	 * @param rotationPidType More Enums to choose from which controls how one will turn, including: GYRO-using gyroscope, COMPASS-nonexistant but uses compass, and ENCODER which uses encoders to keep straight.
	 * @param distance This is in inches and how far you would like to go.
	 * @param relativeAngle Angle.
	 * @param speed The scalar for the speed, i.e. 0.5 for half speed. Don't use 1 unless necessary.
	 */
    public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, double distance, double relativeAngle, double speed) {
        requires(Robot.driveTrain);
        
        this.translatePidType = translatePidType;
        this.rotationPidType = rotationPidType;       
        this.speed = speed;
        this.distance = distance;
        this.relativeAngle = relativeAngle;
    }
    
    public AssistedDrive(AssistedRotateType rotatePidType, double relativeAngle) {
    	this(AssistedTranslateType.NONE, rotatePidType, 0.0, relativeAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (translatePidType == AssistedTranslateType.ENCODER) {
        	Robot.driveTrain.enableDistancePID();
        	Robot.driveTrain.setDistanceInInches(distance);
        }
        
        if (rotationPidType == AssistedRotateType.COMPASS) {
    		Robot.driveTrain.enableCompassPID();
    		Robot.driveTrain.setAngle(relativeAngle, true);
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		Robot.driveTrain.enableGyroPID();
    		Robot.driveTrain.setAngle(relativeAngle, false);
    	} else if (rotationPidType == AssistedRotateType.ENCODER) {
    		Robot.driveTrain.enableEncodersPID();
    		Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean compassAssist = false;
    	boolean encooder = false;
    	if (rotationPidType == AssistedRotateType.COMPASS) {
    		compassAssist = true;
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		compassAssist = false;
    	} else if (rotationPidType == AssistedRotateType.ENCODER) {
    		encooder = true;
    	}
    	if (translatePidType == AssistedTranslateType.ENCODER) {
    		Robot.driveTrain.driveAssisted(compassAssist, encooder, speed);
    	} else {
    		Robot.driveTrain.driveAssisted(0, compassAssist, encooder, speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean encoderOnTarget = true;
    	if (translatePidType == AssistedTranslateType.ENCODER) {
    		encoderOnTarget = Robot.driveTrain.distancePID.onTarget();
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
