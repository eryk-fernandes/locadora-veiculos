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

public class SinginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeUsuario;
	private JPasswordField senha;
	private JButton btnSignin;
	
	private UsuarioController usuarioController = new UsuarioController();

	public SinginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIGN IN ADMINISTRADOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(97, 26, 255, 28);
		contentPane.add(lblNewLabel);
		
		nomeUsuario = new JTextField();
		nomeUsuario.setBounds(133, 107, 192, 20);
		contentPane.add(nomeUsuario);
		nomeUsuario.setColumns(10);
		
		JLabel textoUsuario = new JLabel("USUÁRIO");
		textoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoUsuario.setBounds(97, 84, 255, 12);
		contentPane.add(textoUsuario);
		
		senha = new JPasswordField();
		senha.setBounds(133, 183, 192, 20);
		contentPane.add(senha);
		
		JLabel textoSenha = new JLabel("SENHA");
		textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		textoSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoSenha.setBounds(97, 151, 255, 12);
		contentPane.add(textoSenha);
		
		btnSignin = new JButton("SING IN");
		btnSignin.setBounds(164, 226, 127, 23);
		contentPane.add(btnSignin);
		
		btnSignin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					usuarioController.cadastrarDados("Administrador", nomeUsuario, senha);
					
					JOptionPane.showMessageDialog(contentPane, "ADMINISTRADOR ADICIONADO COM SUCESSO");
					
					setVisible(false);
					new LoginView().setVisible(true);
					
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
	}
}
