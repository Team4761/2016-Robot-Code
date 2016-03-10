package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fire the shooter for a cleat shot. To be run after AimCleatShot
 */
public class FireCleatShot extends CommandGroup {
    
    public  FireCleatShot() {
        addSequential(new MoveShootingWheel(1160));
        addSequential(new FireShooter());
    }
}
