class AutoInformation
{
	int[] defenses;
	int robotPosition;
	int actionCommand;
	int forwardMoveCommand;
	int backwardMoveCommand;
	public AutoInformation fromString(String s)
	{
		defenses = getDefensesFromString(s);
		robotPosition = getPositionFromString(s);
		actionCommand = getActionCommandFromString(s);
		forwardMoveCommand = getForwardMoveCommandFromString(s);
		backwardMoveCommand = getBackwardMoveCommandFromString(s);		
	}
	int[] getDefensesFromString(String s)
	{
		
	}
	int getPositionFromString(String s)
	{
		
	}
	int getActionCommandFromString(String s)
	{
		
	}
	int getForwardMoveCommandFromString(String s)
	{
		
	}
	int getBackwardMoveCommandFromString(String s)
}
