package br.ufrpe.flight_system.dados;

import java.util.List;

import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.EditErrorException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public interface IRepositorioVoos {
	
	void cadastrar(Voos v);
	List<Voos> listar();
	void editar(int index, Voos v) throws EditErrorException;
	void remover(int index) throws RemoveErrorException;
	void salvarArquivo();
}
