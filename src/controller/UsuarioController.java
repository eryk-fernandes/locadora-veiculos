package controller;

import java.io.IOException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import excecoes.SenhaIncorretaException;
import excecoes.UsuarioJaAdicionadoException;
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

	public void cadastrarDados(String tipo, JTextField nomeUsuario, JPasswordField senha) throws Exception {

		if (recuperar(nomeUsuario.getText()) != null) {
			throw new UsuarioJaAdicionadoException("USUÁRIO JÁ ADICIONADO");
		}
		
		if (tipo == null)
			throw new IllegalArgumentException("ESCOLHA UMA DAS OPÇÕES DE USUÁRIO");
		else if (tipo.equals("Administrador"))
			usuario = new Administrador();
		else if (tipo.equals("Gerente"))
			usuario = new Gerente();
		else
			usuario = new Atendente();

		usuario.setNomeUsuario(nomeUsuario.getText());
		usuario.setSenha(String.valueOf(senha.getPassword()));

		try {
			salvar();
		} catch (IOException e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
	}

}
