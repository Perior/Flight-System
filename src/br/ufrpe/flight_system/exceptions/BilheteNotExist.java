package br.ufrpe.flight_system.exceptions;

public class BilheteNotExist extends Exception {
	
	private static final long serialVersionUID = 5776727476967030981L;

	public BilheteNotExist() {
		super("Nenhum bilhete cadastrado.");
	}
}
