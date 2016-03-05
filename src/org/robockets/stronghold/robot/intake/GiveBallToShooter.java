package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GiveBallToShooter extends CommandGroup {
    
    public  GiveBallToShooter(IntakeSide intakeSide) {
    	addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 0 : 180));
    	addParallel(new MoveHood(0));
    	addSequential(new SpinIntake(Direction.BACKWARD, 5, intakeSide));
    	addSequential(new MoveHood((intakeSide == IntakeSide.FRONT) ? 70 : -70)); // Forward would be positive degrees. This command traps the ball
    }
}
