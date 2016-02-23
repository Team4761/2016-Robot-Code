package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.ResetIntake;
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
    	SmartDashboard.putData("Horizontal Align", new HorizontalAlign(false));
    	SmartDashboard.putData("Vertical Align", new VerticalAlign());
    	SmartDashboard.putData("Spin Turn Table Left", new MoveTurnTable(-1, 0));
    	SmartDashboard.putData("Spin Turn Table Right", new MoveTurnTable(1, 0));
    }

    protected void execute() {
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
