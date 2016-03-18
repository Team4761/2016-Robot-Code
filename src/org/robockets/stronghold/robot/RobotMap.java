package org.robockets.stronghold.robot;

import org.robockets.stronghold.robot.pidsources.EncoderPIDSource;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;

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
	public static Victor intakeMotorFront = new Victor(1); //1,3,0,2,6,7
	public static Victor intakeMotorBack = new Victor(3);
	public static Encoder intakeEncoderBack = new Encoder(11, 12);
	public static Encoder intakeEncoderFront = new Encoder(4, 5);
	public static RobotDrive robotDrive = new RobotDrive(18, 19);
	public static Encoder turnTableEncoder = new Encoder(8, 9);
	public static EncoderPIDSource turntablePIDSource = new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement);;
	public static Victor intakeVerticalMotorFront = new Victor(0);
	public static Victor intakeVerticalMotorBack = new Victor(2);
	public static Victor turnTableMotor = new Victor(6);
	public static Victor hoodMotor = new Victor(7);
	public static Encoder hoodEncoder = new Encoder(6, 7);
	public final static double COUNTS_PER_DEGREE_HOOD = 7.3111;
	public final static EncoderPIDSource hoodPIDSource = new EncoderPIDSource(RobotMap.hoodEncoder, 1.0 / COUNTS_PER_DEGREE_HOOD, PIDSourceType.kDisplacement);
	public static Encoder driveEncoder = new Encoder(0, 1);
	public static Encoder driveEncoder2 = new Encoder(2, 3);
	public static CANTalon shootingWheelMotor = new CANTalon(2);
	public static DigitalInput frontBB = new DigitalInput(10);
	public static DigitalInput backBB = new DigitalInput(13);
	public static Servo shootingFlipper = new Servo(5);
	
	public RobotMap () {
		navX.zeroYaw();
		shootingWheelMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shootingWheelMotor.configEncoderCodesPerRev(1);
		shootingWheelMotor.changeControlMode(TalonControlMode.PercentVbus);		
		driveEncoder.setReverseDirection(true);
	}
}
