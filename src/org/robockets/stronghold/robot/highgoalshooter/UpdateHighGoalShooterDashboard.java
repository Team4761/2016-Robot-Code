package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.commands.ResetGyro;
import org.robockets.stronghold.robot.commands.TempGyroStart;
import org.robockets.stronghold.robot.drivetrain.Joyride;
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
    	SmartDashboard.putData("Gyro Start 90", new TempGyroStart(90));
    	SmartDashboard.putData("Gyro Start 0", new TempGyroStart(0));
    	SmartDashboard.putData("Gyro Reset", new ResetGyro());
    	SmartDashboard.putData("Joyride", new Joyride());
    }

    protected void execute() {
    	SmartDashboard.putNumber("Turn table encoder", new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement).pidGet());
    	SmartDashboard.putNumber("Hood angle", Robot.shooter.getHoodAngle());
    	SmartDashboard.putNumber("Drive encoder 1", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Drive encoder 2", RobotMap.driveEncoder2.get());
    	SmartDashboard.putNumber("Back Intake angle", RobotMap.intakeEncoderBack.get() / Robot.intakeBack.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Gyro Angle Graph", RobotMap.navX.getYaw());
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.navX.getYaw());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
