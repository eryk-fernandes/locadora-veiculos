package view;

import java.awt.Font;
import javax.swing.JLabel;

public class TelaPagamentos extends Tela {

	private static final long serialVersionUID = 1L;

	public TelaPagamentos() {
		super();
		
		JLabel label = new JLabel();
		label.setText("Pagamento");
		label.setFont(new Font("Helvetica", Font.BOLD, 20));;
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label);
	}

}
