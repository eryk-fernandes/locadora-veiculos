package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;

public class CadastrarClienteView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField telefone;
	private JTextField email;
	private JTextField cpf;
	private JButton btnCadastrar;
	private JButton btnVoltar;
	
	private static ClienteController clienteController = new ClienteController();

	public CadastrarClienteView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoCadastro = new JLabel("CADASTRAR CLIENTE");
		textoCadastro.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastro.setBounds(0, 30, 454, 38);
		contentPane.add(textoCadastro);
		
		JLabel textoNome = new JLabel("NOME");
		textoNome.setHorizontalAlignment(SwingConstants.CENTER);
		textoNome.setBounds(29, 94, 184, 14);
		contentPane.add(textoNome);
		
		JLabel textoCpf = new JLabel("CPF");
		textoCpf.setHorizontalAlignment(SwingConstants.CENTER);
		textoCpf.setBounds(29, 160, 184, 14);
		contentPane.add(textoCpf);
		
		JLabel textoTelefone = new JLabel("TELEFONE");
		textoTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		textoTelefone.setBounds(235, 94, 184, 14);
		contentPane.add(textoTelefone);
		
		JLabel textoEmail = new JLabel("EMAIL");
		textoEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textoEmail.setBounds(235, 160, 184, 14);
		contentPane.add(textoEmail);
		
		nome = new JTextField();
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setBounds(29, 119, 184, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		telefone = new JTextField();
		telefone.setHorizontalAlignment(SwingConstants.CENTER);
		telefone.setColumns(10);
		telefone.setBounds(245, 119, 184, 20);
		contentPane.add(telefone);
		
		email = new JTextField();
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setColumns(10);
		email.setBounds(245, 185, 184, 20);
		contentPane.add(email);
		
		cpf = new JTextField();
		cpf.setHorizontalAlignment(SwingConstants.CENTER);
		cpf.setBounds(29, 185, 184, 20);
		contentPane.add(cpf);
		
		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(130, 245, 185, 23);
		contentPane.add(btnCadastrar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(340, 245, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}
	
	@Override
	public void botaoListener() {
		
		btnCadastrar.addActionListener(e -> {
			try {
				clienteController.cadastrarDados(nome, cpf, telefone, email);
					
				JOptionPane.showMessageDialog(contentPane, "CLIENTE ADICIONADO COM SUCESSO");
			}
			catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			}
			catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR CLIENTE");
			}
		});
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
		});
	}
	
}
