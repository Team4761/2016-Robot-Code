package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Aim the hood so that the camera aligns with the goal with fancy equations.
 */
public class AimShooter extends Command {

	NetworkTable table;
	double gravAcc = 32;
	double floorToTargetHeight = 8;
	double robotShooterToTargetHeight = 16;
	double wheelDiameter = 6;
	double velocity;
	
    public AimShooter() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	table = NetworkTable.getTable("Vision/report"); //TODO: Name this stuff.
    	setTimeout(10); // Should not take longer than 10 seconds.
    }

    protected void execute() {
    	//TODO: Horizontally align.
    	double distanceToTarget = table.getNumber("distance", 10);
    	
    	double angle = Math.atan(2 * ( floorToTargetHeight - (robotShooterToTargetHeight / 12) / distanceToTarget) * 180 / Math.PI);
    	
    	velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
    	
    	double shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    	
    	Robot.shooter.setHoodAngle(angle);
    	Robot.shooter.setShootingWheelSpeed(shaftRPM);
    	
    	Robot.shooter.spinTurnTableAssisted();
    }

    protected boolean isFinished() {
    	return Robot.shooter.hoodPidController.onTarget()
    			&& Robot.shooter.hoodPidController.onTarget()
    			&& Robot.shooter.shootingWheelOnTarget(velocity)
    			|| isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
