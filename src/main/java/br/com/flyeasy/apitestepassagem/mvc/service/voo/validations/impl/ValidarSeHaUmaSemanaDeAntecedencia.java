package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeHaUmaSemanaDeAntecedencia implements ValidarVoo {

	@Override
	public void validar(VooCadastroDTO dados) {
		LocalDate dataVoo = dados.getDiaPartida();
		LocalDate dataAtual = LocalDate.now();
		
		if(dataVoo.isAfter(dataAtual)) {
			if(dataVoo.minusDays(7).isBefore(dataAtual)) throw new ValidacaoException("A data do vôo precisa ser, no mínimo, uma semana da data atual");			
		}	
	}

}
