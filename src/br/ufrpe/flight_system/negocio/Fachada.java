package br.ufrpe.flight_system.negocio;

import java.util.List;

import br.ufrpe.flight_system.beans.*;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;
import br.ufrpe.flight_system.exceptions.EditErrorException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public class Fachada implements IFachada{
	private CadastroPassageiros cadastroPassageiros;
	private CadastroVoos cadastroVoos;
	private CadastroBilhetes cadastroBilhetes;
	private static Fachada instance;

	private Fachada() {
		this.cadastroPassageiros = CadastroPassageiros.getInstance();
		this.cadastroVoos = CadastroVoos.getInstance();
		this.cadastroBilhetes = CadastroBilhetes.getInstance();
	}

	public static Fachada getInstance() {
		if(instance == null) {
			instance = new Fachada();
		}

		return instance;
	}

	//Passageiros
	public void adicionarPassageiro(Passageiros p) throws AlreadyExistException {
		this.cadastroPassageiros.adicionarPassageiro(p);
	}

	public List<Passageiros> listarPassageiros() {
		return this.cadastroPassageiros.listarPassageiros();
	}

	public void alterarPassageiro(int index, Passageiros p) {
		this.cadastroPassageiros.alterarPassageiro(index, p);
	}

	public void removerPassageiro(int index) throws RemoveErrorException {
		this.cadastroPassageiros.removerPassageiro(index);
	}

	public void salvarArquivoPassageiros() {
		this.cadastroPassageiros.salvarArquivos();
	}

	//Voos
	public void adicionarVoo(Voos v) {
		this.cadastroVoos.adicionarVoo(v);
	}

	public List<Voos> listarVoos() {
		return this.cadastroVoos.listarVoo();
	}

	public void alterarVoo(int index, Voos v) throws EditErrorException {
		this.cadastroVoos.alterarVoo(index, v);
	}

	public void removerVoo(int index) throws RemoveErrorException {
		this.cadastroVoos.removerVoo(index);
	}

	public void salvarArquivosVoos() {
		this.cadastroVoos.salvarArquivos();
	}

	//Bilhetes
	public void criarBilhete(Passageiros p, Voos v, int assento) {
		this.cadastroBilhetes.criarBilhete(p, v, assento);
	}

	public List<Bilhete> listarBilhetes(Voos v) throws BilheteNotExist {
		return this.cadastroBilhetes.listarBilhetes(v);
	}

	public void salvarArquivosBilhetes() {
		this.cadastroBilhetes.salvarArquivos();
	}
}
