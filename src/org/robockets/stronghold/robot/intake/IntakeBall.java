package org.robockets.stronghold.robot.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide, double angle) {
    	addSequential(new IntakePosition(intakeSide, angle));
    	addSequential(new IntakeBallMinimal(intakeSide));
    }
}
