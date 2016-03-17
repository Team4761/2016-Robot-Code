package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.intake.IntakesUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the robot into the position for driving around, shooting, and intaking ball
 */
public class DrivePosition extends CommandGroup {
    
    public  DrivePosition() {
        addSequential(new MoveHood(-75));
        addSequential(new IntakesUp());
        addSequential(new MoveTurnTable(0));
    }
}
