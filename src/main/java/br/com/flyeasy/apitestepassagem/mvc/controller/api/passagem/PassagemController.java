package br.com.flyeasy.apitestepassagem.mvc.controller.api.passagem;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.flyeasy.apitestepassagem.mvc.model.passagem.Passagem;
import br.com.flyeasy.apitestepassagem.mvc.model.passagem.PassagemCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.passagem.PassagemListagemDTO;
import br.com.flyeasy.apitestepassagem.mvc.service.passagem.PassagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/passagem")
public class PassagemController {
	@Autowired
	private PassagemService passagemService;
	
	@GetMapping
	private ResponseEntity<Page<PassagemListagemDTO>> listar(@PageableDefault(size=5) Pageable page){
		return ResponseEntity.ok(passagemService.listar(page).map(PassagemListagemDTO::new));
	}
	
	@GetMapping("{id}")
	private ResponseEntity<PassagemListagemDTO> listarPorId(@PathVariable Long id){
		return ResponseEntity.ok(new PassagemListagemDTO(this.passagemService.listarPorId(id)));
	}
	
	@PostMapping("/novo")
	private ResponseEntity<Passagem> inserir(@RequestBody @Valid PassagemCadastroDTO dados, UriComponentsBuilder uriBuilder) {
		Passagem novo = passagemService.inserir(dados);
		URI uri = uriBuilder.path("passagem/{id}").buildAndExpand(novo).toUri(); //header
		return ResponseEntity.created(uri).body(novo); //codigo 201(criado) + corpo + cabecalho
	}
}
