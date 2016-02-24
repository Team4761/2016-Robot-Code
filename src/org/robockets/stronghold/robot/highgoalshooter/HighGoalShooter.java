package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;
import org.robockets.stronghold.robot.pidsources.TalonPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {
	public final double HOOD_ERROR = 2;
	public final double WHEEL_ERROR = 50;
	public final double COUNTS_PER_DEGREE_HOOD = 7.3111;
	
	public final PIDController turnTablePidController;
	public final PIDController hoodPidController;
	public final PIDController shootingWheelPidController;
	
	public HighGoalShooter() {
		EncoderPIDSource turnTableSource = new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement);
		EncoderPIDSource hoodSource = new EncoderPIDSource(RobotMap.hoodEncoder, 1.0 / COUNTS_PER_DEGREE_HOOD, PIDSourceType.kDisplacement);
		
		turnTablePidController = new PIDController(0.06, 0, 0, turnTableSource, RobotMap.turnTableMotor);
		//hoodPidController = new PIDController(0.02, 0.0001, 0, new HoodPIDSource(), RobotMap.hoodMotor); Used to be correct but for some reason it changed
		hoodPidController = new PIDController(0.075, 0.0001, 0, hoodSource, RobotMap.hoodMotor);
		shootingWheelPidController = new PIDController(0.0001, 0, 0.0005, new TalonPIDSource(), RobotMap.shootingWheelMotor);
		
		turnTablePidController.disable();
		hoodPidController.disable();
		shootingWheelPidController.disable();
		
		turnTablePidController.setSetpoint(0);
		turnTablePidController.setPercentTolerance(0.05);
		turnTablePidController.setContinuous(true);
		
		hoodPidController.setSetpoint(0);
		hoodPidController.setContinuous(true);
		
		shootingWheelPidController.setSetpoint(0);
		shootingWheelPidController.setContinuous(true);
		shootingWheelPidController.setOutputRange(0, 1);
		
		turnTablePidController.enable();
		hoodPidController.enable();
		shootingWheelPidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    public void setShooterFlipper(double angle) {
    	RobotMap.shootingFlipper.setAngle(angle);
    }
    
    public void setTurnTableAngle(double angle) {
    	turnTablePidController.setSetpoint(angle);
    }
    
    public double getTurnTableAngle() {
    	return RobotMap.turnTableEncoder.get() * 0.16096579;
    }
    
    public double getTurnTableSetpoint() {
    	return turnTablePidController.getSetpoint();
    }
    
    public void setHoodAngle(double angle) {
    	hoodPidController.setSetpoint(angle);
    }
    
    public double getHoodSetpoint() {
    	return hoodPidController.getSetpoint();
    }
    
    public double getHoodAngle() {
    	return RobotMap.hoodEncoder.get() / COUNTS_PER_DEGREE_HOOD;
    }
    
    public boolean hoodOnTarget() {
    	return Math.abs(Math.abs(hoodPidController.getSetpoint()) - Math.abs(getHoodAngle())) < HOOD_ERROR;
    }

    /**
     * Roll the shooting wheel to fire the boulder at a desired speed.
     * @param speed 	The rpm to set the shooting mechanism at.
     */
    public void setShootingWheelSpeed(double speed) {
    	shootingWheelPidController.setSetpoint(speed);
    }
    
    public void setShootingWheelVoltage(double voltage) {
    	RobotMap.shootingWheelMotor.set(voltage);
    }
    
    public double getShootingWheelSpeed() {
    	return RobotMap.shootingWheelMotor.getEncVelocity() / 1024.0 * 60.0;
    }
    
    public boolean shootingWheelOnTarget() {
    	return Math.abs(Math.abs(shootingWheelPidController.getSetpoint()) - Math.abs(getShootingWheelSpeed())) < WHEEL_ERROR;
    }
    
    public void enableTurnTablePID() {
    	turnTablePidController.enable();
    }
    
    public void disableTurnTablePID() {
    	turnTablePidController.disable();
    }
    
    public void enableHoodPID() {
		hoodPidController.enable();
		hoodPidController.reset();
		hoodPidController.setSetpoint(RobotMap.hoodEncoder.get());
    }
}

