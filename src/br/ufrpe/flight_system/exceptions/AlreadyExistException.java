package br.ufrpe.flight_system.exceptions;

public class AlreadyExistException extends Exception{
	
	private static final long serialVersionUID = -2638438619659504925L;

	public AlreadyExistException() {
		super("Já cadastrado.");
	}
}
