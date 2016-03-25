package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOnAligned extends CommandGroup {
    
    public  ShootOnAligned() {
    	addSequential(new WaitForAligned());
    	addSequential(new FireShooter());
    }
}
