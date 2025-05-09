package com.tarefasColaborativas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefasColaborativas.dto.LoginDTO;
import com.tarefasColaborativas.dto.UsuarioCadastroDTO;
import com.tarefasColaborativas.dto.UsuarioDTO;
import com.tarefasColaborativas.mapper.UsuarioMapper;
import com.tarefasColaborativas.model.Usuario;
import com.tarefasColaborativas.service.JwtTokenService;
import com.tarefasColaborativas.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper, JwtTokenService jwtTokenService) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
		this.jwtTokenService =jwtTokenService;
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody UsuarioCadastroDTO dto){
		try {
			Usuario usuario = usuarioMapper.toEntity(dto);
			Usuario UsuarioSalvo = usuarioService.CadastrarUsuario(usuario);
			return ResponseEntity.ok(usuarioMapper.toDTO(UsuarioSalvo));
		}
		catch(RuntimeException e) {
			 ResponseEntity.internalServerError().body("Erro ao cadastrar um novo usuário" + e.getMessage());
			 return null;
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDto){
		Usuario usuario = usuarioService.buscarPorEmail(loginDto.getEmail())
				.orElseThrow();
		
		if(usuario == null || !usuarioService.verificaSenha(loginDto.getSenha(), usuario.getSenha())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
		}
		
		String token = jwtTokenService.gerarToken(usuario.getEmail());
		
		return ResponseEntity.ok(Map.of("token", token));
		
	}

}
