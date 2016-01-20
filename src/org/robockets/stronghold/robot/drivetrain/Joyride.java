package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.OI;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// 2 is lefttrigger and 3 is right while 1 is updown stick thing
    	stick = OI.joystick.getRawAxis(1);
    	leftTrigger = OI.joystick.getRawAxis(2);
    	rightTrigger = OI.joystick.getRawAxis(3);
    	
    	System.out.println(stick);
    	
    	if (leftTrigger > 0.1 && rightTrigger > 0.1) {
    		Robot.driveTrain.driveArcade(stick, 0);
    	} else if (rightTrigger > 0.1) {
    		Robot.driveTrain.driveArcade(stick, -rightTrigger);
    	} else if (leftTrigger > 0.1) {
    		Robot.driveTrain.driveArcade(stick, leftTrigger);
    	} else {
    		Robot.driveTrain.driveArcade(stick, 0);
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
