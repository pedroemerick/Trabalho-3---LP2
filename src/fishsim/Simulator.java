package fishsim;

import java.awt.Color;

/**
 * (Fill in description and author info here)
 */

public class Simulator {
	private Ocean ocean;
	private SimulatorView simView;
	private boolean reinicia = false;

	public static void main(String[] args) throws InterruptedException {
		Simulator sim = new Simulator(50, 60);

		try {
			for (int i = 0; i < 10000; i++) {
				//sim.run(i);
				if (sim.isReinicia()) {
					sim.getSimView().setVisible(false);
					sim = new Simulator(50, 60);
					i = 0;
				}
				
				sim.run(i);
			}
		} catch (InterruptedException e) {
			System.out.println("Erro no sim.run()");
		}
	}

	/**
	 * Set up the simulation
	 * 
	 * @param height
	 *            ocean dimension
	 * @param width
	 *            ocean dimension
	 */
	public Simulator(int height, int width) {
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
		simView.setColor(Groper.class, Color.yellow);
		simView.setColor(Shark.class, Color.black);
	}

	/**
	 * Run the simulation propper
	 * 
	 * @param steps
	 *            number of iterations
	 * @throws InterruptedException
	 */
	public void run(int steps) throws InterruptedException {
		// put the simulation main loop here

		while (!simView.getIniciar()) {
			Thread.sleep(50);
		}

		ocean.act(steps);
		simView.showStatus(steps, ocean);

		if (simView.getReiniciar()) {
			reinicia = true;

		}
		/*
		 * if (simView.getReiniciar() == true) { //simView = new SimulatorView(50, 50);
		 * simView.setIniciar(false); simView.setReiniciar(false); //run(100); }
		 */

	}

	public boolean isReinicia() {
		return reinicia;
	}

	public void setReinicia(boolean reinicia) {
		this.reinicia = reinicia;
	}

	public SimulatorView getSimView() {
		return simView;
	}

	public void setSimView(SimulatorView simView) {
		this.simView = simView;
	}
}
