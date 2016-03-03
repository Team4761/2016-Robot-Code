import edu.wpi.first.wpilibj.command.Command;
import org.robockets.stronghold.robot.FieldConfiguration;
import org.robockets.stronghold.robot.AAction;
import org.robockets.stronghold.robot.ADefense;
import java.util.*;
import java.awt.*;
class AutoCommands
{
	ADefense[] defenses;
	AAction action;
	int robotPosition, returnPosition;
	ADefense forwardDefense;
	ADefense returnDefense;
	Command moveCommand;
	public void init()
	{
		defenses = new ADefense[5];
		defenses[0] = parseDefense(FieldConfiguration.defense1);
		defenses[1] = parseDefense(FieldConfiguration.defense2);
		defenses[2] = parseDefense(FieldConfiguration.defense3);
		defenses[3] = parseDefense(FieldConfiguration.defense4);
		defenses[4] = parseDefense(FieldConfiguration.defense5);
		robotPosition = parsePosition(FieldConfiguration.robotPosition);
		forwardDefense = defenses[robotPosition];
		returnDefense = parseDefense(getReturnDefenseString());
		List defensesList = Arrays.asList(defenses);
		returnPosition = defensesList.indexOf(returnDefense);
	}
	public void run()
	{
		runCommandUntilFinished(forwardDefense.forwardCommand);
		runCommandUntilFinished(action.command);
		if (action == AAction.SHOOT_FROM_CLEAT)
			runCommandUntilFinished(new AutoMoveToPositionFromCleat(returnPosition));
		else
			runCommandUntilFinished(new AutoMoveToPositionFromPosition(returnPosition, robotPosition));
		runCommandUntilFinished(returnDefense.backwardCommand);
	}
	public void runCommandUntilFinished(Command c)
	{
		c.start();
		while (!c.isFinished());
	}
	public ADefense parseDefense(String s)
	{
		switch (s)
		{
			case "Portcullist" :
				return ADefense.PORTICULLIS;
			case "Cheval de Frise" :
				return ADefense.CHEVAL_DE_FRISE;
			case "Moat" :
				return ADefense.MOAT;
			case "Ramparts" :
				return ADefense.RAMPARTS;
			case "DrawBridge" :
				return ADefense.DRAWBRIDGE;
			case "Sally Port" :
				return ADefense.SALLY_PORT;
			case "Rock Wall" :
				return ADefense.ROCK_WALL;
			case "Rough Terrian" :
				return ADefense.ROUGH_TERRAIN;
			case "Low Bar" :
				return ADefense.LOW_BAR;
			default :
				return null;
		}
	}
	public int parsePosition(String s)
	{
		return ((int)s.charAt(s.length()-1))-0x30;	// because it really doesn't need a switch statement...
	}
	public String getReturnDefenseString()
	{
		String ret;
		if ((ret=FieldConfiguration.returnDefense1)!=null)
			return ret;
		if ((ret=FieldConfiguration.returnDefense2)!=null)
			return ret;
		if ((ret=FieldConfiguration.returnDefense3)!=null)
			return ret;
		if ((ret=FieldConfiguration.returnDefense4)!=null)
			return ret;
		if ((ret=FieldConfiguration.returnDefense5)!=null)
			return ret;
		return "Low Bar";	// This should not be the default...
	}
	
}

