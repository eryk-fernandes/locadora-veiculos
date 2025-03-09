package controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import excecoes.SenhaIncorretaException;
import excecoes.UsuarioAdicionadoException;
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
	
	public List<Usuario> recuperarTodos() throws IOException {
		return new UsuarioDAO().recuperarTodos();
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

	public String retornarTipoUsuario(JTextField nomeUsuario) throws Exception {
		
		Usuario usuario = recuperar(nomeUsuario.getText());
		
		return usuario.getClass().getSimpleName();
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

	public void cadastrarDados(Object tipo, JTextField nomeUsuario, JPasswordField senha) throws Exception {

		if (recuperar(nomeUsuario.getText()) != null) {
			throw new UsuarioAdicionadoException("USUÁRIO JÁ ADICIONADO");
		}
		
		if (tipo.toString().equals("ADMINISTRADOR")) {
			usuario = new Administrador();
		}
		else if (tipo.toString().equals("GERENTE")) {
			usuario = new Gerente();
		}
		else if (tipo.toString().equals("ATENDENTE")) {
			usuario = new Atendente();
		}
		else {
			throw new IllegalArgumentException("ESCOLHA UMA DAS OPÇÕES DE USUÁRIO");
		}

		usuario.setNomeUsuario(nomeUsuario.getText());
		usuario.setSenha(String.valueOf(senha.getPassword()));

		try {
			salvar();
		} catch (IOException e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
	}

}
