package br.com.flyeasy.apitestepassagem.mvc.service.voo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooAtualizarDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto.AeroportoRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.validations.ValidarVoo;
import jakarta.validation.Valid;

@Service
public class VooService {
	
	//casos de uso 
	
	@Autowired 
	private VooRepository vooRepository;
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
		
	@Autowired
	private PoltronaRepository poltronaRepository;
	
	@Autowired
	private List<ValidarVoo> validarVoo;
	
	public Voo inserirVoo(@Valid VooCadastroDTO dados) {
		
		//validação de integridade
		if(!aeroportoRepository.existsById(dados.getOrigem_id())) {
			throw new ValidacaoException("Id do aeroporto de origem não existe!");
		}
		
		if(!aeroportoRepository.existsById(dados.getDestino_id())) {
			throw new ValidacaoException("Id do aeroporto de destino não existe!");
		}
				
		Aeroporto aeroportoOrigem  = aeroportoRepository.findById(dados.getOrigem_id()).get();
		Aeroporto aeroportoDestino = aeroportoRepository.findById(dados.getDestino_id()).get();		
		
		validarVoo.forEach(validarVoo -> validarVoo.validar(dados));
		
		Voo novo = new Voo(dados, aeroportoOrigem, aeroportoDestino);
		
		if(dados.getVoos_id().size() > 0) {
			for(Long id : dados.getVoos_id()) {
				novo.adicionarConexao(vooRepository.findById(id).get());
			}
		}
				
		if(novo.getConexoes().size() > 0) {
			novo.atualizarTempoEstimado();	
		} else {
			novo.carregarPoltronas(poltronaRepository);
		}
		
		vooRepository.save(novo);
		
		return novo;
	}
	
	
	public void atualizarVoo(@Valid VooAtualizarDTO dados) {
		//validação de integridade
		if(!aeroportoRepository.existsById(dados.getOrigem_id())) {
			throw new ValidacaoException("Id do aeroporto de origem não existe!");
		}
		
		if(!aeroportoRepository.existsById(dados.getDestino_id())) {
			throw new ValidacaoException("Id do aeroporto de destino não existe!");
		}
				
		Aeroporto aeroportoOrigem  = aeroportoRepository.findById(dados.getOrigem_id()).get();
		Aeroporto aeroportoDestino = aeroportoRepository.findById(dados.getDestino_id()).get();		
				
		validarVoo.forEach(validarVoo -> validarVoo.validar(dados));
		
		Voo voo = vooRepository.findById(dados.getId()).get();
		voo.atualizar(dados, aeroportoOrigem, aeroportoDestino);
		vooRepository.save(voo);
	}

	public List<Voo> listarVooPorId(Long id) {
		Voo voo = vooRepository.findById(id).get();
		List<Voo> voos = new ArrayList<Voo>();
		voos.add(voo);
		return voos;
	}

	public Voo buscarVooPorId(Long id) {
		return vooRepository.findById(id).get();
	}


	public Page<Voo> listarVoos(Pageable page) {
		return vooRepository.findAll(page);
	}
}
