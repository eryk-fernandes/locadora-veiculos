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

public class AtendenteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLocacao;
	private JButton btnDevolucao;

	public AtendenteView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoAtendente = new JLabel("ATENDENTE");
		textoAtendente.setHorizontalAlignment(SwingConstants.CENTER);
		textoAtendente.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoAtendente.setBounds(139, 31, 149, 34);
		contentPane.add(textoAtendente);
		
		btnLocacao = new JButton("REGISTRAR LOCACAO");
		btnLocacao.setBounds(114, 99, 211, 40);
		contentPane.add(btnLocacao);
		
		btnLocacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					new LocacaoView().setVisible(true);
					
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		
		btnDevolucao = new JButton("REGISTRAR DEVOLUÇÂO");
		btnDevolucao.setBounds(114, 187, 211, 40);
		contentPane.add(btnDevolucao);
		
		btnDevolucao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					new DevolucaoView().setVisible(true);
					
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
	}
}
