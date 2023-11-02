package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeEhVooUnico implements ValidarVoo {
	@Autowired
	private VooRepository vooRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		if(dados.getVoos_id().size() > 0) {
			for(int i = 0; i < dados.getVoos_id().size(); i++) {
				Voo conexao = vooRepository.findById(dados.getVoos_id().get(i)).get();
				
				if(conexao.getConexoes().size() > 0){
					throw new ValidacaoException("Uma conexão deve ser um vôo único");
				}
			}
		}
	}

}
