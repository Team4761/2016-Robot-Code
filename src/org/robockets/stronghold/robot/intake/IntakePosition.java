package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakePosition extends CommandGroup {
    
    public  IntakePosition(IntakeSide intakeSide, double angle) {
    	if (intakeSide == IntakeSide.FRONT) {
    		addParallel(new MoveTurnTable(0));
    	} else {
    		addParallel(new MoveTurnTable(180));
    	}
    	
    	//addSequential(new SetVerticalIntake(5, intakeSide)); // Ideal position for ball intake.
        addSequential(new SetVerticalIntake(angle, intakeSide)); // Ideal position for ball intake.
    }
}
