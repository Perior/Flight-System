package br.ufrpe.flight_system.beans;

import java.io.Serializable;

public class Passageiros implements Serializable{

	private static final long serialVersionUID = 6606040650044173902L;
	private String name;
	private String surname;
	private long cpf;
	private long passaporte;

	//Construtor
	public Passageiros(String name, String surname, long cpf, long passaporte) {
		this.name = name;
		this.surname = surname;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	//Metodos Getters e Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getCpf() {
		return cpf;
	}

	public long getPassaporte() {
		return passaporte;
	}
	
}
