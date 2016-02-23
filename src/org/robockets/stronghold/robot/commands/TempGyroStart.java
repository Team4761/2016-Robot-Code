package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TempGyroStart extends Command {

	double angle;
	
    public TempGyroStart(double angle) {
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.gyroPID.setPID(SmartDashboard.getNumber("Gyro P"), SmartDashboard.getNumber("Gyro I"), SmartDashboard.getNumber("Gyro D"));
    	Robot.driveTrain.gyroPID.setSetpoint(angle);
    	Robot.driveTrain.gyroPID.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Gyro PID", Robot.driveTrain.gyroPID.get());
    	Robot.driveTrain.driveAssisted(false, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.gyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
