package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {
	public final double COUNTS_PER_DEGREE_HOOD = 7.75;
	public final double ERROR = 20;
	
	public final PIDController turnTablePidController;
	public final PIDController hoodPidController;
	
	public HighGoalShooter() {
		turnTablePidController = new PIDController(1, 1, 0, RobotMap.turnTableEncoder, new DummyPIDOutput());
		hoodPidController = new PIDController(0.02, 0.0001, 0, RobotMap.hoodEncoder, new DummyPIDOutput());
		
		turnTablePidController.disable();
		hoodPidController.disable();
		
		turnTablePidController.setSetpoint(0);
		turnTablePidController.setContinuous(true);
		
		hoodPidController.setSetpoint(0);
		hoodPidController.setContinuous(true);
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
    
    public void spinTurnTableAssisted() {
    	RobotMap.turnTableMotor.set(turnTablePidController.get());
    }
    
    public void setTurnTableAngle(double angle) {
    	turnTablePidController.setSetpoint(angle);
    }
    
    public void spinHood(double speed) {
    	RobotMap.hoodMotor.set(speed);
    }
    
    public void spinHoodAssisted() {
    	RobotMap.hoodMotor.set(hoodPidController.get());
    }
    
    public void setHoodAngle(double angle) {
    	hoodPidController.setSetpoint(angle * COUNTS_PER_DEGREE_HOOD);
    }
    
    public boolean hoodAngleOnTarget() {
    	return hoodPidController.getSetpoint() - ERROR < RobotMap.hoodEncoder.get() && hoodPidController.getSetpoint() + ERROR > RobotMap.hoodEncoder.get();
    }

    /**
     * Roll the shooting wheel to fire the boulder at a desired speed.
     * @param speed 	The rpm to set the shooting mechanism at.
     */
    public void setShootingWheelSpeed(double speed) {
    	double correctedSpeed = -1 * Math.abs(speed); // Only negative numbers
    	double voltage = -0.00050523 * correctedSpeed + 0.01511; // Regression I did on desmos
    	if (Math.abs(voltage) < 1) { // Don't try to go over max speeds
        	RobotMap.shootingWheelMotor.set(voltage);
    	} else {
    		System.out.println("Invalid shooting wheel speed!");
    	}
    }
    
    public void setShootingWheelVoltage(double voltage) {
    	RobotMap.shootingWheelMotor.set(voltage);
    }
    
    public boolean shootingWheelOnTarget(double target) {
    	return Math.abs(Math.abs(target) - Math.abs(RobotMap.shootingWheelMotor.getEncVelocity() / 1024.0 * 60.0)) < 50; //TODO: Test error
    }
    
    public void enableTurnTablePID() {
    	turnTablePidController.enable();
    	turnTablePidController.reset();
    	turnTablePidController.setSetpoint(RobotMap.turnTableEncoder.get()); // Make sure setpoint starts as current position
    }
    
    public void enableHoodPID() {
    	hoodPidController.enable();
    	hoodPidController.reset();
    	hoodPidController.setSetpoint(RobotMap.hoodEncoder.get());
    }
    
    public void enableShootingWheelPID() {
    	RobotMap.shootingWheelMotor.enableControl();
    }
}

