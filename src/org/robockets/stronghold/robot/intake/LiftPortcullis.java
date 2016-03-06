package org.robockets.stronghold.robot.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lift the portcullis defense.
 */
public class LiftPortcullis extends CommandGroup {
    
    public  LiftPortcullis(IntakeSide side) {
    	addSequential(new SetVerticalIntake(270, side)); // Er somethin.
    	addSequential(new SetVerticalIntake(0, side));
    }
}
