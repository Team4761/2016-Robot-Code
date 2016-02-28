package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide) {
    	addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 0 : 180));
    	addParallel(new MoveHood(0));
        addSequential(new SetVerticalIntake(5, intakeSide)); // Ideal position for ball intake
        addSequential(new CheckIntakeBreakBeam(intakeSide)); // Spins until break break sensor is broken
        addSequential(new SetVerticalIntake(-90, intakeSide)); // Up
    }
}
