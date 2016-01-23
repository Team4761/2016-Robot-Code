package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {
	public final PIDController turnTablePidController;
	public final PIDController hoodPidController;
	
	public HighGoalShooter() {
		turnTablePidController = new PIDController(1, 1, 0, RobotMap.turnTableEncoder, new DummyPIDOutput());
		hoodPidController = new PIDController(1, 1, 0, RobotMap.turnTableEncoder, new DummyPIDOutput());
		
		turnTablePidController.disable();
		hoodPidController.disable();
		
		turnTablePidController.setSetpoint(0);
		turnTablePidController.setPercentTolerance(0.05);
		turnTablePidController.setContinuous(true);
		
		hoodPidController.setSetpoint(0);
		hoodPidController.setPercentTolerance(0.05);
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
    
    /**
     * Roll the shooting wheel to fire the cannonball at a desired speed.
     * @param speed 	The speed to set the shooting mechanism at.
     */
    public void spinShootingWheel(double speed) {
    	RobotMap.shootingWheelMotor.set(speed);
    }
    
    public void spinTurnTable(double speed) {
    	RobotMap.turnTableMotor.set(speed);
    }
    
    public void spinTurnTableAssisted() {
    	RobotMap.turnTableMotor.set(hoodPidController.get());
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
    	hoodPidController.setSetpoint(angle);
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
}

