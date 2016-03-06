package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fire the shooter for a cleat shot. To be run after AimCleatShot
 */
public class FireCleatShot extends CommandGroup {
    
    public  FireCleatShot() {
        addSequential(new MoveShootingWheel(1150));
        addSequential(new FireShooter());
    }
}
