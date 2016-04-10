package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.intake.ChevalDeFrise;
import org.robockets.stronghold.robot.intake.IntakeSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Command group for CDF in teleop. Driver needs to crash into CDF, then operator presses the button from there.
 */
public class TeleopCDF extends CommandGroup {
    
    public  TeleopCDF(IntakeSide intakeSide) {
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 5, 0, 12));
        addSequential(new WaitCommand(1));
        addSequential(new ChevalDeFrise(intakeSide));
    }
}
