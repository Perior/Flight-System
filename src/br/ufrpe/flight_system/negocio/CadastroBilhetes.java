package br.ufrpe.flight_system.negocio;

import java.util.List;

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.dados.RepositorioBilhetes;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;

public class CadastroBilhetes {
	private RepositorioBilhetes repositorio;
	private static CadastroBilhetes instance;

	private CadastroBilhetes() {
		this.repositorio = RepositorioBilhetes.getInstance();
	}

	public static CadastroBilhetes getInstance() {
		if(instance == null) {
			instance = new CadastroBilhetes();
		}

		return instance;
	}

	public void criarBilhete(Passageiros p, Voos v, int numAssento) {
		if(p == null || v == null || numAssento == 0) {
			throw new IllegalArgumentException("Passageiro, poltrona ou vôo inexistente(s).");
		}
		else {
			this.repositorio.criar(p, v, numAssento);
		}
	}

	public List<Bilhete> listarBilhetes(Voos v) throws BilheteNotExist {
		if(this.repositorio.listar(v).isEmpty()) {
			throw new BilheteNotExist();
		}
		else {
			return this.repositorio.listar(v);
		}
	}
	
	public void salvarArquivos() {
		repositorio.salvarArquivo();
	}
}
