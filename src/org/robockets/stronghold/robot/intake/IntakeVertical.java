package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeVertical extends Subsystem {

	public final double COUNTS_PER_DEGREE = 7.3333333333;
	
	public final PIDController encoderPID;
	private Victor intakeVerticalMotor;
	private Encoder intakeEncoder;

	public IntakeVertical(IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorFront;
			intakeEncoder = RobotMap.intakeEncoderFront;
			encoderPID = new PIDController(0.03, 0.000025, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
			//encoderPID = new PIDController(0.01, 0.0001, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
		} else {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorBack;
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

	public void move(double speed) {
		intakeVerticalMotor.set(speed);
	}
	
	public void setIntakeAngle(double angle) {
		encoderPID.setSetpoint(angle * COUNTS_PER_DEGREE);
	}
	
	public double getIntakeAngle() {
		return intakeEncoder.get() / COUNTS_PER_DEGREE;
	}
	
	public double getIntakeSetpointAngle() {
		return encoderPID.getSetpoint() / COUNTS_PER_DEGREE;
	}
	
	public boolean intakeOnTarget() {
		return Math.abs(getIntakeSetpointAngle() - getIntakeAngle()) < 3;
	}

	public void enablePID() {
		encoderPID.reset();
		encoderPID.setSetpoint(encoderPID.getSetpoint());
		encoderPID.enable();
	}
	
	public void resetEncoder() {
		intakeEncoder.reset();
	}

	public void stop() {
		intakeVerticalMotor.set(0);
	}
}
