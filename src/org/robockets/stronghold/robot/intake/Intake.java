package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem having to do with inputting cannonballs from the floor.
 */
public class Intake extends Subsystem {

	public final PIDController intakePidController;
	
	 public Intake(){
		 intakePidController = new PIDController(1, 1, 0, RobotMap.intakeMotorEncoder, new DummyPIDOutput());
	 } 
    
	public void spin(double speed){
		RobotMap.intakeMotor.set(speed);
	} 
	
	public void setIntakeAngle(double angle){
		intakePidController.setSetpoint(angle);
	}
	
	public void enableIntakePid(){
		intakePidController.enable();
		intakePidController.reset();
		intakePidController.setSetpoint(RobotMap.intakeMotorEncoder.getDistance());
	}
	
	public void spinAssisted(){
		RobotMap.intakeMotor.set(intakePidController.get());
	}
	 
    public void spinRoller(double speed) {
    	RobotMap.intakeRollerMotor.set(speed);
    }
    
    public void spinRollersIn() {
    	RobotMap.intakeRollerMotor.set(0.5);
    }
    
    public void spinRollersOut() {
    	RobotMap.intakeRollerMotor.set(-0.5);
    }

	@Override
	protected void initDefaultCommand() {		
	}
}

