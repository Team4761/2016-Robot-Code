package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.commands.Reset;
import org.robockets.stronghold.robot.commands.SetPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

	Command MoveHood, MoveTurnTable1, MoveTurnTable2, SetShootingWheel1, SetPIDHood, SetPIDTurn, MoveTurnTable, SetShootingWheel2, EnableTurnTablePID, resetTurnTableEncoder;
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	
    	MoveHood = new MoveHood(new Double(0.3));
    	MoveTurnTable1 = new MoveTurnTable(new Double(0.3));
    	MoveTurnTable2 = new MoveTurnTable(new Double(-0.3));
    	SetShootingWheel1 = new SetShootingWheel(0.1);
    	SetPIDHood = new SetPID(Robot.shooter.hoodPidController, "Hood");
    	SetPIDTurn = new SetPID(Robot.shooter.hoodPidController, "Turn-table");
    	MoveTurnTable = new MoveTurnTable();
    	SetShootingWheel2 = new SetShootingWheel(3);
    	EnableTurnTablePID = new EnableTurnTablePID();
    	resetTurnTableEncoder = new Reset(RobotMap.turnTableEncoder);
    }

    protected void execute() {
    	SmartDashboard.putNumber("Hood Encoder", RobotMap.hoodEncoder.getDistance());
    	SmartDashboard.putNumber("Turn table setpoint", Robot.shooter.turnTablePidController.getSetpoint());
    	SmartDashboard.putNumber("Turn table output", Robot.shooter.turnTablePidController.get());
    	SmartDashboard.putNumber("Turn table encoder", RobotMap.turnTableEncoder.getDistance());
    	//
    	SmartDashboard.putData("Move Hood", MoveHood);
    	SmartDashboard.putData("Move Turn Table", MoveTurnTable1);
    	SmartDashboard.putData("Move Turn Table Back", MoveTurnTable2);
    	SmartDashboard.putData("Shooting Wheel Button", SetShootingWheel1);
    	SmartDashboard.putData("Set Hood", SetPIDHood);
    	SmartDashboard.putData("Set Turntable", SetPIDTurn);
    	SmartDashboard.putData("Move Turn Table Free", MoveTurnTable);
    	SmartDashboard.putData("Move Shooting Wheel", SetShootingWheel2);
    	SmartDashboard.putData("Enable turn table PID", EnableTurnTablePID);
    	SmartDashboard.putData("Reset Turn table encoder", resetTurnTableEncoder);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
