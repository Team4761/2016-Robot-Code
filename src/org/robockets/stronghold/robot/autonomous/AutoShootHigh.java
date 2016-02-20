package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;

import edu.wpi.first.wpilibj.command.Command;
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
