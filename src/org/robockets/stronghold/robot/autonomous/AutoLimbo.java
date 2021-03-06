package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command just does all of limbo BUT the turntable part.
 * This is because the turntable currently takes awhile to get to its
 * setpoint so we don't want to wait for it
 */
public class AutoLimbo extends CommandGroup {
    
    public  AutoLimbo() {
    	addParallel(new SetVerticalIntake(90, IntakeSide.FRONT));
    	addSequential(new MoveTurnTable(6));
        //addParallel(new SetVerticalIntake(90, IntakeSide.BACK));
        addSequential(new MoveHood(Robot.hood.HOOD_START));
    }
}
