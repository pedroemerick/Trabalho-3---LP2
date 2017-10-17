package fishsim;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.HashMap;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2003.12.22
 */
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private OceanView oceanView;
    
    // A map for storing colors for participants in the simulation
    private HashMap<Class,Color> colors;
    // A statistics object computing and storing simulation information
    private OceanStats stats;
    
    private boolean pausar;
    private int velocidade;
    private boolean reiniciar;

    /**
     * Create a view of the given width and height.
     * @param height The simulation height.
     * @param width The simulation width.
     */
    public SimulatorView(int height, int width)
    {
        stats = new OceanStats();
        colors = new HashMap<Class,Color>();

        setTitle("SimOcean");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
//        JPanel painel1 = new JPanel ();
//        painel1.add(stepLabel, BorderLayout.NORTH);
//        painel1.add(oceanView, BorderLayout.CENTER);
//        painel1.add(population, BorderLayout.SOUTH);
        
        Painel2 painel = new Painel2 ();
        painel.setSize(10, 10);
        
        setLocation(100, 50);
        
        oceanView = new OceanView(height, width);

        Container contents = getContentPane();
        contents.add(stepLabel, BorderLayout.NORTH);
        contents.add(oceanView, BorderLayout.CENTER);
        contents.add(population, BorderLayout.SOUTH);
        
        contents.add(painel, BorderLayout.EAST);
//        painel.setVisible(true);
        
        //this.setContentPane(painel); 
        
        //contents.add(painel1, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
        
        // VERIFICAR SE ER AQUI MSM
        velocidade = 400;
    }
    
    /**
     * Define a color to be used for a given class of animal.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the ocean.
     * @param step Which iteration step it is.
     * @param ocean The ocean whose status is to be displayed.
     */
    public void showStatus(int step, Ocean ocean)
    {
        if(!isVisible())
            setVisible(true);
        
        if (reiniciar) {
        	step = 0;
        }
        
        // Verifica se pausar esta ativo
        while (pausar) {
        	try { Thread.sleep (50); } catch (InterruptedException ex) {}
        }

        stepLabel.setText(STEP_PREFIX + step);

        stats.reset();
        oceanView.preparePaint();
            
        for(int row = 0; row < ocean.getHeight(); row++) {
            for(int col = 0; col < ocean.getWidth(); col++) {
                Fish animal = ocean.getFishAt(row, col);
                Plancton plancton = ocean.getPlanctonAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    oceanView.drawMark(col, row, getColor(animal.getClass()));
                }
                else if (plancton != null && plancton.getInitialPlancton() > 0) {
                	int tonalidade_verde = (int) (plancton.getInitialPlancton()*255/10);
                	
                	if (tonalidade_verde < 128) {
                		tonalidade_verde = 128;
                	}
                	
                	oceanView.drawMark(col, row, new Color (0,tonalidade_verde,0));
                }
                else {
                    oceanView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(ocean));
        oceanView.repaint();
        
        // MUdando a velocidade
        try { Thread.sleep (velocidade); } catch (InterruptedException ex) {}
        
        //return step;
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Ocean ocean)
    {
        return stats.isViable(ocean);
    }
    
    /**
     * Provide a graphical view of a rectangular ocean. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the ocean.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class OceanView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image oceanImage;

        /**
         * Create a new OceanView component.
         */
        public OceanView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                oceanImage = oceanView.createImage(size.width, size.height);
                g = oceanImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this ocean in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The ocean view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(oceanImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(oceanImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(oceanImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
    
    public class Painel2 extends JPanel {

    	/**
    	 * Create the panel.
    	 */
    	public Painel2() {
    		GridBagLayout gridBagLayout = new GridBagLayout();
    		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    		setLayout(gridBagLayout);
    		
    		// BOTAO INICIAR
    		JButton btnIniciar = new JButton("Iniciar");
    		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
    		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
    		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
    		gbc_btnIniciar.gridx = 6;
    		gbc_btnIniciar.gridy = 2;
    		add(btnIniciar, gbc_btnIniciar);
    		
    		// BOTAO PAUSAR
    		JButton btnPausar = new JButton("Pausar");
    		btnPausar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				if (!pausar) {
	    				pausar = true;
	    				btnPausar.setText("Resumir");
    				} else {
    					pausar = false;
	    				btnPausar.setText("Pausar");
    				}
    			}
    		});
    		GridBagConstraints gbc_btnPausar = new GridBagConstraints();
    		gbc_btnPausar.fill = GridBagConstraints.HORIZONTAL;
    		gbc_btnPausar.insets = new Insets(0, 0, 5, 0);
    		gbc_btnPausar.anchor = GridBagConstraints.NORTH;
    		gbc_btnPausar.gridx = 6;
    		gbc_btnPausar.gridy = 3;
    		add(btnPausar, gbc_btnPausar);
    		
    		// BOTAO REINICIAR
    		JButton btnReiniciar = new JButton("Reiniciar");
    		btnPausar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    			}
    		});
    		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
    		gbc_btnReiniciar.fill = GridBagConstraints.HORIZONTAL;
    		gbc_btnReiniciar.insets = new Insets(0, 0, 5, 0);
    		gbc_btnReiniciar.gridx = 6;
    		gbc_btnReiniciar.gridy = 4;
    		add(btnReiniciar, gbc_btnReiniciar);
    		
    		// CONTROLE DE VELOCIDADE
    		JSlider slider = new JSlider(50, 800, 400);
    		slider.addChangeListener(new ChangeListener() {
    			public void stateChanged(ChangeEvent e) {
    				velocidade = slider.getValue();
    		    }
    		});
    		GridBagConstraints gbc_slider = new GridBagConstraints();
    		gbc_slider.insets = new Insets(0, 0, 5, 0);
    		gbc_slider.gridx = 6;
    		gbc_slider.gridy = 6;
    		add(slider, gbc_slider);
    		
    		JLabel lblControleDeVelocidade = new JLabel("Controle de Velocidade");
    		GridBagConstraints gbc_lblControleDeVelocidade = new GridBagConstraints();
    		gbc_lblControleDeVelocidade.gridx = 6;
    		gbc_lblControleDeVelocidade.gridy = 7;
    		add(lblControleDeVelocidade, gbc_lblControleDeVelocidade);

    	}
    }
}
