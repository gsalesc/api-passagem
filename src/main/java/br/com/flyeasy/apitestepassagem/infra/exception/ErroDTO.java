package br.com.flyeasy.apitestepassagem.infra.exception;

import org.springframework.validation.FieldError;

public class ErroDTO {
	private String campo;
	private String mensagem;
	
	public ErroDTO(FieldError err) {
		this.campo = err.getField();
		this.mensagem = err.getDefaultMessage();
	}
	
	private String getCampo() {
		return this.campo;
	}
	
	private String getMensagem() {
		return this.mensagem;
	}
}
