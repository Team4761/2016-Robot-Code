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

	boolean gyroStart;
	
    public Joyride() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.encodersPID.enable();
    	Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    	SmartDashboard.putNumber("Gyro P", Robot.driveTrain.gyroPID.getP());
    	SmartDashboard.putNumber("Gyro I", Robot.driveTrain.gyroPID.getI());
    	SmartDashboard.putNumber("Gyro D", Robot.driveTrain.gyroPID.getD());
    	SmartDashboard.putNumber("Gyro Setpoint", 0);
    	SmartDashboard.putBoolean("Gyro Start", false);
    	gyroStart = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Gyro Angle Graph", RobotMap.navX.getYaw());
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.navX.getYaw());
    	Robot.driveTrain.gyroPID.setPID(SmartDashboard.getNumber("Gyro P"), SmartDashboard.getNumber("Gyro I"), SmartDashboard.getNumber("Gyro D"));
    	Robot.driveTrain.gyroPID.setSetpoint(SmartDashboard.getNumber("Gyro Setpoint"));
    	SmartDashboard.putNumber("Gyro PID", Robot.driveTrain.gyroPID.get());
    	
    	if (SmartDashboard.getBoolean("Gyro Start")) {
    		Robot.driveTrain.gyroPID.enable();
    		Robot.driveTrain.driveAssisted(false, false);
    	} else {
    		if (gyroStart) {
    			gyroStart = false;
    			Robot.driveTrain.gyroPID.disable();
    		}
    		
    	}
    	
    	/*if (Math.abs(OI.joystick.getRawAxis(4)) < 0.1 && Math.abs(OI.joystick.getRawAxis(1)) > 0.1) {	
    		Robot.driveTrain.encodersPID.enable();
    		Robot.driveTrain.driveArcade(-OI.joystick.getRawAxis(1), -Robot.driveTrain.encodersPID.get());
    	} else if (Math.abs(OI.joystick.getRawAxis(1)) < 0.1 && Math.abs(OI.joystick.getRawAxis(4)) < 0.1) {
    		Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		Robot.driveTrain.encodersPID.reset();
    		Robot.driveTrain.driveArcade(0, 0);
    	} else {	
    		Robot.driveTrain.driveArcade(-OI.joystick.getRawAxis(1), -OI.joystick.getRawAxis(4));
    		Robot.driveTrain.encodersPID.setSetpoint(Robot.driveTrain.getEncodersOffset());
    		Robot.driveTrain.encodersPID.reset();
    	} */
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
