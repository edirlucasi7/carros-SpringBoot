package com.carros.domain;

import org.springframework.stereotype.Service;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;

@Service
public class DataService {
	
	public void localDate() {
		
		LocalDate primeiraData = LocalDate.of(2020, Month.MAY, 20);
		LocalDate segundaData = LocalDate.of(1996, Month.MAY, 17);
				
		Period periodo = Period.between(primeiraData, segundaData);
		
		
		int dias = periodo.getDays();
		int absDias = Math.abs(dias);
		int meses = periodo.getMonths();
		int absMeses = Math.abs(meses);
		int anos = periodo.getYears();
		int absAnos = Math.abs(anos);
		
		System.out.print(absDias + " Dias, ");
		System.out.print(absMeses + " Meses, ");
		System.out.print(absAnos + " Anos.");
		
	}
	
public void localDateTime() {
		
		LocalDateTime primeiraData = LocalDateTime.of(2024, 1, 14, 12, 30);
		LocalDateTime segundaData = LocalDateTime.of(2019, 1, 14, 7, 30);
		
		Duration duracao = Duration.between(segundaData, primeiraData);	
		
		long difHoras = duracao.toHours();
		long difDias = duracao.toDays();
		long difMeses = duracao.toDays() / 30;
		long difAno = duracao.toDays() / (30 * 12);
		
		System.out.println(difHoras);
		System.out.println(difDias);
		System.out.println(difMeses);
		System.out.println(difAno);
		
	}
}
