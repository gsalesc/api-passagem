package br.com.flyeasy.apitestepassagem.mvc.repository.poltrona;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;

public interface PoltronaRepository extends JpaRepository<Poltrona, Long> {
	Poltrona findByVooAndSigla (Voo voo, String sigla);
}
