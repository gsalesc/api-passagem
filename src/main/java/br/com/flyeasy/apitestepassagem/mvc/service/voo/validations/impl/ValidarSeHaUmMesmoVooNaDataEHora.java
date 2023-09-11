package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto.AeroportoRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;

@Component
public class ValidarSeHaUmMesmoVooNaDataEHora implements ValidarVoo {

	@Autowired
	private VooRepository repository;
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		Aeroporto origem = aeroportoRepository.findById(dados.getOrigem_id()).get();
		Aeroporto destino = aeroportoRepository.findById(dados.getDestino_id()).get();
		
		List<Voo> voos = repository.findByOrigemAndDestino(origem, destino);
		
		boolean mesmoDiaHorario = false;
		int i = 0;
		
		while(mesmoDiaHorario == false && i < voos.size()) {
			if(voos.get(i).getHoraPartida().equals(dados.getHoraPartida())
					&& voos.get(i).getDiaPartida().equals(dados.getDiaPartida())) {
				mesmoDiaHorario = true;
			}
			i++;
		}
		
		if(mesmoDiaHorario == true) throw new ValidacaoException("Já há um mesmo voo nesse horário");
		
	}

}
