package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Aim the hood so that the camera aligns with the goal with fancy equations.
 */
public class VerticalAlign extends Command {

	NetworkTable table;
	double gravAcc = 32;
	double floorToTargetHeight = 8;
	double robotShooterToTargetHeight = 14;
	double wheelDiameter = 6;
	
	boolean continuous;
	Double distance = null;
	
	boolean hitSpeedTarget = false;
	
    public VerticalAlign(boolean continuous) {
    	requires(Robot.hood);
    	requires(Robot.shootingWheel);
    	this.continuous = continuous;
    }
    
    public VerticalAlign(boolean continuous, double distance) {
    	//requires(Robot.shooter);
    	this.continuous = continuous;
    	this.distance = distance;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	hitSpeedTarget = false;
    	SmartDashboard.putBoolean("Shoot Vertically Aligned", false);
    }

    protected void execute() {
    	double distanceToTarget;
    	if (distance == null) {
    		distanceToTarget = table.getNumber("distance_guess", 6);
    	} else {
    		distanceToTarget = distance;
    	}
    		
    	SmartDashboard.putNumber("distance", distanceToTarget);

    	double angle = -(Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12)) / distanceToTarget) * 180 / Math.PI);
    	SmartDashboard.putNumber("angle", angle);
    	
    	SmartDashboard.putNumber("distance", distanceToTarget);
    	
    	Robot.hood.setAngle(angle);
    }

    protected boolean isFinished() {
    	if (Robot.hood.onTarget()) {
    		SmartDashboard.putBoolean("Shoot Vertically Aligned", true);
    		if (continuous == false) { return true; }
    	} else { SmartDashboard.putBoolean("Shoot Vertically Aligned", false); }
    	return false;
    }

    protected void end() {
    	SmartDashboard.putBoolean("Shoot Vertically Aligned", false);
    }

    protected void interrupted() {
    }
}
