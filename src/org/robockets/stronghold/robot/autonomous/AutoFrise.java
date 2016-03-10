package org.robockets.stronghold.robot.autonomous;

import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.intake.ChevalDeFrise;
import org.robockets.stronghold.robot.intake.IntakeSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Auto version of CDF
 *
 */
public class AutoFrise extends CommandGroup {
    
    public  AutoFrise(IntakeSide intakeSide) {
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 48, 0, 0.5));
        addSequential(new ChevalDeFrise(intakeSide));
    }
}
