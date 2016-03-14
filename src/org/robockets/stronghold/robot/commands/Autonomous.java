package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.autonomous.LowBar;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.highgoalshooter.SetShooterFlipper;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous(double autoNumber) {
    	addParallel(new SetShooterFlipper(0.1));

    	if (autoNumber == 1) {
        	addSequential(new LowBar(Direction.FORWARD));
    	} else if (autoNumber == 2) {
            addParallel(new SetVerticalIntake(20, IntakeSide.FRONT));
            addParallel(new SetVerticalIntake(20, IntakeSide.BACK));
            addSequential(new MoveHood(-80));
            addParallel(new MoveTurnTable(180));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 220, 0, 48)); //pos moves backwards
    	} else if (autoNumber == 3) {
    		addSequential(new LowBar(Direction.FORWARD));
            addParallel(new SetVerticalIntake(0, IntakeSide.FRONT));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, -130, 0, -24));
        	addSequential(new MoveTurnTable(90));
        	addSequential(new FreeFire());
    	} else if (autoNumber == 4) {
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    	} else if (autoNumber == 5) {
    		addParallel(new SetVerticalIntake(20, IntakeSide.FRONT));
            addParallel(new SetVerticalIntake(20, IntakeSide.BACK));
            addSequential(new MoveHood(-80));
            addParallel(new MoveTurnTable(180));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 180, 0, 48));
        	addSequential(new FreeFire());
    	}
    }
}
