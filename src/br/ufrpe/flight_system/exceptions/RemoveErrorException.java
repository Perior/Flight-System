package br.ufrpe.flight_system.exceptions;

public class RemoveErrorException extends Exception{
	
	private static final long serialVersionUID = -6247456468378694362L;

	public RemoveErrorException() {
		super("Não foi possível remover.");
	}
}
