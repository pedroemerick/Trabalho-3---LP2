package fishsim;

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
}
