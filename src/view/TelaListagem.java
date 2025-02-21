package view;

import java.awt.Font;
import javax.swing.JLabel;

public class TelaListagem extends Tela {
	
	private static final long serialVersionUID = 1L;

	public TelaListagem() {
		super();
		
		JLabel label = new JLabel();
		label.setText("Listagem");
		label.setFont(new Font("Helvetica", Font.BOLD, 20));;
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label);
	}

}
