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

public class AdministradorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdministradorView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoAdministrador = new JLabel("ADMINISTRADOR");
		textoAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		textoAdministrador.setBounds(0, 22, 454, 25);
		contentPane.add(textoAdministrador);
		
		JButton btnCadastrar = new JButton("CADASTRAR USUÁRIO");
		btnCadastrar.setBounds(140, 94, 181, 34);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new CadastrarUsuarioView().setVisible(true);
			}
		});
		
		JButton btnGerenciar = new JButton("GERENCIAR USUÁRIOS");
		btnGerenciar.setBounds(140, 164, 181, 34);
		contentPane.add(btnGerenciar);
		
		btnGerenciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new GerenciarUsuarioView().setVisible(true);
			}
		});
		
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
