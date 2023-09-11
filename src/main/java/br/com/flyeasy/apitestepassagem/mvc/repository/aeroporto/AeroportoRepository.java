package br.com.flyeasy.apitestepassagem.mvc.repository.aeroporto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;

@Repository
@Component
public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

}
