package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RPMAlign extends Command {

	NetworkTable table;
	double gravAcc = 32;
	double floorToTargetHeight = 8;
	double robotShooterToTargetHeight = 12;
	double wheelDiameter = 6;
	double shaftRPM;
	
	boolean continuous;
	boolean hitSpeedTarget = false;
	
    public RPMAlign(boolean continuous) {
        // No requires so RPM can be run while horizontal align
    	this.continuous = continuous;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	hitSpeedTarget = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distanceToTarget = table.getNumber("distance_guess", 6);
    	
    	double velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
        
    	shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    	shaftRPM += (18.929 * distanceToTarget) + 92.5;
    	SmartDashboard.putNumber("shaftRPM", shaftRPM);
    	
    	Robot.shooter.setShootingWheelSpeed(shaftRPM);
    	
    	if (continuous && Robot.shooter.shootingWheelOnTarget()) {
    		if (!hitSpeedTarget) {
    			setTimeout(3);
    		}
    		
    		hitSpeedTarget = true;
    	} else {
    		hitSpeedTarget = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(continuous == false) {
    		return Robot.shooter.shootingWheelOnTarget()
    				&& isTimedOut() && hitSpeedTarget;
    	} else { return false; }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setShootingWheelSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
