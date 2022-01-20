package br.ufrpe.flight_system.dados;

import java.util.Comparator;

import br.ufrpe.flight_system.beans.Passageiros;

public class PassByName implements Comparator<Passageiros>{

	@Override
	public int compare(Passageiros p1, Passageiros p2) {
		return p1.getName().compareTo(p2.getName());
	}

}
