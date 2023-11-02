package br.com.flyeasy.apitestepassagem.mvc.model.passageiro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table
public class Passageiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(unique = true)
	private String CPF;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private String telefone;
	@Column(unique = true)
	private String email;
	@ManyToOne
	@JsonIgnore
	private Voo voo;
	@OneToOne
	private Poltrona poltrona;
	
	public Passageiro() {
	}
	
	public Passageiro(PassageiroCadastroDTO dados) {
		this.nome = dados.getNome();
		this.CPF = dados.getCpf();
		this.dataNascimento = dados.getDataNascimento();
		this.telefone = dados.getTelefone();
		this.email = dados.getEmail();
	}
	
	public Passageiro(@Valid PassageiroCadastroDTO dados, Voo voo, Poltrona poltrona) {
		this.nome = dados.getNome();
		this.CPF = dados.getCpf();
		this.dataNascimento = dados.getDataNascimento();
		this.telefone = dados.getTelefone();
		this.email = dados.getEmail();
		this.voo = voo;
		this.poltrona = poltrona;
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

	public Voo getVoo() {
		return voo;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public void atribuirVooEPoltrona(Voo voo2, Poltrona poltrona2) {
		this.voo = voo2;
		this.poltrona = poltrona2;	
	}
}
