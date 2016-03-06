package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Direction;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Aim the shooter towards the cleat
 */
public class AimCleatShot extends CommandGroup {
    
    public  AimCleatShot(Direction direction) {
    	Command turntableCommand = null;
    	
    	switch (direction) {
        	case LEFT:
        		turntableCommand = new MoveTurnTable(-90);
        		break;
        	case RIGHT:
        		turntableCommand = new MoveTurnTable(90);
        		break;
        	/*case CENTER:
        		turntableCommand = new MoveTurnTable(0);
        		break; */
        	default:
        		// Oh no!
        		break;
    	}
    	
    	addSequential(turntableCommand);
        addSequential(new MoveHood(40));
    }
}
