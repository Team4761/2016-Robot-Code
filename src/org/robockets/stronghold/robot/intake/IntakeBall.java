package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide, double angle) {
    	if (intakeSide == IntakeSide.FRONT) {
    		addParallel(new MoveTurnTable(0));
    	} else {
    		addParallel(new MoveTurnTable(180));
    	}		
    	//addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 180 : 0));
    	addParallel(new MoveHood(0));
        //addSequential(new SetVerticalIntake(5, intakeSide)); // Ideal position for ball intake.
        addSequential(new SetVerticalIntake(angle, intakeSide)); // Ideal position for ball intake.
        addSequential(new CheckIntakeBreakBeam(intakeSide, true, false, 0.4)); // Spins until break break sensor is broken
        //addSequential(new SetVerticalIntake(90, intakeSide)); // Up
        addSequential(new SetVerticalIntake(10, intakeSide)); // Up
    }
}
