package org.example.view;

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

import org.example.controller.ClienteController;
import org.example.controller.LocacaoController;
import org.example.controller.VeiculoController;
import org.example.model.exception.TamanhoInvalidoException;
import org.example.model.exception.VeiculoLocadoException;

public class LocacaoView extends JFrame implements BotaoListener {

	private final JPanel contentPane;
	private final JFormattedTextField dataRetirada;
	private final JFormattedTextField dataDevolucao;
	private final JComboBox<String> clientes;
	private final JComboBox<String> veiculos;
	private final JButton btnCadastrar;
	private final JButton btnVoltar;
	
	private static final ClienteController clienteController = new ClienteController();
	private static final VeiculoController veiculoController = new VeiculoController();
	private static final LocacaoController locacaoController = new LocacaoController();

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
		
		try {
			MaskFormatter mask = new MaskFormatter("##/##/####");
			mask.install(dataRetirada);
			mask = new MaskFormatter("##/##/####");
			mask.install(dataDevolucao);
		}
		catch (ParseException exception) {
			JOptionPane.showMessageDialog(contentPane, "ERRO AO RECEBER DATA");
		}
		
		clientes = new JComboBox<>();
		clientes.setBounds(76, 112, 121, 22);
		
		try {
			for (String s : clienteController.criarListaCPFs())
				clientes.addItem(s);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO AO RECUPERAR CLIENTES");
		}
		
		contentPane.add(clientes);
		
		veiculos = new JComboBox<>();
		veiculos.setBounds(234, 112, 179, 22);
		
		try {
			for (String s : veiculoController.criarListaPlacas()) {
				veiculos.addItem(s);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO AO RECUPERAR VEÍCULOS");
		}
		
		contentPane.add(veiculos);
		
		btnCadastrar = new JButton("CADASTRAR LOCAÇÃO");
		btnCadastrar.setBounds(147, 255, 167, 27);
		contentPane.add(btnCadastrar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}
	
	@Override
	public void botaoListener() {
		btnCadastrar.addActionListener(e -> {
			try {
				Object veiculo = veiculos.getSelectedItem();
				Object cliente = clientes.getSelectedItem();

                assert veiculo != null;
                double valorLocacao = locacaoController.calcularCustoLocacao(veiculo, dataRetirada, dataDevolucao);
				
				int opcao = JOptionPane.showConfirmDialog(contentPane, String.format("VALOR DA LOCAÇÃO: R$ %.2f", valorLocacao));
				
				if (opcao == 0) {
                    assert cliente != null;
                    locacaoController.cadastrarDados(cliente, veiculo, dataRetirada, dataDevolucao);
					
					JOptionPane.showMessageDialog(contentPane, "LOCAÇÃO ADICIONADA COM SUCESSO");
					
					setVisible(false);
				}
			}
			catch (VeiculoLocadoException | TamanhoInvalidoException | IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR LOCAÇÃO");
			}
		});
		
		btnVoltar.addActionListener(e -> setVisible(false));
		
	}
}
