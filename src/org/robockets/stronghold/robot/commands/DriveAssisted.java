package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAssisted extends Command {

	public boolean pidEnable;
	
    public DriveAssisted() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putBoolean("Enable", false);
    	Robot.drivetrain.gyroPID.setSetpoint(0);
    	
    	System.out.println("DriveAssisted Init");
    	SmartDashboard.putNumber("p",Robot.drivetrain.gyroPID.getP());
		SmartDashboard.putNumber("i",Robot.drivetrain.gyroPID.getI());
		SmartDashboard.putNumber("d",Robot.drivetrain.gyroPID.getD());		
		pidEnable = true;
		
		Robot.drivetrain.gyroPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("GyroAngle", RobotMap.navX.getAccumulatedYaw());
    	SmartDashboard.putNumber("GyroPID", Robot.drivetrain.gyroPID.get());
    	SmartDashboard.putNumber("Setpoint", Robot.drivetrain.gyroPID.getSetpoint());
    	//System.out.println(Robot.drivetrain.gyroPID.get());
    	if (!SmartDashboard.getBoolean("Enable")) {
    		pidEnable = true;
    		Robot.drivetrain.gyroPID.disable();
    		if (Robot.oi.stick.getRawButton(6)) {
    			Robot.drivetrain.driveArcade(0, 0.75);
    		} else if (Robot.oi.stick.getRawButton(5)) {
    			Robot.drivetrain.driveArcade(0, -0.75);
    		}
    	} else {
    		if(pidEnable) {
    			Robot.drivetrain.gyroPID.enable();
    			pidEnable = false;
    		} else {
    			
    			Robot.drivetrain.driveAssisted(0, 0, false);
    	
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
