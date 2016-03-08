package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.ResetPID;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

	NetworkTable table;
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	SmartDashboard.putData("Horizontal Align", new HorizontalAlign(true));
    	SmartDashboard.putData("Vertical Align", new VerticalAlign(true));
    	SmartDashboard.putData("Spin Turn Table Left", new MoveTurnTable(-30, 0));
    	SmartDashboard.putData("Spin Turn Table Right", new MoveTurnTable(30, 0));
    	SmartDashboard.putData("Free fire", new FreeFire());
    	SmartDashboard.putData("Zero hood", new MoveHood(0));
    	SmartDashboard.putData("Reset Intake Back", new ResetPID(RobotMap.intakeEncoderBack, Robot.intakeBack.encoderPID));
    	SmartDashboard.putData("Reset Turntable", new ResetPID(RobotMap.turnTableEncoder, Robot.shooter.turnTablePidController));
    	SmartDashboard.putData("Reset Hood", new ResetPID(RobotMap.hoodEncoder, Robot.shooter.hoodPidController));
    	SmartDashboard.putData("Fire Shooter", new FireShooter());
    	SmartDashboard.putData("Move Hood 0", new MoveHood(0));
    	SmartDashboard.putData("Stop Wheels", new MoveShootingWheel(0));
    }

    protected void execute() {
    	SmartDashboard.putBoolean("Breakbeam", RobotMap.backBB.get());
    	SmartDashboard.putNumber("Turn table encoder", new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement).pidGet());
    	SmartDashboard.putNumber("Hood angle", Robot.shooter.getHoodAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shooter.getShootingWheelSpeed());
    	SmartDashboard.putNumber("Drive encoder 1", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Drive encoder 2", RobotMap.driveEncoder2.get());
    	SmartDashboard.putNumber("Back Intake angle", RobotMap.intakeEncoderBack.get() / Robot.intakeBack.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
    	
    	table = NetworkTable.getTable("vision");
    	
    	SmartDashboard.putNumber("Lock", table.getNumber("can_see_target", 0));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
