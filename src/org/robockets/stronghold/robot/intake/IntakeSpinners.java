package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSpinners extends Subsystem {
    
	private Victor intakeMotor;
	
    public IntakeSpinners(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
			intakeMotor = RobotMap.intakeMotorFront;
		} else {
			intakeMotor = RobotMap.intakeMotorBack;
		}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void spin(double speed) {
		intakeMotor.set(speed);
	}

	public void spinIn() {
		intakeMotor.set(0.75);
	}

	public void spinOut() {
		intakeMotor.set(-0.75);
	}

	public void stop() {
		intakeMotor.set(0);
	}
}

