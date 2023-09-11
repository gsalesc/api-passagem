package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarConexoesInicialEFinal implements ValidarVoo{

	@Autowired
	private VooRepository vooRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		//if(dados.getQtdConexoes() > 0 && dados.getVoos_id().size() == dados.getQtdConexoes()) {
		if(dados.getVoos_id().size() > 0) {
			//origem
			Long origem_id = dados.getVoos_id().get(0);
			Voo inicial = vooRepository.findById(origem_id).get();
						
			if(!(dados.getOrigem_id() == inicial.getOrigem().getId())) {
				throw new ValidacaoException("O vôo inicial deve coincidir com a origem informada");
			}
			
			//destino
			Long destino_id = dados.getVoos_id().get(dados.getVoos_id().size()-1);
			Voo fim = vooRepository.findById(destino_id).get();
			
			if(!(dados.getDestino_id() == fim.getDestino().getId())) {
				throw new ValidacaoException("O vôo final deve coincidir com o destino informado");
			}
		} 
	}

}
