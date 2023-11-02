package br.com.flyeasy.apitestepassagem.mvc.model.passagem;

import java.math.BigDecimal;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Classe;

public class PassagemListagemDTO {
	
	private Long id;
	
	private Long voo;
	
	private String poltrona;
	
	private Long passageiro;
	
	private BigDecimal preco;
	
	
	public PassagemListagemDTO() {
	}
	
	public PassagemListagemDTO(Passagem passagem) {
		id = passagem.getId();
		voo = passagem.getVoo().getId();
		poltrona = passagem.getPoltrona().getSigla();
		passageiro = passagem.getPassageiro().getId();
		preco = passagem.getPreco();
	}

	public Long getId() {
		return id;
	}

	public Long getVoo() {
		return voo;
	}

	public String getPoltrona() {
		return poltrona;
	}

	public Long getPassageiro() {
		return passageiro;
	}

	public BigDecimal getPreco() {
		return preco;
	}
}
