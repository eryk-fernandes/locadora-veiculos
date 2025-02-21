package view;

import javax.swing.JFrame;

public abstract class Tela extends JFrame {

	private static final long serialVersionUID = 1L;

	public Tela() {
		this.setTitle("Locadora");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
	}

}
