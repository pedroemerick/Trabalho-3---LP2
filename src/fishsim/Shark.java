package fishsim;

import java.util.Arrays;

public class Shark extends Predator {
        
        public Shark(Cell cell, FishParams params)
        {
            super(cell, params);
            status = 3;
        }
        
        public Fish spawn(Cell cell)
        {
            return cell.createFish("shark");
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
                	if (!(c.getFish() instanceof Shark)) 
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
