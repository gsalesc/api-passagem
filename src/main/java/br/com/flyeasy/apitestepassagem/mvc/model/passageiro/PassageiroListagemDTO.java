package br.com.flyeasy.apitestepassagem.mvc.model.passageiro;

import java.time.LocalDate;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;

public class PassageiroListagemDTO {
	private Long id;
	private String nome;
	private String CPF;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private Long voo;
	private String poltrona;
	
	public PassageiroListagemDTO(Passageiro passageiro) {
		this.id = passageiro.getId();
		this.nome = passageiro.getNome();
		this.CPF = passageiro.getCPF();
		this.dataNascimento = passageiro.getDataNascimento();
		this.telefone = passageiro.getTelefone();
		this.email = passageiro.getEmail();
		this.voo = passageiro.getVoo().getId();
		this.poltrona = passageiro.getPoltrona().getSigla();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCPF() {
		return CPF;
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

	public Long getVoo() {
		return voo;
	}

	public String getPoltrona() {
		return poltrona;
	}
}
