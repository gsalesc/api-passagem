package br.com.flyeasy.apitestepassagem.mvc.model.passagem;

import java.math.BigDecimal;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Classe;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Passagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Voo voo;
	
	@OneToOne
	private Poltrona poltrona;
	
	@OneToOne
	private Passageiro passageiro;
	
	private BigDecimal preco;

	public Passagem() {
	}
	
	public Passagem(PassagemCadastroDTO dados, Voo voo, Poltrona poltrona, Passageiro passageiro) {
		this.voo = voo;
		this.poltrona = poltrona;
		this.passageiro = passageiro;
		this.preco = new BigDecimal(dados.getPreco());
	}
	
	public Long getId() {
		return id;
	}

	public Voo getVoo() {
		return voo;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public BigDecimal getPreco() {
		return preco;
	}
}
