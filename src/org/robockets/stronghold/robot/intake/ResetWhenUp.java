package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ResetWhenUp extends CommandGroup {
    
    public  ResetWhenUp() {
    		if (RobotMap.frontLS.get()) {
    			addParallel(new ResetIntake(IntakeSide.FRONT)); //This may or may not be an issue
    		}
    }
}
