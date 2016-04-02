package org.robockets.stronghold.robot.catapult;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FireCatapult extends CommandGroup {
    
    public  FireCatapult() {
        addSequential(new SetCatapultSolenoid(Robot.catapult.FIRE_BOOL));
        addSequential(new WaitCommand(Robot.catapult.FIRE_WAIT));
        addSequential(new SetCatapultSolenoid(Robot.catapult.CLOSE_BOOL));
    }
}
