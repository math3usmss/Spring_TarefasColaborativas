package com.tarefasColaborativas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarefasColaborativas.model.Usuario;
import com.tarefasColaborativas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	PasswordEncoder passwordEncoded;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoded) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoded = passwordEncoded;
	}
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		try {
			String senhaCriptografada = passwordEncoded.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			return usuarioRepository.save(usuario);
			
		} catch (RuntimeException e) {
			throw new RuntimeException();
		}
	}

}
