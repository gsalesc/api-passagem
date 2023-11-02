package br.com.flyeasy.apitestepassagem.mvc.model.passagem;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.PassageiroCadastroDTO;
import jakarta.validation.constraints.NotNull;

public class PassagemCadastroDTO {
	
	@NotNull
	private Long voo_id;
	
	@NotNull
	private Long poltrona_id;
	
	@NotNull
	@JsonProperty(value="passageiro")
	private PassageiroCadastroDTO passageiro;
	
	public Long getVoo_id() {
		return voo_id;
	}

	public Long getPoltrona_id() {
		return poltrona_id;
	}

	public PassageiroCadastroDTO getPassageiro_id() {
		return passageiro;
	}
}
