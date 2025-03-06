package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VeiculoController;

public class CadastroVeiculoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField placa;
	private JTextField modelo;
	private JTextField ano;
	private String tipo;
	private String status;
	private JButton btnCadastrar;
	private JButton btnLocado;
	private JButton btnDisponivel;
	private JButton btnCarro;
	private JButton btnMoto;
	private JButton btnCaminhao;
	
	private VeiculoController veiculoController = new VeiculoController();

	public CadastroVeiculoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoCadastro = new JLabel("CADASTRO DE VEÍCULO");
		textoCadastro.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastro.setBounds(119, 11, 223, 38);
		contentPane.add(textoCadastro);
		
		placa = new JTextField();
		placa.setBounds(29, 85, 184, 20);
		contentPane.add(placa);
		placa.setColumns(10);
		
		modelo = new JTextField();
		modelo.setColumns(10);
		modelo.setBounds(29, 141, 184, 20);
		contentPane.add(modelo);
		
		ano = new JTextField();
		ano.setColumns(10);
		ano.setBounds(29, 207, 184, 20);
		contentPane.add(ano);
		
		JLabel textoPlaca = new JLabel("PLACA");
		textoPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textoPlaca.setBounds(29, 60, 184, 14);
		contentPane.add(textoPlaca);
		
		JLabel textoModelo = new JLabel("MODELO");
		textoModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textoModelo.setBounds(29, 116, 184, 14);
		contentPane.add(textoModelo);
		
		JLabel textoAno = new JLabel("ANO");
		textoAno.setHorizontalAlignment(SwingConstants.CENTER);
		textoAno.setBounds(29, 182, 184, 14);
		contentPane.add(textoAno);
		
		JLabel textoLocacao = new JLabel("STATUS DE LOCAÇÃO");
		textoLocacao.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacao.setBounds(233, 182, 184, 14);
		contentPane.add(textoLocacao);
		
		JLabel tipoDeVeiculo = new JLabel("TIPO DE VEÍCULO");
		tipoDeVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tipoDeVeiculo.setBounds(233, 60, 184, 14);
		contentPane.add(tipoDeVeiculo);

		btnDisponivel = new JButton("DISPONÍVEL");
		btnDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnDisponivel.setBounds(233, 207, 89, 23);
		contentPane.add(btnDisponivel);
		
		btnDisponivel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				status = "DISPONIVEL";
			}
		});
		
		btnLocado = new JButton("LOCADO");
		btnLocado.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnLocado.setBounds(331, 207, 89, 23);
		contentPane.add(btnLocado);
		
		btnLocado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				status = "LOCADO";
			}
		});
		
		btnCadastrar = new JButton("CADASTRAR VEÍCULO");
		btnCadastrar.setBounds(130, 257, 192, 23);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					veiculoController.cadastrarDados(tipo, placa.getText(), modelo.getText(), ano.getText(), status);
					
					JOptionPane.showMessageDialog(contentPane, "VEÍCULO ADICIONADO COM SUCESSO");
					
					setVisible(false);
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(contentPane, "ARGUMENTO INVÁLIDO");
				}
			}
		});
		
		btnCarro = new JButton("CARRO");
		btnCarro.setBounds(233, 84, 184, 23);
		contentPane.add(btnCarro);
		
		btnCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "CARRO";
			}
		});
		
		btnMoto = new JButton("MOTO");
		btnMoto.setBounds(233, 112, 184, 23);
		contentPane.add(btnMoto);
		
		btnMoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "MOTO";
			}
		});
		
		btnCaminhao = new JButton("CAMINHÃO");
		btnCaminhao.setBounds(233, 140, 184, 23);
		contentPane.add(btnCaminhao);
		
		btnCaminhao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "CAMINHAO";
			}
		});
	}

}
