package org.robockets.stronghold.robot;

import org.robockets.buttonmanager.ButtonManager;
import org.robockets.stronghold.robot.commands.Autonomous;
import org.robockets.stronghold.robot.commands.Teleop;
import org.robockets.stronghold.robot.drivetrain.Drivetrain;
import org.robockets.stronghold.robot.flipper.Flipper;
import org.robockets.stronghold.robot.highgoalshooter.UpdateHighGoalShooterDashboard;
import org.robockets.stronghold.robot.hood.Hood;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.IntakeSpinners;
import org.robockets.stronghold.robot.intake.IntakeVertical;
import org.robockets.stronghold.robot.shootingwheel.SpinningWheel;
import org.robockets.stronghold.robot.turntable.Turntable;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static final Drivetrain driveTrain = new Drivetrain();
	public static final IntakeVertical intakeVerticalFront = new IntakeVertical(IntakeSide.FRONT);
	public static final IntakeVertical intakeVerticalBack = new IntakeVertical(IntakeSide.BACK);
	public static final IntakeSpinners intakeSpinnersFront = new IntakeSpinners(IntakeSide.FRONT);
	public static final IntakeSpinners intakeSpinnersBack = new IntakeSpinners(IntakeSide.BACK);
	public static final Flipper flipper = new Flipper();
	public static final Hood hood = new Hood();
	public static final Turntable turntable = new Turntable();
	public static final SpinningWheel shootingWheel = new SpinningWheel();
	
	Command teleop;
	Command uHGSD;
	Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        NetworkTable.globalDeleteAll();
    	
	    oi = new OI();
	    teleop = new Teleop();
	    uHGSD = new UpdateHighGoalShooterDashboard();
	    CameraServer server = CameraServer.getInstance();
	    server.startAutomaticCapture("cam0"); 
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit() {
    	table = NetworkTable.getTable("PiTouch");
    	SmartDashboard.putNumber("Auto mode", SmartDashboard.getNumber("Auto mode", 0));
    	SmartDashboard.putNumber("pid error", 0);
    	SmartDashboard.putBoolean("On target!", false);
		
		uHGSD.start();
    }
	
    NetworkTable table;
    int auto = 0;
    int position = 0;
    String autoDefense = "";
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		position = Integer.parseInt(table.getString("position", "2"));
		boolean shoot = Boolean.parseBoolean(table.getString("shoot", "false"));
		autoDefense = "defense" + position;
		
		
		switch (autoDefense) {
			case "Lowbar":
			case "Portcullis": auto = 1;
				break;
			case "Frise": auto = 3;
				break;
			case "Ramparts":
			case "Moat":
			case "RockWall":
			case "RoughTerrain": auto = 2;
				break;
			default: auto = 0; // Door ones. This is temporary
				break;
		}
		
		if (shoot) {
			auto += 3;
		}
	
		intakeVerticalBack.setIntakeAngle(intakeVerticalBack.getIntakeAngle()); 
		intakeVerticalFront.setIntakeAngle(intakeVerticalFront.getIntakeAngle()); 
		hood.setAngle(hood.getAngle()); 
		shootingWheel.setSpeed(shootingWheel.getSpeed()); 
		turntable.setAngle(turntable.getAngle()); 
		
		autonomousCommand = new Autonomous(auto, position);
    	
    	//autonomousCommand = new Autonomous(SmartDashboard.getNumber("Auto mode", 0), 1); // Default to 1
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	ButtonManager.start();
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        teleop.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
