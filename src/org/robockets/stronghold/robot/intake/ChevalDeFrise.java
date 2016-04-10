package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ChevalDeFrise extends CommandGroup {

    public ChevalDeFrise(IntakeSide intakeSide) {
    	addSequential(new SetVerticalIntake(95, intakeSide));
        addParallel(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -48, 0, -24));
        addSequential(new WaitCommand(1.5));
        addSequential(new SetVerticalIntake(20, intakeSide)); // Slowly lift arm as robot moves across
        //addParallel(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -12, 0, -12)); 
    }
}
