package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Track extends CommandGroup {
    
    public  Track() {
        addParallel(new HorizontalAlign(true));
        addParallel(new RPMAlign(true));
    }
}
