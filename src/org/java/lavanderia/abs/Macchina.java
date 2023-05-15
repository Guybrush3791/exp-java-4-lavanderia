package org.java.lavanderia.abs;

import org.java.lavanderia.obj.ProgrammaMacchina;

import com.bethecoder.ascii_table.ASCIITable;

public abstract class Macchina {

	private int id;
	private boolean lavatrice;
	private int gettoni;
	private boolean aperta;
	private boolean inFunzione;
	private ProgrammaMacchina programmaAttuale;
	private int tempoRimanente;
	
	private ProgrammaMacchina[] PMS;
	
	public Macchina(int id, boolean lavatrice, int gettoni, boolean apertura,			
			ProgrammaMacchina[] pms) {
		
		setId(id);
		setLavatrice(lavatrice);
		setGettoni(gettoni);
		setAperta(apertura);
		
		setInFunzioneOff();
		programmaAttuale = null;
		setTempoRimanente(0);
		
		this.PMS = pms;
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public boolean isLavatrice() {
		return lavatrice;
	}

	public void setLavatrice(boolean lavatrice) {
		this.lavatrice = lavatrice;
	}

	public int getGettoni() {
		return gettoni;
	}
	public void setGettoni(int gettoni) {
		this.gettoni = gettoni;
	}
	public void addGettoni(int gettoni) {
		
		setGettoni(getGettoni() + gettoni);
	}
	public void removeGettoni(int gettoni) {
		
		setGettoni(getGettoni() - gettoni);
	}
	public boolean isAperta() {
		return aperta;
	}
	public void setAperta(boolean aperta) {
		this.aperta = aperta;
	}
	public void toggleAperta() {
		
		setAperta(!isAperta());
	}
	public boolean isInFunzione() {
		return inFunzione;
	}
	public void setInFunzioneOff() {
		
		inFunzione = false;
	}
	public void setInFunzioneOn() throws Exception {
		
		if (getProgrammaAttuale() == null
			|| isAperta()
			|| getGettoni() < getProgrammaAttuale().getGettoni()
			|| isInFunzione())
			throw new Exception("Condizioni non soddisfatte");
		
		inFunzione = true;
		removeGettoni(getProgrammaAttuale().getGettoni());
	}
	public ProgrammaMacchina getProgrammaAttuale() {
		return programmaAttuale;
	}
	public String getProgrammaAttualeStr() {
		
		return getProgrammaAttuale() == null
				? ""
				: getProgrammaAttuale().getNome();
	}
	public void setProgrammaAttuale(ProgrammaMacchina programmaAttuale) throws Exception {
		
		if (isInFunzione()) throw new Exception("Impossibile cambiare il programma ora");
		
		this.programmaAttuale = programmaAttuale;
		
		if (programmaAttuale != null)
			setTempoRimanente(programmaAttuale.getDurata());
	}
	public void setProgrammaAttuale(int ind) throws Exception {
		
		for (ProgrammaMacchina pm : PMS) 
			if (pm.getId() == ind)
				setProgrammaAttuale(pm);
	}
	public int getTempoRimanente() {
		return tempoRimanente;
	}
	public void setTempoRimanente(int tempoRimanente) {
		this.tempoRimanente = tempoRimanente;
	}
	public ProgrammaMacchina[] getPMS() {
		return PMS;
	}
	public void setPMS(ProgrammaMacchina[] pMS) {
		PMS = pMS;
	}

	public abstract void printMyPrograms();
	protected void printMyPrograms(boolean lavatrice) {
		
		String[] headers = ProgrammaMacchina.getHeaders(lavatrice);
		
		int pmsLng = PMS.length;
		String[][] data = new String[pmsLng][];
		for (int x=0;x<pmsLng;x++) {
			
			ProgrammaMacchina pm = PMS[x];
			data[x] = pm.getTableString();
		}
		
		ASCIITable.getInstance().printTable(headers, data);
	}
	
	public String[] getTableString() {
		
		return new String[] {
			"" + getId(),
			isLavatrice() ? "Lavatrice" : "Asciugatrice",
			"" + getGettoni(),
			"" + isAperta(),
			"" + isInFunzione(),
			getProgrammaAttualeStr(),
			"" + getTempoRimanente(),
			"N.A.",
			"N.A."
		};
	}
	public static String[] getHeaders() {
		
		return new String[] {
			"Numbero",
			"Tipo",
			"Gettoni",
			"Apertura",
			"In Funzione",
			"Programma",
			"Tempo rimanente",
			"Detersivo",
			"Ammorbidente"
		};
	}
}
