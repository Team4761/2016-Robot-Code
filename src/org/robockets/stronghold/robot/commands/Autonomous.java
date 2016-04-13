package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.autonomous.AutoLimbo;
import org.robockets.stronghold.robot.autonomous.AutoLowBar;
import org.robockets.stronghold.robot.autonomous.AutoShootHigh;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.drivetrain.TimeDrive;
import org.robockets.stronghold.robot.flipper.SetShooterFlipper;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous(double autoNumber, double defense) {
    	addParallel(new SetShooterFlipper(0.1));
    	//addParallel(new SetShooterFlipper(0.5));

    	if (autoNumber == 1) { // Lowbar + Porticullis, no shoot
    		addSequential(new WaitCommand(3));
    		addSequential(new AutoLowBar(Direction.FORWARD));
    	} else if (autoNumber == 2) { // Other defense (no door ones), no shoot
    		addSequential(new DrivePosition(false));
    		addSequential(new WaitCommand(6));
    		//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, true, -40, 0, -36));
    		addSequential(new TimeDrive(0.85, 0.85, 2.75));
    	} else if (autoNumber == 3) { // Shovel the fries, no shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    	} else if (autoNumber == 4) { // Lowbar + Porticullis, and shoot
    		addSequential(new AutoLowBar(Direction.FORWARD));
            addParallel(new SetVerticalIntake(0, IntakeSide.FRONT));
            addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 5) { // Other defense (no door ones, no shovel), and shoot
    		addSequential(new DrivePosition(false));
    		addSequential(new WaitCommand(3));
    		//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, true, -40, 0, -36));
    		addSequential(new TimeDrive(0.85, 0.85, 2.75)); // 0.85 0.85 2.75
    		addSequential(new AutoShootHigh(defense));
    		addSequential(new Kill());
    	} else if (autoNumber == 6) { // Shovel the fries, and shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    		addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 7) { // Just shoot
    		addSequential(new AutoShootHigh(defense));
    	}
    }
}
