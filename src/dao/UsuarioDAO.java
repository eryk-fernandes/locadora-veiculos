package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Usuario;

public class UsuarioDAO implements Persistencia<Usuario, String> {
	
	private static final String CAMINHO_JSON = "dados/usuarios.json";
	
	@Override
	public Usuario recuperar(String nomeUsuario) throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}
		
		for (Usuario usuario : recuperarTodos()) {
			if (nomeUsuario.equals(usuario.getNomeUsuario())) {
				return usuario;
			}
		}
		
		return null;
	}

	@Override
	public List<Usuario> recuperarTodos() throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}

		ArrayList<Usuario> veiculos = new ArrayList<>();;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Usuario.class, new SerializadorModel<Usuario>());
			
			Gson gson = gsonBuilder.create();
			
			veiculos = gson.fromJson(fr, new TypeToken<ArrayList<Usuario>>(){}.getType());
		}
		
		return veiculos;
	}

	@Override
	public void salvar(Usuario usuario) throws IOException {
		List<Usuario> usuarios;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				usuarios = new ArrayList<>();
			else
				usuarios = new ArrayList<>(recuperarTodos());
		}
		
		for (Usuario usuarioAtual : usuarios) {
			if (usuario.getNomeUsuario().equals(usuarioAtual.getNomeUsuario())) {
				return;
			}
		}
		
		usuarios.add(usuario);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Usuario.class, new SerializadorModel<Usuario>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(usuarios, new TypeToken<ArrayList<Usuario>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Usuario usuario) throws IOException {
		List<Usuario> veiculos;
		
		List<Usuario> veiculosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				veiculos = new ArrayList<>();
			else
				veiculos = new ArrayList<>(recuperarTodos());
		}
		
		for (Usuario usuarioAtual : veiculos) {
			if (!usuario.getNomeUsuario().equals(usuarioAtual.getNomeUsuario())) {
				veiculosNovo.add(usuarioAtual);
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Usuario.class, new SerializadorModel<Usuario>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(veiculos, new TypeToken<ArrayList<Usuario>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Usuario usuario) throws IOException {
		List<Usuario> usuarios;
		
		List<Usuario> usuariosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				usuarios = new ArrayList<>();
			else
				usuarios = new ArrayList<>(recuperarTodos());
		}
		
		for (Usuario usuarioAtual : usuarios) {
			if (usuario.getNomeUsuario().equals(usuarioAtual.getNomeUsuario())) {
				usuariosNovo.add(usuario);
			}
			else {
				usuariosNovo.add(usuarioAtual);
			}
		}
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Usuario.class, new SerializadorModel<Usuario>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(usuarios, new TypeToken<ArrayList<Usuario>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

}
