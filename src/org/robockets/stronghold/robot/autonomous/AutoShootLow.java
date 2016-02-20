package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.ManualSpinIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * ALL VALUES ARE TEMPORARY
 */
public class AutoShootLow extends CommandGroup {
    
    public  AutoShootLow(int defense) {
    	addSequential(new MoveToMiddleGoal(defense));
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 24, 0));
        addSequential(new ManualSpinIntake(Direction.FORWARD, 3, IntakeSide.FRONT));
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -24, 0));
    }
}
