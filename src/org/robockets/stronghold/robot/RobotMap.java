package org.robockets.stronghold.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
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
	public static Victor intakeMotorFront = new Victor(3);
	public static Victor intakeMotorBack = new Victor(1);
	public static Encoder intakeEncoderFront = new Encoder(10, 11);
	public static Encoder intakeEncoderBack = new Encoder(4, 5);
	public static RobotDrive robotDrive = new RobotDrive(18, 19);
	public static Encoder turnTableEncoder = new Encoder(8, 9);
	public static Victor intakeVerticalMotorFront = new Victor(2);
	public static Victor intakeVerticalMotorBack = new Victor(0);
	public static Victor turnTableMotor = new Victor(6);
	public static Victor hoodMotor = new Victor(7);
	public static Encoder hoodEncoder = new Encoder(6, 7);
	public static Encoder driveEncoder = new Encoder(0, 1);
	public static Encoder driveEncoder2 = new Encoder(2, 3);
	public static CANTalon shootingWheelMotor = new CANTalon(2);
	public static Servo shootingFlipper = new Servo(5);
	public static DigitalInput frontBB = new DigitalInput(12);
	public static DigitalInput backBB = new DigitalInput(13);
	
	public RobotMap () {
		navX.zeroYaw();
		shootingWheelMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shootingWheelMotor.configEncoderCodesPerRev(1);
		shootingWheelMotor.changeControlMode(TalonControlMode.PercentVbus);		
		driveEncoder.setReverseDirection(true);
	}
}
