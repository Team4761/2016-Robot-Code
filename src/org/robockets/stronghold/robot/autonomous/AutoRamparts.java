package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRamparts extends CommandGroup {
    
	
    public AutoRamparts() {
    	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 100 /*Or something*/, 0, 0.5));
    }
}
