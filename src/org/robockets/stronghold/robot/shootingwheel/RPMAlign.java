package org.robockets.stronghold.robot.shootingwheel;

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
	
	Double distance;
	
    public RPMAlign(boolean continuous) {
        requires(Robot.shootingWheel);
    	this.continuous = continuous;
    }
    
    public RPMAlign(boolean continuous, Double distance) {
    	this.continuous = continuous;
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	table = NetworkTable.getTable("vision"); //TODO: Name this stuff.
    	hitSpeedTarget = false;
    	SmartDashboard.putBoolean("Shoot RPM Aligned", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distanceToTarget;
    	if (distance == null) {
    		distanceToTarget = table.getNumber("distance_guess", 6);
    	} else {
    		distanceToTarget = distance;
    	}
    	
    	double velocity = Math.sqrt( (4 * Math.pow(floorToTargetHeight - robotShooterToTargetHeight / 12 , 2) + Math.pow(distanceToTarget, 2) ) * gravAcc / ( 2 * (floorToTargetHeight - robotShooterToTargetHeight / 12 ) ));
        
    	shaftRPM = velocity * 60 / (Math.PI * wheelDiameter / 12);
    	
    	if ((distanceToTarget > 5) && (distanceToTarget < 6)) {
    		shaftRPM += 200;
    	} else if ((distanceToTarget > 6) && (distanceToTarget < 7)) {
    		shaftRPM += 200;
    	} else if ((distanceToTarget > 7) && (distanceToTarget < 8)) {
    		shaftRPM += 200;
    	} else if ((distanceToTarget > 8) && (distanceToTarget < 9)) {
    		shaftRPM += 200;
    	} else if ((distanceToTarget > 9) && (distanceToTarget < 10)) {
    		shaftRPM += 210;
    	} else if ((distanceToTarget > 10) && (distanceToTarget < 11)) {
    		shaftRPM += 230;
    	} else if ((distanceToTarget > 11) && (distanceToTarget < 12)) {
    		shaftRPM += 250;
    	} else {
    		shaftRPM += (18.929 * distanceToTarget) + 82.5;
    	}
    	
    	SmartDashboard.putNumber("shaftRPM", shaftRPM);
    	
    	Robot.shootingWheel.setSpeed(shaftRPM);
    	
    	if (!continuous) {
	    	if (Robot.shootingWheel.onTarget()) {
	    		if (!hitSpeedTarget) {
	    			setTimeout(1);
	    		}
	    		
	    		hitSpeedTarget = true;
	    	} else {
	    		hitSpeedTarget = false;
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.shootingWheel.onTarget() && isTimedOut() && hitSpeedTarget) {
    		SmartDashboard.putBoolean("Shoot RPM Aligned", true);
    		if (continuous == false) { return true; }
    		else { return false; }
    	} else {
    		SmartDashboard.putBoolean("Shoot RPM Aligned", false);
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Shoot RPM Aligned", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
