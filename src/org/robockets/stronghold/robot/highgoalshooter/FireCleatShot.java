package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FireCleatShot extends CommandGroup {
    
    public  FireCleatShot() {
        addSequential(new MoveShootingWheel(1200));
        addSequential(new FireShooter());
    }
}
