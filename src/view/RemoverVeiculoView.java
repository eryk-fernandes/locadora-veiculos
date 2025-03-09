package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VeiculoController;

public class RemoverVeiculoView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> veiculos;
	private JButton btnConfirmar;
	private JButton btnVoltar;
	
	private static VeiculoController veiculoController = new VeiculoController();

	public RemoverVeiculoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoRemover = new JLabel("REMOVER VEÍCULOS");
		textoRemover.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoRemover.setHorizontalAlignment(SwingConstants.CENTER);
		textoRemover.setBounds(0, 39, 454, 23);
		contentPane.add(textoRemover);
		
		JLabel textoVeiculos = new JLabel("VEÍCULOS");
		textoVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
		textoVeiculos.setBounds(0, 109, 454, 14);
		contentPane.add(textoVeiculos);
		
		veiculos = new JComboBox<String>();
		veiculos.setBounds(95, 134, 258, 22);

		try {
			for (String veiculo : veiculoController.criarListaVeiculos()) {
				veiculos.addItem(veiculo);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO AO RECUPERAR VEÍCUOS");
		}
		
		contentPane.add(veiculos);
		
		btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(144, 245, 159, 23);
		contentPane.add(btnConfirmar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(340, 245, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnConfirmar.addActionListener(e -> {
			
			try {
				int opcao = JOptionPane.showConfirmDialog(contentPane, "REMOVER VEÍCULO?");
				
				if (opcao == 0) {
					veiculoController.removerVeiculo(veiculos.getSelectedItem());
					
					JOptionPane.showMessageDialog(contentPane, "VEÍCULO REMOVIDO COM SUCESSO");
					
					setVisible(false);
				}
				
			}
			catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO REMOVER VEÍCULO"
						+ "");
			}
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
		});
		
	}
}
