package br.ufrpe.flight_system.negocio;

import java.util.List;

import br.ufrpe.flight_system.beans.*;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;
import br.ufrpe.flight_system.exceptions.EditErrorException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public interface IFachada {
	//Passageiros
	void adicionarPassageiro(Passageiros p) throws AlreadyExistException;
	List<Passageiros> listarPassageiros();
	void alterarPassageiro(int index, Passageiros p);
	void removerPassageiro(int index) throws RemoveErrorException;
	void salvarArquivoPassageiros();
	
	//Voos
	void adicionarVoo(Voos v);
	List<Voos> listarVoos();
	void alterarVoo(int index, Voos v) throws EditErrorException;
	void removerVoo(int index) throws RemoveErrorException;
	void salvarArquivosVoos();
	
	//Bilhetes
	void criarBilhete(Passageiros p, Voos v, int assento);
	List<Bilhete> listarBilhetes(Voos v) throws BilheteNotExist;
	void salvarArquivosBilhetes();
	
}
