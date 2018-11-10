/**
 * 
 */
package lab3.foxesAndRabbits2;



/** Creates new Simulator for FoxesAndRabbits.
 * @author vrieser
 * @version 16.11.2011
 */
public class StartFoxesAndRabbits
{
	
	//Default time steps the simulation runs.
	private static final int DEFAULT_TIME_STEPS = 300;
	
	/**
	 * Main method creates Simulator object.
	 * @param number of time steps. 
	 * @param dimesnions of the grid. Optional.
	 * If no parameter is given the simulation starts and executes one step.
	 */
	public static void main( String[] args )
	{

		if( args.length > 1 )
		{
			System.out.println( "You can only provide one argument: the number of time steps." );
		} else
		{
			// number of time steps
			int n = DEFAULT_TIME_STEPS;

			if( args.length == 1 )
			{
				n = Integer.parseInt( args[0] );
			}

			System.out.println( "Starting FoxesAndRabbits..." );
			Simulator sim = new Simulator();
			sim.simulate( n );

			System.out.println( "FoxesAndRabbits Simulation ended with " + Integer.toString( n ) + " iterations." );
		
		}
	}
}
