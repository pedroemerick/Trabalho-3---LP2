package fishsim;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
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
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnIniciar = new JButton("Iniciar");
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciar.gridx = 6;
		gbc_btnIniciar.gridy = 2;
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
		gbc_btnPausar.gridy = 3;
		add(btnPausar, gbc_btnPausar);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReiniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnReiniciar.gridx = 6;
		gbc_btnReiniciar.gridy = 4;
		add(btnReiniciar, gbc_btnReiniciar);
		
		JSlider slider = new JSlider();
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
