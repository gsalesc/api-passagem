package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeEhNoFuturo implements ValidarVoo{

	@Override
	public void validar(VooCadastroDTO dados) {
		LocalDate dataVoo = dados.getDiaPartida();
		LocalDate dataAtual = LocalDate.now();
		
		if(dataVoo.isBefore(dataAtual)) {			
			throw new ValidacaoException("A data do vôo não pode ser anterior à data atual");
		}
	}

}
