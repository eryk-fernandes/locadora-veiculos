package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import view.PrincipalView;
import view.SinginView;

public class Main {

	public static void main(String[] args) {
		
		new File("dados").mkdirs();
		
		String[] arquivos = {
				"dados/clientes.json", "dados/veiculos.json", "dados/usuarios.json",
				"dados/locacoes.json", "dados/pagamentos.json"
		};
		
		try {
			for (String arquivo : arquivos) {
				new FileWriter(arquivo, true).close();
			}
			
			FileReader fr = new FileReader("dados/usuarios.json");
			
			if (fr.read() == -1) {
				new SinginView().setVisible(true);
			}
			else {
				new PrincipalView().setVisible(true);
			}
			
			fr.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR PROGRAMA");
		}
		
	}
}
