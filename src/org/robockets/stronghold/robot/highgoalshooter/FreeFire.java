package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoot the high goal shooter from anywhere on the field.
 */
public class FreeFire extends CommandGroup {
    
    public  FreeFire() {
    	addSequential(new HorizontalAlign(false));
    	addSequential(new VerticalAlign(false));
    	addSequential(new FireShooter());
    	addSequential(new MoveShootingWheel(0));
    }
}
