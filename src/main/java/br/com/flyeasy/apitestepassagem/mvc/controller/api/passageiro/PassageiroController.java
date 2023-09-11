package br.com.flyeasy.apitestepassagem.mvc.controller.api.passageiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.PassageiroCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.PassageiroListagemDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.repository.passageiro.PassageiroRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/passageiro")
public class PassageiroController {

	@Autowired
	private PassageiroRepository repository;
	
	@Autowired 
	private VooRepository vooRepository;
	
	@Autowired
	private PoltronaRepository poltronaRepository;
	
	@GetMapping
	private List<PassageiroListagemDTO> listar(){
		return repository.findAll().stream().map(PassageiroListagemDTO::new).toList();
	}
	
	@PostMapping
	private void inserir(@RequestBody @Valid PassageiroCadastroDTO dados){
		Voo voo = vooRepository.getReferenceById(dados.getVoo_id());
		Poltrona poltrona = poltronaRepository.getReferenceById(dados.getPoltrona_id());
		poltrona.alterarStatusOcupado();
		poltronaRepository.save(poltrona);
		Passageiro novo = new Passageiro(dados, voo, poltrona);
		repository.save(novo);
		poltrona.adicionarPassageiro(novo);
		poltronaRepository.save(poltrona);
		voo.inserirPassageiro(novo);
		vooRepository.save(voo);
	}
}
