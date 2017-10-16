package fishsim;

/**
 * The Herring fish class
 * @author jdb
 * @version 28/11/2008
 */
public class Herring extends Fish {
	private double planctonEaten;
	
        /**
         * Construct a new herring
         * @param cell fish location
         * @param params parameters for the new fish
         */
        public  Herring(Cell cell, FishParams params) {
            super(cell, params);
            planctonEaten = params.getPlanctonEaten();
            status = 1;
        }
        
        /**
         * Return a new fish of the same kind
         * @param cell fish location
         * @return new fish
         */
        public Fish spawn(Cell cell) {
            return cell.createFish("herring");
        }
	
        /**
         * Iterate this fish through one simulator cycle
         * @param step counter that should be incremented for each
         * call. Used to avoid the same fish acting more than once
         * in a cycle
         */
	public void act(int step) {
		if (this.step == step)
			return;
		this.step = step;
		age++;
                
                // Eat some plancton
		double p = cell.getPlancton();
		if (p > planctonEaten) {
			eat(planctonEaten);
			p -= planctonEaten;
		} else {
			eat(p);
			p = 0.0; // don't reduce to zero
		}
		cell.setPlancton(p);
                
                // burn some weight and see if we are still viable
		weight *= weightReduce;
		if (weight < viableWeight || age > maxAge) {
			cell.setFish(null);
			return;
		}
                
                // look for the neighbouring cell with the most plancton
                // and no other fish
		Cell bestNeighbour = null;
                Cell cells[] = cell.neighbours(1);
                for (Cell c: cells) {
			if (c.getFish() != null)
				continue;
			if (bestNeighbour == null || c.getPlancton() > bestNeighbour.getPlancton())
				bestNeighbour = c;
		}
		if (bestNeighbour == null)
			return;
                
                // Either spawn into the neighbouring cell or if we can't
                // breed, move into it.
		if (weight >= breedWeight && age > breedAge)
		{
			Fish child = spawn(bestNeighbour);
                        child.setWeight(weight * 0.4);
			weight *= 0.6;
		} else if (bestNeighbour.getPlancton() > cell.getPlancton())
			move(bestNeighbour);
	}
}
