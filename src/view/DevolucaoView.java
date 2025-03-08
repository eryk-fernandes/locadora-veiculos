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

import controller.LocacaoController;
import controller.PagamentoController;

public class DevolucaoView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField dataPagamento;
	private JComboBox<String> locacoes;
	private JComboBox<String> metodos;
	private JButton btnPagamento;
	private JButton btnVoltar;
	
	private static LocacaoController locacaoController = new LocacaoController();
	private static PagamentoController pagamentoController = new PagamentoController();

	public DevolucaoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoDevolucao = new JLabel("DEVOLUÇÃO");
		textoDevolucao.setHorizontalAlignment(SwingConstants.CENTER);
		textoDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoDevolucao.setBounds(151, 21, 138, 32);
		contentPane.add(textoDevolucao);
		
		JLabel textoLocacoes = new JLabel("LOCAÇÕES");
		textoLocacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacoes.setBounds(81, 89, 305, 14);
		contentPane.add(textoLocacoes);
		
		JLabel textoData = new JLabel("DATA DE PAGAMENTO");
		textoData.setHorizontalAlignment(SwingConstants.CENTER);
		textoData.setBounds(81, 173, 138, 14);
		contentPane.add(textoData);

		JLabel textoMetodoPagamento = new JLabel("MÉTODO DE PAGAMENTO");
		textoMetodoPagamento.setHorizontalAlignment(SwingConstants.CENTER);
		textoMetodoPagamento.setBounds(244, 173, 153, 14);
		contentPane.add(textoMetodoPagamento);
		
		dataPagamento = new JFormattedTextField();
		dataPagamento.setHorizontalAlignment(SwingConstants.CENTER);
		dataPagamento.setBounds(81, 201, 138, 20);
		
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("##/##/####");
			mask.install(dataPagamento);
		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		contentPane.add(dataPagamento);

		locacoes = new JComboBox<String>();
		locacoes.setBounds(81, 114, 305, 22);
		contentPane.add(locacoes);
		
		locacoes.addItem("");
		for (String locacao : locacaoController.recuperarTodosComboBox()) {
			locacoes.addItem(locacao);
		}
		
		metodos = new JComboBox<String>();
		metodos.setBounds(258, 200, 128, 22);
		
		metodos.addItem("");
		metodos.addItem("DINHEIRO");
		metodos.addItem("BOLETO");
		metodos.addItem("DÉBITO");
		metodos.addItem("CRÉDITO");
		metodos.addItem("PIX");
		
		contentPane.add(metodos);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnPagamento = new JButton("GERAR PAGAMENTO");
		btnPagamento.setBounds(137, 257, 161, 23);
		contentPane.add(btnPagamento);
		
		botaoListener();
	}
	
	@Override
	public void botaoListener() {
		
		btnPagamento.addActionListener(e -> {;
			try {
				
				double locacao = pagamentoController.resgatarCustoLocacao(locacoes.getSelectedItem());
				double multa = pagamentoController.calcularMulta(locacoes.getSelectedItem(), dataPagamento);
				double total = pagamentoController.calcularTotal(locacoes.getSelectedItem(), dataPagamento);
				
				int opcao = JOptionPane.showConfirmDialog(
						contentPane,
						String.format("LOCAÇÃO: R$ %.2f\nMULTA: R% %.2f\nTOTAL: R% %.2f", locacao, multa, total)
				);
				
				if (opcao == 0) {
					pagamentoController.cadastrarDados(
							locacoes.getSelectedItem(), 
							metodos.getSelectedItem(),
							dataPagamento
					);

					JOptionPane.showMessageDialog(contentPane, "PAGAMENTO EFETUADO COM SUCESSO");
				}

			}
			catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR PAGAMENTO");
			}
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new AtendenteView().setVisible(true);
		});
	}
}
