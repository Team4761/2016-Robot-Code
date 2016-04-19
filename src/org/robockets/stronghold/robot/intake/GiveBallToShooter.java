package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GiveBallToShooter extends CommandGroup {
    
    public  GiveBallToShooter(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		addParallel(new MoveTurnTable(0));
    	} else {
    		addParallel(new MoveTurnTable(180));
    	}
    	//addParallel(new MoveTurnTable((intakeSide == IntakeSide.FRONT) ? 180 : 0));
    	addSequential(new MoveHood(25));
    	addSequential(new SetVerticalIntake(20, intakeSide));
    	addSequential(new SpinIntake(Direction.FORWARD, 1, IntakeSide.FRONT));
    	//addSequential(new CheckIntakeBreakBeam(intakeSide, true, true, 0));
    	addSequential(new WaitCommand(1));
    	addSequential(new MoveHood(Robot.hood.HOOD_MIN)); // Forward would be positive degrees. This command traps the ball
    }
}
