package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
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
	double robotShooterToTargetHeight = 12;
	double wheelDiameter = 6;
	double shaftRPM;
	
	boolean continuous;
	
	boolean hitSpeedTarget = false;
	boolean release = false;
	
    public VerticalAlign(boolean continuos) {
    	requires(Robot.shooter);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	//if(!continuous) { setTimeout(10); } // Should not take longer than 10 seconds.
    	hitSpeedTarget = false;
    	release = false;
    }

    protected void execute() {
    	//TODO: Horizontally align.
    	double distanceToTarget = table.getNumber("distance_guess", 6);
    	
    	double angle = -(Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12)) / distanceToTarget) * 180 / Math.PI);
    	SmartDashboard.putNumber("angle", angle);
    	
    	double velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
    
    	shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    	shaftRPM += (18.929 * distanceToTarget) + 92.5;
    	
    	SmartDashboard.putNumber("shaftRPM", shaftRPM);
    	SmartDashboard.putNumber("distance", distanceToTarget);
    	
    	Robot.shooter.setHoodAngle(angle);
    	Robot.shooter.setShootingWheelSpeed(shaftRPM);
    	
    	if (Robot.shooter.shootingWheelOnTarget()) {
    		if (!hitSpeedTarget) {
    			setTimeout(3);
    		}
    		
    		hitSpeedTarget = true;
    	} else {
    		hitSpeedTarget = false;
    	}
    }

    protected boolean isFinished() {
    	//if(continuous == false){
    		return Robot.shooter.hoodOnTarget()
    				&& Robot.shooter.shootingWheelOnTarget()
    				&& Robot.shooter.turnTableOnTarget()
    				&& isTimedOut() && hitSpeedTarget;
    	//} else { return false; }
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
