package br.ufrpe.flight_system.negocio;

import java.time.ZonedDateTime;
import java.util.List;

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.dados.RepositorioBilhetes;
import br.ufrpe.flight_system.dados.RepositorioPassageiros;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public class CadastroPassageiros {
	private RepositorioPassageiros repositorio;
	private static CadastroPassageiros instance;

	private CadastroPassageiros() {
		this.repositorio = RepositorioPassageiros.getInstance();
	}

	public static CadastroPassageiros getInstance() {
		if(instance == null) {
			instance = new CadastroPassageiros();
		}

		return instance;
	}

	public void adicionarPassageiro(Passageiros p) throws AlreadyExistException{
		if(p == null) {
			throw new IllegalArgumentException("Entrada inválida.");
		}
		else if(this.repositorio.existe(p)) {
			throw new AlreadyExistException();
		}
		else {
			this.repositorio.cadastrar(p);
		}
	}

	public List<Passageiros> listarPassageiros(){
		return this.repositorio.listar();
	}

	public void alterarPassageiro(int index, Passageiros p) {
		if(p == null) {
			throw new IllegalArgumentException("Entrada Inválida");
		}
		else {
			this.repositorio.editar(index, p);
		}

	}

	public void removerPassageiro(int index) throws RemoveErrorException{
		Bilhete resultado = RepositorioBilhetes.getInstance().buscar(this.repositorio.listar().get(index));
		if(resultado != null && resultado.getFlight().getDataSaida().isAfter(ZonedDateTime.now())) {
			throw new RemoveErrorException();
		}
		else {
			this.repositorio.remover(index);
		}
	}

	public void salvarArquivos() {
		repositorio.salvarArquivo();
	}
}
