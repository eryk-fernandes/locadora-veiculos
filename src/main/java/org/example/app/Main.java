package org.example.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.example.view.PrincipalView;
import org.example.view.SinginView;
 
public class Main {

	public static void main(String[] args) {

		try {
			File file = new File("dados/json");

			if (!file.mkdirs()) {
				throw new Exception();
			}

			file = new File("dados/relatorios");

			if (!file.mkdir()) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR PROGRAMA");
		}
		
		String[] arquivos = {
				"dados/json/clientes.json", "dados/json/veiculos.json", "dados/json/usuarios.json",
				"dados/json/locacoes.json", "dados/json/pagamentos.json"
		};
		
		try {
			for (String arquivo : arquivos) {
				new FileWriter(arquivo, true).close();
			}
			
			FileReader fr = new FileReader("dados/json/usuarios.json");
			
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
