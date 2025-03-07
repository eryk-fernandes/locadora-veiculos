package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.LocacaoController;
import controller.VeiculoController;

public class LocacaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ClienteController clienteController = new ClienteController();
	private VeiculoController veiculoController = new VeiculoController();
	private LocacaoController locacaoController = new LocacaoController();

	public LocacaoView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocacao = new JLabel("CADASTRO DE LOCAÇÃO");
		textoLocacao.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocacao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoLocacao.setBounds(95, 22, 242, 23);
		contentPane.add(textoLocacao);
		
		JLabel textoVeiculos = new JLabel("VEÍCULOS");
		textoVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
		textoVeiculos.setBounds(153, 124, 121, 14); 
		contentPane.add(textoVeiculos);
		
		JLabel textoClientes = new JLabel("CLIENTES");
		textoClientes.setHorizontalAlignment(SwingConstants.CENTER);
		textoClientes.setBounds(153, 66, 121, 14);
		contentPane.add(textoClientes);
		
		JLabel textoData = new JLabel("DATA DE DEVOLUÇÃO");
		textoData.setHorizontalAlignment(SwingConstants.CENTER);
		textoData.setBounds(153, 182, 121, 14);
		contentPane.add(textoData);
		
		JFormattedTextField devolucao = new JFormattedTextField();
		devolucao.setHorizontalAlignment(SwingConstants.CENTER);
		devolucao.setBounds(153, 209, 121, 20);
		contentPane.add(devolucao);
		
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("##/##/####");
			mask.install(devolucao);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JComboBox<String> cbClientes = new JComboBox<String>();
		cbClientes.setBounds(153, 91, 121, 22);
		contentPane.add(cbClientes);
		
		adicionarItensClientes(cbClientes);
		
		JComboBox<String> cbVeiculos = new JComboBox<String>();
		cbVeiculos.setBounds(153, 149, 121, 22);
		contentPane.add(cbVeiculos);
		
		adicionarItensVeiculos(cbVeiculos);
		
		JButton btnCadastrar = new JButton("CADASTRAR LOCAÇÃO");
		btnCadastrar.setBounds(141, 253, 167, 27);
		contentPane.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					
					locacaoController.cadastrarDados(cbClientes.getSelectedItem(), cbVeiculos.getSelectedItem(), devolucao);
					
					JOptionPane.showMessageDialog(contentPane, "LOCAÇÃO ADICIONADO COM SUCESSO");
					
					setVisible(false);
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPane, "ERRO AO ADICIONAR LOCAÇÃO");
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
				new AtendenteView().setVisible(true);
			}
		});
		
	}
	
	public void adicionarItensClientes(JComboBox<String> comboBox) {
		for (String s : clienteController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}
	
	public void adicionarItensVeiculos(JComboBox<String> comboBox) {
		for (String s : veiculoController.recuperarTodosComboBox()) {
			comboBox.addItem(s);
		}
	}
}
