package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.highgoalshooter.FreeFire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * NOTE: All values are temporary
 */
public class AutoShootHigh extends CommandGroup {
    
    public  AutoShootHigh(int defense) {
        addSequential(new MoveToMiddleGoal(defense));
        addSequential(new FreeFire());
    }
}
