package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command just does all of limbo BUT the turntable part.
 * This is because the turntable currently takes awhile to get to its
 * setpoint so we don't want to wait for it
 */
public class AutoLimbo extends CommandGroup {
    
    public  AutoLimbo() {
    	addParallel(new SetVerticalIntake(20, IntakeSide.FRONT));
        addParallel(new SetVerticalIntake(20, IntakeSide.BACK));
        addSequential(new MoveHood(-80));
    }
}
