class AutoCommands
{
	Defense[] defenses;
	Action action;
	int robotPosition;
	Defense forwardDefense;
	Defense returnDefense;
	public void init()
	{
		defenses = new Defense[5];
		defenses[0] = parseDefense(FieldConfiguration.defense1);
		defenses[1] = parseDefense(FieldConfiguration.defense2);
		defenses[2] = parseDefense(FieldConfiguration.defense3);
		defenses[3] = parseDefense(FieldConfiguration.defense4);
		defenses[4] = parseDefense(FieldConfiguration.defense5);
		robotPosition = parsePosition(FieldConfiguration.robotPosition);
		forwardDefense = defenses[robotPosition];
		returnDefense = parseDefense(getReturnDefenseString());
	}
	public void run()
	{
		runCommandUntilFinished(forwardDefense.forwardCommand);
		runCommandUntilFinished(action.command);
		runCommandUntilFinished(returnDefense.backwardCommand);
	}
	public void runCommandUntilFinished(Command c)
	{
		c.start();
		while (!c.isFinished());
	}
	public Defense parseDefense(String s)
	{
		switch (String s)
		{
			case "Portcullist" :
				return Defense.PORTICULLIS;
			case "Cheval de Frise" :
				return Defense.CHEVAL_DE_FRISE;
			case "Moat" :
				return Defense.MOAT;
			case "Ramparts" :
				return Defense.RAMPARTS;
			case "DrawBridge" :
				return Defense.DRAWBRIDGE;
			case "Sally Port" :
				return Defense.SALLY_PORT;
			case "Rock Wall" :
				return Defense.ROCK_WALL;
			case "Rough Terrian" :
				return Defense.ROUGH_TERRAIN;
			case "Low Bar" :
				return Defense.LOW_BAR;
			case default :
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
