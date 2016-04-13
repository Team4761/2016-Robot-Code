package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class UnstickBall extends CommandGroup {
    
    public  UnstickBall() {
        addSequential(new MoveShootingWheel(-1000)); // Wheel is only allowed to go -0.1 so this should be big
        addSequential(new WaitCommand(2)); // TODO: Tune time
        addSequential(new MoveShootingWheel(0));
    }
}
