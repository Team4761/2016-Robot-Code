package org.robockets.stronghold.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Victor;
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
	public static RobotDrive robotDrive = new RobotDrive(0, 1);
	public static Encoder intakeEncoder = new Encoder(5, 6);
	public static Victor intakeRollerMotor = new Victor(2);
	public static Victor intakeVerticalMotor = new Victor(3); //TEMP
	public static Victor intakeMotor = intakeVerticalMotor; // ???
	public static Victor jeffRoller1 = new Victor(4); // TEMP
	public static Victor jeffRoller2 = new Victor(5); // TEMP
	public static Victor turnTableMotor = new Victor(9); // TEMP
	public static Encoder turnTableEncoder = new Encoder(3, 4);
	public static Victor hoodMotor = new Victor(8); // TEMP
	public static Encoder hoodEncoder = new Encoder(7, 8);
	public static Encoder driveEncoder = new Encoder(0, 1);
	public static CANTalon shootingWheelMotor = new CANTalon(2);

	
	public RobotMap () {
		navX.zeroYaw();
		shootingWheelMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shootingWheelMotor.configEncoderCodesPerRev(1);
		shootingWheelMotor.changeControlMode(TalonControlMode.PercentVbus);		
		driveEncoder.setReverseDirection(true);
	}
}
