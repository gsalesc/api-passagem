package br.com.flyeasy.apitestepassagem.mvc.model.passageiro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class PassageiroCadastroDTO {
	
	@NotNull
	private String nome;
	@NotNull
	private String cpf;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	@NotNull
	private String telefone;
	@NotNull
	private String email;
	@NotNull 
	private Long voo_id;
	@NotNull 
	private Long poltrona_id;
	
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getEmail() {
		return email;
	}
	public Long getVoo_id() {
		return voo_id;
	}
	public Long getPoltrona_id() {
		return poltrona_id;
	}
}
