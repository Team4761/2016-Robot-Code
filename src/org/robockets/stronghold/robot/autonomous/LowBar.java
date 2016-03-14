package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.commands.Limbo;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
	
    public LowBar(Direction direction) {
    	//addSequential(new Limbo());
    	// This is a copy pasta of limbo, please fix
    	addParallel(new MoveHood(-80));
        addParallel(new SetVerticalIntake(95, IntakeSide.FRONT));
        addParallel(new SetVerticalIntake(95, IntakeSide.BACK));
        addSequential(new MoveTurnTable(180));
    	
        if (direction == Direction.FORWARD) {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -180, 0, -48)); // Dummy inputs for distance and relativeAngle
        } else {
        	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 180, 0, 48)); // Dummy inputs for distance and relativeAngle
        }        
    }
}
