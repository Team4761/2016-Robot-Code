package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

	NetworkTable visionTable;
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	visionTable = NetworkTable.getTable("vision");
    	
    	SmartDashboard.putData("Move Servo", new FireShooter());
    	SmartDashboard.putNumber("pid error", 0);
    	
    	SmartDashboard.putNumber("Left P", Robot.driveTrain.leftWheelsPID.getP());
    	SmartDashboard.putNumber("Left I", Robot.driveTrain.leftWheelsPID.getI());
    	SmartDashboard.putNumber("Left D", Robot.driveTrain.leftWheelsPID.getD());
    	SmartDashboard.putNumber("Left PID", Robot.driveTrain.leftWheelsPID.get());
    	
    	SmartDashboard.putNumber("Right P", Robot.driveTrain.rightWheelsPID.getP());
    	SmartDashboard.putNumber("Right P", Robot.driveTrain.rightWheelsPID.getI());
    	SmartDashboard.putNumber("Right P", Robot.driveTrain.rightWheelsPID.getD());
    	SmartDashboard.putNumber("Right PID", Robot.driveTrain.rightWheelsPID.get());
    	
    	SmartDashboard.putData("Drive to 0ft", new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 0, 0, 12));
    	SmartDashboard.putData("Drive to 6ft", new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 72, 0, 12));
    }

    protected void execute() {
    	SmartDashboard.putNumber("Encoders Setpoint", Robot.driveTrain.getLeftDistanceSetpointInInches());
    	SmartDashboard.putBoolean("Front Breakbeam", RobotMap.frontBB.get());
    	SmartDashboard.putNumber("Turn table angle", Robot.shooter.getTurnTableAngle());
    	SmartDashboard.putNumber("Turn table encoder", RobotMap.turnTableEncoder.get());
    	SmartDashboard.putNumber("Hood angle", Robot.shooter.getHoodAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shooter.getShootingWheelSpeed());
    	SmartDashboard.putNumber("Drive Encoder Left", RobotMap.driveEncoderLeft.get());
    	SmartDashboard.putNumber("Drive Encoder Right", RobotMap.driveEncoderRight.get());
    	SmartDashboard.putNumber("Front Intake angle", RobotMap.intakeEncoderFront.get() / Robot.intakeFront.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
    	
    	SmartDashboard.putNumber("Lock", visionTable.getNumber("can_see_target", 0));
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
