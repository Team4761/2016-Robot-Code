package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.highgoalshooter.Limbo;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lift the portcullis defense.
 */
public class LiftPortcullis extends CommandGroup {
    
    public  LiftPortcullis() {
    	addSequential(new Limbo());
    	addSequential(new SetIntake(270)); // Er somethin.
    	addSequential(new SetIntake(0));
    }
}
