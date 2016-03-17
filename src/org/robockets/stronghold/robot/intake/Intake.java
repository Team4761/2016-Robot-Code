package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake subsystem
 */
public class Intake extends Subsystem {

	/**
	 * Used for encoder PID
	 */
	public final double COUNTS_PER_DEGREE = 7.3333333333;
	
	public final PIDController encoderPID;
	private Victor intakeVerticalMotor;
	private Victor intakeMotor;
	private Encoder intakeEncoder;

	/**
	 * Initialize intake motors and such 
	 * @param intakeSide The front or back of the robot
	 */
	public Intake(IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorFront;
			intakeMotor = RobotMap.intakeMotorFront;
			intakeEncoder = RobotMap.intakeEncoderFront;
			encoderPID = new PIDController(0.03, 0.000025, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
			//encoderPID = new PIDController(0.01, 0.0001, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
		} else {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorBack;
			intakeMotor = RobotMap.intakeMotorBack;
			intakeEncoder = RobotMap.intakeEncoderBack;
			encoderPID = new PIDController(0.03, 0.000025, 0, RobotMap.intakeEncoderBack, RobotMap.intakeVerticalMotorBack);
			//encoderPID = new PIDController(0.01, 0.0001, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
		}

		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);
		encoderPID.enable();
	}

	public void initDefaultCommand() {
	}

	/**
	 * Start the intake motor
	 * @param speed The speed of the motor
	 */
	public void spin(double speed) {
		intakeMotor.set(speed);
	}

	/**
	 * Spin the intake inwards
	 */
	public void spinIn() {
		intakeMotor.set(0.75);
	}
	
	/**
	 * Spin the intake outwards
	 */
	public void spinOut() {
		intakeMotor.set(-0.75);
	}

	/**
	 * Move the intake arm up or down
	 * @param speed The speed of the motor
	 */
	public void move(double speed) {
		intakeVerticalMotor.set(speed);
	}
	
	/**
	 * Use encoder PID to set the intake arm to a location
	 * @param angle The setpoint for PID
	 */
	public void setIntakeAngle(double angle) {
		encoderPID.setSetpoint(angle * COUNTS_PER_DEGREE);
	}
	
	/**
	 * Get the intake arm's angle
	 * @return The current location
	 */
	public double getIntakeAngle() {
		return intakeEncoder.get() / COUNTS_PER_DEGREE;
	}
	
	/**
	 * Get the intake arm's setpoint
	 * @return The current setpoint
	 */
	public double getIntakeSetpointAngle() {
		return encoderPID.getSetpoint() / COUNTS_PER_DEGREE;
	}
	
	/**
	 * Check if the intake is on the setpoint
	 * @return Boolean if intake is on setpoint
	 */
	public boolean intakeOnTarget() {
		return Math.abs(getIntakeSetpointAngle() - getIntakeAngle()) < 3;
	}

	/**
	 * Enable PID
	 */
	public void enablePID() {
		encoderPID.enable();
		encoderPID.reset();
		encoderPID.setSetpoint(encoderPID.getSetpoint());
	}

	/**
	 * Stop the intake
	 */
	public void stopIntake() {
		intakeMotor.set(0);
	}

	/**
	 * Stop the intake arm
	 */
	public void stopVertical() {
		intakeVerticalMotor.set(0);
	}
}
