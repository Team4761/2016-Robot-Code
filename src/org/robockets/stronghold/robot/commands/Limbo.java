package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Limbo extends CommandGroup {
    
    public  Limbo() {
        addSequential(new MoveHood(-75));
        addParallel(new SetVerticalIntake(65, IntakeSide.FRONT));
        addParallel(new SetVerticalIntake(65, IntakeSide.BACK));
        addSequential(new MoveTurnTable(0));
    }
}
