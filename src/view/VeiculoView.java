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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VeiculoController;

public class VeiculoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField placa;
	private JTextField modelo;
	private JTextField ano;
	private JButton btnCadastrar;
	
	private VeiculoController veiculoController = new VeiculoController();

	public VeiculoView() {
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
		textoCadastro.setBounds(119, 11, 223, 38);
		contentPane.add(textoCadastro);
		
		JLabel textoPlaca = new JLabel("PLACA");
		textoPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textoPlaca.setBounds(29, 78, 184, 14);
		contentPane.add(textoPlaca);
		
		JLabel textoModelo = new JLabel("MODELO");
		textoModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textoModelo.setBounds(29, 134, 184, 14);
		contentPane.add(textoModelo);
		
		JLabel textoAno = new JLabel("ANO");
		textoAno.setHorizontalAlignment(SwingConstants.CENTER);
		textoAno.setBounds(29, 190, 184, 14);
		contentPane.add(textoAno);
		
		JLabel textoLocacao = new JLabel("STATUS DE LOCAÇÃO");
		textoLocacao.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacao.setBounds(233, 162, 184, 14);
		contentPane.add(textoLocacao);
		
		JLabel textotipoDeVeiculo = new JLabel("TIPO DE VEÍCULO");
		textotipoDeVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		textotipoDeVeiculo.setBounds(233, 78, 184, 14);
		contentPane.add(textotipoDeVeiculo);
		
		placa = new JTextField();
		placa.setBounds(29, 103, 184, 20);
		contentPane.add(placa);
		placa.setColumns(10);
		
		modelo = new JTextField();
		modelo.setColumns(10);
		modelo.setBounds(29, 159, 184, 20);
		contentPane.add(modelo);
		
		ano = new JTextField();
		ano.setColumns(10);
		ano.setBounds(29, 216, 184, 20);
		contentPane.add(ano);
		
		JComboBox<String> cbTipoVeiculo = new JComboBox<String>();
		cbTipoVeiculo.setBounds(256, 103, 134, 22);
		contentPane.add(cbTipoVeiculo);
		
		cbTipoVeiculo.addItem("");
		cbTipoVeiculo.addItem("Carro");
		cbTipoVeiculo.addItem("Moto");
		cbTipoVeiculo.addItem("Caminhão");
		
		JComboBox<String> cbStatusLocacao = new JComboBox<String>();
		cbStatusLocacao.setBounds(256, 200, 134, 22);
		contentPane.add(cbStatusLocacao);
		
		cbStatusLocacao.addItem("");
		cbStatusLocacao.addItem("Disponível");
		cbStatusLocacao.addItem("Locado");
		
		btnCadastrar = new JButton("CADASTRAR VEÍCULO");
		btnCadastrar.setBounds(101, 257, 192, 23);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					veiculoController.cadastrarDados(cbTipoVeiculo.getSelectedItem(), placa, modelo, ano, cbStatusLocacao.getSelectedItem());
					
					JOptionPane.showMessageDialog(contentPane, "VEÍCULO ADICIONADO COM SUCESSO");
					
					setVisible(false);
					
				}
				catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(contentPane, exception.getMessage());
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR VEÍCULO");
				}
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new GerenteView().setVisible(true);
			}
		});
	}

}
