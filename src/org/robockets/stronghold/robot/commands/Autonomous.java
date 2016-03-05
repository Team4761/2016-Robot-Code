package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.autonomous.LowBar;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	System.out.println("test");
    	addParallel(new UpdateDashboard());
    	addParallel(new LowBar(Direction.FORWARD));
    }
}
