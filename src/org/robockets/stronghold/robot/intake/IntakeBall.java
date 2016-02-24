package org.robockets.stronghold.robot.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide) {
        addSequential(new SetVerticalIntake(5, intakeSide)); // Ideal position for ball intake
        addSequential(new CheckIntakeBreakBeam(intakeSide)); // Spins until break break sensor is broken
        addSequential(new SetVerticalIntake(-90, intakeSide)); // Up
    }
}
