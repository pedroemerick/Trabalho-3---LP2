package fishsim;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class PainelControle extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PainelControle() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPesoInicial = new JLabel("Peso Inicial:");
		GridBagConstraints gbc_lblPesoInicial = new GridBagConstraints();
		gbc_lblPesoInicial.anchor = GridBagConstraints.WEST;
		gbc_lblPesoInicial.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesoInicial.gridx = 1;
		gbc_lblPesoInicial.gridy = 1;
		add(lblPesoInicial, gbc_lblPesoInicial);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

	}

}
