package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.OI;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Joyride extends Command {

	double left;
	double right;
		
    public Joyride() {
        //requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveTrain.encodersPID.enable();
    	//Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	left = OI.joystick.getRawAxis(1);
    	right = OI.joystick2.getRawAxis(5);

		/*left *= 0.2;
		right *= 0.2;

    	if (OI.joystick.getRawButton(5)) {
			left *= 0.5;
			right *= 0.5;
		}
    	
		if (OI.joystick.getRawButton(6)) {
    		left *= 0.8;
    		right *= 0.8;
    	}
		
    	//if (Math.abs(OI.joystick.getRawAxis(4)) < 0.1 && Math.abs(OI.joystick.getRawAxis(1)) > 0.1) {	
    		//Robot.driveTrain.encodersPID.enable();
    		//Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -Robot.driveTrain.encodersPID.get());

    	//} else if (Math.abs(OI.joystick.getRawAxis(1)) < 0.1 && Math.abs(OI.joystick.getRawAxis(4)) < 0.1) {
    		//Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		//Robot.driveTrain.encodersPID.reset();
    		//Robot.driveTrain.driveArcade(0, 0);
    	//} else {	
		
		if (OI.buttonBoard1.getRawButton(6)) { // Don't allow Jack to turn the robot while trying to shoot
			Robot.driveTrain.joystickAllowed = false;
		} else {
			Robot.driveTrain.joystickAllowed = true;
		}*/
		
		//if (Robot.driveTrain.joystickAllowed) {
			Robot.driveTrain.driveArcade(left, -right);
		//}
    		//Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		//Robot.driveTrain.encodersPID.reset();
    	//} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.driveTrain.stop();
    	//Robot.driveTrain.encodersPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
