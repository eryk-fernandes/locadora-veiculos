package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import excecoes.UsuarioJaAdicionadoException;

public class CadastrarUsuarioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senha;
	private JTextField usuario;
	
	private UsuarioController usuarioController = new UsuarioController();

	public CadastrarUsuarioView() {
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
		textoSenha.setBounds(130, 191, 184, 14);
		contentPane.add(textoSenha);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoUsuario.setBounds(130, 135, 184, 14);
		contentPane.add(textoUsuario);
		
		JLabel textoTipoUsuario = new JLabel("TIPO DE USUÁRIO");
		textoTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textoTipoUsuario.setBounds(130, 78, 184, 14);
		contentPane.add(textoTipoUsuario);
		
		JLabel textoCadastrarUsuario = new JLabel("CADASTRAR USUÁRIO");
		textoCadastrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastrarUsuario.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastrarUsuario.setBounds(99, 11, 244, 43);
		contentPane.add(textoCadastrarUsuario);
		
		senha = new JPasswordField();
		senha.setBounds(130, 216, 184, 20);
		contentPane.add(senha);
		
		usuario = new JTextField();
		usuario.setBounds(130, 160, 184, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(130, 257, 184, 23);
		contentPane.add(btnCadastrar);
		
		JComboBox<String> cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBounds(130, 102, 184, 22);
		contentPane.add(cbTipoUsuario);
		
		cbTipoUsuario.addItem("");
		cbTipoUsuario.addItem("Administrador");
		cbTipoUsuario.addItem("Gerente");
		cbTipoUsuario.addItem("Atendente");
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					usuarioController.cadastrarDados(cbTipoUsuario.getSelectedItem(), usuario, senha);
					
					JOptionPane.showMessageDialog(contentPane, "USUÁRIO ADICIONADO COM SUCESSO");
				}
				catch (UsuarioJaAdicionadoException exception) {
					JOptionPane.showMessageDialog(contentPane, exception.getMessage());
				}
				catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(contentPane, exception.getMessage());
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR USUÁRIO");
				}
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(339, 257, 89, 23);
		contentPane.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new AdministradorView().setVisible(true);
			}
		});
	}
}
