/**
 * 
 */
package fishsim;

/**
 * @author pedro
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		Simulator sim = new Simulator(50, 60);

		try {
			for (int i = 0; i < 10000; i++) {
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

}
