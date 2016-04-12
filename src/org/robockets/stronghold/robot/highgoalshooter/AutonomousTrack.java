package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTrack extends CommandGroup {
    
    public  AutonomousTrack() {
    	addParallel(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
    	addParallel(new HorizontalAlign(false));
    }
}
