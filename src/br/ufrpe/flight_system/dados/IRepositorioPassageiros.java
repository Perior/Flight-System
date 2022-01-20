package br.ufrpe.flight_system.dados;

import java.util.List;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public interface IRepositorioPassageiros {
	
	void cadastrar(Passageiros p) throws AlreadyExistException;
	boolean existe(Passageiros p);
	List<Passageiros> listar();
	void editar(int index, Passageiros p);
	void remover(int index) throws RemoveErrorException;
	void salvarArquivo();
	
}
