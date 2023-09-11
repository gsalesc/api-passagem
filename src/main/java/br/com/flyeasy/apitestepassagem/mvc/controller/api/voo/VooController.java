package br.com.flyeasy.apitestepassagem.mvc.controller.api.voo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooAtualizarDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroParadaDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooListagemClienteDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooListagemGeralDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooListagemPoltronasDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto.AeroportoRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.voo.VooService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voos")
public class VooController {

	@Autowired 
	private VooRepository vooRepository;
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@Autowired
	private VooService vooService;
	
	@GetMapping
	private ResponseEntity<Page<VooListagemGeralDTO>> listar(@PageableDefault(size=10) Pageable page) {
		return ResponseEntity.ok(vooService.listarVoos(page).map(VooListagemGeralDTO::new));
	}
	
	@GetMapping("/{id}")
	private VooListagemGeralDTO buscarPorId(@PathVariable Long id){
		Voo voo = vooService.buscarVooPorId(id);
		return new VooListagemGeralDTO(voo);
	}
	
	@GetMapping("/{id}/poltronas")
	private ResponseEntity<List<VooListagemPoltronasDTO>> listarPoltronas(@PathVariable Long id)  {
		List<Voo> voo = vooService.listarVooPorId(id);
		return ResponseEntity.ok(voo.stream().map(VooListagemPoltronasDTO::new).toList());
	}
	
	@PostMapping("/novo")
	private ResponseEntity<VooListagemGeralDTO> inserir(@RequestBody @Valid VooCadastroDTO dados, UriComponentsBuilder uriBuilder) {		
		Voo novo = vooService.inserirVoo(dados);
		URI uri = uriBuilder.path("voos/{id}").buildAndExpand(novo).toUri(); //header
		return ResponseEntity.created(uri).body(new VooListagemGeralDTO(novo));
	}
	
	@PutMapping
	private ResponseEntity alterar(@RequestBody @Valid VooAtualizarDTO dados) {		
		vooService.atualizarVoo(dados);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/parada/novo")
	private void inserirParada(@RequestBody @Valid VooCadastroParadaDTO dados) {
		Voo novo = vooRepository.getReferenceById(dados.getId());
		Aeroporto aeroporto = aeroportoRepository.getReferenceById(dados.getDestino_id());
		novo.adicionarParada(aeroporto);
		vooRepository.save(novo);
	}
	

}