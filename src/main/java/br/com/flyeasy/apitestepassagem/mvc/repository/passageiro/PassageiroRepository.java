package br.com.flyeasy.apitestepassagem.mvc.repository.passageiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
