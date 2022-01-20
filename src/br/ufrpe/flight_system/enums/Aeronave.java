package br.ufrpe.flight_system.enums;

public enum Aeronave {
	ATR42("ATR 42-500", 48), ATR72("ATR 72-500", 66), EMBRAER175("Embraer 175", 86), EMBRAER190("Embraer 190", 106), 
	AIRBUS("Airbus A318", 120), BOEING737("Boeing 737-700", 138), BOEING767("Boeing 767-300ER", 221);

	public String tipoAeronave;
	public int numAssentos;

	Aeronave (String tipoAeronave, int numAssentos){
		this.tipoAeronave = tipoAeronave;
		this.numAssentos = numAssentos;
	}

	public int getNumAssentos() {
		return this.numAssentos;
	}

}
