package br.com.flyeasy.apitestepassagem.mvc.model.voo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.flyeasy.apitestepassagem.mvc.model.aeroporto.Aeroporto;
import br.com.flyeasy.apitestepassagem.mvc.model.passageiro.Passageiro;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Classe;
import br.com.flyeasy.apitestepassagem.mvc.model.poltrona.Poltrona;
import br.com.flyeasy.apitestepassagem.mvc.model.usuario.DadosLoginDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooAtualizarDTO;
import br.com.flyeasy.apitestepassagem.mvc.model.voo.dto.VooCadastroDTO;
import br.com.flyeasy.apitestepassagem.mvc.repository.poltrona.PoltronaRepository;
import br.com.flyeasy.apitestepassagem.mvc.repository.voo.VooRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "voo")
@AllArgsConstructor
public class Voo {
	
	/*@Autowired
	private VooRepository vooRepository;*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Aeroporto origem;

	@ManyToOne
	private Aeroporto destino;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaPartida;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaPartida;

	private BigDecimal tempoEstimado;

	private Integer qtdConexoes;

	private Integer qtdParadas;

	private Integer qtdPoltronas;

	private BigDecimal precoMinimo;

	@OneToMany
	private List<Voo> conexoes;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Aeroporto> paradas;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Poltrona> poltronas;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Passageiro> passageiros;
	
	@Enumerated
	private StatusVoo status;

	public Voo() {

	}

	public Voo(@Valid VooCadastroDTO dados, Aeroporto origem, Aeroporto destino) {
		this.origem = origem;
		this.destino = destino;
		this.diaPartida = dados.getDiaPartida();
		this.horaPartida = dados.getHoraPartida();
		this.precoMinimo = new BigDecimal(dados.getPrecoMinimo());
		this.qtdParadas = dados.getQtdParadas();
		this.qtdPoltronas = dados.getQtdPoltronas();
		this.qtdConexoes = dados.getQtdConexoes();
		conexoes = new ArrayList<Voo>();
		paradas = new ArrayList<Aeroporto>(this.qtdParadas);
		poltronas = new ArrayList<Poltrona>(this.qtdPoltronas);
		passageiros = new ArrayList<Passageiro>();
		
		
		if(dados.getVoos_id().size() == 0) {this.tempoEstimado = new BigDecimal(dados.getTempoEstimado());}
		else {
			this.tempoEstimado = BigDecimal.ZERO;
		}
		
		this.status = StatusVoo.AGENDADO;
	}
	
	public void atualizar(@Valid VooAtualizarDTO dados, Aeroporto origem, Aeroporto destino) {
		this.origem = origem;
		this.destino = destino;
		this.diaPartida = dados.getDiaPartida();
		this.horaPartida = dados.getHoraPartida();
		this.precoMinimo = new BigDecimal(dados.getPrecoMinimo());
		this.qtdParadas = dados.getQtdParadas();
		this.qtdPoltronas = dados.getQtdPoltronas();
		this.qtdConexoes = dados.getQtdConexoes();
		
		if(this.qtdConexoes == 0) {this.tempoEstimado = new BigDecimal(dados.getTempoEstimado());}
		else {
			this.tempoEstimado = BigDecimal.ZERO;
			this.conexoes.clear();
			
			/*for(Long id : dados.getVoos_id()) {
				if(vooRepository.existsById(id)) {
					Voo conexao = vooRepository.findById(id).get();
					this.conexoes.add(conexao);
					this.tempoEstimado.add(conexao.getTempoEstimado());
				}
			}*/
		}
	}

	public void adicionarParada(Aeroporto parada) {
		if (this.paradas.size() < this.qtdParadas)
			this.paradas.add(parada);
	}
	
	public void adicionarConexao(Voo voo) {
		//if (this.conexoes.size() < this.qtdConexoes) {
			this.conexoes.add(voo);
		//}
	}
	
	public void atualizarTempoEstimado() {
		for(Voo conexao : this.getConexoes()) {
			this.tempoEstimado.add(conexao.getTempoEstimado());
		}
	}

	public void atualizarTempoEstimado(Voo conexao) {
		//imutável
		BigDecimal novo = this.tempoEstimado.add(conexao.getTempoEstimado());
		this.tempoEstimado = novo;
	}
	
	public void carregarPoltronas(PoltronaRepository poltronaRepository) {
		if(this.qtdConexoes == 0) {
		int qtdExecutiva = (this.qtdPoltronas * 30) / 100;
			for (int i = 0; i < this.qtdPoltronas; i++) {
				if (i < qtdExecutiva) {
					Poltrona poltrona = new Poltrona(this, String.valueOf((i + 1)), Classe.EXECUTIVA);
					poltronaRepository.save(poltrona);
					this.poltronas.add(poltrona);
				} else {
					Poltrona poltrona = new Poltrona(this, String.valueOf((i + 1)), Classe.ECONOMICA);
					poltronaRepository.save(poltrona);
					this.poltronas.add(poltrona);
				}
			}
		}
	}

	public void inserirPassageiro(Passageiro novo) {
		this.passageiros.add(novo);
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

	public Integer getQtdParadas() {
		return qtdParadas;
	}

	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
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

	public Integer getQtdConexoes() {
		return qtdConexoes;
	}

	public List<Voo> getConexoes() {
		return conexoes;
	}
}
