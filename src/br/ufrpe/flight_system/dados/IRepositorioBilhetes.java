package br.ufrpe.flight_system.dados;

import java.util.List;

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;

public interface IRepositorioBilhetes {
	
	void criar(Passageiros p, Voos v, int numAssento);
	Bilhete buscar(Object o);
	List<Bilhete> listar(Voos v) throws BilheteNotExist;
	void salvarArquivo();
}
