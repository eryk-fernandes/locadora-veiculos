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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoGerente = new JLabel("GERENTE");
		textoGerente.setHorizontalAlignment(SwingConstants.CENTER);
		textoGerente.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoGerente.setBounds(112, 28, 214, 23);
		contentPane.add(textoGerente);
		
		btnCliente = new JButton("CADASTRAR CLIENTE");
		btnCliente.setBounds(112, 93, 214, 29);
		contentPane.add(btnCliente);
		
		btnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				new CadastroClienteView().setVisible(true);;
			}
		});

		
		btnVeiculo = new JButton("CADASTRAR VEÍCULO");
		btnVeiculo.setBounds(112, 149, 214, 29);
		contentPane.add(btnVeiculo);

		btnVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				new CadastroVeiculoView().setVisible(true);;
			}
		});
		
		btnRelatorio = new JButton("VISUALIZAR RELATÓRIO");
		btnRelatorio.setBounds(112, 206, 214, 29);
		contentPane.add(btnRelatorio);
	}
}
