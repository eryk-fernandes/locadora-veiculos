package view;

import java.awt.Font;

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
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoDevolucao = new JLabel("DEVOLUÇÃO");
		textoDevolucao.setHorizontalAlignment(SwingConstants.CENTER);
		textoDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoDevolucao.setBounds(160, 25, 138, 32);
		contentPane.add(textoDevolucao);
		
		JLabel textoLocacoes = new JLabel("LOCAÇÕES");
		textoLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacoes.setBounds(53, 68, 86, 14);
		contentPane.add(textoLocacoes);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(34, 88, 138, 22);
		contentPane.add(comboBox);
		
		adicionarItensComboBox(comboBox);
		
	}
	
	public void adicionarItensComboBox(JComboBox<String> comboBox) {
		for (String s : locacaoController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}

}
