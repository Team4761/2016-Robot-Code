package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.drivetrain.TimeDrive;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.TurnUntilTarget;
import org.robockets.stronghold.robot.highgoalshooter.UnstickBall;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * NOTE: All values are temporary
 */
public class AutoShootHigh extends CommandGroup {
    
	final double TURN_SPEED = 0.7;
	
    public  AutoShootHigh(double defense) {
    	addParallel(new SetVerticalIntake(20, IntakeSide.FRONT));
    	addSequential(new MoveTurnTable(6));
    	addSequential(new MoveHood(-45));
    	addSequential(new UnstickBall());
    	
    	// All distance and angle values are temporary
    	switch ((int) defense) {
    		case 1:
    			// Same as below
    			addSequential(new TimeDrive(-0.85, -0.85, 0.65));
    	    	addSequential(new TurnUntilTarget(-TURN_SPEED));
    			break;
    		case 2:
    			// Drive to wall and turn turntable right
    			addSequential(new TimeDrive(-0.85, -0.85, 0.5));
    	    	addSequential(new TurnUntilTarget(-TURN_SPEED));
    			break;
    		case 3:
    			// Drive to goal and turn turntable slightly right
    			addSequential(new TimeDrive(0, 0, 0));
    	    	addSequential(new TurnUntilTarget(-TURN_SPEED));
    			break;
    		case 4:
    			// Drive to goal and turn turntable slightly left
    			addSequential(new TimeDrive(0, 0, 0));
    	    	addSequential(new TurnUntilTarget(TURN_SPEED));
    			break;
    		case 5:
    			// Drive to goal and turn turntable left
    			addSequential(new TimeDrive(-0.85, -0.85, 0.5));
    	    	addSequential(new TurnUntilTarget(TURN_SPEED));
    			break;
    		default:
    			break;
    	}

        addSequential(new FreeFire());
    }
}
