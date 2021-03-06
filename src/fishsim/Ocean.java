package fishsim;

import java.util.*;

import javax.swing.JOptionPane;

/**
 * Manage the rectangle of cells representing an ocean
 */
public class Ocean {
	// Configurable parameters
	// Plancton
	// private double initialPlancton = 5.0;
	// private double maxPlancton = 10.0;
	// private double incPlancton = 1.2;

	FishParams herringParams, groperParams, sharkParams;
	private int width, height;
	private ArrayList<Fish> fishes;
	// private double plancton[];

	private ArrayList<Plancton> plancton;

	/**
	 * Create a new ocean
	 * 
	 * @param height
	 *            height in cells
	 * @param width
	 *            width in cells
	 * @param herringParams
	 *            provides parameters for any herrings created
	 * @param groperParams
	 *            provides parameters for any gropers created
	 * @param sharkParams
	 *            provides parameters for any sharks created
	 */
	public Ocean(int height, int width, FishParams herringParams, FishParams groperParams, FishParams sharkParams) {
		this.width = width;
		this.height = height;
		this.herringParams = herringParams;
		this.groperParams = groperParams;
		this.sharkParams = sharkParams;
		fishes = new ArrayList<Fish>(width * height);
		// plancton = new double[width * height];
		plancton = new ArrayList<Plancton>();
		for (int n = 0; n < (width * height); n++) {
			fishes.add(n, null);
			// plancton[n] = initialPlancton;
			plancton.add(new Plancton());
		}
	}

	/**
	 * Fish creation factory Create a new fish of the named type
	 * 
	 * @param cell
	 * @param fishType
	 *            string with the name of the kind of fish
	 * @return created fish
	 */
	public Fish createFish(Cell cell, String fishType) {
		if (fishType.equals("herring"))
			return new Herring(cell, herringParams);
		if (fishType.equals("groper"))
			return new Groper(cell, groperParams);
		if (fishType.equals("shark"))
			return new Shark(cell, sharkParams);
		return null;
	}

	/**
	 * Put the ocean through one iteraction of the simulator
	 * 
	 * @param step
	 *            number of this iteration
	 */
	public void act(int step) {
		/*
		 * Seed the ocean with new fish occasionally
		 */
		if (step % 100 == 0)
			createFish(new Cell(this, 10, 10), "herring");
		if (step % 100 == 50) {
			createFish(new Cell(this, 20, 20), "groper");
			createFish(new Cell(this, 40, 40), "shark");
		}

		// Act on all the fish
		// VERIFICAR SEGUNTO PONTO DO PDF SE JA ESTA PRONTO
		Cell cells[] = Cells();
		for (int n = 0; n < cells.length; n++) {
			if (cells[n].getFish() != null) {
//				cells[n].getFish().plusAge(step);
//				cells[n].getFish().eat(cells[n]);
//				if (!cells[n].getFish().isAlive())
//					cells[n].setFish(null);
//				else
//					cells[n].getFish().breed(cells[n]);
				
				cells[n].getFish().act(step, cells[n]);
			}
			
		}
		
		// Grow the plancton
		for (int n = 0; n < plancton.size(); n++) {
			//plancton[n] = Math.min(plancton[n] * incPlancton, maxPlancton);

			Plancton atual = plancton.get(n);
			
			double temp = atual.getInitialPlancton() * atual.getIncPlancton();
			temp -= atual.getInitialPlancton();
			temp /= 2;
			
			double proximo_valor = atual.getInitialPlancton() + temp;
			proximo_valor = Math.min(proximo_valor, atual.getMaxPlancton());
			atual.setInitialPlancton(proximo_valor);
			
			Cell vizinhos[] = cells[n].neighbours(1);
    		
			Plancton aux;
    		for (int i = 0; i < vizinhos.length; i++)
    		{
    			aux = getPlanctonAt(vizinhos[i].getRow(), vizinhos[i].getCol());
    			double valor = aux.getInitialPlancton() + (temp/4);
    			aux.setInitialPlancton(Math.min(valor, aux.getMaxPlancton()));
    		}
	
		}
	}

