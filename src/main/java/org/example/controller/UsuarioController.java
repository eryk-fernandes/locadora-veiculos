package org.example.controller;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.example.model.dao.UsuarioDAO;
import org.example.model.exception.SenhaIncorretaException;
import org.example.model.exception.UsuarioAdicionadoException;
import org.example.model.exception.UsuarioNaoEncontradoException;
import org.example.model.entities.Administrador;
import org.example.model.entities.Atendente;
import org.example.model.entities.Gerente;
import org.example.model.entities.Usuario;

public class UsuarioController {

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

        Usuario usuario;
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
