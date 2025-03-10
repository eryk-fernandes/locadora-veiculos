package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PrincipalView extends JFrame implements BotaoListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLogin;

	public PrincipalView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 470, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textoLocadora = new JLabel("LOCADORA DE VEÍCULOS");
		textoLocadora.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textoLocadora.setHorizontalAlignment(SwingConstants.CENTER);
		textoLocadora.setBounds(0, 77, 454, 32);
		contentPane.add(textoLocadora);
		
		JLabel textoAutoCordeiros = new JLabel("AUTO CORDEIROS");
		textoAutoCordeiros.setHorizontalAlignment(SwingConstants.CENTER);
		textoAutoCordeiros.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textoAutoCordeiros.setBounds(0, 34, 454, 32);
		contentPane.add(textoAutoCordeiros);
		
		JLabel textoCarro = new JLabel("CARRO: R$ 100,00");
		textoCarro.setHorizontalAlignment(SwingConstants.CENTER);
		textoCarro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textoCarro.setBounds(143, 181, 156, 32);
		contentPane.add(textoCarro);
		
		JLabel textoMoto = new JLabel("MOTO: R$ 60,00");
		textoMoto.setHorizontalAlignment(SwingConstants.CENTER);
		textoMoto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textoMoto.setBounds(143, 208, 156, 32);
		contentPane.add(textoMoto);
		
		JLabel textoCaminhao = new JLabel("CAMINHÃO: R$ 200,00");
		textoCaminhao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textoCaminhao.setBounds(143, 237, 156, 32);
		contentPane.add(textoCaminhao);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(143, 136, 156, 23);
		contentPane.add(btnLogin);
		
		botaoListener();
	}

	@Override
	public void botaoListener() {
		
		btnLogin.addActionListener(e -> {
			setVisible(false);
			new LoginView().setVisible(true);
		});
	}
}
