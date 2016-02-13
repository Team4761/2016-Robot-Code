package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move the turntable left or right.
 */
public class MoveTurnTable extends Command {

	Double angle = null; // Note that this is intentionally not the primitive angle so it can be compared to null.
	Double speed = null;
	
	/**
	 * Move turntable with Smart Dashboard input.
	 */
	public MoveTurnTable(){
		requires(Robot.shooter);
		SmartDashboard.putNumber("Turn Table Angle Add", SmartDashboard.getNumber("Turn Table Angle", 573.500));
		angle = SmartDashboard.getNumber("Turn Table Angle Add");
	}
	
	/**
	 * Move the hood upwards or downwards continuously.
	 * @param rate		The speed to move at.
	 */
    public MoveTurnTable(double rate) {
        requires(Robot.shooter);
        speed = rate;
    }
    
    /**
	 * Move the hood upwards or downwards continuously. Note you have to enable the PID for this.
	 * @param angle			The angle to move the hood by. This is added to the current angle.
	 */
    public MoveTurnTable(float ang) {
        requires(Robot.shooter);
        
        angle = Robot.shooter.turnTablePidController.getSetpoint() + ang;
        SmartDashboard.putNumber("Turn Table Angle Add", angle);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (angle != null){
    		Robot.shooter.turnTablePidController.setSetpoint(angle);
    	}
    	setTimeout(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle == null) Robot.shooter.spinTurnTable(speed);
    	else {
    		angle = SmartDashboard.getNumber("Turn Table Angle Add");
    		Robot.shooter.turnTablePidController.setSetpoint(angle);
    		Robot.shooter.spinTurnTableAssisted();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (angle != null) return Robot.shooter.turnTablePidController.onTarget();
        return false || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.spinTurnTable(0);
    }
}