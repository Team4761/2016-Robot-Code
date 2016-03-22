package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.autonomous.AutoLimbo;
import org.robockets.stronghold.robot.autonomous.AutoLowBar;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;
import org.robockets.stronghold.robot.flipper.SetShooterFlipper;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous(double autoNumber) {
    	addParallel(new SetShooterFlipper(0.1));

    	if (autoNumber == 1) {
        	addSequential(new AutoLowBar(Direction.FORWARD));
    	} else if (autoNumber == 2) {
        	addSequential(new AutoLimbo());
            addParallel(new MoveTurnTable(0));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 220, 0, 48)); //pos moves backwards
    	} else if (autoNumber == 3) {
    		addSequential(new AutoLowBar(Direction.FORWARD));
            addParallel(new SetVerticalIntake(0, IntakeSide.FRONT));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -130, 0, -24));
        	addSequential(new MoveTurnTable(90));
        	addSequential(new FreeFire());
    	} else if (autoNumber == 4) {
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    	} else if (autoNumber == 5) {
    		addSequential(new AutoLimbo());
            addParallel(new MoveTurnTable(0));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 180, 0, 48));
        	addSequential(new FreeFire());
    	}
    }
}
