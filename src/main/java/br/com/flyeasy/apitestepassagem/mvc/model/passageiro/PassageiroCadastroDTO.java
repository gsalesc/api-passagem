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
	
	public PassageiroCadastroDTO() {
	}
	
	public PassageiroCadastroDTO(@NotNull String nome, @NotNull String cpf, @NotNull LocalDate dataNascimento,
			@NotNull String telefone, @NotNull String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;

	}
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
}
