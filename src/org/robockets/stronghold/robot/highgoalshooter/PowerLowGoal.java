package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PowerLowGoal extends CommandGroup {
    
    public  PowerLowGoal() {
        addSequential(new MoveHood(-1));
    	addSequential(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
    	addSequential(new FireShooter());
    	addSequential(new MoveShootingWheel(0));
    }
}
