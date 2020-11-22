package com.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private Long id;
	private String tipo;
	private String nome;

	public static CarroDTO update(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
	}
	
	
}
