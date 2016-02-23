package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide) {
        addSequential(new SetVerticalIntake(0, intakeSide)); // Ideal position is 0 degrees with the bottom of arm litle more than parallel to ground
        addSequential(new CheckIntakeBreakBeam(intakeSide)); // Spins until break break sensor is broken
        addSequential(new SetVerticalIntake(-90, intakeSide)); // Up
        addSequential(new SpinIntake(Direction.BACKWARD, 5, intakeSide)); // 5 seconds I guess
    }
}
