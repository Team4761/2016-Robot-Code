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
	double robotShooterToTargetHeight = 16;
	double wheelDiameter = 6;
	
	boolean continuous;
	double bonusAngle = 0;
	
	boolean hitSpeedTarget = false;
	
    public VerticalAlign(boolean continuous) {
    	requires(Robot.hood);
    	//requires(Robot.shootingWheel);
    	this.continuous = continuous;
    }
    
    public VerticalAlign(boolean continuous, double bonusAngle) {
    	//requires(Robot.shooter);
    	this.continuous = continuous;
    	this.bonusAngle = bonusAngle;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	SmartDashboard.putBoolean("Shoot Vertically Aligned", false);
    }

    protected void execute() {
    	if (table.getNumber("can_see_target", 0) == 1) {
    		double distanceToTarget;
    		distanceToTarget = table.getNumber("distance_guess", 6);

    		double angle = 0;
    		if (distanceToTarget < 4) {
    			angle = -1;
	    	} else if ((distanceToTarget > 4) && (distanceToTarget < 5)) {
    			angle = -51;
	    	} else if ((distanceToTarget > 5) && (distanceToTarget < 6)) {
    			angle = -41.5;
	    	} else if ((distanceToTarget > 6) && (distanceToTarget < 7)) {
	    		angle = -38;
	    	} else if ((distanceToTarget > 7) && (distanceToTarget < 8)) {
	    		angle = -37;
	    	} else if ((distanceToTarget > 8) && (distanceToTarget < 9)) {
	    		angle = -41.5;
	    	} else if ((distanceToTarget > 9) && (distanceToTarget < 10)) {
	    		angle = -39.5;
	    	} else if ((distanceToTarget > 10) && (distanceToTarget < 11)) {
	    		angle = -41;
	    	} else if ((distanceToTarget > 11) && (distanceToTarget < 12)) {
	    		angle = -42;
	    	}
    		
    		angle += bonusAngle;
    		
    		if (SmartDashboard.getNumber("Bonus Angle", 0) != 0) {
    			angle = SmartDashboard.getNumber("Bonus Angle", 0);
    		}
    		
    		//double angle = -(Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12)) / distanceToTarget) * 180 / Math.PI);
    		//angle += 5;

    		SmartDashboard.putNumber("angle", angle);

    		Robot.hood.setAngle(angle);
    	}
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
