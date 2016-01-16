package org.robockets.stronghold.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;

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
	public static RobotDrive robotDrive = new RobotDrive(1, 2, 3, 4);
	
	public static Encoder encoderFR = new Encoder(1,2);
	public static Encoder encoderFL = new Encoder(3,4);
	public static Encoder encoderBR = new Encoder(5,6);
	public static Encoder encoderBL = new Encoder(7,8);
	
	public RobotMap () {
		navX.zeroYaw();
	}
	
}