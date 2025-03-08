package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;
import excecoes.UsuarioAdicionadoException;

public class UsuarioView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senha;
	private JTextField usuario;
	private JComboBox<String> tiposUsuario;
	private JButton btnCadastrar;
	private JButton btnVoltar;
	
	private UsuarioController usuarioController = new UsuarioController();

	public UsuarioView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoSenha = new JLabel("SENHA");
		textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		textoSenha.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoSenha.setBounds(0, 191, 454, 14);
		contentPane.add(textoSenha);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoUsuario.setBounds(0, 135, 454, 14);
		contentPane.add(textoUsuario);
		
		JLabel textoTipoUsuario = new JLabel("TIPO DE USUÁRIO");
		textoTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoTipoUsuario.setBounds(0, 78, 454, 14);
		contentPane.add(textoTipoUsuario);
		
		JLabel textoCadastrarUsuario = new JLabel("CADASTRAR USUÁRIO");
		textoCadastrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastrarUsuario.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastrarUsuario.setBounds(0, 11, 444, 43);
		contentPane.add(textoCadastrarUsuario);
		
		senha = new JPasswordField();
		senha.setHorizontalAlignment(SwingConstants.CENTER);
		senha.setBounds(130, 216, 184, 20);
		contentPane.add(senha);
		
		usuario = new JTextField();
		usuario.setHorizontalAlignment(SwingConstants.CENTER);
		usuario.setBounds(130, 160, 184, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		tiposUsuario = new JComboBox<String>();
		tiposUsuario.setBounds(130, 102, 184, 22);
		tiposUsuario.addItem("");
		tiposUsuario.addItem("ADMINISTRADOR");
		tiposUsuario.addItem("GERENTE");
		tiposUsuario.addItem("ATENDENTE");
		contentPane.add(tiposUsuario);
		
		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(130, 257, 184, 23);
		contentPane.add(btnCadastrar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnCadastrar.addActionListener(e -> {
			try {
				usuarioController.cadastrarDados(tiposUsuario.getSelectedItem(), usuario, senha);
					
				JOptionPane.showMessageDialog(contentPane, "USUÁRIO ADICIONADO COM SUCESSO");
			}
			catch (UsuarioAdicionadoException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR USUÁRIO");
			}
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new LoginView().setVisible(true);
		});
		
	}
}
