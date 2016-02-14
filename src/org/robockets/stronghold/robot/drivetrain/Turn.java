package org.robockets.stronghold.robot.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Turn extends CommandGroup {
    
    public Turn(int degrees) {
    	addParallel(new AssistedDrive(AssistedRotateType.GYRO, degrees));
    }
}
