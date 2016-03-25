package org.robockets.stronghold.robot.shootingwheel;

import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.TalonPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The spinny janky wanky part of the shooting subsystem.
 */
public class SpinningWheel extends Subsystem {
	public final double WHEEL_ERROR = 50;
	public final PIDController shootingWheelPIDController;
	
	public SpinningWheel(){
		shootingWheelPIDController = new PIDController(0.0075, 0.001, 0.001, new TalonPIDSource(), RobotMap.shootingWheelMotor);
		shootingWheelPIDController.disable();
		
		shootingWheelPIDController.setSetpoint(0);
		shootingWheelPIDController.setContinuous(true);
		shootingWheelPIDController.setOutputRange(0, 1);
		
		shootingWheelPIDController.enable();
	}

    public void initDefaultCommand() {
    }
    
    public void setSpeed(double speed) {
    	//shootingWheelPIDController.setPID(SmartDashboard.getNumber("Spin P"), SmartDashboard.getNumber("Spin I"), SmartDashboard.getNumber("Spin D"));
    	shootingWheelPIDController.setSetpoint(speed);
    }
    
    public void setVoltage(double voltage) {
    	RobotMap.shootingWheelMotor.set(voltage);
    }
    
    public double getSpeed() {
    	return RobotMap.shootingWheelMotor.getEncVelocity() / 1024.0 * 60.0;
    }
    
    public boolean onTarget() {
    	return Math.abs(Math.abs(shootingWheelPIDController.getSetpoint()) - Math.abs(getSpeed())) < WHEEL_ERROR;
    }
}

