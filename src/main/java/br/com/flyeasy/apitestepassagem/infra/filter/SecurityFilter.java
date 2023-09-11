package br.com.flyeasy.apitestepassagem.infra.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.flyeasy.apitestepassagem.mvc.model.usuario.Usuario;
import br.com.flyeasy.apitestepassagem.mvc.repository.usuario.UsuarioRepository;
import br.com.flyeasy.apitestepassagem.mvc.service.usuario.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter { //identifica que é um filter 

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository userRepository;

	// intercepta antes de chegar no controller
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		if (token != null) {
			String subject = tokenService.getSubject(token); // recupera o subject do token
			var user = userRepository.findByLogin(subject);
			
			// validar token	
			
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth); //definindo autenticacao automática pelo usuario que colocou
			//sendo que ele mandou o token no cabeçalho de autenticação
		} 
		
		filterChain.doFilter(request, response); // continua a cadeia de filtro, liberando a passasgem	
	}

	private String recuperarToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}

}
