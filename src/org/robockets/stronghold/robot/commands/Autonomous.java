package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.autonomous.AutoLimbo;
import org.robockets.stronghold.robot.autonomous.AutoLowBar;
import org.robockets.stronghold.robot.autonomous.AutoShootHigh;
import org.robockets.stronghold.robot.drivetrain.AssistedDrive;
import org.robockets.stronghold.robot.drivetrain.AssistedRotateType;
import org.robockets.stronghold.robot.drivetrain.AssistedTranslateType;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.highgoalshooter.SetShooterFlipper;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous(double autoNumber, int defense) {
    	addParallel(new SetShooterFlipper(0.1));

    	if (autoNumber == 1) { // Lowbar + Porticullis, no shoot
        	addSequential(new AutoLowBar(Direction.FORWARD));
    	} else if (autoNumber == 2) { // Other defense (no door ones), no shoot
        	addSequential(new AutoLimbo());
            addParallel(new MoveTurnTable(180));
    		addSequential(new AssistedDrive(AssistedTranslateType.ENCODER, AssistedRotateType.ENCODER, 220, 0, 48)); //pos moves backwards
    	} else if (autoNumber == 3) { // Shove the fries, no shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    	} else if (autoNumber == 4) { // Lowbar + Porticullis, and shoot
    		addSequential(new AutoLowBar(Direction.FORWARD));
            addParallel(new SetVerticalIntake(0, IntakeSide.FRONT));
            addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 5) { // Other defense (no door ones, no shovel), and shoot
    		addSequential(new AutoLimbo());
            addParallel(new MoveTurnTable(180));
            addSequential(new AutoShootHigh(defense));
    	} else if (autoNumber == 3) { // Shove the fries, and shoot
    		addSequential(new AutoFrise(IntakeSide.FRONT));
    		addSequential(new AutoShootHigh(defense));
    	}
    }
}
