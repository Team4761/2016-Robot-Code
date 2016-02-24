package org.usfirst.frc.team4761.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickProtocol
{
	static Joystick joystick = new Joystick(0);
	public static byte blockingReadByte()
	{
		byte number = 0;
		waitReadyToRead();
		for (int n = 1; n < 9; n++)
			number |= (joystick.getRawButton(n)?1:0)<<(n-1);
		return number;
	}
	static void waitReadyToRead()
	{
		while (joystick.getRawButton(9)){}	// Wait until the bit is off
		while (!joystick.getRawButton(9)){}	// Wait until it turns back on
	}
	public static int blockingReadInt()
	{
		return (blockingReadByte()<<24) | (blockingReadByte()<<16) | (blockingReadByte()<<8) | (blockingReadByte());
	}
	public static String blockingReadString()
	{
		String ret = "";
		byte ch;
		while ((ch=blockingReadByte())!=0)
		{
			ret += (char)ch;
		}
		return ret;
	}
}
class JoystickProtocolHandler implements Runnable
{
	ArrayList<Byte> buffer = new ArrayList<Byte>();
	public void run()
	{
		while (true)
		{
			buffer.add(JoystickProtocol.blockingReadByte());
		}
	}
	int blockingReadByte()
	{
		while (buffer.size() == 0){}
		return readByte();
	}
	int readByte()
	{
		if (buffer.size() == 0)
			return -1;
		int ret = buffer.get(buffer.size()-1);
		buffer.remove(buffer.size()-1);
		return ret;
	}
	int blockingReadInt()
	{
		return (blockingReadByte()<<24) | (blockingReadByte()<<16) | (blockingReadByte()<<8) | (blockingReadByte());
	}
	String blockingReadString()
	{
		String ret = "";
		char ch;
		while ((ch=(char)blockingReadByte())!=0)
		{
			ret += ch;
		}
		return ret;
	}
}
// Optimized functions
class OptimizedJoystickProtocolHandler implements Runnable
{
	byte[] buffer = new byte[512];
	int z;
	boolean enabled;
	void startHandling()
	{
		new Thread(this).start();
		enabled = true;
	}
	void stopHandling()
	{
		enabled = false;
	}
	public void run() {
		while (enabled) {
			buffer[z++] = JoystickProtocol.blockingReadByte();
			if (z>511) {
				System.err.println("[OpJoystickHandler] Data buffer is full. Discarding any new data.");
				while (z > 511);
			}
		}
	}
	int blockingReadByte() {
		while (z == 0);
		return buffer[--z];
	}
	int readByte() {
		if (buffer.length == 0) {
			return -1;
		}
		return buffer[--z];
	}
	int blockingReadInt() {
		while (z < 4);
		z-=4;
		return (buffer[z+3]<<24) | (buffer[z+2]<<16) | (buffer[z+1]<<8) | buffer[z];
	}
	String blockingReadString() {
		int q = -1;
		while (buffer[++q]!=0);
		if (q>=z) {
			while (z==0);	// Prevent ArrayIndexOutOfBounds exceptions.
			while (buffer[z-1] != 0);
			q = z;
		}
		z -= q;
		return new String(buffer, 0, q);
	}
	String readString() {
		int q = -1;
		while (buffer[++q]!=0);	// Assumes that the buffer hasn't filled up. If it has this will throw an error.
		if (q>=z){
			return null;
		}
		z -= q;
		return new String(buffer, 0, q);
	}
}
