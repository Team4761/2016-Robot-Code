package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FireSpyShot extends CommandGroup {
    
    public  FireSpyShot() {
    	addSequential(new RPMAlign(false, 11.0));
        addSequential(new VerticalAlign(false, 11.0));
        addSequential(new FireShooter());
    }
}
