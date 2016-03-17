package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * AssistedDrive is a command for PID driving.
 */
public class AssistedDrive extends Command {
	private AssistedTranslateType translatePidType;
	private AssistedRotateType rotationPidType;

	double inchesPerSecond;
	double distance;
	double relativeAngle;
	
    /**
     * Reccommended constructor.
     * @param translatePidType Choose from a few Enums on how you want to move. Theres ENCODER which uses encoders to track distance and NONE.
     * @param rotationPidType More Enums to choose from which controls how one will turn, including: GYRO-using gyroscope, COMPASS-nonexistant but uses compass, and ENCODER which uses encoders to keep straight.
     * @param distance This is in inches and how far you would like to go.
     * @param relativeAngle Angle.
     * @param inchesPerSecond The rate of speed you wish to go, such as 12 inches per second.
     */
    public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, double distance, double relativeAngle, double inchesPerSecond) {
        requires(Robot.driveTrain);
        
        this.translatePidType = translatePidType;
        this.rotationPidType = rotationPidType;
        this.distance = distance;
        this.relativeAngle = relativeAngle;   
        this.inchesPerSecond = inchesPerSecond;
    }
    
    public AssistedDrive(AssistedRotateType rotatePidType, double relativeAngle) {
    	this(AssistedTranslateType.NONE, rotatePidType, 0.0, relativeAngle, 0.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (translatePidType == AssistedTranslateType.ENCODER) {
        	Robot.driveTrain.enableDistancePID();
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
        if (translatePidType == AssistedTranslateType.ENCODER) {
        	if (Math.abs(Robot.driveTrain.getDistanceSetpointInInches() + (inchesPerSecond * 0.02)) < Math.abs(distance)) {
        		Robot.driveTrain.setDistanceInInches(Robot.driveTrain.getDistanceSetpointInInches() + (inchesPerSecond * 0.02));
        	}
        }
    	
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
    		Robot.driveTrain.driveAssisted(compassAssist, encooder, 1);
    	} else {
    		Robot.driveTrain.driveAssisted(0, compassAssist, encooder, 1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean encoderOnTarget = true;
    	if (translatePidType == AssistedTranslateType.ENCODER) {
    		System.out.println("Distance " + Math.abs(Robot.driveTrain.getDistanceInInches()));
    		System.out.println("Want " + Math.abs(distance));
    		encoderOnTarget = Math.abs(Robot.driveTrain.getDistanceInInches()) >= Math.abs(distance) - 10;
    	}
    	
    	if (rotationPidType == AssistedRotateType.COMPASS) {
    		return Robot.driveTrain.compassPID.onTarget() && encoderOnTarget;
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		return Robot.driveTrain.gyroPID.onTarget() && encoderOnTarget;
    	} else {
    		return encoderOnTarget;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.setDistanceInInches(Robot.driveTrain.getDistanceInInches());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
