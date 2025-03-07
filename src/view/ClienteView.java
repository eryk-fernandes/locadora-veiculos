package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import excecoes.TamanhoInvalidoException;

public class ClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField cpf;
	private JTextField telefone;
	private JTextField email;
	
	private ClienteController clienteController = new ClienteController();

	public ClienteView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoCadastro = new JLabel("CADASTRO DE CLIENTE");
		textoCadastro.setFont(new Font("Verdana", Font.PLAIN, 18));
		textoCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		textoCadastro.setBounds(117, 38, 223, 38);
		contentPane.add(textoCadastro);
		
		JLabel textoNome = new JLabel("NOME");
		textoNome.setHorizontalAlignment(SwingConstants.CENTER);
		textoNome.setBounds(29, 103, 184, 14);
		contentPane.add(textoNome);
		
		JLabel textoCpf = new JLabel("CPF");
		textoCpf.setHorizontalAlignment(SwingConstants.CENTER);
		textoCpf.setBounds(29, 157, 184, 14);
		contentPane.add(textoCpf);
		
		JLabel textoTelefone = new JLabel("TELEFONE");
		textoTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		textoTelefone.setBounds(235, 103, 184, 14);
		contentPane.add(textoTelefone);
		
		JLabel textoEmail = new JLabel("EMAIL");
		textoEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textoEmail.setBounds(235, 157, 184, 14);
		contentPane.add(textoEmail);
		
		nome = new JTextField();
		nome.setBounds(29, 126, 184, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		cpf = new JTextField();
		cpf.setColumns(10);
		cpf.setBounds(29, 182, 184, 20);
		contentPane.add(cpf);
		
		telefone = new JTextField();
		telefone.setColumns(10);
		telefone.setBounds(235, 126, 184, 20);
		contentPane.add(telefone);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(235, 182, 184, 20);
		contentPane.add(email);
		
		JButton btnCadastrar = new JButton("CADASTRAR CLIENTE");
		btnCadastrar.setBounds(131, 232, 185, 23);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					clienteController.cadastrarDados(nome, cpf, telefone, email);
					
					JOptionPane.showMessageDialog(contentPane, "CLIENTE ADICIONADO COM SUCESSO");
					
					setVisible(false);
					
				} 
				catch (TamanhoInvalidoException exception) {
					JOptionPane.showMessageDialog(contentPane, exception.getMessage());
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR CLIENTE");
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
				new GerenteView().setVisible(true);
			}
		});
		
	}

}
