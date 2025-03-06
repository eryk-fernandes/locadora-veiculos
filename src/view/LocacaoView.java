package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.LocacaoController;
import controller.VeiculoController;

public class LocacaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ClienteController clienteController = new ClienteController();
	private VeiculoController veiculoController = new VeiculoController();
	private LocacaoController locacaoController = new LocacaoController();

	public LocacaoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocacao = new JLabel("CADASTRO DE LOCAÇÃO");
		textoLocacao.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoLocacao.setBounds(95, 22, 242, 23);
		contentPane.add(textoLocacao);
		
		JLabel textoVeiculos = new JLabel("VEÍCULOS");
		textoVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
		textoVeiculos.setBounds(256, 77, 121, 14);
		contentPane.add(textoVeiculos);
		
		JLabel textoClientes = new JLabel("CLIENTES");
		textoClientes.setHorizontalAlignment(SwingConstants.CENTER);
		textoClientes.setBounds(58, 77, 121, 14);
		contentPane.add(textoClientes);
		
		JComboBox<String> comboBoxClientes = new JComboBox<String>();
		comboBoxClientes.setBounds(58, 102, 121, 22);
		contentPane.add(comboBoxClientes);
		
		adicionarItensClientes(comboBoxClientes);
		
		JComboBox<String> comboBoxVeiculos = new JComboBox<String>();
		comboBoxVeiculos.setBounds(256, 102, 121, 22);
		contentPane.add(comboBoxVeiculos);
		
		adicionarItensVeiculos(comboBoxVeiculos);
		
		JButton btnCadastrar = new JButton("CADASTRAR LOCAÇÃO");
		btnCadastrar.setBounds(120, 233, 200, 31);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					
					locacaoController.cadastrarDados(comboBoxClientes.getSelectedItem(), comboBoxVeiculos.getSelectedItem());
					
					JOptionPane.showMessageDialog(contentPane, "LOCAÇÃO ADICIONADO COM SUCESSO");
					
					setVisible(false);
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR LOCAÇÃO");
				}
			}
		});
		
	}
	
	public void adicionarItensClientes(JComboBox<String> comboBox) {
		for (String s : clienteController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}
	
	public void adicionarItensVeiculos(JComboBox<String> comboBox) {
		for (String s : veiculoController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}
}
