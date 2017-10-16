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
    @Override
    public void act(int step) {
        if (this.step == step) {
            return;
        }
        this.step = step;
        this.age++;

        // Eat as many fish as are in the neighbourhood
        // in decreasing order by status
        Cell neighbours[] = cell.neighbours(huntDistance);
        Arrays.sort(neighbours);
        double eaten = 0.0;
        for (Cell c : neighbours) {
            if (c.getStatus() == 0) {
                break;
            }

            if (c.getStatus() < status) {
                double w = c.getFish().getWeight();
                eat(w);
                c.setFish(null);
                eaten += w;
                if (eaten >= maxEat)
                    break;
            }
        }

        // Apply our weightloss and see if we are too light to live
        weight *= weightReduce;
        if (weight < viableWeight || age > maxAge) {
            cell.setFish(null);
            return;
        }

        // If we are qualified to breed then do so
        // by splitting in two.
        if (weight > breedWeight && age >= breedAge) {
            Cell c = Cell.random(cell.neighbours(huntDistance, true));
            if (c != null) {
                Fish child = spawn(c);
                child.setWeight(weight / 2);
                weight /= 2;
            }
        } else {
            // Otherwise just swim to a neighbouring empty cell
            Cell c = Cell.random(cell.neighbours(5, true));
            if (c != null)
                move(c);
        }

    }
}
