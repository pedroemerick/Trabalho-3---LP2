package fishsim;

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

}
