package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.EncoderPIDSource;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.TalonPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {
	public final double ERROR = 5;
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
		
		turnTablePidController.enable();
		hoodPidController.enable();
		shootingWheelPidController.enable();
	}
	
    public void initDefaultCommand() {
    }
    
    /**
     * Roll the Jeff rollers (the motors that guide the ball into the firing mechanism) at a desired speed.
     * @param speed 	The speed to set both Jeff rollers at.
     */
    public void spinJeffRollers(double speed) {
    	RobotMap.jeffRoller1.set(speed);
    	RobotMap.jeffRoller2.set(speed);
    }
    
    public void spinTurnTable(double speed) {
    	RobotMap.turnTableMotor.set(speed);
    }
    
    public void setTurnTableAngle(double angle) {
    	turnTablePidController.setSetpoint(angle);
    }
    
    public void spinHood(double speed) {
    	RobotMap.hoodMotor.set(speed);
    }
    
    public void setHoodAngle(double angle) {
    	hoodPidController.setSetpoint(angle);
    }
    
    public boolean hoodAngleOnTarget() {
    	return hoodPidController.getSetpoint() - ERROR < RobotMap.hoodEncoder.get() && hoodPidController.getSetpoint() + ERROR > RobotMap.hoodEncoder.get();
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
    
    public boolean shootingWheelOnTarget(double target) {
    	return Math.abs(Math.abs(target) - Math.abs(RobotMap.shootingWheelMotor.getEncVelocity() / 1024.0 * 60.0)) < 50; //TODO: Test error
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

