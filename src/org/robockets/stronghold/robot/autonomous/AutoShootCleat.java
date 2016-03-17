package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * ALL VALUES ARE TEMPORARY
 */
public class AutoShootCleat extends CommandGroup {
    
    public  AutoShootCleat(int defense) {
        addSequential(new MoveToMiddleGoal(defense));
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 24, 0, 12));
        addSequential(new FreeFire());
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -24, 0, 12));
    }
}
