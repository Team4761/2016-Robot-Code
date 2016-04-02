package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used for self-driving with PID control.
 */
public class AssistedDrive extends Command {
	final double CONSTANT_DISTANCE_UPDATE = 10 * 0.02;
	boolean cutOnHighSpeed = false;
	
	private AssistedTranslateType translatePidType;
	private AssistedRotateType rotationPidType;
	double inchesPerSecond;
	double distance;
	double relativeAngle;
	
	/**
	 * Main constructor to control robot driving with PID.
	 * @param translatePidType Choose from a few Enums on how you want to move. There's ENCODER which uses encoders to track distance and NONE.
	 * @param rotationPidType More Enums to choose from which controls how one will turn, including: GYRO-using gyroscope, COMPASS-nonexistant but uses compass, and ENCODER which uses encoders to keep straight.
	 * @param distance This is in inches and how far you would like to go.
	 * @param relativeAngle Angle relative to robot that you want to turn.
	 * @param inchesPerSecond Rate of speed in inches per second. Full speed is 162 inches per second.
	 */
    public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, double distance, double relativeAngle, double inchesPerSecond) {
        requires(Robot.driveTrain);
        
        this.translatePidType = translatePidType;
        this.rotationPidType = rotationPidType;
        
        this.distance = distance;
        this.relativeAngle = relativeAngle;
        
        this.inchesPerSecond = inchesPerSecond;
    }
    
    /**
     * Constructor for PID compensation, where it will stop at desired setpoint without slowing down before.
     * @param translatePidType Choose from a few Enums on how you want to move. There's ENCODER which uses encoders to track distance and NONE.
	 * @param rotationPidType More Enums to choose from which controls how one will turn, including: GYRO-using gyroscope, COMPASS-nonexistant but uses compass, and ENCODER which uses encoders to keep straight.
	 * @param cutOnHighSpeed True to make the setpoint larger than set for speed, then stops at real desired setpoint without slowing down. Don't use this constructor if false.
	 * @param distance This is in inches and how far you would like to go.
	 * @param relativeAngle Angle relative to robot that you want to turn.
	 * @param inchesPerSecond Rate of speed in inches per second. Full speed is 162 inches per second.
     */
    public AssistedDrive(AssistedTranslateType translatePidType, AssistedRotateType rotationPidType, boolean cutOnHighSpeed, double distance, double relativeAngle, double inchesPerSecond) {
        this(translatePidType, rotationPidType, distance, relativeAngle, inchesPerSecond);
        
        this.cutOnHighSpeed = cutOnHighSpeed;
    }
    
    /**
     * Constructor for rotating in place.
     * @param rotatePidType More Enums to choose from which controls how one will turn, including: GYRO-using gyroscope, COMPASS-nonexistant but uses compass, and ENCODER which uses encoders to keep straight.
     * @param relativeAngle Angle relative to robot that you want to turn.
     */
    public AssistedDrive(AssistedRotateType rotatePidType, double relativeAngle) {
    	this(AssistedTranslateType.NONE, rotatePidType, 0.0, relativeAngle, 0.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (translatePidType == AssistedTranslateType.ENCODER) { Robot.driveTrain.enableWheelPID(); }
    	
        if (rotationPidType == AssistedRotateType.COMPASS) {
    		Robot.driveTrain.enableCompassPID();
    		Robot.driveTrain.setAngle(relativeAngle, rotationPidType);
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		Robot.driveTrain.enableGyroPID();
    		Robot.driveTrain.setAngle(relativeAngle, rotationPidType);
    	} else if (rotationPidType == AssistedRotateType.ENCODER) {
    		Robot.driveTrain.enableWheelPID();
    		Robot.driveTrain.setAngle(relativeAngle, rotationPidType);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() {
    	
        if (translatePidType == AssistedTranslateType.ENCODER) {
        	double extraInches = (cutOnHighSpeed) ? CONSTANT_DISTANCE_UPDATE : 0;
        	if (Math.abs(Math.abs(distance) - Math.abs(Robot.driveTrain.getLeftDistanceSetpointInInches() + (inchesPerSecond * 0.02) + extraInches)) > Math.abs(4 * (inchesPerSecond * 0.02))) {
        		Robot.driveTrain.setDistanceInInches(Robot.driveTrain.getLeftDistanceSetpointInInches() + (inchesPerSecond * 0.02) + extraInches);
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
    		encoderOnTarget = Math.abs(Math.abs(distance) - Math.abs(Robot.driveTrain.getLeftDistanceInInches())) <= 2 && 
    				Math.abs(Math.abs(distance) - Math.abs(Robot.driveTrain.getRightDistanceInInches())) <= 2;
    	}
    	
    	if (!encoderOnTarget && cutOnHighSpeed) {
    		if (inchesPerSecond > 0) {
    			encoderOnTarget = Robot.driveTrain.getLeftDistanceInInches() > distance &&
    				Robot.driveTrain.getRightDistanceInInches() > distance;
    		} else {
    			encoderOnTarget = Robot.driveTrain.getLeftDistanceInInches() < distance &&
    				Robot.driveTrain.getRightDistanceInInches() < distance;
    		}
    	}
    	    	
    	if (rotationPidType == AssistedRotateType.COMPASS) {
    		return Robot.driveTrain.compassPID.onTarget() && encoderOnTarget;
    	} else if (rotationPidType == AssistedRotateType.GYRO) {
    		return Robot.driveTrain.gyroPID.onTarget() && encoderOnTarget;
    	} else if (rotationPidType == AssistedRotateType.ENCODER) {
    		return Robot.driveTrain.encodersOnTarget();
    	} else {
    		return encoderOnTarget;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.disableWheelPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
