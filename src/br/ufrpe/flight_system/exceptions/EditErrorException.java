package br.ufrpe.flight_system.exceptions;

public class EditErrorException extends Exception {

	private static final long serialVersionUID = 8600462359510553502L;
	
	public EditErrorException() {
		super("Não foi possível modificar.");
	}

}
