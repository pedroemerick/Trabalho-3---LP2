package fishsim;
import java.util.*;

/**
 * Represent a single location in the ocean
 * @author jdb
 * @version 28/11/2008
 */
public class Cell implements Comparable<Cell>
{
    private int row, col;
    private Ocean ocean;

    /**
     * Constructor for objects of class Cell
     */
    public Cell(Ocean ocean, int row, int col)
    {
        this.ocean = ocean;
        this.row = row;
        this.col = col;
    }
    
    /**
     * Compare the status of the fish in a pair of cells
     * Used when sorting neighbouring cells to decide which fish
     * to eac first
     * @param cell
     * @return
     */
    public int compareTo(Cell cell) {
        return cell.getStatus() - getStatus();
    }
    
    /**
     * Add a new fish to this cell. Any existing fish at this cell will
     * be destroyed
     * @param fishType
     * @return the new fish
     */
    public Fish createFish(String fishType)
    {
        return ocean.createFish(this, fishType);
    }

    /**
     * Accessor
     * @return row of this cell
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Accessor
     * @return column of this cell
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * Return all cells within an x and y distance r of here
     * @param rectangular distance
     * @return array of neighbouring cells
     */
    public Cell[] neighbours(int r)
    {
    	return neighbours(r, false);
    }
    
    /**
     * Return an array of cells in a rectangle surrounding this cell. Cells
     * are included if there row and collumn distance from here are both
     * less than or equal to r
     * @param r the maximum distance from here of cells returned.
     * @param empty if true only empty cessl are returned
     * @return array of neighbouring cells
     */
    public Cell[] neighbours(int r, boolean empty)
    {
    	int left = Math.max(0, col - r);
    	int right = Math.min(ocean.getWidth(), col + r + 1);
    	int top = Math.max(0, row - r);
    	int bottom = Math.min(ocean.getHeight(), row + r + 1);
    	Cell cells[] = new Cell[(bottom - top)*(right - left) - 1];
    	int n = 0;
    	for (int y = top; y < bottom; y++)
    		for (int x = left; x < right; x++) {
    			if (empty && ocean.getFishAt(y, x) != null)
    				continue;
    			if (x != col || y != row)
    				cells[n++] = new Cell(ocean, y, x);
    		}
        if (n < cells.length)
            return Arrays.copyOf(cells, n);
        else
            return cells;
    }
    
    /**
     * Get the status of any fish in the cell
     * @return fish status or 0 if the cell is unoccupied
     */
    public int getStatus()
    {
        Fish f = getFish();
        if (f != null)
            return f.getStatus();
        return 0;
    }
    
    /**
     * Get the fish at this cell
     * @return fish reference or null if the cell is unoccupied
     */
    public Fish getFish()
    {
        return ocean.getFishAt(row, col);
    }
    
    /**
     * Add a new fish to the cell. Any existing occupant is discarded
     * @param fish the fish to add. Use null to empty the cell
     */
    public void setFish(Fish fish)
    {
        ocean.setFishAt(fish, row, col);
    }
    
    /**
     * Get the plancton level at this location
     * @return plancton level
     */
    public double getPlancton()
    {
    	return ocean.getPlanctonAt(row, col).getInitialPlancton();
    }
    
    /**
     * Set the plancton level at this locatopn
     * @param p the new plancton level
     */
    public void setPlancton(double p)
    {
    	ocean.setPlanctonAt(p, row, col);
    }
    
    /**
     * Return a random cell from the array of cells
     * @param cells
     * @return chosen cell
     */
    static Cell random(Cell[] cells) {
        if (cells.length == 0)
            return null;
        return cells[(int)(cells.length * Math.random())];
    }
}
