package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * NOTE: All values are temporary
 */
public class AutoShootHigh extends CommandGroup {
    
    public  AutoShootHigh(int defense) {
    	Command moveCommand = null;
    	Command aimTurntableCommand = null;
    	
    	// All distance and angle values are temporary
    	switch (defense) {
    		case 1:
    			// Same as below
    		case 2:
    			// Drive to wall and turn turntable right
    			moveCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 220, 0, 48);
    			aimTurntableCommand = new MoveTurnTable(90);
    			break;
    		case 3:
    			// Drive to goal and turn turntable slightly right
    			moveCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 150, 0, 48);
    			aimTurntableCommand = new MoveTurnTable(20);
    			break;
    		case 4:
    			// Drive to goal and turn turntable slightly left
    			moveCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 150, 0, 48);
    			aimTurntableCommand = new MoveTurnTable(-20);
    			break;
    		case 5:
    			// Drive to goal and turn turntable left
    			moveCommand = new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 150, 0, 48);
    			aimTurntableCommand = new MoveTurnTable(-60);
    			break;
    	}
    	
        addSequential(moveCommand);
        addParallel(aimTurntableCommand);
        addSequential(new FreeFire());
    }
}
