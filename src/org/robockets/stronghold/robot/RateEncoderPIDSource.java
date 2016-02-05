package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RateEncoderPIDSource implements PIDSource {

	double previousDistance = 0;
	double previousTime = 0;
	double rate;
	Encoder encoder;
	
	public RateEncoderPIDSource(Encoder encoder){
		this.encoder = encoder;
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
		double currentTime = System.nanoTime();
		double currentDistance = RobotMap.shootingWheelEncoder.getDistance();
		double rate = (currentDistance - previousDistance)/(currentTime - previousTime);
		previousDistance = currentDistance;
		previousTime = currentTime;
		return rate;
		
	}

}
