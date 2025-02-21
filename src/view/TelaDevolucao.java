package view;

import java.awt.Font;
import javax.swing.JLabel;

public class TelaDevolucao extends Tela {

	private static final long serialVersionUID = 1L;

	public TelaDevolucao() {
		super();
		
		JLabel label = new JLabel();
		label.setText("Devolução");
		label.setFont(new Font("Helvetica", Font.BOLD, 20));;
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label);
	}

}
