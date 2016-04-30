package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;
import org.robockets.stronghold.robot.turntable.MenzieAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoot the high goal shooter from anywhere on the field.
 */
public class FreeFire extends CommandGroup {
    
    public  FreeFire(boolean menzieShot) {
    	//addSequential(new WaitForLock());
    	//addSequential(new AutonomousTrack());
    	
    	if (menzieShot) {
    		addSequential(new MenzieAlign(false));
    	} else {
    		addSequential(new HorizontalAlign(false));
    	}
    	
    	addSequential(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
    	addSequential(new VerticalAlign(false));
    	addSequential(new WaitCommand(0.25));
    	addSequential(new FireShooter());
    	addSequential(new MoveShootingWheel(0));
    }
}
