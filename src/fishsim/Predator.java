package fishsim;

import java.util.Arrays;

/**
 * A fish that eats other fish - either a Groper or a Shark
 * @author jdb
 */
public abstract class Predator extends Fish {

    protected int huntDistance;
    protected double maxEat;

    /**
     * Construct a new predator
     * @param cell location
     * @param params prameters
     */
    public Predator(Cell cell, FishParams params) {
        super(cell, params);
        huntDistance = params.getHuntDistance();
        maxEat = params.getMaxEat();
    }

    /**
     * Called once for each iteration step
     * @param step iteration counter
     */
    //@Override
    public void act (int step, Cell cell_atual) {
        if (this.step == step) {
            return;
        }
        this.step = step;
        this.age++;

        this.eat(cell_atual);
        if (!this.isAlive())
			cell_atual.setFish(null);
		else
			this.breed(cell_atual);

    }
    
    public void eat (Cell cell_atual) {
    	// Eat as many fish as are in the neighbourhood
        // in decreasing order by status
        //Cell neighbours[] = cell.neighbours(huntDistance);
    	Cell neighbours[] = cell_atual.neighbours(huntDistance);
    	Arrays.sort(neighbours);
        double eaten = 0.0;
        for (Cell c : neighbours) {
            if (c.getStatus() == 0) {
                break;
            }

            if (c.getStatus() < status) {
            	double w = c.getFish().getWeight();
            	
            	// nao comer peixes maiores
            	if (this.getWeight() < w)
            		return;
            	
                super.eat(w);
                c.setFish(null);
                eaten += w;
                if (eaten >= maxEat)
                    break;
            }
        }
    }
    
    public boolean isAlive () {
    	 // Apply our weightloss and see if we are too light to live
        weight *= weightReduce;
        if (weight < viableWeight || age > maxAge) {
            return false;
        }
        
        return true;
    }
    
    public void breed (Cell cell_atual) {
    	// If we are qualified to breed then do so
        // by splitting in two.
        if (weight > breedWeight && age >= breedAge) {
            Cell c = Cell.random(cell_atual.neighbours(huntDistance, true));
            if (c != null) {
                Fish child = spawn(c);
                child.setWeight(weight / 2);
                weight /= 2;
            }
        } else {
			// Otherwise just swim to a neighbouring empty cell
            Cell c = Cell.random(cell_atual.neighbours(5, true));
            if (c != null)
                super.move(c, cell_atual);
        }
    }
    
//    public void move (Cell c, List <Cell> neighborhood, int cell_atual) {
//    	//super.move(cell);
//    	neighborhood.get(cell_atual).setFish(null);
//		c.setFish(this);
//    }
}
