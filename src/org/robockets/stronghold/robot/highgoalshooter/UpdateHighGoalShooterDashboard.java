package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putNumber("Current Spin Speed", RobotMap.shootingWheelMotor.getEncVelocity() / 1024.0 * 60.0);
    	SmartDashboard.putNumber("Current Hood Angle", RobotMap.hoodEncoder.get() / Robot.shooter.COUNTS_PER_DEGREE_HOOD);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
