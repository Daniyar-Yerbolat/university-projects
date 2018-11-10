package lab3.foxesAndRabbits1;

import java.util.ArrayList;



/**
 * Starts Verson1 of FoxesAndRabbits.
 * 
 * @author Verena Rieser
 * @version 16.11.2011
 */

public class Runner
{

	/**
	 * Main method creates Simulator object.
	 * @param number of time steps. Must be greater than zero.
	 * @param dimesnions of the grid. Optional.
	 */
	public static void main( String[] args )
	{
		System.out.println("Starting FoxesAndRabbits...");
		if(args.length==0){
			System.out.println("You must provide at least one argument indicating the number of time steps.");
		}
		else{
			ArrayList<Integer> params = new ArrayList<Integer>();
			for(String s:  args){
				params.add(Integer.parseInt(s));
			}
			
			if(args.length==1){
				Simulator sim = new Simulator();
				//sim.simulate(args[0]);
				sim.simulate(params.get(0));
				
			}
			//if only one dimesion parameter is given, use it for both coordinates.
			if(args.length==2){
				Simulator sim = new Simulator(params.get(1),params.get(1));
				sim.simulate(params.get(0));
			}
			if(args.length==3){
				//Simulator sim = new Simulator(args[1],args[2]);
				//sim.simulate(args[0]);
				Simulator sim = new Simulator(params.get(1),params.get(2));
				sim.simulate(params.get(0));
			}
			if(args.length>3){
				System.out.println("You can provide up to 3 agruments");
						//, where the first agrgument indicates number of time steps, the second the x-dimension of the gris and the third the y-dimension. If only one dimesion parameter is given, it is used for both coordinates.");
			}
				
		}
			
		Simulator sim = null;	
		System.out.println("FoxesAndRabbits Simulation ended");
	}

}
