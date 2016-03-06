package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	addParallel(new UpdateDashboard());
    }
}
