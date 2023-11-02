package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto.AeroportoRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;


public class ValidarSeTemUmVooNoMesmoHorarioNaMesmaOrigem implements ValidarVoo {

	@Autowired
	private VooRepository repository;
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		Long origem_id = dados.getOrigem_id();
		LocalDate data = dados.getDiaPartida();
		LocalTime hora = dados.getHoraPartida();
		
		Aeroporto origem = aeroportoRepository.findById(origem_id).get();
		
		Optional<Voo> temVoo = Optional.of(repository.findByOrigemAndDiaPartidaAndHoraPartida(origem, data, hora));
		
		if(temVoo.isPresent()) {
			throw new ValidacaoException("Já há um voo no mesmo horário para essa origem");
		}
	}

}
