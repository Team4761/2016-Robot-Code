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
	double shaftRPM;
	
    public VerticalAlign() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	setTimeout(10); // Should not take longer than 10 seconds.
    }

    protected void execute() {
    	//TODO: Horizontally align.
    	double distanceToTarget = table.getNumber("distance_guess", 10);
    	SmartDashboard.putNumber("distance", distanceToTarget);
    	
    	double angle = Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12) / distanceToTarget) * 180 / Math.PI);
    	SmartDashboard.putNumber("angle", angle);
    	
    	double velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
    
    	shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    	
    	Robot.shooter.setHoodAngle(angle);
    	//Robot.shooter.setShootingWheelSpeed(shaftRPM);
    }

    protected boolean isFinished() {
    	return Robot.shooter.hoodOnTarget()
    			&& Robot.shooter.shootingWheelOnTarget()
    			&& Robot.shooter.turnTableOnTarget()
    			|| isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
