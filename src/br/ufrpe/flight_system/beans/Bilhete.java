package br.ufrpe.flight_system.beans;

import java.io.Serializable;

public class Bilhete extends Passageiros implements Serializable{
	
	private static final long serialVersionUID = -6178585297679829231L;
	private int numAssento;
	private Passageiros passenger;
	private Voos flight;

	public Bilhete(Passageiros p, Voos v, int numAssento) {
		super(p.getName(), p.getSurname(), p.getCpf(), p.getPassaporte());
		this.passenger = p;
		this.flight = v;
		this.numAssento = numAssento;
	}

	public int getNumAssento() {
		return numAssento;
	}
	
	public Passageiros getPassenger() {
		return passenger;
	}
	
	public Voos getFlight() {
		return flight;
	}
	
}
