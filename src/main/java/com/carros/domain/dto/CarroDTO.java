package com.carros.domain.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import com.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String nome;

	public static CarroDTO update(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
	}
	
	
}
