package br.com.flyeasy.apitestepassagem.mvc.service.usuario;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.flyeasy.apitestepassagem.mvc.model.usuario.Usuario;

@Service
public class TokenService {
	// @Value("${api.token.secret}")
	private String secret = "123456";

	public String gerarToken(Usuario user) {
		String token = "";
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			LocalDateTime data = LocalDateTime.now().plusHours(2);
			token = JWT.create().withIssuer("passagem").withSubject(user.getUsername())
					.withExpiresAt(data.toInstant(ZoneOffset.of("-03:00"))).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar o token JWT!");
		}

		return token;
	}

	//recuperar o subject que está no token
	public String getSubject(String tokenJWT) {
		try {
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("passagem").build().verify(tokenJWT).getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}
}
