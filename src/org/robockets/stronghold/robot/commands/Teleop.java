package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.drivetrain.Joyride;
import org.robockets.stronghold.robot.highgoalshooter.SetShooterFlipper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Teleop extends CommandGroup {
    
    public Teleop() {
        addParallel(new SetShooterFlipper(1));
    	addParallel(new UpdateDashboard());
    	addParallel(new Joyride());
    }
}
