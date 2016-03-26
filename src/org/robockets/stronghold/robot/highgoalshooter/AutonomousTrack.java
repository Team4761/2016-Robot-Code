package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTrack extends CommandGroup {
    
    public  AutonomousTrack() {
    	addParallel(new HorizontalAlign(false));
    	addSequential(new RPMAlign(false));
    }
}
