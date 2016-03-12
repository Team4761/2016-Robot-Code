package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public IntakeBall(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		addParallel(new MoveTurnTable(180));
    	} else {
    		addParallel(new MoveTurnTable(0));
    	}		
    	//addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 180 : 0));
    	addParallel(new MoveHood(0));
        //addSequential(new SetVerticalIntake(5, intakeSide)); // Ideal position for ball intake
        addSequential(new SetVerticalIntake(85, intakeSide)); // Ideal position for ball intake
        addSequential(new CheckIntakeBreakBeam(intakeSide, true, false, 0)); // Spins until break break sensor is broken
        //addSequential(new SetVerticalIntake(90, intakeSide)); // Up
        addSequential(new SetVerticalIntake(10, intakeSide)); // Up
    }
}
