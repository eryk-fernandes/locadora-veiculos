package view;

import java.awt.Font;
import javax.swing.JLabel;

public class TelaCadastroVeiculo extends Tela {
	
	private static final long serialVersionUID = 1L;

	public TelaCadastroVeiculo() {
		super();
		
		JLabel label = new JLabel();
		label.setText("Cadastro de Veículo");
		label.setFont(new Font("Helvetica", Font.BOLD, 20));;
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label);
	}

}
