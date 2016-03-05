package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.intake.CheckIntakeBreakBeam;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowGoal extends CommandGroup {
    
    public  LowGoal(IntakeSide intakeSide) {
        addSequential(new SetVerticalIntake(80, intakeSide));
        addSequential(new CheckIntakeBreakBeam(intakeSide, false, true));
    }
}
