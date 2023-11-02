package br.com.flyeasy.apitestepassagem.mvc.model.poltrona;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Poltrona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sigla;
	
	@ManyToOne
	@JsonIgnore
	private Voo voo;
	
	@OneToOne
	@JsonIgnore
	private Passageiro passageiro;
	
	@Enumerated()
	private Classe classe;
	
	@Enumerated
	private StatusPoltrona situacao;
	
	public Poltrona() {
	}
	
	public Poltrona(Voo voo, String sigla, Classe classe) {
		this.sigla = sigla;
		this.voo = voo;
		this.passageiro = null;
		this.classe = classe;
		this.situacao = StatusPoltrona.LIVRE;
	}
	
	public void alterarStatusReservado() {
		this.situacao = StatusPoltrona.RESERVADO;
	}
	
	public void alterarStatusOcupado() {
		this.situacao = StatusPoltrona.OCUPADO;
	}
	
	public void adicionarPassageiro(Passageiro novo) {
		this.passageiro = novo;		
	}

	public Long getId() {
		return id;
	}

	public String getSigla() {
		return sigla;
	}

	public Classe getClasse() {
		return classe;
	}

	public Voo getVoo() {
		return voo;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}
	
	public StatusPoltrona getSituacao() {
		return situacao;
	}

	public void atribuirVoo(Voo voo) {
		this.voo = voo;
	}
}
