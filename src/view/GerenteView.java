package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import service.GeradorPDF;

public class GerenteView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRemover;
	private JButton btnGerenciar;
	private JButton btnCadastrar;
	private JButton btnVeiculo;
	private JButton btnRelatorio;
	private JButton btnVoltar;

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
		textoGerente.setBounds(0, 28, 454, 23);
		contentPane.add(textoGerente);
		
		btnCadastrar = new JButton("CADASTRAR CLIENTE");
		btnCadastrar.setBounds(123, 62, 214, 29);
		contentPane.add(btnCadastrar);
		
		btnVeiculo = new JButton("CADASTRAR VEÍCULO");
		btnVeiculo.setBounds(123, 142, 214, 29);
		contentPane.add(btnVeiculo);
		
		btnRelatorio = new JButton("GERAR RELATÓRIO");
		btnRelatorio.setBounds(123, 217, 214, 29);
		contentPane.add(btnRelatorio);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnGerenciar = new JButton("GERENCIAR CLIENTES");
		btnGerenciar.setBounds(123, 102, 214, 29);
		contentPane.add(btnGerenciar);
		
		btnRemover = new JButton("REMOVER VEÍCULO");
		btnRemover.setBounds(123, 180, 214, 29);
		contentPane.add(btnRemover);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnCadastrar.addActionListener(e -> {
			new CadastrarClienteView().setVisible(true);;
		});
		
		btnVeiculo.addActionListener(e -> {
			new CadastrarVeiculoView().setVisible(true);;
		});
		
		btnRemover.addActionListener(e -> {
			new RemoverVeiculoView().setVisible(true);;
		});
		
		btnGerenciar.addActionListener(e -> {
			new GerenciarClientesView().setVisible(true);;
		});
		
		btnRelatorio.addActionListener(e -> {
			GeradorPDF gerador = new GeradorPDF();

			try {
				gerador.gerarRelatorioVeiculosLocados();
				gerador.gerarRelatorioLocacoes();
				gerador.gerarRelatorioFaturamentos();
				
				JOptionPane.showMessageDialog(contentPane, "RELATÓRIOS GERADOS COM SUCESSO NA PASTA \"dados/relatorios\"");
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO GERAR RELATÓRIO");
			}
			
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new LoginView().setVisible(true);
		});
	}
	
	
}
