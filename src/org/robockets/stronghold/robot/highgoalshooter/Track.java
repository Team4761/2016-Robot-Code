package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Track extends CommandGroup {
    
    public  Track() {
    	addSequential(new SetVerticalIntake(50, IntakeSide.FRONT));
    	addSequential(new MoveHood(-80));
    	addParallel(new HorizontalAlign(true));
        addParallel(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
    }
}
