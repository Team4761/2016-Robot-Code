package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.stronghold.robot.highgoalshooter.UpdateHighGoalShooterDashboard;

/**
 * Update all the dashboards.
 */
public class UpdateDashboard extends CommandGroup {
    
    public  UpdateDashboard() {
    	addParallel(new UpdateHighGoalShooterDashboard());
    }
}
