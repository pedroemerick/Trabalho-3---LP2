package fishsim;

import java.util.Arrays;

/**
 * Blue groper fish class
 * @author jdb
 */
public class Groper extends Predator {

    /**
     * Create a new blue groper
     * @param cell location of the fish
     * @param params initial parameters
     */
    public Groper(Cell cell, FishParams params)
    {
        super(cell, params);
        status = 2;
    }

    /**
     * Create another groper
     * @param cell location for the new fish
     * @return the fish spawned
     */
    public Fish spawn(Cell cell)
    {
        return cell.createFish("groper");
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

            if (c.getStatus() < status) 
            {
            	// nao comer peixes maiores
            	if (c.getFish() instanceof Herring) 
            	{
	            	double w = c.getFish().getWeight();
	                super.eat(w);
	                c.setFish(null);
	                eaten += w;
	                if (eaten >= maxEat)
	                    break;
            	}
            }	
        }
    }

}
