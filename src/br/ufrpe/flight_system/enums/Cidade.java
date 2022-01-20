package br.ufrpe.flight_system.enums;

import java.time.ZoneId;

public enum Cidade {
	SP("São Paulo", "America/Sao_Paulo"), NORONHA("Fernando de Noronha", "America/Noronha"), 
	RJ("Rio de Janeiro", "America/Sao_Paulo"), BH("Belo Horizonte", "America/Sao_Paulo"), 
	BAHIA("Salvador", "America/Bahia"), PA("Porto Alegre", "America/Sao_Paulo"), CURITIBA("Curitiba", "America/Sao_Paulo"), 
	RECIFE("Recife", "America/Recife"), FORTALEZA("Fortaleza", "America/Fortaleza"), NATAL("Natal","America/Fortaleza");

	public String nomeCidade;
	public ZoneId id;

	Cidade(String nomeCidade, String zone){
		this.nomeCidade = nomeCidade;
		this.id = ZoneId.of(zone);
	}

	public String getNomeCidade(){
		return nomeCidade;
	}
	
	public ZoneId getZoneId() {
		return id;
	}
}
