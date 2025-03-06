package controller;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import excecoes.SenhaIncorretaException;
import excecoes.UsuarioNaoEncontradoException;
import model.Administrador;
import model.Atendente;
import model.Gerente;
import model.Usuario;

public class UsuarioController {
	
	private Usuario usuario;
	
	public UsuarioController() {
		
	}
	
	public Usuario recuperar(String nomeUsuario) {
		try {
			return new UsuarioDAO().recuperar(nomeUsuario);
		} catch (IOException e) {
			return null;
		}
	}
	
	public void salvar() throws IOException {
		new UsuarioDAO().salvar(usuario);
	}
	
	public void remover() throws IOException {
		new UsuarioDAO().remover(usuario);
	}
	
	public void atualizar() throws IOException {
		new UsuarioDAO().atualizar(usuario);
	}
	
	public void verificarUsuario(JTextField nomeUsuario, JPasswordField senha) throws Exception {
		
		Usuario usuario;
		
		usuario = recuperar(nomeUsuario.getText());
		
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("USUÁRIO NÃO ENCONTRADO");
		}
		if (!usuario.getSenha().equals(String.valueOf(senha.getPassword()))) {
			throw new SenhaIncorretaException("USUÁRIO OU SENHA INCORRETOS");
		}
			
	}
	
	public void cadastrarDados(String tipo, JTextField nomeUsuario, JPasswordField senha) throws Exception {
		
		if (tipo.equals("ADMINISTRADOR"))
			this.usuario = new Administrador();
		else if (tipo.equals("GERENTE"))
			this.usuario = new Gerente();
		else if (tipo.equals("ATENDENTE"))
			this.usuario = new Atendente();
		else
			throw new IllegalArgumentException();
		
		try {
			usuario.setNomeUsuario(nomeUsuario.getText());
			usuario.setSenha(String.valueOf(senha.getPassword()));
		}
		catch (InputMismatchException e) {
			throw e;
		}
		
		try {
			salvar();
		} catch (Exception e) {
			throw e;
		}
	}

}
