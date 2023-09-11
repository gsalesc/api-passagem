package br.com.flyeasy.apitestepassagem.mvc.model.aeroporto;

public class AeroportoListagemDTO {
	private Long id;
	private String nome;
	private String sigla;
	private String pais;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSigla() {
		return sigla;
	}
	public String getPais() {
		return pais;
	}
	
	public AeroportoListagemDTO(Aeroporto aeroporto) {
		this.id = aeroporto.getId();
		this.nome = aeroporto.getNome();
		this.pais = aeroporto.getPais();
		this.sigla = aeroporto.getSigla();
	}
}
