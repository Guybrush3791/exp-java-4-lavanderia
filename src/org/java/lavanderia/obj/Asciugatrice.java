package org.java.lavanderia.obj;

import org.java.lavanderia.abs.Macchina;

public class Asciugatrice extends Macchina {

	private final static ProgrammaMacchina[] ASCIUGATRICE_PMS = {
		new ProgrammaMacchina(1, "Rapido", 20, 5),
		new ProgrammaMacchina(2, "Intenso", 60, 10)
	};
	
	public Asciugatrice(int id, int gettoni, boolean apertura) {
		
		super(id, false, gettoni, apertura, ASCIUGATRICE_PMS);
	}

	@Override
	public void printMyPrograms() {
		super.printMyPrograms(false);
	}
}
