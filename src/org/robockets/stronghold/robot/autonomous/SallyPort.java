package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class SallyPort extends CommandGroup {
	public SallyPort() {
		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 1, 0 ));

		//addSequential(new TurnRelative(50));

	}
}
