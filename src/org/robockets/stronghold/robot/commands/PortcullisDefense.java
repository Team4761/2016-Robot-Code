package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.Limbo;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.LiftPortcullis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortcullisDefense extends CommandGroup {
    
	/**
	 * Move the robot through the portcullis defense.
	 * @param forward
	 */
    public  PortcullisDefense(boolean forward) {
    	IntakeSide side = (forward) ? IntakeSide.FRONT : IntakeSide.BACK;
    	addSequential(new Limbo());
    	addSequential(new LiftPortcullis(side));
    	addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, (forward) ? 1 : -1, 0)); // 1 is dumb.
    }
}
