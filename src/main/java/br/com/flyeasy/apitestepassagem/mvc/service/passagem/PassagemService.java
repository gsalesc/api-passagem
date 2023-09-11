package br.com.flyeasy.apitestepassagem.mvc.service.passagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.flyeasy.apitestepassagem.infra.exception.ValidacaoException;
import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.passagem.Passagem;
import br.com.flyeasy.apitestepassagem.mvc.model.passagem.PassagemCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.repository.passageiro.PassageiroRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.passagem.PassagemRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import jakarta.validation.Valid;

@Service
public class PassagemService {
	
	@Autowired
	private PassagemRepository passagemRepository;
	
	@Autowired 
	private VooRepository vooRepository;
	
	@Autowired
	private PoltronaRepository poltronaRepository;
	
	@Autowired
	private PassageiroRepository passageiroRepository;
	
	public Passagem inserir(@Valid PassagemCadastroDTO dados) {
		
		if(!vooRepository.existsById(dados.getVoo_id())) {
			throw new ValidacaoException("Não há um voo com esse id");
		}
		
		if(!poltronaRepository.existsById(dados.getPoltrona_id())) {
			throw new ValidacaoException("Não há uma poltrona com esse id");
		}
		
		if(!passageiroRepository.existsById(dados.getPassageiro_id())) {
			throw new ValidacaoException("Não há um passageiro com esse id");
		}
		
		Voo voo = vooRepository.findById(dados.getVoo_id()).get();
		Poltrona poltrona = poltronaRepository.findById(dados.getPoltrona_id()).get();
		Passageiro passageiro = passageiroRepository.findById(dados.getPassageiro_id()).get();
		Passagem novo = new Passagem(dados, voo, poltrona, passageiro);
		passagemRepository.save(novo); 
		
		return novo;
	}
	
	public Page<Passagem> listar(Pageable page){
		return passagemRepository.findAll(page);
	}
}
