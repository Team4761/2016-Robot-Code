package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTrack extends CommandGroup {
    
    public  AutonomousTrack() {
    	addParallel(new RPMAlign(false));
    	addParallel(new HorizontalAlign(false));
    }
}
