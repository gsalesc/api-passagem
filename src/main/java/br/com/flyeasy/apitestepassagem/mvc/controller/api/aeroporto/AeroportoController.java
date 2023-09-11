package br.com.flyeasy.apitestepassagem.mvc.controller.api.aeroporto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.AeroportoCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.AeroportoListagemDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto.AeroportoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aeroporto")
public class AeroportoController {

	@Autowired
	private AeroportoRepository repository;
	
	@GetMapping
	private ResponseEntity<List<AeroportoListagemDTO>> listar(){
		return ResponseEntity.ok(repository.findAll().stream().map(AeroportoListagemDTO::new).toList());
	}
	
	@PostMapping
	private ResponseEntity<AeroportoListagemDTO> inserir(@RequestBody @Valid AeroportoCadastroDTO dados){
		Aeroporto novo = new Aeroporto(dados);
		repository.save(novo);
		
		return ResponseEntity.ok(new AeroportoListagemDTO(novo));
	}
}
