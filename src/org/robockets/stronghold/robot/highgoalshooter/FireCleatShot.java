package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fire the shooter for a cleat shot. To be run after AimCleatShot
 */
public class FireCleatShot extends CommandGroup {
    
    public  FireCleatShot() {
        addParallel(new RPMAlign(false, 5.0));
        addSequential(new VerticalAlign(false, 5.0));
        addSequential(new FireShooter());
    }
}
