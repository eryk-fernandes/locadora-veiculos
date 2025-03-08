package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;

public class GerenciarClientesView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> operacoes;
	private JComboBox<String> clientes;
	private JComboBox<String> opcoes;
	private JButton btnConfirmar;
	private JButton btnVoltar;
	
	private static ClienteController clienteController = new ClienteController();
	private JTextField atributo;

	public GerenciarClientesView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoGerenciar = new JLabel("GERENCIAR CLIENTES");
		textoGerenciar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoGerenciar.setHorizontalAlignment(SwingConstants.CENTER);
		textoGerenciar.setBounds(0, 32, 454, 22);
		contentPane.add(textoGerenciar);
		
		JLabel textoOperacoes = new JLabel("OPERAÇÕES");
		textoOperacoes.setHorizontalAlignment(SwingConstants.CENTER);
		textoOperacoes.setBounds(280, 86, 116, 14);
		contentPane.add(textoOperacoes);
		
		JLabel textoClientes = new JLabel("CLIENTES");
		textoClientes.setHorizontalAlignment(SwingConstants.CENTER);
		textoClientes.setBounds(52, 86, 199, 14);
		contentPane.add(textoClientes);
		
		JLabel textoOpcoes = new JLabel("OPÇÕES");
		textoOpcoes.setHorizontalAlignment(SwingConstants.CENTER);
		textoOpcoes.setBounds(52, 153, 114, 14);
		contentPane.add(textoOpcoes);
		
		atributo = new JTextField();
		atributo.setBounds(195, 179, 199, 20);
		contentPane.add(atributo);
		atributo.setColumns(10);
		
		operacoes = new JComboBox<String>();
		operacoes.setBounds(280, 107, 114, 22);
		operacoes.addItem("");
		operacoes.addItem("EDITAR");
		operacoes.addItem("REMOVER");
		
		contentPane.add(operacoes);
		
		clientes = new JComboBox<String>();
		clientes.setBounds(52, 107, 199, 22);
		
		for (String cliente : clienteController.criarListaCPFs())
			clientes.addItem(cliente);
		
		contentPane.add(clientes);
		
		opcoes = new JComboBox<String>();
		opcoes.setBounds(52, 178, 114, 22);
		opcoes.addItem("");
		opcoes.addItem("NOME");
		opcoes.addItem("TELEFONE");
		opcoes.addItem("EMAIL");
		contentPane.add(opcoes);
		
		btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(52, 245, 159, 23);
		contentPane.add(btnConfirmar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(340, 245, 89, 23);
		contentPane.add(btnVoltar);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnVoltar.addActionListener(e -> {
			setVisible(false);
			new GerenteView().setVisible(true);
		});
	}
}
