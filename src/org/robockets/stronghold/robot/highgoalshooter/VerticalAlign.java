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
	
	boolean continuous;
	
	boolean hitSpeedTarget = false;
	
    public VerticalAlign(boolean continuous) {
    	requires(Robot.shooter);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	hitSpeedTarget = false;
    }

    protected void execute() {
    	//if(table.getNumber("heartbeat", 0) == 1){
    		double distanceToTarget = table.getNumber("distance_guess", 6);
    		SmartDashboard.putNumber("distance", distanceToTarget);

    		double angle = -(Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12)) / distanceToTarget) * 180 / Math.PI);
    		SmartDashboard.putNumber("angle", angle);
    	
    		double velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
    		double shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    		shaftRPM += (18.929 * distanceToTarget) + 92.5;
    	
    	SmartDashboard.putNumber("distance", distanceToTarget);
    	
    	Robot.shooter.setHoodAngle(angle);
    }

    protected boolean isFinished() {
    	if(continuous == false) {
    		return Robot.shooter.turnTableOnTarget();
    	} else { return false; }
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
