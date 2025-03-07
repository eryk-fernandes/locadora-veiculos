package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GerenteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCliente;
	private JButton btnVeiculo;
	private JButton btnRelatorio;

	public GerenteView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoGerente = new JLabel("GERENTE");
		textoGerente.setHorizontalAlignment(SwingConstants.CENTER);
		textoGerente.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoGerente.setBounds(112, 28, 214, 23);
		contentPane.add(textoGerente);
		
		btnCliente = new JButton("CADASTRAR CLIENTE");
		btnCliente.setBounds(112, 93, 214, 29);
		contentPane.add(btnCliente);
		
		btnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				new ClienteView().setVisible(true);;
			}
		});

		
		btnVeiculo = new JButton("CADASTRAR VEÍCULO");
		btnVeiculo.setBounds(112, 149, 214, 29);
		contentPane.add(btnVeiculo);

		btnVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				new VeiculoView().setVisible(true);;
			}
		});
		
		btnRelatorio = new JButton("VISUALIZAR RELATÓRIO");
		btnRelatorio.setBounds(112, 206, 214, 29);
		contentPane.add(btnRelatorio);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new LoginView().setVisible(true);
			}
		});
	}
}
