package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.commands.Limbo;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
	
    public LowBar(Direction direction) {
    	addSequential(new Limbo());
        
        //if (direction == Direction.FORWARD) {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 138, 0, 0.5)); // Dummy inputs for distance and relativeAngle
        //} else {
        //	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 138, 0, 0.5)); // Dummy inputs for distance and relativeAngle
        //}
    }
}
