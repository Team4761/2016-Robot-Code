package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoot the high goal shooter from anywhere on the field.
 */
public class FreeFire extends CommandGroup {
    
    public  FreeFire() {
    	addSequential(new WaitForLock());
    	//addSequential(new AutonomousTrack());
    	addParallel(new RPMAlign(false));
    	addSequential(new HorizontalAlign(false));
    	addSequential(new VerticalAlign(false));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new FireShooter());
    	addSequential(new MoveShootingWheel(0));
    }
}
