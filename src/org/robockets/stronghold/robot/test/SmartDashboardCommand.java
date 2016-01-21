package org.robockets.stronghold.robot.test;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardCommand extends Command {

    public SmartDashboardCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.testEncoder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.testEncoder.setAngle(60);
    	SmartDashboard.putNumber("P", Robot.testEncoder.pidController.getP());
    	SmartDashboard.putNumber("I", Robot.testEncoder.pidController.getI());
    	SmartDashboard.putNumber("D", Robot.testEncoder.pidController.getD());
    	SmartDashboard.putBoolean("PID Enable", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder", RobotMap.testEncoder.get());
    	SmartDashboard.putNumber("PID", Robot.testEncoder.pidController.get());
    	
    	if (!SmartDashboard.getBoolean("PID Enable")) {
    		Robot.testEncoder.pidController.disable();
    		Robot.testEncoder.pidController.reset();
    		
	    	if (Robot.oi.joystick.getRawButton(5)) {
	    		Robot.testEncoder.turn(-0.15);
	    	} else if (Robot.oi.joystick.getRawButton(6)){
	    		Robot.testEncoder.turn(0.15);
	    	} else if (Robot.oi.joystick.getRawButton(1)) { 
	    		RobotMap.testEncoder.reset();
	    	} else {
	    		Robot.testEncoder.stop();
	    	}
    	} else {
    		Robot.testEncoder.pidController.enable();
    		Robot.testEncoder.turnAssisted();
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
