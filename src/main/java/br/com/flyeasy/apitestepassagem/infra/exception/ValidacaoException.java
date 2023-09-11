package br.com.flyeasy.apitestepassagem.infra.exception;

public class ValidacaoException extends RuntimeException{
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
