package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import org.robockets.stronghold.robot.drivetrain.TurnRelative;


public class SallyPort extends CommandGroup {
	public SallyPort() {
		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 1, 0 ));

		//addSequential(new TurnRelative(50));

	}
}
