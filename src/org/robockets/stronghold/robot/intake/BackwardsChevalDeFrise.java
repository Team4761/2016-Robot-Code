package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BackwardsChevalDeFrise extends CommandGroup {
	double encooder; // Dummy variable for now until a method is made for receiving encoder output
	// Assuming position fully down is 0 and fully up is 90.
    public BackwardsChevalDeFrise(IntakeSide intakeSide) { 	
    	Robot.driveTrain.gyroPID.setSetpoint(0); // 0 for now -Jake B. 2016 x2
    	if (encooder > 80) { // Temporary way to check if arm is up
    		addSequential(new SetVerticalIntake(0, intakeSide));
    	} else if (encooder < 80) { // Temporary way to check if arm is down
    		addSequential(new SetVerticalIntake(90, intakeSide));
    		addSequential(new SetVerticalIntake(0, intakeSide));
    	}
    	
        addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.GYRO, -1, 0, 12)); // Dummy inputs for distance and relativeAngle
    	addParallel(new SetVerticalIntake(90, intakeSide)); // Slowly lift arm as robot moves across
    }
}
