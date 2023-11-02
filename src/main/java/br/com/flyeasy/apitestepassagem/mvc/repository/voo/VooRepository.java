package br.com.flyeasy.apitestepassagem.mvc.repository.voo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;

@Repository
@Component
public interface VooRepository extends JpaRepository<Voo, Long> {
	@Query("select v from Voo v where v.origem = :origem and v.destino = :destino")
	List<Voo> findByOrigemAndDestino(Aeroporto origem, Aeroporto destino);
	
	@Query("select v from Voo v where v.origem = :origem and v.destino = :destino and v.diaPartida = :data")
	List<Voo> findByOrigemAndDestinoAndDiaPartida(Aeroporto origem, Aeroporto destino, LocalDate data);
	
	@Query("select v from Voo v where v.origem = :origem and v.diaPartida = :data and v.horaPartida = :hora")
	Voo findByOrigemAndDiaPartidaAndHoraPartida(Aeroporto origem, LocalDate data, LocalTime hora);
}
