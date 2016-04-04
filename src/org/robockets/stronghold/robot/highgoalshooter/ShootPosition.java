package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.hood.MoveHood;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootPosition extends CommandGroup {
    
    public  ShootPosition() {
    	addSequential(new MoveHood(-70));
    	addSequential(new FireShooter()); // Unstick ball
    	addSequential(new MoveHood(-80));
    }
}
