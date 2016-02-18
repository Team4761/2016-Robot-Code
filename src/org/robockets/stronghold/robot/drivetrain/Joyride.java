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
    	Robot.driveTrain.encodersPID.enable();
    	Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(OI.joystick.getRawAxis(4)) < 0.1 && Math.abs(OI.joystick.getRawAxis(1)) > 0.1) {
    		Robot.driveTrain.encodersPID.enable();
    		Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -Robot.driveTrain.encodersPID.get());
    	} else if (Math.abs(OI.joystick.getRawAxis(1)) < 0.1 && Math.abs(OI.joystick.getRawAxis(4)) < 0.1) {
    		Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		Robot.driveTrain.encodersPID.reset();
    		Robot.driveTrain.driveArcade(0, 0);
    	} else {
    		Robot.driveTrain.encodersPID.reset();
    		Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -OI.joystick.getRawAxis(4));		
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.encodersPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
