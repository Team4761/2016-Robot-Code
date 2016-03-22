package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionHorizontalTest extends CommandGroup {
    
    public  VisionHorizontalTest() {
    	addSequential(new MoveTurnTable(0));
    	addSequential(new MoveTurnTable(180));
    	addSequential(new MoveTurnTable(0));
    	addSequential(new MoveTurnTable(30));
    	addSequential(new VisionEquationOutput(1)); // Or something like that.
    }
}
