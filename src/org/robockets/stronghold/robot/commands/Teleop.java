package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.drivetrain.Joyride;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Teleop extends CommandGroup {
    
    public  Teleop() {
        addParallel(new Joyride());
    }
}
