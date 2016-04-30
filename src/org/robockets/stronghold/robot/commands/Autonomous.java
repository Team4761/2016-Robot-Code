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

    	switch ((int) autoNumber) {
    		case 1: // Lowbar, no shoot
    			addSequential(new AutoLowBar(Direction.BACKWARD));
    			break;
    		case 2: // Other defense (no door ones), no shoot
        		addSequential(new TimeDrive(0.85, 0.85, 2.5));
    			break;
    		case 3: // Shovel the fries, no shoot
        		addSequential(new AutoFrise(IntakeSide.FRONT));
    			break;
    		case 4: // Lowbar, and shoot
        		addSequential(new AutoLowBar(Direction.FORWARD));
                addSequential(new AutoShootHigh(1));
    			break;
    		case 5: // Other defense (no door ones, no shovel), and shoot
        		addSequential(new TimeDrive(0.85, 0.85, 2.5)); // 0.85 0.85 2.75
        		addSequential(new AutoShootHigh(defense));
    			break;
    		case 6: // Shovel the fries, and shoot
        		addSequential(new AutoFrise(IntakeSide.FRONT));
        		addSequential(new TimeDrive(0.5, 0.5, 1.5));
        		addSequential(new AutoShootHigh(defense));
    			break;
    		case 7: // Just shoot
        		addSequential(new AutoShootHigh(0));
    			break;
    		case 8: // Porticullis, no shoot
        		addSequential(new AutoLowBar(Direction.FORWARD));
    			break;
    		case 11: // Porticullis
        		addSequential(new AutoLowBar(Direction.FORWARD));
                addSequential(new AutoShootHigh(1));
    			break;
    	}
    	
    	addSequential(new SetVerticalIntake(20, IntakeSide.FRONT));
    	addSequential(new MoveHood(-45));
    	addSequential(new UnstickBall());
    }
}
