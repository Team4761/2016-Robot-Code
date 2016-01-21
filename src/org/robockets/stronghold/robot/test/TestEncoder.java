package org.robockets.stronghold.robot.test;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestEncoder extends Subsystem {
	public final PIDController pidController = new PIDController(0.005, 0.0001, 0, RobotMap.testEncoder, new DummyPIDOutput());
	
	public TestEncoder() {
		pidController.disable();
		pidController.setSetpoint(0);
		pidController.setPercentTolerance(0.05);
		pidController.setContinuous(true);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turn(double speed) {
    	RobotMap.testMotor1.set(speed);
    	RobotMap.testMotor2.set(speed);
    }
    
    public void turnAssisted() {
    	//pidController.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
    	pidController.setPID(SmartDashboard.getNumber("P"), pidController.getI(), SmartDashboard.getNumber("D"));
    	
    	RobotMap.testMotor1.set(pidController.get());
    	RobotMap.testMotor2.set(pidController.get());
    }
    
    public void stop() {
    	RobotMap.testMotor1.set(0);
    	RobotMap.testMotor2.set(0);
    }
    
    public void enablePID() {
    	pidController.enable();
    	pidController.reset();
    	pidController.setSetpoint(RobotMap.testEncoder.get());
    }
    
    public void setAngle(double angle) {
    	pidController.setSetpoint(angle);
    }
}