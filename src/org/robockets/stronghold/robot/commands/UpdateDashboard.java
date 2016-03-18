package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.highgoalshooter.UpdateHighGoalShooterDashboard;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Update all the dashboards.
 */
public class UpdateDashboard extends CommandGroup {
    
    public  UpdateDashboard() {
    	addParallel(new UpdateHighGoalShooterDashboard());
    }
}
