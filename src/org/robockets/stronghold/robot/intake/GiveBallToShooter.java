package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GiveBallToShooter extends CommandGroup {
    
    public  GiveBallToShooter(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		addParallel(new MoveTurnTable(180));
    	} else {
    		addParallel(new MoveTurnTable(0));
    	}
    	//addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 180 : 0));
    	addParallel(new MoveHood(0));
    	addSequential(new SetVerticalIntake(10, intakeSide));
    	addSequential(new CheckIntakeBreakBeam(intakeSide, true, true, 0));
    	addSequential(new WaitCommand(1));
    	addSequential(new MoveHood((intakeSide == IntakeSide.FRONT) ? -80 : 80)); // Forward would be positive degrees. This command traps the ball
    }
}
