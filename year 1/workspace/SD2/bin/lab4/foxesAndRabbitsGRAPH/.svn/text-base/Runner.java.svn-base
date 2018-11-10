package lab4.foxesAndRabbitsGRAPH;

import java.util.ArrayList;

import lab4.foxesAndRabbitsGRAPH.simulation.Simulator;

/**
 * Starts Verson1 of FoxesAndRabbits.
 * 
 * @author Verena Rieser
 * @version 16.11.2011
 */

public class Runner
{
	//Default time steps the simulation runs.
	private static final int DEFAULT_TIME_STEPS = 300;

	/**
	 * Main method creates Simulator object.
	 * 
	 * @param number
	 *            of time steps. Must be greater than zero.
	 * @param dimesnions
	 *            of the grid. Optional.
	 */
	public static void main( String[] args )
	{
		System.out.println( "Starting FoxesAndRabbits..." );
		ArrayList<Integer> params = new ArrayList<Integer>();

		if( args.length == 0 )
		{
			//System.out.println("You must provide at least one argument indicating the number of time steps.");
			params.add( DEFAULT_TIME_STEPS );
		} else
		{

			for ( String s : args )
			{
				params.add( Integer.parseInt( s ) );
			}

		}
		if( params.size() == 1 )
		{
			Simulator sim = new Simulator();
			sim.simulate( params.get( 0 ) );

		}
		//if only one dimesion parameter is given, use it for both coordinates.
		if( params.size() == 2 )
		{
			Simulator sim = new Simulator( params.get( 1 ), params.get( 1 ) );
			sim.simulate( params.get( 0 ) );
		}
		if( params.size() == 3 )
		{
			//Simulator sim = new Simulator(args[1],args[2]);
			//sim.simulate(args[0]);
			Simulator sim = new Simulator( params.get( 1 ), params.get( 2 ) );
			sim.simulate( params.get( 0 ) );
		}
		if( params.size() > 3 )
		{
			System.out.println( "You can provide up to 3 agruments" );
			//, where the first agrgument indicates number of time steps, the second the x-dimension of the gris and the third the y-dimension. If only one dimesion parameter is given, it is used for both coordinates.");
		}

		//Simulator sim = null;
		System.out.println( "FoxesAndRabbits Simulation ended" );
	}

}
