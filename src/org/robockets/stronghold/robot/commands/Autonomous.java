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
import org.robockets.stronghold.robot.highgoalshooter.UnstickBall;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
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

    	if (autoNumber == 1) { // Lowbar, no shoot
    		addSequential(new AutoLowBar(Direction.BACKWARD));
    	} else if (autoNumber == 2) { // Other defense (no door ones), no shoot
    		//addSequential(new DrivePosition(false));
    		//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, true, -40, 0, -36));
    		addSequential(new TimeDrive(0.85, 0.85, 2.5));
    	} else if (autoNumber == 3) { // Shovel the fries, no shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    	} else if (autoNumber == 4) { // Lowbar, and shoot
    		addSequential(new AutoLowBar(Direction.FORWARD));
            //addParallel(new SetVerticalIntake(0, IntakeSide.FRONT));
            addSequential(new AutoShootHigh(1));
    	} else if (autoNumber == 5) { // Other defense (no door ones, no shovel), and shoot
    		//addSequential(new DrivePosition(false));
    		//addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, true, -40, 0, -36));
    		addSequential(new TimeDrive(0.85, 0.85, 2.5)); // 0.85 0.85 2.75
    		addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 6) { // Shovel the fries, and shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    		addSequential(new TimeDrive(0.5, 0.5, 1.5));
    		addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 7) { // Just shoot
    		addSequential(new AutoShootHigh(0));
    	} else if (autoNumber == 8) { // Porticullis, no shoot
    		addSequential(new AutoLowBar(Direction.FORWARD));
    	} else if (autoNumber == 11) { // Porticullis
    		addSequential(new AutoLowBar(Direction.FORWARD));
            addSequential(new AutoShootHigh(1));
    	}
    	
    	addSequential(new MoveHood(-45));
    	addSequential(new UnstickBall());
    }
}
