package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AtendenteView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLocacao;
	private JButton btnDevolucao;
	private JButton btnVerLocacoes;
	private JButton btnVoltar;

	public AtendenteView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoAtendente = new JLabel("ATENDENTE");
		textoAtendente.setHorizontalAlignment(SwingConstants.CENTER);
		textoAtendente.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoAtendente.setBounds(0, 31, 454, 34);
		contentPane.add(textoAtendente);
		
		btnLocacao = new JButton("REGISTRAR LOCACAO");
		btnLocacao.setBounds(114, 86, 211, 40);
		contentPane.add(btnLocacao);
		
		btnDevolucao = new JButton("REGISTRAR DEVOLUÇÂO");
		btnDevolucao.setBounds(114, 137, 211, 40);
		contentPane.add(btnDevolucao);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnVerLocacoes = new JButton("VISUALIZAR LOCAÇÕES");
		btnVerLocacoes.setBounds(114, 188, 211, 40);
		contentPane.add(btnVerLocacoes);
	
		botaoListener();
	}
	
	@Override
	public void botaoListener() {
		
		btnLocacao.addActionListener(e -> {
			new LocacaoView().setVisible(true);
		});
		
		btnDevolucao.addActionListener(e -> {
			new DevolucaoView().setVisible(true);
		});
		
		btnVerLocacoes.addActionListener(e -> {
			setVisible(false);
			new VisualizarLocacoesView().setVisible(true);
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new LoginView().setVisible(true);
		});
	}
}
