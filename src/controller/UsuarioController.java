package controller;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import exception.SenhaIncorretaException;
import exception.UsuarioAdicionadoException;
import exception.UsuarioNaoEncontradoException;
import model.Administrador;
import model.Atendente;
import model.Gerente;
import model.Usuario;

public class UsuarioController {

	private Usuario usuario;

	public UsuarioController() {

	}

	public String retornarTipoUsuario(JTextField nomeUsuario) throws Exception {
		
		Usuario usuario = new UsuarioDAO().recuperar(nomeUsuario.getText());
		
		return usuario.getClass().getSimpleName();
	}

	public void verificarUsuario(JTextField nomeUsuario, JPasswordField senha) throws Exception {

		Usuario usuario;

		usuario = new UsuarioDAO().recuperar(nomeUsuario.getText());

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("USUÁRIO NÃO ENCONTRADO");
		}
		if (!usuario.getSenha().equals(String.valueOf(senha.getPassword()))) {
			throw new SenhaIncorretaException("USUÁRIO OU SENHA INCORRETOS");
		}

	}

	public void cadastrarDados(Object tipo, JTextField nomeUsuario, JPasswordField senha) throws Exception {

		if (new UsuarioDAO().recuperar(nomeUsuario.getText()) != null) {
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

		new UsuarioDAO().salvar(usuario);
	}

}
