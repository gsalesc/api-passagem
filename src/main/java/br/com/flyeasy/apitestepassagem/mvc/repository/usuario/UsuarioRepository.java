package br.com.flyeasy.apitestepassagem.mvc.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.flyeasy.apitestepassagem.mvc.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	UserDetails findByLogin(String login);
	UserDetails findByLoginAndSenha(String username, String senha);	
}
