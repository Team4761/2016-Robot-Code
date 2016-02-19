package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * NOTE: All values are temporary
 */
public class AutoShootHigh extends CommandGroup {
    
    public  AutoShootHigh(int defense) {
        Command turnCommand = null;
        Command translateCommand = null;

        switch (defense) {
	        case 1:
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, 45);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 5, Robot.driveTrain.getEncodersOffset());
		        break;
	        case 2:
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, 30);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 3, Robot.driveTrain.getEncodersOffset());
		        break;
	        case 3:
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, 0);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 1, Robot.driveTrain.getEncodersOffset());
		        break;
	        case 4:
		        turnCommand = new AssistedDrive(AssistedRotateType.GYRO, -30);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 3, Robot.driveTrain.getEncodersOffset());
		        break;
	        case 5:
	        	turnCommand = new AssistedDrive(AssistedRotateType.GYRO, -45);
		        translateCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 5, Robot.driveTrain.getEncodersOffset());
	        	break;
        }
        
        addSequential(turnCommand);
        addSequential(translateCommand);
        addSequential(new FreeFire());
    }
}
