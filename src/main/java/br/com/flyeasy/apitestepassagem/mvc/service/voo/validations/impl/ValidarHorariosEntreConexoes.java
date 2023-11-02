package br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.impl;

import java.time.LocalTime;

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
public class ValidarHorariosEntreConexoes implements ValidarVoo{

	@Autowired
	private VooRepository vooRepository;
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@Override
	public void validar(VooCadastroDTO dados) {
		if(dados.getVoos_id().size() > 0) {
			
			for(int i = 0; i < dados.getVoos_id().size(); i++) {
				if(i == 0) {
					Voo conexaoAnterior = vooRepository.findById(dados.getVoos_id().get(i)).get();
					Voo conexaoAtual = vooRepository.findById(dados.getVoos_id().get(i+1)).get();
					
					LocalTime horaInicioAnterior = conexaoAnterior.getHoraPartida();
					long tempoAdd = conexaoAnterior.getTempoEstimado().longValue();
					
					LocalTime horaInicioAtual = conexaoAtual.getHoraPartida();
					
					if(horaInicioAnterior.plusHours(tempoAdd).isAfter(horaInicioAtual)) {
						

						
						throw new ValidacaoException("A duração estimada de uma conexão não deve passar o inicio da próxima"
								+ "\nConexao anterior (ID: " + conexaoAnterior.getId() + ") tem horário de partida " + conexaoAnterior.getHoraPartida() + " e duração de " + conexaoAnterior.getTempoEstimado()
								+ "\nPróxima conexão (ID: " + conexaoAtual.getId() + ") tem horário de partida " + conexaoAtual.getHoraPartida()
								+ ""
								+ ""
								+ ""
								+ ""
								+ ""
								+ "");		
					}

				}else{
					Voo conexaoAnterior = vooRepository.findById(dados.getVoos_id().get(i-1)).get();
					Voo conexaoAtual = vooRepository.findById(dados.getVoos_id().get(i)).get();
					
					LocalTime horaInicioAnterior = conexaoAnterior.getHoraPartida();
					long tempoAdd = conexaoAnterior.getTempoEstimado().longValue();
					
					LocalTime horaInicioAtual = conexaoAtual.getHoraPartida();
					
					if(horaInicioAnterior.plusHours(tempoAdd).isAfter(horaInicioAtual)) {
						throw new ValidacaoException("A duração estimada de uma conexão não deve passar o inicio da próxima"
								+ "\nConexao anterior (ID: " + conexaoAnterior.getId() + ") tem horário de partida " + conexaoAnterior.getHoraPartida() + " e duração de " + conexaoAnterior.getTempoEstimado()
								+ "\nPróxima conexão (ID: " + conexaoAtual.getId() + ") tem horário de partida " + conexaoAtual.getHoraPartida()
								+ ""
								+ ""
								+ ""
								+ ""
								+ ""
								+ "");					
						}
				}
			}
		}
	}

}
