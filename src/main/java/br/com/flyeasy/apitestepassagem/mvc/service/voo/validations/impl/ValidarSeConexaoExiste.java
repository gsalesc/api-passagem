package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

public class ValidarSeConexaoExiste implements ValidarVoo{
	@Autowired
	private VooRepository vooRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		if(dados.getVoos_id().size() > 0) {
			for(int i = 0; i < dados.getVoos_id().size(); i++) {

				if(!(vooRepository.existsById(dados.getVoos_id().get(i)))){
					throw new ValidacaoException("Vôo de conexão inexistente");
				}

			}
		}
	}

}
