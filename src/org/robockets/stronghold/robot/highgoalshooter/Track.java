package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheelSmartDashboard;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Track extends CommandGroup {
    
    public  Track() {
    	addSequential(new SetVerticalIntake(15, IntakeSide.FRONT));
    	addSequential(new MoveHood(Robot.hood.HOOD_MIN));
    	addParallel(new HorizontalAlign(true));
    	//addParallel(new UnstickBall());
    	addParallel(new MoveShootingWheel(Robot.shootingWheel.CONSTANT_SPEED));
    	//addSequential(new MoveShootingWheelSmartDashboard());
    	//addSequential(new RPMAlign(true));
    }
}
