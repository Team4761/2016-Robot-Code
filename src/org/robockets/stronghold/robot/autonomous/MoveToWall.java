package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command to drive from the end of the outer works to deep in the courtyard, near the wall. Back axle alligned.
 */
public class MoveToWall extends CommandGroup {
    
    public  MoveToWall() {
    	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 150, 0, 0.5));
    }
}
