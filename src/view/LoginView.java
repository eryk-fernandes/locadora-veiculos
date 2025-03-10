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
import exception.SenhaIncorretaException;
import exception.UsuarioNaoEncontradoException;

public class LoginView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeUsuario;
	private JPasswordField senha;
	private JButton btnEntrar;
	private JButton btnVoltar;
	
	private UsuarioController usuarioController = new UsuarioController();

	public LoginView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocadora = new JLabel("LOCADORA DE VEÍCULOS");
		textoLocadora.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocadora.setFont(new Font("Verdana", Font.PLAIN, 20));
		textoLocadora.setBounds(0, 11, 454, 20);
		contentPane.add(textoLocadora);
		
		JLabel textoLogin = new JLabel("LOGIN DE USUÁRIOS");
		textoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		textoLogin.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoLogin.setBounds(0, 52, 454, 20);
		contentPane.add(textoLogin);
		
		JLabel textoSenha = new JLabel("SENHA");
		textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		textoSenha.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoSenha.setBounds(0, 158, 454, 20);
		contentPane.add(textoSenha);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoUsuario.setBounds(0, 96, 454, 20);
		contentPane.add(textoUsuario);
		
		nomeUsuario = new JTextField();
		nomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		nomeUsuario.setBounds(136, 127, 180, 20);
		contentPane.add(nomeUsuario);
		nomeUsuario.setColumns(10);
		
		senha = new JPasswordField();
		senha.setHorizontalAlignment(SwingConstants.CENTER);
		senha.setBounds(136, 189, 180, 20);
		senha.setColumns(10);
		contentPane.add(senha);
		
		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBounds(136, 231, 180, 23);
		contentPane.add(btnEntrar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
		
	}

	@Override
	public void botaoListener() {
		
		btnEntrar.addActionListener(e -> {
			try {
				usuarioController.verificarUsuario(nomeUsuario, senha);
					
				JOptionPane.showMessageDialog(contentPane, "USUÁRIO E SENHA CORRETOS");
					
				String tipo = usuarioController.retornarTipoUsuario(nomeUsuario);
					
				if (tipo.equals("Administrador")) {
					new UsuarioView().setVisible(true);;
				}
				else if (tipo.equals("Gerente")) {
					new GerenteView().setVisible(true);
				}
				else {
					new AtendenteView().setVisible(true);
				}
					
				setVisible(false);
			}
			catch (UsuarioNaoEncontradoException | SenhaIncorretaException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO EFETUAR LOGIN");
			} 
			
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new PrincipalView().setVisible(true);
		});
		
	}

}
