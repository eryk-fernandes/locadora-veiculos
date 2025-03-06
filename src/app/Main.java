package app;

import java.io.FileReader;
import java.io.IOException;

import view.LoginView;
import view.SinginView;

public class Main {

	public static void main(String[] args) {
		
		try (FileReader fr = new FileReader("src/json/usuarios.json")) {
			if (fr.read() == -1) {
				new SinginView().setVisible(true);
			}
			else {
				new LoginView().setVisible(true);
			}
		}
		catch (IOException e) {
			
		}
		
	}
}
 