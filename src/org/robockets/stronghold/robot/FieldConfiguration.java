



/**

 * 

 */

package com.michael.staticstests;

/**

 * @author mwcapone

 *

 */

public class FieldConfiguration {

	public static String defense1;

	public static String defense2;

	public static String defense3;

	public static String defense4;

	public static String defense5;

	public static String robotPosition;

	public static String returnDefense;

	public static String shotHeight;

	public static String shotPosition;

	public static String returnDefense1;

	public static String returnDefense2;

	public static String returnDefense3;

	public static String returnDefense4;

	public static String returnDefense5;

	private static int stringCount = 0;

	

	public static void saveIt(String theString){

		switch(stringCount){

		case 0:

			defense1 = translate(theString);

			break;

		case 1:

			defense2 = translate(theString);

			break;

		case 2:

			defense3 = translate(theString);

			break;

		case 3:

			defense4 = translate(theString);

			break;

		case 4:

			defense5 = translate(theString);

			break;

		case 5:

			robotPosition = translate(theString);

			break;

		case 6:

			returnDefense = translate(theString);

			break;

		case 7 :

			shotHeight = translate(theString);

			break;

		case 8: 

			shotPosition = translate(theString);

			break;

		case 9:

			returnDefense1 = translate(theString);

			break;

		case 10:

			returnDefense2 = translate(theString);

			break;

		case 11:

			returnDefense3 = translate(theString);

			break;

		case 12:

			returnDefense4= translate(theString);

			break;

		

		case 13:

			returnDefense5 = translate(theString);

			break;		}

		stringCount++;

	}

	public static String translate(String theString){

		String theNewString = "null";

		if (theString == "r1"){	

			robotPosition = "Robot Position 1";

			

			theNewString = "Robot Position 1";

		}

		else if (theString == "r2"){

			robotPosition = "Robot Position 2";

			theNewString = "Robot Postion 2";

		}

		else if (theString == "r3"){

			robotPosition = "Robot Position 3";

			theNewString = "Robot Postion 3";

		}

		else if (theString == "r4"){

			robotPosition = "Robot pPosition 4";

			theNewString = "Robot Postion 4";

		}	

		else if (theString == "r5"){

			robotPosition = "Robot Position 5";

			theNewString = "Robot Postion 5";

		}

		else if (theString == "po"){

			theNewString = "Portcullist";

		}

		else if (theString == "fr"){

			theNewString = "Cheval de Frise";

		}

		else if (theString == "mo"){

			theNewString = "Moat";

		}

		else if (theString == "ra"){

			theNewString = "Ramparts";

		}

		else if (theString == "br"){

			theNewString = "DrawBridge";

		}

		else if (theString == "sa"){

			theNewString = "Sally Port";

		}

		else if (theString == "rk"){

			theNewString = "Rock Wall";

		}

		else if (theString == "ro"){

			theNewString = "Rough Terrian";

		}

		else if (theString == "hi"){

			theNewString = "High Shot";

		}

		else if (theString == "ls"){

			theNewString = "Low Shot";

		}

		else if(theString == "ms"){

			theNewString = "Middle Shot";

		}

		else if(theString == "ls"){

			theNewString = "Left shot";

		}

		else if(theString == "rs"){

			theNewString = "Right Shot";

		}

		else{

			theNewString = "Error";

		}

		return(theNewString);

	}

		

}
