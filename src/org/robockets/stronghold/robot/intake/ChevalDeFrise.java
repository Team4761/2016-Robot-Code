package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ChevalDeFrise extends CommandGroup {
	Intake intake;

    public ChevalDeFrise(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
    		intake = Robot.intakeFront;
    	} else {
    		intake = Robot.intakeBack;
    	}
    	
    	addSequential(new SetVerticalIntake(80, intakeSide));
        addParallel(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 15, 0, 0.7)); // Dummy inputs for distance and relativeAngle
        addSequential(new WaitCommand(1));
        addParallel(new SetVerticalIntake(20, intakeSide)); // Slowly lift arm as robot moves across
        addParallel(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 25, 0.5)); // Dummy inputs for distance and relativeAngle
    }
}