	/**
	 * Get all the cells in the ocean
	 * 
	 * @return array of cells
	 */
	public Cell[] Cells() {
		Cell cells[] = new Cell[width * height];
		for (int n = 0; n < cells.length; n++)
			cells[n] = new Cell(this, n / width, n % width);
		return cells;
	}

	/**
	 * Return the fish at the given location, if any.
	 * 
	 * @param row
	 *            The desired row.
	 * @param col
	 *            The desired column.
	 * @return The fish at the given location, or null if there is none.
	 */
	public Fish getFishAt(int row, int col) {
		return fishes.get(width * row + col);
	}

	/**
	 * Low-level method to add the fish to the ocean. Used by cells
	 * 
	 * @param fish
	 *            added
	 * @param row
	 *            cell location
	 * @param col
	 *            cell location
	 */
	public void setFishAt(Fish fish, int row, int col) {
		fishes.set(width * row + col, fish);
	}

	/**
	 * Get the plancton level
	 * 
	 * @param row
	 *            location
	 * @param col
	 *            location
	 * @return level
	 */
	public Plancton getPlanctonAt(int row, int col) {
		return plancton.get(width * row + col);
	}

	/**
	 * Mutator
	 * 
	 * @param p
	 *            new plancton level
	 * @param row
	 *            location
	 * @param col
	 *            location
	 */
	public void setPlanctonAt(double p, int row, int col) {
		plancton.get(width * row + col).setInitialPlancton(p);
	}

	/**
	 * @return The height of the ocean.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The width of the ocean.
	 */
	public int getWidth() {
		return width;
	}
	
	public void modificaHerringParams (double modificacoes_double[], int modificacoes_int[]) {
		if (modificacoes_double[0] != 0) {
			herringParams.setInitWeight(modificacoes_double[0]);
		}
		
		if (modificacoes_double[1] != 0) {
			herringParams.setWeightReduce(modificacoes_double[1]);
		}
		
		if (modificacoes_int[0] != 0) {
			herringParams.setBreedAge(modificacoes_int[0]);
		}
		
		if (modificacoes_int[1] != 0) {
			herringParams.setMaxAge(modificacoes_int[1]);
		}
		
		JOptionPane.showMessageDialog(null, "Mudança nas sardinhas salva com sucesso !!!");
	}
	
	public void modificaGroperParams (double modificacoes_double[], int modificacoes_int[]) {
		if (modificacoes_double[0] != 0) {
			groperParams.setInitWeight(modificacoes_double[0]);
		}
		
		if (modificacoes_double[1] != 0) {
			groperParams.setWeightReduce(modificacoes_double[1]);
		}
		
		if (modificacoes_int[0] != 0) {
			groperParams.setBreedAge(modificacoes_int[0]);
		}
		
		if (modificacoes_int[1] != 0) {
			groperParams.setMaxAge(modificacoes_int[1]);
		}
		
		JOptionPane.showMessageDialog(null, "Mudança nos atuns salva com sucesso !!!");
	}
	
	public void modificaSharkParams (double modificacoes_double[], int modificacoes_int[]) {
		if (modificacoes_double[0] != 0) {
			sharkParams.setInitWeight(modificacoes_double[0]);
		}
		
		if (modificacoes_double[1] != 0) {
			sharkParams.setWeightReduce(modificacoes_double[1]);
		}
		
		if (modificacoes_int[0] != 0) {
			sharkParams.setBreedAge(modificacoes_int[0]);
		}
		
		if (modificacoes_int[1] != 0) {
			sharkParams.setMaxAge(modificacoes_int[1]);
		}
		
		JOptionPane.showMessageDialog(null, "Mudança nos tubaroes salva com sucesso !!!");
	}
}
