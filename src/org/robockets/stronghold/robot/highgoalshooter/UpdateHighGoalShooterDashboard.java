package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.ResetPID;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.intake.IntakeBall;
import org.robockets.stronghold.robot.intake.ChevalDeFrise;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	SmartDashboard.putData("Reset Intake Back", new ResetPID(RobotMap.intakeEncoderBack, Robot.intakeBack.encoderPID));
    	SmartDashboard.putData("Reset Turntable", new ResetPID(RobotMap.turnTableEncoder, Robot.shooter.turnTablePidController));
    	SmartDashboard.putData("Reset Hood", new ResetPID(RobotMap.hoodEncoder, Robot.shooter.hoodPidController));
    	SmartDashboard.putData("Shovel some fries", new ChevalDeFrise(IntakeSide.BACK));
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
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
