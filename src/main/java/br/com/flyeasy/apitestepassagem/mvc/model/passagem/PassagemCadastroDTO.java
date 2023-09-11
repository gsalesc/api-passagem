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

public class PassagemCadastroDTO {
	
	private Long voo_id;
	
	private Long poltrona_id;
	
	private Long passageiro_id;
	
	private double preco;
	
	private Classe classe;

	public Long getVoo_id() {
		return voo_id;
	}

	public Long getPoltrona_id() {
		return poltrona_id;
	}

	public Long getPassageiro_id() {
		return passageiro_id;
	}

	public double getPreco() {
		return preco;
	}

	public Classe getClasse() {
		return classe;
	}
}
