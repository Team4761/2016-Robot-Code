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
	boolean gyroPIDDisable;
	
    public Joyride() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putBoolean("Start GyroPID", false);
    	SmartDashboard.putBoolean("Reset Gyro", false);
    	SmartDashboard.putNumber("Gyro P", Robot.driveTrain.gyroPID.getP());
    	SmartDashboard.putNumber("Gyro I", Robot.driveTrain.gyroPID.getI());
    	SmartDashboard.putNumber("Gyro D", Robot.driveTrain.gyroPID.getD());
    	SmartDashboard.putNumber("Gyro Setpoint", Robot.driveTrain.gyroPID.getSetpoint());
    	Robot.driveTrain.gyroPID.setSetpoint(SmartDashboard.getNumber("Gyro Setpoint"));
    	Robot.driveTrain.setAngle(0, false);
    	gyroPIDDisable = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.gyroPID.setPID(SmartDashboard.getNumber("Gyro P"),SmartDashboard.getNumber("Gyro I"),SmartDashboard.getNumber("Gyro D"));
    	if(SmartDashboard.getBoolean("Reset Gyro")){
    		SmartDashboard.putBoolean("Reset Gyro", false);
    		RobotMap.navX.zeroYaw();
    		System.out.println("Zero Yaw");
    	} 
    	
    	SmartDashboard.putNumber("Gyro Angle Acc", RobotMap.navX.getAccumulatedYaw());
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.navX.getYaw());
    	System.out.println(RobotMap.navX.getYaw());
    	if (SmartDashboard.getBoolean("Start GyroPID")) {
    		Robot.driveTrain.gyroPID.enable();
    		Robot.driveTrain.driveAssisted(false);
    	} else {
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
