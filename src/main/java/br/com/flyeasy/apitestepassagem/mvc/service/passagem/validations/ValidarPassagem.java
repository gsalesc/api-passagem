package br.com.flyeasy.apitestepassagem.mvc.service.passagem.validations;

import br.com.flyeasy.apitestepassagem.mvc.model.passagem.PassagemCadastroDTO;

public interface ValidarPassagem {
	void validar(PassagemCadastroDTO dados);
}
