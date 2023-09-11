package br.com.flyeasy.apitestepassagem.mvc.model.voo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VooAtualizarDTO extends VooCadastroDTO{
	@NotNull
	private Long id;
	
	public Long getId() {
		return id;
	}
}
