package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeVooComConexoesIniciaNaMesmaHoraQueAPrimeira implements ValidarVoo{

	@Autowired
	private VooRepository vooRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		if(dados.getVoos_id().size() > 0) {
			Voo primeiraConexao = vooRepository.findById(dados.getVoos_id().get(0)).get();
			
			if(dados.getHoraPartida() != primeiraConexao.getHoraPartida()) {
				throw new ValidacaoException("A hora de partida do vôo deve coincidir com a da sua primeira conexão (ID: " + primeiraConexao.getId() + " )");
			}
		}
	}

}
