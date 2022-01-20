package br.ufrpe.flight_system.beans;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ufrpe.flight_system.enums.Aeronave;
import br.ufrpe.flight_system.enums.Cidade;

public class Voos implements Serializable{

	private static final long serialVersionUID = -369457410466368637L;
	private int codIU;
	private Cidade cidadeOrigem, cidadeDestino;
	private Aeronave tipoAeronave;
	private ZonedDateTime dataSaida, dataChegada;
	private String dtSaida, dtChegada, strOrigem, strDestino;
	private ArrayList<Integer> fixed;


	//Construtor
	public Voos(int codIU, Cidade origem, Cidade destino, Aeronave tipoAeronave, ZonedDateTime saida, ZonedDateTime chegada) {
		this.codIU = codIU;
		this.cidadeOrigem = origem;
		this.cidadeDestino = destino;
		this.tipoAeronave = tipoAeronave;
		this.dataSaida = saida;
		this.dataChegada = chegada;
		this.dtSaida = saida.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm O"));
		this.dtChegada = chegada.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm O"));
		this.strOrigem = origem.getNomeCidade();
		this.strDestino = destino.getNomeCidade();
		this.fixed = new ArrayList<Integer>();
		for(int i=0; i<this.tipoAeronave.getNumAssentos(); i++) {
			this.fixed.add(Integer.valueOf(i+1));
		}
	}

	//Metodos Getters e Setters
	public ArrayList<Integer> getFixed() {
		return fixed;
	}

	public int getCodIU() {
		return codIU;
	}

	public void setCodIU(int codIU) {
		this.codIU = codIU;
	}

	public Cidade getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(Cidade cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public Aeronave getTipoAeronave() {
		return tipoAeronave;
	}

	public void setTipoAeronave(Aeronave tipoAeronave) {
		this.tipoAeronave = tipoAeronave;
	}

	public ZonedDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(ZonedDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public ZonedDateTime getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(ZonedDateTime dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public String getDtSaida() {
		return dtSaida;
	}
	
	public String getDtChegada() {
		return dtChegada;
	}
	
	public String getStrOrigem() {
		return strOrigem;
	}
	
	public String getStrDestino() {
		return strDestino;
	}
}
