package br.com.flyeasy.apitestepassagem.mvc.model.voo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.Voo;
import jakarta.validation.Valid;

public class VooListagemGeralDTO {

	private Long id;

	private Aeroporto origem;
	
	private Aeroporto destino;
	
	private LocalDate diaPartida;
	
	private LocalTime horaPartida;
	
	private BigDecimal tempoEstimado;

	private BigDecimal precoMinimo;

	private Integer qtdParadas;
	
	private Integer qtdPoltronas;
	
	private List<Aeroporto> paradas;
	
	private List<Poltrona> poltronas;
	
	private List<Passageiro> passageiros;
	
	private List<Voo> conexoes;
	
	public VooListagemGeralDTO() {

	}
	
	public VooListagemGeralDTO(Voo voo) {
		this.id = voo.getId();
		this.origem = voo.getOrigem();
		this.destino = voo.getDestino();
		this.diaPartida = voo.getDiaPartida();
		this.horaPartida = voo.getHoraPartida();
		this.tempoEstimado = voo.getTempoEstimado();
		this.precoMinimo = voo.getPrecoMinimo();
		this.qtdParadas = voo.getQtdParadas();
		this.qtdPoltronas = voo.getQtdPoltronas();
		this.paradas = voo.getParadas();
		this.poltronas = voo.getPoltronas();
		this.passageiros = voo.getPassageiros();
		this.conexoes = voo.getConexoes();
	}
	
	public Long getId() {
		return id;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	public Aeroporto getDestino() {
		return destino;
	}

	public LocalDate getDiaPartida() {
		return diaPartida;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public BigDecimal getTempoEstimado() {
		return tempoEstimado;
	}
	
	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
	}

	public Integer getQtdParadas() {
		return qtdParadas;
	}

	public Integer getQtdPoltronas() {
		return qtdPoltronas;
	}
	
	public List<Aeroporto> getParadas() {
		return paradas;
	}

	public List<Poltrona> getPoltronas() {
		return poltronas;
	}

	public List<Passageiro> getPassageiros() {
		return passageiros;
	}

	public List<Voo> getConexoes() {
		return conexoes;
	}
}
