package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import excecoes.UsuarioJaAdicionadoException;

public class UsuarioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senha;
	private JTextField usuario;
	private String tipo;
	
	private UsuarioController usuarioController = new UsuarioController();

	public UsuarioView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoSenha = new JLabel("SENHA");
		textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		textoSenha.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoSenha.setBounds(232, 173, 184, 14);
		contentPane.add(textoSenha);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoUsuario.setBounds(232, 96, 184, 14);
		contentPane.add(textoUsuario);
		
		JLabel textoTipoUsuario = new JLabel("TIPO DE USUÁRIO");
		textoTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoTipoUsuario.setBounds(38, 96, 184, 14);
		contentPane.add(textoTipoUsuario);
		
		JLabel textoCadastrarUsuario = new JLabel("CADASTRAR USUÁRIO");
		textoCadastrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastrarUsuario.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastrarUsuario.setBounds(108, 24, 244, 43);
		contentPane.add(textoCadastrarUsuario);
		
		senha = new JPasswordField();
		senha.setBounds(232, 198, 184, 20);
		contentPane.add(senha);
		
		usuario = new JTextField();
		usuario.setBounds(232, 133, 184, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JButton btnAdministrador = new JButton("ADMINISTRADOR");
		btnAdministrador.setBounds(63, 132, 135, 23);
		contentPane.add(btnAdministrador);
		
		btnAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "Administrador";
			}
		});
		
		JButton btnGerente = new JButton("GERENTE");
		btnGerente.setBounds(63, 164, 135, 23);
		contentPane.add(btnGerente);
		
		btnGerente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "Gerente";
			}
		});
		
		JButton btnAtendente = new JButton("ATENDENTE");
		btnAtendente.setBounds(63, 197, 135, 23);
		contentPane.add(btnAtendente);
		
		btnAtendente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				tipo = "Atendente";
			}
		});
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(130, 255, 184, 23);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					usuarioController.cadastrarDados(tipo, usuario, senha);
					
					JOptionPane.showMessageDialog(contentPane, "USUÁRIO ADICIONADO COM SUCESSO");
					
					tipo = null;
				}
				catch (UsuarioJaAdicionadoException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
				}
				catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR USUÁRIO");
				}
			}
		});
	}
}
