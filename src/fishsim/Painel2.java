package fishsim;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Painel2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Painel2() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnIniciar = new JButton("Iniciar");
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciar.gridx = 6;
		gbc_btnIniciar.gridy = 1;
		add(btnIniciar, gbc_btnIniciar);
		
		JButton btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnPausar = new GridBagConstraints();
		gbc_btnPausar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPausar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPausar.anchor = GridBagConstraints.NORTH;
		gbc_btnPausar.gridx = 6;
		gbc_btnPausar.gridy = 2;
		add(btnPausar, gbc_btnPausar);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnReiniciar.gridx = 6;
		gbc_btnReiniciar.gridy = 4;
		add(btnReiniciar, gbc_btnReiniciar);
		
		JButton btnPainelDeControle = new JButton("Painel de Controle");
		GridBagConstraints gbc_btnPainelDeControle = new GridBagConstraints();
		gbc_btnPainelDeControle.insets = new Insets(0, 0, 5, 0);
		gbc_btnPainelDeControle.gridx = 6;
		gbc_btnPainelDeControle.gridy = 6;
		add(btnPainelDeControle, gbc_btnPainelDeControle);
		
		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 0);
		gbc_slider.gridx = 6;
		gbc_slider.gridy = 8;
		add(slider, gbc_slider);
		
		JLabel lblControleDeVelocidade = new JLabel("Controle de Velocidade");
		GridBagConstraints gbc_lblControleDeVelocidade = new GridBagConstraints();
		gbc_lblControleDeVelocidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblControleDeVelocidade.insets = new Insets(0, 0, 5, 0);
		gbc_lblControleDeVelocidade.gridx = 6;
		gbc_lblControleDeVelocidade.gridy = 9;
		add(lblControleDeVelocidade, gbc_lblControleDeVelocidade);

	}

}
