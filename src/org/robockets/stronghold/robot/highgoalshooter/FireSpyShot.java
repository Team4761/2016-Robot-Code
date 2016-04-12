package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FireSpyShot extends CommandGroup {
    
    public  FireSpyShot() {
    	addSequential(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
        addSequential(new VerticalAlign(false, 11.0));
        addSequential(new FireShooter());
    }
}
