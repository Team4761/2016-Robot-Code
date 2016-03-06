package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.intake.IntakeSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	addParallel(new UpdateDashboard());
    	addParallel(new AutoFrise(IntakeSide.FRONT));
    }
}
