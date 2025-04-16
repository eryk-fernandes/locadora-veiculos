package org.example.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.example.controller.LocacaoController;

public class VisualizarLocacoesView extends JFrame implements BotaoListener {

    private final JButton btnVoltar;

    public VisualizarLocacoesView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
        JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocacoes = new JLabel("VISUALIZAR LOCAÇÕES");
		textoLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacoes.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoLocacoes.setBounds(0, 11, 454, 34);
		contentPane.add(textoLocacoes);

        JComboBox<String> locacoes = new JComboBox<>();
		locacoes.setBounds(45, 66, 360, 22);
		contentPane.add(locacoes);
		
		try {
            LocacaoController locacaoController = new LocacaoController();
            for (String locacao : locacaoController.recuperarStringLocacoes()) {
				locacoes.addItem(locacao);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO AO RECUPERAR LOCAÇÕES");
		}
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(179, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}
	
	@Override
	public void botaoListener() {
		btnVoltar.addActionListener(e -> setVisible(false));
	}
}
