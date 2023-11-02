package br.com.flyeasy.apitestepassagem.mvc.service.passagem;

import java.util.List;

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
import br.com.flyeasy.apitestepassagem.mvc.service.passagem.validations.ValidarPassagem;
import jakarta.transaction.Transactional;
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
	
	@Autowired
	private List<ValidarPassagem> validaPassagem;
	
	@Transactional(rollbackOn = Exception.class)
	public Passagem inserir(@Valid PassagemCadastroDTO dados) {
		
		if(!vooRepository.existsById(dados.getVoo_id())) {
			throw new ValidacaoException("Não há um voo com esse id");
		}
		
		if(!poltronaRepository.existsById(dados.getPoltrona_id())) {
			throw new ValidacaoException("Não há uma poltrona com esse id");
		}
				
		validaPassagem.forEach(valida -> valida.validar(dados));
		
		Voo voo = vooRepository.findById(dados.getVoo_id()).get();
		Poltrona poltrona = poltronaRepository.findById(dados.getPoltrona_id()).get();
		
		Passageiro passageiro = new Passageiro(dados.getPassageiro_id());
		passageiro.atribuirVooEPoltrona(voo, poltrona);
		
		Passageiro passageiroVoo = this.passageiroRepository.save(passageiro);
		voo.inserirPassageiro(passageiroVoo);
		
		poltrona.adicionarPassageiro(passageiroVoo);
		poltrona.alterarStatusOcupado();
		Poltrona poltronaVoo = poltronaRepository.save(poltrona);
		
		Passagem novo = new Passagem(voo, poltronaVoo, passageiroVoo);
		passagemRepository.save(novo); 
		
		return novo;
	}
	
	public Page<Passagem> listar(Pageable page){
		return passagemRepository.findAll(page);
	}

	public Passagem listarPorId(Long id) {
		return this.passagemRepository.findById(id).get();
	}
}
