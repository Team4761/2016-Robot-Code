package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the robot into the position for driving around, shooting, and intaking ball
 */
public class DrivePosition extends CommandGroup {
    
    public  DrivePosition(boolean moveTurntable) {
        addParallel(new SetVerticalIntake(20, IntakeSide.FRONT));
        addParallel(new SetVerticalIntake(20, IntakeSide.BACK));
        addParallel(new MoveHood(-80));
        
        if (moveTurntable) {
        	addParallel(new MoveTurnTable(0));
        }
    }
}
