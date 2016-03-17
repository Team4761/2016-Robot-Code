package org.robockets.stronghold.robot.highgoalshooter;

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
