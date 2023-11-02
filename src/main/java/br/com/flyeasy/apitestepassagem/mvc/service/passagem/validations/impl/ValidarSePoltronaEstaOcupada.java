package br.com.flyeasy.apitestepassagem.mvc.service.passagem.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.passagem.PassagemCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.StatusPoltrona;
import br.com.flyeasy.apitestepassagem.mvc.repository.passageiro.PassageiroRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.passagem.PassagemRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.passagem.validations.ValidarPassagem;

public class ValidarSePoltronaEstaOcupada implements ValidarPassagem {
	@Autowired
	private PassagemRepository passagemRepository;
	
	@Autowired 
	private VooRepository vooRepository;
	
	@Autowired
	private PoltronaRepository poltronaRepository;
	
	@Autowired
	private PassageiroRepository passageiroRepository;
	
	@Override
	public void validar(PassagemCadastroDTO dados) {
		Poltrona poltrona = poltronaRepository.findById(dados.getPoltrona_id()).get();
		
		if(poltrona.getSituacao() == StatusPoltrona.OCUPADO) {
			throw new ValidacaoException("Essa poltrona (" + poltrona.getSigla() + ") está ocupada");
		}
	}

}
