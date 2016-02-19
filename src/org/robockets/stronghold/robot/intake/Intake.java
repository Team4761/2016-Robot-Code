package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	Victor intakeVerticalMotor;
	Victor intakeMotor;

	public final PIDController encoderPID;

	public Intake(IntakeSide intakeSide) {
		if (intakeSide == IntakeSide.FRONT) {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorFront;
			intakeMotor = RobotMap.intakeMotorFront;
			encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoderFront, RobotMap.intakeVerticalMotorFront);
		} else {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorBack;
			intakeMotor = RobotMap.intakeMotorBack;
			encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoderBack, RobotMap.intakeVerticalMotorBack);
		}

		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);
	}

	public void initDefaultCommand() {

	}

	public void spin(double speed) {
		intakeMotor.set(speed);
	}

	public void spinIn() {
		intakeMotor.set(0.5);
	}

	public void spinOut() {
		intakeMotor.set(-0.5);
	}

	public void moveUp() {
		intakeVerticalMotor.set(0.5);
	}

	public void moveDown() {
		intakeVerticalMotor.set(-0.5);
	}

	public void move(int speed) {
		intakeVerticalMotor.set(speed);
	}

	public void move(double setpoint) {
		encoderPID.setSetpoint(setpoint);
	}
	
	public void setIntakeAngle(double angle) {
		encoderPID.setSetpoint(angle);
	}

	public void enablePID() {
		encoderPID.enable();
		encoderPID.reset();
		encoderPID.setSetpoint(encoderPID.getSetpoint());
	}

	public void stopIntake() {
		intakeMotor.set(0);
	}

	public void stopVertical() {
		intakeVerticalMotor.set(0);
	}
}
