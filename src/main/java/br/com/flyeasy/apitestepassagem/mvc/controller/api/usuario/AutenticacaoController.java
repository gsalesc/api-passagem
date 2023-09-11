package br.com.flyeasy.apitestepassagem.mvc.controller.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flyeasy.apitestepassagem.mvc.model.usuario.DadosLoginDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.usuario.Usuario;
import br.com.flyeasy.apitestepassagem.mvc.repository.usuario.UsuarioRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.usuario.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	private ResponseEntity<String> login(@RequestBody @Valid DadosLoginDTO dados) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
		Authentication auth = manager.authenticate(token); //já reconhece o AutenticacaoService e o método de busca do login
		return ResponseEntity.ok(tokenService.gerarToken((Usuario)auth.getPrincipal()));
	}
	
	@PostMapping("/novo")
	private ResponseEntity inserirLogin(@RequestBody @Valid DadosLoginDTO dados) {
		String senha = new BCryptPasswordEncoder().encode(dados.getSenha());
		Usuario user = new Usuario(dados.getLogin(), senha);
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
}
