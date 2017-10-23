package fishsim;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public PainelMenu (SimulatorView simView) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		// BOTAO INICIAR
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simView.setIniciar(true);
			}
		});
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciar.gridx = 6;
		gbc_btnIniciar.gridy = 1;
		add(btnIniciar, gbc_btnIniciar);

		// BOTAO PAUSAR
		JButton btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!simView.isPausar()) {
					simView.setPausar (true);
					btnPausar.setText("Resumir");
				} else {
					simView.setPausar (false);
					btnPausar.setText("Pausar");
				}
			}
		});
		GridBagConstraints gbc_btnPausar = new GridBagConstraints();
		gbc_btnPausar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPausar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPausar.anchor = GridBagConstraints.NORTH;
		gbc_btnPausar.gridx = 6;
		gbc_btnPausar.gridy = 2;
		add(btnPausar, gbc_btnPausar);

		// BOTAO REINICIAR
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simView.setReiniciar(true);
			}
		});
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReiniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnReiniciar.gridx = 6;
		gbc_btnReiniciar.gridy = 3;
		add(btnReiniciar, gbc_btnReiniciar);
		
		// BOTAO PAINEL DE CONTROLE
		JButton btnPainelDeControle = new JButton("Painel de Controle");
		btnPainelDeControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PainelDeControle painel_controle = new PainelDeControle (simView);
				 painel_controle.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnPainelDeControle = new GridBagConstraints();
		gbc_btnPainelDeControle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPainelDeControle.insets = new Insets(0, 0, 5, 0);
		gbc_btnPainelDeControle.gridx = 6;
		gbc_btnPainelDeControle.gridy = 4;
		add(btnPainelDeControle, gbc_btnPainelDeControle);

		// CONTROLE DE VELOCIDADE
		JSlider slider = new JSlider(0, 1000, 500);
		slider.setMajorTickSpacing(1000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				simView.setVelocidade (1000 - slider.getValue());
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
