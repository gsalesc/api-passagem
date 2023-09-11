package br.com.flyeasy.apitestepassagem.mvc.model.voo.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class VooCadastroDTO {
	
	@NotNull
	private Long origem_id;
	
	@NotNull
	private Long destino_id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaPartida;
	
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaPartida;
	
	private double tempoEstimado;
		
	@NotNull
	private double precoMinimo;
	
	private Integer qtdParadas;
	
	@NotNull
	private Integer qtdPoltronas;
	
	private Integer qtdConexoes;
	
	private List<Long> voos_id;
	
	public Long getOrigem_id() {
		return origem_id;
	}

	public Long getDestino_id() {
		return destino_id;
	}

	public LocalDate getDiaPartida() {
		return diaPartida;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public double getTempoEstimado() {
		return tempoEstimado;
	}

	public Integer getQtdParadas() {
		return qtdParadas;
	}

	public Integer getQtdPoltronas() {
		return qtdPoltronas;
	}

	public double getPrecoMinimo() {
		return precoMinimo;
	}

	public Integer getQtdConexoes() {
		return qtdConexoes;
	}

	public List<Long> getVoos_id() {
		return voos_id;
	}
}
