package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.OI;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Joyride extends Command {
	double stick;
	double leftTrigger;
	double rightTrigger;

    public Joyride() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putNumber("Encoder P", Robot.driveTrain.encoderPID.getP());
        SmartDashboard.putNumber("Encoder I", Robot.driveTrain.encoderPID.getI());
        SmartDashboard.putNumber("Encoder D", Robot.driveTrain.encoderPID.getD());
        SmartDashboard.putNumber("Encoder Setpoint", Robot.driveTrain.encoderPID.getSetpoint());
        SmartDashboard.putBoolean("Reset encoderPID", false);
        SmartDashboard.putBoolean("Enable encoderPID", false);
        Robot.driveTrain.encoderPID.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder PID", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Encoder PID Out", Robot.driveTrain.encoderPID.get());
        System.out.println(RobotMap.driveEncoder.get()); // temp
        Robot.driveTrain.encoderPID.setSetpoint(SmartDashboard.getNumber("Encoder Setpoint"));
        if (!SmartDashboard.getBoolean("Enable encoderPID")) {
            Robot.driveTrain.encoderPID.disable();
            Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -OI.joystick.getRawAxis(4));
            if (SmartDashboard.getBoolean("Reset encoderPID")) {
            	RobotMap.driveEncoder.reset();
            }
        } else {
        	Robot.driveTrain.encoderPID.setPID(
        			SmartDashboard.getNumber("Encoder P"), 
        			SmartDashboard.getNumber("Encoder I"), 
        			SmartDashboard.getNumber("Encoder D"));
            Robot.driveTrain.encoderPID.enable();
            Robot.driveTrain.driveArcade(Robot.driveTrain.encoderPID.get(), 0);
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
