package br.com.flyeasy.apitestepassagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class ApiTestePassagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTestePassagemApplication.class, args);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  if (!registry.hasMappingForPattern("/assets/**")) {
	     registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	  }
	}
}
