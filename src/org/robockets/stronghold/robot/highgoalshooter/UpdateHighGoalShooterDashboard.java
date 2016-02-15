package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.EncoderPIDSource;
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
    	SmartDashboard.putNumber("Turn table encoder", new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579).pidGet());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
