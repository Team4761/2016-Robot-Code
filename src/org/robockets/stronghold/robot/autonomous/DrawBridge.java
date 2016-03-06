package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrawBridge extends CommandGroup {
    
    public DrawBridge(Direction direction) {
    	if (direction == Direction.BACKWARD) {
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -1, 0)); // Dummy inputs for distance and relativeAngle
    	} else {
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 1, 0)); // Dummy inputs for distance and relativeAngle)
    	}
    }
}
