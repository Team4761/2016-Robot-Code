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
	
    public VerticalAlign(boolean continuous) {
    	requires(Robot.shooter);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	hitSpeedTarget = false;
    }

    protected void execute() {
    	//TODO: Horizontally align.
    	double distanceToTarget = table.getNumber("distance_guess", 6);
    	
    	double angle = -(Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12)) / distanceToTarget) * 180 / Math.PI);
    	SmartDashboard.putNumber("angle", angle);
    	
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
