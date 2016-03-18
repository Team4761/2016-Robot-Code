package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFire extends CommandGroup {
    
    public  AutoFire() {
        addSequential(new VerticalAlign(false));
        addSequential(new FireShooter());
    }
}
