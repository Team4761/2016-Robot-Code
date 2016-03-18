package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToMiddleGoal extends CommandGroup {
    
    public  MoveToMiddleGoal(int defense) {
       /* Command turnCommand = null;
        Command translateCommand = null;
        double angle = 0;
        
        if (defense > 5 || defense < 1) {
        	defense = 3; // Default to middle
        }

        switch (defense) {
	        case 1:
	        	angle = 45;
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, angle);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 5, Robot.driveTrain.getEncodersOffset(), 12);
		        break;
	        case 2:
	        	angle = 30;
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, angle);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 3, Robot.driveTrain.getEncodersOffset(), 12);
		        break;
	        case 3:
	        	angle = 0;
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, angle);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 1, Robot.driveTrain.getEncodersOffset(), 12);
		        break;
	        case 4:
	        	angle = -30;
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, angle);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 3, Robot.driveTrain.getEncodersOffset(), 12);
		        break;
	        case 5:
	        	angle = -45;
	        	turnCommand = new AssistedDrive(AssistedRotateType.GYRO, angle);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 5, Robot.driveTrain.getEncodersOffset(), 12);
	        	break;
        }
        
        addSequential(turnCommand);
        addSequential(translateCommand);
        addSequential(new AssistedDrive(AssistedRotateType.GYRO, -angle)); // Turn back to face the middle goal */
    }
}
