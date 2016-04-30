package org.robockets.stronghold.robot.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBallMinimal extends CommandGroup {
    
    public  IntakeBallMinimal(IntakeSide intakeSide) {
    	addSequential(new CheckIntakeBreakBeam(intakeSide, true, false, 0.5)); // Spins until break break sensor is broken
        //addSequential(new SetVerticalIntake(90, intakeSide)); // Up
        addSequential(new SetVerticalIntake(10, intakeSide)); // Up
    }
}
