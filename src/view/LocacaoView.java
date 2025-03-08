package view;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.LocacaoController;
import controller.VeiculoController;
import excecoes.TamanhoInvalidoException;
import excecoes.VeiculoLocadoException;

public class LocacaoView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField dataRetirada;
	private JFormattedTextField dataDevolucao;
	private JComboBox<String> clientes;
	private JComboBox<String> veiculos;
	private JButton btnCadastrar;
	private JButton btnVoltar;
	
	private static ClienteController clienteController = new ClienteController();
	private static VeiculoController veiculoController = new VeiculoController();
	private static LocacaoController locacaoController = new LocacaoController();

	public LocacaoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocacao = new JLabel("CADASTRO DE LOCAÇÃO");
		textoLocacao.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoLocacao.setBounds(0, 22, 454, 23);
		contentPane.add(textoLocacao);
		
		JLabel textoVeiculos = new JLabel("VEÍCULOS");
		textoVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
		textoVeiculos.setBounds(234, 87, 179, 14); 
		contentPane.add(textoVeiculos);
		
		JLabel textoClientes = new JLabel("CLIENTES");
		textoClientes.setHorizontalAlignment(SwingConstants.CENTER);
		textoClientes.setBounds(76, 87, 121, 14);
		contentPane.add(textoClientes);
		
		JLabel textoRetirada = new JLabel("DATA DE RETIRADA");
		textoRetirada.setHorizontalAlignment(SwingConstants.CENTER);
		textoRetirada.setBounds(76, 164, 121, 14);
		contentPane.add(textoRetirada);
		
		JLabel textoDevolucao = new JLabel("DATA DE DEVOLUÇÃO");
		textoDevolucao.setHorizontalAlignment(SwingConstants.CENTER);
		textoDevolucao.setBounds(257, 164, 121, 14);
		contentPane.add(textoDevolucao);
		
		dataRetirada = new JFormattedTextField();
		dataRetirada.setHorizontalAlignment(SwingConstants.CENTER);
		dataRetirada.setBounds(76, 189, 121, 20);
		contentPane.add(dataRetirada);
		
		dataDevolucao = new JFormattedTextField();
		dataDevolucao.setHorizontalAlignment(SwingConstants.CENTER);
		dataDevolucao.setBounds(257, 189, 121, 20);
		contentPane.add(dataDevolucao);
		
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("##/##/####");
			mask.install(dataRetirada);
			mask = new MaskFormatter("##/##/####");
			mask.install(dataDevolucao);
		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		clientes = new JComboBox<String>();
		clientes.setBounds(76, 112, 121, 22);
		
		clientes.addItem("");
		for (String s : clienteController.criarListaCPFs())
			clientes.addItem(s);
		
		contentPane.add(clientes);
		
		veiculos = new JComboBox<String>();
		veiculos.setBounds(234, 112, 179, 22);
		
		veiculos.addItem("");
		for (String s : veiculoController.criarListaPlacas())
			veiculos.addItem(s);
		
		contentPane.add(veiculos);
		
		btnCadastrar = new JButton("CADASTRAR LOCAÇÃO");
		btnCadastrar.setBounds(147, 255, 167, 27);
		contentPane.add(btnCadastrar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		listener();
	}
	
	public void listener() {
		
		btnCadastrar.addActionListener(e -> {
			try {
				Object veiculo = veiculos.getSelectedItem();
				Object cliente = clientes.getSelectedItem();
				
				double valorLocacao = locacaoController.calcularCustoLocacao(veiculo, dataRetirada, dataDevolucao);
				
				int opcao = JOptionPane.showConfirmDialog(contentPane, String.format("VALOR DA LOCAÇÃO: R$ %.2f", valorLocacao));
				
				if (opcao == 0) {
					locacaoController.cadastrarDados(cliente, veiculo, dataRetirada, dataDevolucao);
					
					JOptionPane.showMessageDialog(contentPane, "LOCAÇÃO ADICIONADA COM SUCESSO");
				}
			}
			catch (VeiculoLocadoException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (TamanhoInvalidoException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR LOCAÇÃO");
			}
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new AtendenteView().setVisible(true);
		});
	}

	@Override
	public void botaoListener() {
		// TODO Auto-generated method stub
		
	}
}
