package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.hood.MoveHood;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide, double angle) {
    	addParallel(new MoveHood(25));
    	addSequential(new IntakePosition(intakeSide, angle));
    	addSequential(new IntakeBallMinimal(intakeSide));
    }
}
