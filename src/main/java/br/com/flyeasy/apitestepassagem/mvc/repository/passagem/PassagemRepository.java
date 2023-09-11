package br.com.flyeasy.apitestepassagem.mvc.repository.passagem;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flyeasy.apitestepassagem.mvc.model.passagem.Passagem;

public interface PassagemRepository extends JpaRepository<Passagem, Long>{

}
