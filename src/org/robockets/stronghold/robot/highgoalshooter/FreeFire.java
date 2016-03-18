package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoot the high goal shooter from anywhere on the field.
 */
public class FreeFire extends CommandGroup {
    
    public  FreeFire() {
    	addSequential(new AutonomousTrack());
    	addSequential(new VerticalAlign(false));
    	addSequential(new FireShooter());
    	addSequential(new MoveShootingWheel(0));
    }
}
