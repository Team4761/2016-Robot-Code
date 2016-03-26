package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoFire extends CommandGroup {
    
    public  AutoFire() {
        addSequential(new VerticalAlign(false));
    	addSequential(new WaitCommand(0.5));
        addSequential(new FireShooter());
    }
}
