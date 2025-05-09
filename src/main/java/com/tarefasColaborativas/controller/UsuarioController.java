package com.tarefasColaborativas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefasColaborativas.dto.UsuarioCadastroDTO;
import com.tarefasColaborativas.dto.UsuarioDTO;
import com.tarefasColaborativas.mapper.UsuarioMapper;
import com.tarefasColaborativas.model.Usuario;
import com.tarefasColaborativas.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
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

}
