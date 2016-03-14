package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Limbo extends CommandGroup {
    
    public  Limbo() {
    	addParallel(new MoveHood(-80));
        addParallel(new SetVerticalIntake(95, IntakeSide.FRONT));
        addParallel(new SetVerticalIntake(95, IntakeSide.BACK));
        addParallel(new MoveTurnTable(180));
    }
}
