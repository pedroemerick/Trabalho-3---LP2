package fishsim;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelDeControle extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPesoPerdido;
	private JTextField textFieldPesoInicial;
	private JTextField textFieldIdadeProcriacao;
	private JTextField textFieldIdadeMaxVida;
	private JButton btnModificar;
	
//	private boolean modificar;
//	private String modificacoes[];

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PainelDeControle frame = new PainelDeControle();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PainelDeControle(SimulatorView simView) {
		setTitle("Painel de Controle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 150, 450, 300);
		setSize(400, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPesoInicial = new JLabel("Peso Inicial:");
		GridBagConstraints gbc_lblPesoInicial = new GridBagConstraints();
		gbc_lblPesoInicial.fill = GridBagConstraints.BOTH;
		gbc_lblPesoInicial.insets = new Insets(0, 0, 5, 0);
		gbc_lblPesoInicial.gridx = 0;
		gbc_lblPesoInicial.gridy = 0;
		contentPane.add(lblPesoInicial, gbc_lblPesoInicial);
		
		textFieldPesoInicial = new JTextField();
		GridBagConstraints gbc_textFieldPesoInicial = new GridBagConstraints();
		gbc_textFieldPesoInicial.fill = GridBagConstraints.BOTH;
		gbc_textFieldPesoInicial.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPesoInicial.gridx = 1;
		gbc_textFieldPesoInicial.gridy = 0;
		contentPane.add(textFieldPesoInicial, gbc_textFieldPesoInicial);
		textFieldPesoInicial.setColumns(10);
		
		JLabel lblPorcentagemDePeso = new JLabel("Porcentagem de Peso Perdido:");
		GridBagConstraints gbc_lblPorcentagemDePeso = new GridBagConstraints();
		gbc_lblPorcentagemDePeso.fill = GridBagConstraints.BOTH;
		gbc_lblPorcentagemDePeso.insets = new Insets(0, 0, 5, 0);
		gbc_lblPorcentagemDePeso.gridx = 0;
		gbc_lblPorcentagemDePeso.gridy = 1;
		contentPane.add(lblPorcentagemDePeso, gbc_lblPorcentagemDePeso);
		
		textFieldPesoPerdido = new JTextField();
		GridBagConstraints gbc_textFieldPesoPerdido = new GridBagConstraints();
		gbc_textFieldPesoPerdido.fill = GridBagConstraints.BOTH;
		gbc_textFieldPesoPerdido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPesoPerdido.gridx = 1;
		gbc_textFieldPesoPerdido.gridy = 1;
		contentPane.add(textFieldPesoPerdido, gbc_textFieldPesoPerdido);
		textFieldPesoPerdido.setColumns(10);
		
		JLabel lblIdadeParaProcriacao = new JLabel("Idade para Procriação:");
		GridBagConstraints gbc_lblIdadeParaProcriacao = new GridBagConstraints();
		gbc_lblIdadeParaProcriacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIdadeParaProcriacao.insets = new Insets(0, 0, 5, 0);
		gbc_lblIdadeParaProcriacao.gridx = 0;
		gbc_lblIdadeParaProcriacao.gridy = 2;
		contentPane.add(lblIdadeParaProcriacao, gbc_lblIdadeParaProcriacao);
		
		textFieldIdadeProcriacao = new JTextField();
		GridBagConstraints gbc_textFieldIdadeProcriacao = new GridBagConstraints();
		gbc_textFieldIdadeProcriacao.fill = GridBagConstraints.BOTH;
		gbc_textFieldIdadeProcriacao.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIdadeProcriacao.gridx = 1;
		gbc_textFieldIdadeProcriacao.gridy = 2;
		contentPane.add(textFieldIdadeProcriacao, gbc_textFieldIdadeProcriacao);
		textFieldIdadeProcriacao.setColumns(10);
		
		JLabel lblIdadeMaxVida = new JLabel("Idade Máxima de Vida:");
		GridBagConstraints gbc_lblIdadeMaxVida = new GridBagConstraints();
		gbc_lblIdadeMaxVida.fill = GridBagConstraints.BOTH;
		gbc_lblIdadeMaxVida.insets = new Insets(0, 0, 5, 0);
		gbc_lblIdadeMaxVida.gridx = 0;
		gbc_lblIdadeMaxVida.gridy = 3;
		contentPane.add(lblIdadeMaxVida, gbc_lblIdadeMaxVida);
		
		textFieldIdadeMaxVida = new JTextField();
		GridBagConstraints gbc_textFieldIdadeMaxVida = new GridBagConstraints();
		gbc_textFieldIdadeMaxVida.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIdadeMaxVida.fill = GridBagConstraints.BOTH;
		gbc_textFieldIdadeMaxVida.gridx = 1;
		gbc_textFieldIdadeMaxVida.gridy = 3;
		contentPane.add(textFieldIdadeMaxVida, gbc_textFieldIdadeMaxVida);
		textFieldIdadeMaxVida.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String modificacoes [] = new String [4];
				modificacoes[0] = textFieldPesoInicial.getText();
				modificacoes[1] = textFieldPesoPerdido.getText();
				modificacoes[2] = textFieldIdadeProcriacao.getText();
				modificacoes[3] = textFieldIdadeMaxVida.getText();
				
				for (int indice = 0; indice < 4; indice++) {
					if (modificacoes[indice].trim().equals("")) {
						modificacoes[indice] = null;
					}
				}
				
				simView.setModificacoes(modificacoes);
				simView.setModificar(true);
			
				dispose();
			}
		});
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnModificar.gridx = 1;
		gbc_btnModificar.gridy = 6;
		contentPane.add(btnModificar, gbc_btnModificar);
		
	}

//	/**
//	 * @return the modificar
//	 */
//	public boolean isModificar() {
//		return modificar;
//	}
//
//	/**
//	 * @param modificar the modificar to set
//	 */
//	public void setModificar(boolean modificar) {
//		this.modificar = modificar;
//	}
//
//	/**
//	 * @return the modificacoes
//	 */
//	public String[] getModificacoes() {
//		return modificacoes;
//	}

}
