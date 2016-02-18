package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
			encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoderFront, new DummyPIDOutput());
		} else {
			intakeVerticalMotor = RobotMap.intakeVerticalMotorBack;
			intakeMotor = RobotMap.intakeMotorBack;
			encoderPID = new PIDController(0.1, 0.1, 0, RobotMap.intakeEncoderBack, new DummyPIDOutput());
		}

		encoderPID.disable();
		encoderPID.setSetpoint(0);
		encoderPID.setPercentTolerance(0.05);
		encoderPID.setContinuous(true);

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

	public void spinAssisted() {
		encoderPID.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D")); // Still broken
		intakeMotor.set(encoderPID.get());
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
