package br.com.flyeasy.apitestepassagem.mvc.model.aeroporto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Aeroporto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	private String pais;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSigla() {
		return sigla;
	}
	public String getPais() {
		return pais;
	}
	
	public Aeroporto() {

	}
	
	public Aeroporto(AeroportoCadastroDTO dados) {
		this.nome = dados.getNome();
		this.pais = dados.getSigla();
		this.sigla = dados.getSigla();
	}
}
