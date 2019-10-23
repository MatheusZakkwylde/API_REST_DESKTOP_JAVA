package com.project.api.client.dto;

public abstract class PessoaDTO {
	private long id;
        
	private String nome;
	
	private String username;
	
	private String email;
	
	private String url_foto_75x100;
	
	private String password;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl_foto_75x100() {
		return url_foto_75x100;
	}

	public void setUrl_foto_75x100(String url_foto_75x100) {
		this.url_foto_75x100 = url_foto_75x100;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
