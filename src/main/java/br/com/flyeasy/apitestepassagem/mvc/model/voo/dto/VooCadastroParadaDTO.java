package br.com.flyeasy.apitestepassagem.mvc.model.voo.dto;

import jakarta.validation.constraints.NotNull;

public class VooCadastroParadaDTO {
	@NotNull 
	private Long id;
	
	@NotNull
	private Long destino_id;

	public Long getId() {
		return id;
	}

	public Long getDestino_id() {
		return destino_id;
	}
}
