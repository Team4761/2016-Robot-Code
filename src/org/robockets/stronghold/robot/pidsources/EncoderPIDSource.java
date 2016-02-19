package org.robockets.stronghold.robot.pidsources;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {

	Encoder encoder;
	double factor;
	PIDSourceType sourceType;
	
	public EncoderPIDSource(Encoder jack, double factor, PIDSourceType type){
		encoder = jack;
		this.factor = factor;
		sourceType = type;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// Currently ignoring
		return sourceType;
	}

	@Override
	public double pidGet() {
		return encoder.getDistance() * factor;
	}

}
