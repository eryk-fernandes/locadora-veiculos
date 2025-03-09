package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

public class SinginView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeUsuario;
	private JPasswordField senha;
	private JButton btnSignin;
	
	private static UsuarioController usuarioController = new UsuarioController();

	public SinginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoSingin = new JLabel("SIGN IN ADMINISTRADOR");
		textoSingin.setHorizontalAlignment(SwingConstants.CENTER);
		textoSingin.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoSingin.setBounds(97, 26, 255, 28);
		contentPane.add(textoSingin);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoUsuario.setBounds(97, 84, 255, 12);
		contentPane.add(textoUsuario);
		
		JLabel textoSenha = new JLabel("SENHA");
		textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		textoSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoSenha.setBounds(97, 151, 255, 12);
		contentPane.add(textoSenha);
		
		nomeUsuario = new JTextField();
		nomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		nomeUsuario.setBounds(133, 107, 192, 20);
		contentPane.add(nomeUsuario);
		nomeUsuario.setColumns(10);
		
		senha = new JPasswordField();
		senha.setHorizontalAlignment(SwingConstants.CENTER);
		senha.setBounds(133, 174, 192, 20);
		contentPane.add(senha);
		
		btnSignin = new JButton("SING IN");
		btnSignin.setBounds(164, 226, 127, 23);
		contentPane.add(btnSignin);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnSignin.addActionListener(e -> {
			try {
				usuarioController.cadastrarDados("ADMINISTRADOR", nomeUsuario, senha);
					
				JOptionPane.showMessageDialog(contentPane, "ADMINISTRADOR ADICIONADO COM SUCESSO");
					
				setVisible(false);
				new PrincipalView().setVisible(true);
					
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO EFETUAR SIGN IN");
			}
		});
	}
}
