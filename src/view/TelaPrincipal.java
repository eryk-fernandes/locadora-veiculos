package view;

import java.awt.Font;
import javax.swing.JLabel;

public class TelaPrincipal extends Tela {
	
	private static final long serialVersionUID = 1L;

	public TelaPrincipal() {
		super();
		
		JLabel label = new JLabel();
		label.setText("Locadora de Veículos");
		label.setFont(new Font("Helvetica", Font.BOLD, 20));;
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label);
	}
}
