package com.tarefasColaborativas.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioCadastroDTO {
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O Email é obrigatório")
	private String email;
	
	@NotBlank(message = "A senha é obrigatória")
	private String senha;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
