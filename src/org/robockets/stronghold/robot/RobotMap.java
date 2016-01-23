package org.robockets.stronghold.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
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
	public static RobotDrive robotDrive = new RobotDrive(1, 2, 3, 4);
	public static Victor intakeMotor = new Victor(5); //TEMP
	public static Victor jeffRoller1 = new Victor(6); // TEMP
	public static Victor jeffRoller2 = new Victor(7); // TEMP
	public static Victor shootingWheelMotor = new Victor(8);
	public static Victor turnTableMotor = new Victor(9); // TEMP
	
	public RobotMap () {
		navX.zeroYaw();
	}
}
