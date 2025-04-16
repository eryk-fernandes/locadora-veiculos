package org.example.view;

import java.awt.Font;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.example.controller.VeiculoController;
import org.example.model.exception.TamanhoInvalidoException;

public class CadastrarVeiculoView extends JFrame implements BotaoListener {

	private final JPanel contentPane;
	private final JTextField placa;
	private final JTextField modelo;
	private final JTextField ano;
	private final JButton btnCadastrar;
	private final JButton btnVoltar;
	private final JComboBox<String> tiposVeiculo;
	
	private static final VeiculoController veiculoController = new VeiculoController();

	public CadastrarVeiculoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoCadastro = new JLabel("CADASTRO DE VEÍCULO");
		textoCadastro.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastro.setBounds(0, 11, 454, 38);
		contentPane.add(textoCadastro);
		
		JLabel textoPlaca = new JLabel("PLACA");
		textoPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textoPlaca.setBounds(29, 91, 184, 14);
		contentPane.add(textoPlaca);
		
		JLabel textoModelo = new JLabel("MODELO");
		textoModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textoModelo.setBounds(29, 162, 184, 14);
		contentPane.add(textoModelo);
		
		JLabel textoAno = new JLabel("ANO");
		textoAno.setHorizontalAlignment(SwingConstants.CENTER);
		textoAno.setBounds(234, 162, 184, 14);
		contentPane.add(textoAno);
		
		JLabel textotipoDeVeiculo = new JLabel("TIPO DE VEÍCULO");
		textotipoDeVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		textotipoDeVeiculo.setBounds(234, 91, 184, 14);
		contentPane.add(textotipoDeVeiculo);
		
		modelo = new JTextField();
		modelo.setHorizontalAlignment(SwingConstants.CENTER);
		modelo.setColumns(10);
		modelo.setBounds(29, 187, 184, 20);
		contentPane.add(modelo);
		
		placa = new JTextField();
		placa.setHorizontalAlignment(SwingConstants.CENTER);
		placa.setBounds(29, 117, 184, 20);
		contentPane.add(placa);
		
		ano = new JTextField();
		ano.setHorizontalAlignment(SwingConstants.CENTER);
		ano.setBounds(234, 187, 184, 20);
		contentPane.add(ano);
		
		tiposVeiculo = new JComboBox<>();
		tiposVeiculo.setBounds(272, 116, 107, 22);
		tiposVeiculo.addItem("");
		tiposVeiculo.addItem("CARRO");
		tiposVeiculo.addItem("MOTO");
		tiposVeiculo.addItem("CAMINHÃO");
		contentPane.add(tiposVeiculo);
		
		btnCadastrar = new JButton("CADASTRAR VEÍCULO");
		btnCadastrar.setBounds(101, 257, 192, 23);
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
				veiculoController.cadastrarDados(Objects.requireNonNull(tiposVeiculo.getSelectedItem()), placa, modelo, ano);
					
				JOptionPane.showMessageDialog(contentPane, "VEÍCULO ADICIONADO COM SUCESSO");
			}
			catch (TamanhoInvalidoException | IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR VEÍCULO");
			}
		});
		
		btnVoltar.addActionListener(e -> setVisible(false));
	}

}
