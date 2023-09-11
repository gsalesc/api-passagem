package br.com.flyeasy.apitestepassagem.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.flyeasy.apitestepassagem.infra.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		//trocando de stateful para stateless
		
		/*return http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().build();*/
		
		/*return http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/login").permitAll() //permite todos fazerem essa requisição
				.anyRequest().authenticated()//todas as outras precisam ser autenticadas
				.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //fazendo com que o filtro de segurança seja executado primeiro do que o do spring, fazendo a atribuição de autenticação primeiro
				.build();*/
		
		//spring boot 3.1
		return http.csrf(csrf -> csrf.disable())
	            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(req -> {
	                /*req.requestMatchers(HttpMethod.POST, "/login").permitAll();
	                req.requestMatchers(HttpMethod.POST, "/login/novo").permitAll();
	                req.requestMatchers(HttpMethod.GET, "/apivoos").permitAll();*/
	            	req.requestMatchers(HttpMethod.POST, "/api/login/**").permitAll();
	            	req.requestMatchers(HttpMethod.POST, "/webapp").permitAll();
	            	req.requestMatchers(HttpMethod.GET, "/api/aeroporto").permitAll();
	            	req.requestMatchers("/voos").permitAll();
	                req.requestMatchers("/api/**").authenticated();
	                req.anyRequest().permitAll();
	            })
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build(); 
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder definirHashing() {
		return new BCryptPasswordEncoder();
	}
}
