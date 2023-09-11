package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations;

import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooAtualizarDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import jakarta.validation.Valid;

//Spring já enxerga pois é uma interface
public interface ValidarVoo {
	void validar(VooCadastroDTO dados);
}
