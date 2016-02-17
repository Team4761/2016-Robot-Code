package org.robockets.stronghold.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	private static SerialPort navXSerialPort = new SerialPort(57600, SerialPort.Port.kMXP);
	private static byte updateRateHz = 50;
	public static IMUAdvanced navX = new IMUAdvanced(navXSerialPort, updateRateHz);
	//public static RobotDrive robotDrive = new RobotDrive(0, 1);
	public static RobotDrive robotDrive = new RobotDrive(18, 19);
	public static Encoder intakeEncoderFront = new Encoder(3, 4);
	public static Encoder intakeEncoderBack = new Encoder(11, 12);
	public static Victor intakeRollerMotorFront = new Victor(6);
	public static Victor intakeRollerMotorBack = new Victor(3);
	public static Victor intakeVerticalMotorFront = new Victor(2); //TEMP
	public static Victor intakeVerticalMotorBack = new Victor(9); //TEMP
	public static Victor jeffRoller1 = new Victor(4); // TEMP
	public static Victor jeffRoller2 = new Victor(5); // TEMP
	public static Victor turnTableMotor = new Victor(7); // TEMP
	public static Encoder turnTableEncoder = new Encoder(5, 6);
	public static Victor hoodMotor = new Victor(8); // TEMP
	public static Encoder hoodEncoder = new Encoder(0, 1);
	public static Encoder driveEncoder = new Encoder(9, 10);
	public static CANTalon shootingWheelMotor = new CANTalon(2);

	
	public RobotMap () {
		//SmartDashboard.putData("Enable Intake PID", );
		navX.zeroYaw();
		shootingWheelMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shootingWheelMotor.configEncoderCodesPerRev(1);
		shootingWheelMotor.changeControlMode(TalonControlMode.PercentVbus);		
	}
}
