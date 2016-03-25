package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.drivetrain.TimeDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBar extends CommandGroup {
	
    public AutoLowBar(Direction direction) {
    	//addSequential(new Limbo());
    	// This is a copy pasta of limbo, please fix
    	addSequential(new AutoLimbo());
    	
        if (direction == Direction.FORWARD) {
        	addSequential(new TimeDrive(-0.75, -0.75, 7));
        	//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 180, 0, 48)); // Dummy inputs for distance and relativeAngle
        } else {
        	addSequential(new TimeDrive(0.75, 0.75, 7));
        	//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -180, 0, -48)); // Dummy inputs for distance and relativeAngle
        }        
    }
}
