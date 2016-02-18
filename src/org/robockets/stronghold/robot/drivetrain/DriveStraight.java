package org.robockets.stronghold.robot.drivetrain;

import org.robockets.stronghold.robot.OI;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {

    public DriveStraight() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("Encoders P", Robot.driveTrain.encodersPID.getP());
    	SmartDashboard.putNumber("Encoders I", Robot.driveTrain.encodersPID.getI());
    	SmartDashboard.putNumber("Encoders D", Robot.driveTrain.encodersPID.getD());
    	SmartDashboard.putNumber("Encoders Setpoint", Robot.driveTrain.encodersPID.getSetpoint());
    	SmartDashboard.putBoolean("Reset encodersPID", false);
    	SmartDashboard.putBoolean("Enable encodersPID", false);
    	Robot.driveTrain.encodersPID.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder Offset", (-RobotMap.driveEncoder.get() - RobotMap.driveEncoder2.get()));
    	SmartDashboard.putNumber("Encoder 1", -RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Encoder 2", RobotMap.driveEncoder2.get());
    	//Robot.driveTrain.encodersPID.setSetpoint(SmartDashboard.getNumber("Encoders Setpoint"));
    	
    	if (!SmartDashboard.getBoolean("Enable encodersPID")) {
    		Robot.driveTrain.encodersPID.disable();
    		Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -OI.joystick.getRawAxis(4));
    		if (SmartDashboard.getBoolean("Reset encodersPID")) {
    			RobotMap.driveEncoder.reset();
    			RobotMap.driveEncoder2.reset();
    		}
    	} else {
    		Robot.driveTrain.encodersPID.setPID(
    				SmartDashboard.getNumber("Encoders P"), 
    				SmartDashboard.getNumber("Encoders I"), 
    				SmartDashboard.getNumber("Encoders D"));
    		Robot.driveTrain.encodersPID.enable();
    		
    		if (Math.abs(OI.joystick.getRawAxis(4)) < 0.1) {
    			Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -Robot.driveTrain.encodersPID.get());
    		} else {
    			System.out.println(Robot.driveTrain.encodersPID.getSetpoint());
    			Robot.driveTrain.encodersPID.setSetpoint(-RobotMap.driveEncoder.get() - RobotMap.driveEncoder2.get());
    			Robot.driveTrain.driveArcade(OI.joystick.getRawAxis(1), -OI.joystick.getRawAxis(4));
    		}
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
