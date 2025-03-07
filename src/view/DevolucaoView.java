package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LocacaoController;

public class DevolucaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private LocacaoController locacaoController = new LocacaoController();

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

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(81, 114, 305, 22);
		contentPane.add(comboBox);
		
		adicionarItensComboBox(comboBox);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnNewButton = new JButton("GERAR PAGAMENTO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(137, 257, 161, 23);
		contentPane.add(btnNewButton);
		
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new AtendenteView().setVisible(true);
			}
		});
		
	}
	
	public void adicionarItensComboBox(JComboBox<String> comboBox) {
		for (String s : locacaoController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}
}
