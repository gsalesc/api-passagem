package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeConexaoFuturaEhDepoisDeOutra implements ValidarVoo{

	@Autowired
	private VooRepository vooRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		if(dados.getVoos_id().size() > 0) {
			for(int i = 0; i < dados.getVoos_id().size(); i++) {
				if(i == 0) {
					Voo primeiraConexao = vooRepository.findById(dados.getVoos_id().get(i)).get();
					
					if(primeiraConexao.getDiaPartida().isBefore(dados.getDiaPartida())
							&& primeiraConexao.getHoraPartida().isBefore(dados.getHoraPartida())){
						throw new ValidacaoException("Uma conexão não deve acontecer antes de outra");
					}

				}else{
					Voo conexaoAnterior = vooRepository.findById(dados.getVoos_id().get(i-1)).get();
					Voo conexaoAtual = vooRepository.findById(dados.getVoos_id().get(i)).get();
					
					if(conexaoAtual.getDiaPartida().isBefore(conexaoAnterior.getDiaPartida())
							&& conexaoAtual .getHoraPartida().isBefore(conexaoAnterior.getHoraPartida())){
						throw new ValidacaoException("Uma conexão não deve acontecer antes de outra");
					}
				}
			}
		}
	}

}
