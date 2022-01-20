package br.ufrpe.flight_system.negocio;

import java.time.ZonedDateTime;
import java.util.List;

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.dados.RepositorioBilhetes;
import br.ufrpe.flight_system.dados.RepositorioVoos;
import br.ufrpe.flight_system.exceptions.EditErrorException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;


public class CadastroVoos {
	private RepositorioVoos repositorio;
	private static CadastroVoos instance;

	private CadastroVoos() {
		this.repositorio = RepositorioVoos.getInstance();
	}

	public static CadastroVoos getInstance() {
		if(instance == null) {
			instance = new CadastroVoos();
		}

		return instance;
	}

	public void adicionarVoo(Voos v) {
		if(v == null) {
			throw new IllegalArgumentException("Entrada inválida.");
		}
		else {
			this.repositorio.cadastrar(v);
		}
	}

	public List<Voos> listarVoo(){
		return this.repositorio.listar();
	}

	public void alterarVoo(int index, Voos v) throws EditErrorException {
		if(v == null) {
			throw new IllegalArgumentException("Entrada inválida.");
		}
		else if(this.repositorio.listar().get(index).getDataSaida().isBefore(ZonedDateTime.now()) && 
				this.repositorio.listar().get(index).getDataChegada().isBefore(ZonedDateTime.now())) {
			throw new EditErrorException();
		}
		else {
			this.repositorio.editar(index, v);
		}

	}

	public void removerVoo(int index) throws RemoveErrorException{
		Bilhete resultado = RepositorioBilhetes.getInstance().buscar(this.repositorio.listar().get(index));
		if((resultado != null || RepositorioVoos.getInstance().listar().get(index).getDataSaida().isBefore(ZonedDateTime.now()))
				&& RepositorioVoos.getInstance().listar().get(index).getDataChegada().isAfter(ZonedDateTime.now())) {
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
