package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOnAligned extends CommandGroup {
    
    public  ShootOnAligned() {
    	//addSequential(new WaitForAligned());
    	addSequential(new HorizontalAlign(false));
    	addParallel(new RPMAlign(false));
    	addSequential(new VerticalAlign(false));
    	addSequential(new FireShooter());
    }
}
