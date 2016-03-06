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
    		addSequential(new MoveTurnTable(180));
    	} else {
    		addSequential(new MoveTurnTable(0));
    	}
    	//addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 180 : 0));
    	addSequential(new MoveHood(0));
    	addSequential(new CheckIntakeBreakBeam(intakeSide, true, true));
    	addSequential(new WaitCommand(1));
    	addSequential(new MoveHood((intakeSide == IntakeSide.FRONT) ? -75 : 75)); // Forward would be positive degrees. This command traps the ball
    }
}
