package org.robockets.stronghold.robot.highgoalshooter;

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
    	SmartDashboard.putData("Move Hood", new MoveHood(10));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
