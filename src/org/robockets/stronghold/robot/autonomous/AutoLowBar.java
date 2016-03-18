package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBar extends CommandGroup {
	
    public AutoLowBar(Direction direction) {
    	//addSequential(new Limbo());
    	// This is a copy pasta of limbo, please fix
    	addSequential(new AutoLimbo());
        addSequential(new MoveTurnTable(180));
    	
        if (direction == Direction.FORWARD) {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -180, 0, -48)); // Dummy inputs for distance and relativeAngle
        } else {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 180, 0, 48)); // Dummy inputs for distance and relativeAngle
        }        
    }
}
