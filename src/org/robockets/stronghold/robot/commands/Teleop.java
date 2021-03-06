package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.drivetrain.Joyride;
import org.robockets.stronghold.robot.flipper.SetShooterFlipper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Teleop extends CommandGroup {
    
    public Teleop() {
    	//addSequential(new Kill());
    	addParallel(new SetShooterFlipper(0.1));
    	//addParallel(new SetShooterFlipper(0.5));
    	addParallel(new UpdateDashboard());
    	addParallel(new Joyride());
    }
}
