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
    //ArrayList <Cell> neighborhood, 
	public void act(int step, Cell cell_atual) {
		if (this.step == step)
			return;
		this.step = step;
		age++;
		
		this.eat(cell_atual);
		if (!this.isAlive())
			cell_atual.setFish(null);
		else
			this.breed(cell_atual);
	}
	
	public void eat (Cell cell_atual) {
		// Eat some plancton
		double p = cell_atual.getPlancton();
		if (p > planctonEaten) {
			super.eat(planctonEaten);
			p -= planctonEaten;
		} else {
			super.eat(p);
			p = 0.1; // don't reduce to zero
		}
		cell_atual.setPlancton(p);
	}
	
	public boolean isAlive () {
		// burn some weight and see if we are still viable
		weight *= weightReduce;
		if (weight < viableWeight || age > maxAge) {
			return false;
		}
		
		return true;
	}
	
//	public void move (Cell bestNeighbour, List <Cell> neighborhood, int cell_atual) {
////		super.move(bestNeighbour);
//		neighborhood.get(cell_atual).setFish(null);
//		//neighborhood.get(cell_atual) = bestNeighbour;
//		bestNeighbour.setFish(this);
//	}
	
	public void breed (Cell cell_atual) {
		// look for the neighbouring cell with the most plancton
        // and no other fish
		Cell bestNeighbour = null;
        Cell cells[] = cell_atual.neighbours(1);
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
		} else if (bestNeighbour.getPlancton() > cell_atual.getPlancton())
			super.move(bestNeighbour, cell_atual);
	}
	
}
