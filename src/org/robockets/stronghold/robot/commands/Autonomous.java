package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.autonomous.AutoFrise;
import org.robockets.stronghold.robot.autonomous.LowBar;
import org.robockets.stronghold.robot.highgoalshooter.SetShooterFlipper;
import org.robockets.stronghold.robot.intake.IntakeSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Run commands during autonomous.
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	addSequential(new SetShooterFlipper(1));
    	//addParallel(new UpdateDashboard());
    	addSequential(new AutoFrise(IntakeSide.FRONT));
    	//addParallel(new LowBar(Direction.FORWARD));
    }
}
