package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.DummyPIDOutput;
import org.robockets.stronghold.robot.EncoderPIDSource;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {
	public final PIDController turnTablePidController;
	public final PIDController hoodPidController;
	public final EncoderPIDSource turnTableSource;
	
	public HighGoalShooter() {
		turnTableSource = new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement);
		turnTablePidController = new PIDController(0.06, 0, 0, turnTableSource, new DummyPIDOutput());
		hoodPidController = new PIDController(1, 1, 0, RobotMap.hoodEncoder, new DummyPIDOutput());
		
		turnTablePidController.setPercentTolerance(0.05);
		turnTablePidController.setContinuous(true);
		turnTablePidController.disable();
		
		hoodPidController.disable();
		
		
		hoodPidController.setSetpoint(0);
		hoodPidController.setPercentTolerance(1/360);
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
    	hoodPidController.setSetpoint(angle);
    }

    /**
     * Roll the shooting wheel to fire the cannonball at a desired speed.
     * @param speed 	The speed to set the shooting mechanism at.
     */
    public void setShootingWheelSpeed(double speed) {
    	RobotMap.shootingWheelMotor.set(speed);
    }
    
    public boolean shootingWheelOnTarget(){
    	return RobotMap.shootingWheelMotor.getError() < 5; //TODO: Set a good error bit here.
    }
    
    public void enableTurnTablePID() {
    	turnTablePidController.enable();
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

