package br.com.flyeasy.apitestepassagem.mvc.repository.poltrona;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;

public interface PoltronaRepository extends JpaRepository<Poltrona, Long> {

}
