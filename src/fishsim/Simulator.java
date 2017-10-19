package fishsim;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * (Fill in description and author info here)
 */

public class Simulator {
	private Ocean ocean;
	private SimulatorView simView;
	private boolean reinicia = false;

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
		
		if (simView.isModificar()) {
			fazerModificacoes (simView.getModificacoes());
			simView.setModificar(false);
			
			//System.out.println("MODIFICOU");
		}
		
//		System.out.println (ocean.groperParams.getInitWeight());
//		System.out.println (ocean.groperParams.getWeightReduce());
//		System.out.println (ocean.groperParams.getBreedAge());
//		System.out.println (ocean.groperParams.getMaxAge());
//		System.out.println();

	}
	
	public void fazerModificacoes (String modificacoes[]) {
		double modificacoes_double []= new double [2];
		int modificacoes_int [] = new int [2];
		
		for (int indice = 1; indice < 3; indice++) {
			if (modificacoes[indice] == null) {
				modificacoes_double[indice-1] = 0.0;
			} else {
				modificacoes_double[indice-1] = Double.parseDouble(modificacoes[indice]);
			}
		}
		
		for (int indice = 3; indice < 5; indice++) {
			if (modificacoes[indice] == null) {
				modificacoes_int[indice-3] = 0;
			} else {
				modificacoes_int[indice-3] = Integer.parseInt(modificacoes[indice]);
			}
		}
		
		switch (modificacoes[0]) {
			case "Sardinhas":
				ocean.modificaHerringParams(modificacoes_double, modificacoes_int);
				break;
			case "Atuns":
				ocean.modificaGroperParams(modificacoes_double, modificacoes_int);
				break;
			case "Tubaroes":
				ocean.modificaSharkParams(modificacoes_double, modificacoes_int);
				break;
			default:
				break;
		}
		
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
