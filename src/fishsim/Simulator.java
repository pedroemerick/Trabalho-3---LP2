package fishsim;
import java.awt.Color;

/**
 * (Fill in description and author info here)
 */

public class Simulator
{
    private Ocean ocean;
    private SimulatorView simView;
    
    
    public static void main(String[] args) 
    {
        Simulator sim = new Simulator(50, 60);
        sim.run(1000000);
    }
    
    /**
     * Set up the simulation
     * @param height ocean dimension
     * @param width ocean dimension
     */
    public Simulator(int height, int width)
    {
        // Create simple fish parameter objects for the three
        // kinds of fish. These could be replaced by control panels
        SFPs herringParams = new SFPs();
        herringParams.initParams(4.0, 1.0, 0.8, 2.0, 6.0, 3, 30, 0, 0.0);
        SFPs groperParams = new SFPs();
        groperParams.initParams(8.0, 1.0, 0.8, 0.0, 16.0, 3, 30, 1, 8.0);
        SFPs sharkParams = new SFPs();
        sharkParams.initParams(16.0, 1.0, 0.8, 0.0, 32.0, 3, 30, 4, 12.0);
        ocean = new Ocean(height, width, herringParams, groperParams, sharkParams);
        simView = new SimulatorView(height, width);
        
        // define in which color fish should be shown
        simView.setColor(Herring.class, Color.red);
        simView.setColor(Groper.class, Color.blue);
        simView.setColor(Shark.class, Color.black);
    }
    
    /**
     * Run the simulation propper
     * @param steps number of iterations
     */
    public void run(int steps)
    {
        // put the simulation main loop here
        for (int i = 0; i < steps; i++)
        {
            ocean.act(i);
            simView.showStatus(i, ocean);
            try { Thread.sleep (500); } catch (InterruptedException ex) {}
        }
    }
}
