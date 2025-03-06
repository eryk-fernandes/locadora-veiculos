package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CadastroUsuariosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senha;
	private JTextField usuario;

	public CadastroUsuariosView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ADMINISTRADOR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(63, 161, 135, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ATENDENTE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(63, 127, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("GERENTE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(63, 197, 135, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("CADASTRAR USUÁRIO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(108, 24, 244, 43);
		contentPane.add(lblNewLabel);
		
		senha = new JPasswordField();
		senha.setBounds(232, 198, 184, 20);
		contentPane.add(senha);
		
		usuario = new JTextField();
		usuario.setBounds(232, 133, 184, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("SENHA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(232, 173, 184, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("USUÁRIO");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(232, 96, 184, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton_3 = new JButton("CADASTRAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(130, 255, 184, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("TIPO DE USUÁRIO");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(38, 96, 184, 14);
		contentPane.add(lblNewLabel_1_1_1);
	}
}
