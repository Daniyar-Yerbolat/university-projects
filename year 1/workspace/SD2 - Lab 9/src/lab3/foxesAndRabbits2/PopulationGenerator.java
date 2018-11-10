package lab3.foxesAndRabbits2;

import java.util.Random;

public class PopulationGenerator extends Simulator {
	/**
	 * Randomly populate the field with foxes and rabbits.
	 */
	protected void populate() {
		Random rand = Randomizer.getRandom();
		field.clear();
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {
				if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Fox fox = new Fox(true, field, location);
					animals.add(fox);
				} else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Rabbit rabbit = new Rabbit(true, field, location);
					animals.add(rabbit);
				}
				// else leave the location empty.
			}
		}
	}
}
