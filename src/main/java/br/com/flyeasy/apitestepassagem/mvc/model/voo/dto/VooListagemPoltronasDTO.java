package br.com.flyeasy.apitestepassagem.mvc.model.voo.dto;

import java.util.List;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;

public class VooListagemPoltronasDTO {

	private Long id;
	private List<Poltrona> poltronas;
	
	public VooListagemPoltronasDTO() {

	}
	
	public VooListagemPoltronasDTO(Voo voo) {
		this.id = voo.getId();
		this.poltronas = voo.getPoltronas();
	}

	public Long getId() {
		return id;
	}

	public List<Poltrona> getPoltronas() {
		return poltronas;
	}	
}