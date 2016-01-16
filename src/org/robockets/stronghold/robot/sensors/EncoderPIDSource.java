package org.robockets.stronghold.robot.sensors;


import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {

	Encoder encoder;
	
	/**
	 * Return EncoderPIDSource to get and set values from a specified encoder.
	 * @param encodersensor The encoder to use for PID values.
	 */
	
	public EncoderPIDSource(Encoder encodersensor) {
		encoder = encoder;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// Currently ignoring
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// Currently ignoring
		return null;
	}

	@Override
	public double pidGet() {
		return RobotMap.navX.getCompassHeading();
	}

}
