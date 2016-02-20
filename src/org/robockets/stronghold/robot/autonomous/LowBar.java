package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
	
	double encooder; // Dummy variable for now until a method is made for receiving encoder output in degrees
	// Assuming position fully forward is 90 and back -90
    public LowBar(Direction direction) {
    	Robot.shooter.hoodPidController.enable();
        if (encooder != -90) {
        	addSequential(new MoveHood(-90));
        }
        
        if (direction == Direction.FORWARD) {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.GYRO, 1, 0)); // Dummy inputs for distance and relativeAngle
        } else {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.GYRO, -1, 0)); // Dummy inputs for distance and relativeAngle
        }
    }
}
