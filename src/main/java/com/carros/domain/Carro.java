package com.carros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name="carro" )
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	private String nome;
	private String descricao;
	
	@Column(name="url_foto")
	private String urlFoto;
	
	@Column(name="url_video")
	private String urlVideo;
	
	private String latitude;
	private String longitude;
	
	

}
