package br.com.flyeasy.apitestepassagem.mvc.repository.passageiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;

@Repository
@Component
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
