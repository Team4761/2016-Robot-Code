package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.autonomous.AutoLimbo;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Limbo extends CommandGroup {
    
    public  Limbo() {
    	addSequential(new AutoLimbo());
    	addSequential(new MoveTurnTable(0));
    }
}
