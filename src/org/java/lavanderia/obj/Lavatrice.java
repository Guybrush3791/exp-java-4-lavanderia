package org.java.lavanderia.obj;

import org.java.lavanderia.abs.Macchina;

public class Lavatrice extends Macchina {

	public final static int CAP_DETERSIVO = 1000,		// ml
							 CAP_AMMORBIDENTE = 500; 	// ml
	
	private final static ProgrammaMacchina[] LAVATRICE_PMS = {
		new ProgrammaMacchina(1, "Rinfrescante", 20, 5, 25, 20),
		new ProgrammaMacchina(2, "Rinnovante", 40, 10, 50, 40),
		new ProgrammaMacchina(3, "Sgrassante", 60, 15, 100, 60)
	};
	
	
	private int quant_detersivo;
	private int quant_ammorbidente;
	
	public Lavatrice(int id, int gettoni, boolean apertura,			
			int quant_detersivo, int quant_ammorbidente) {
		
		super(id, true, gettoni, apertura, LAVATRICE_PMS);
		
		setQuant_detersivo(quant_detersivo);
		setQuant_ammorbidente(quant_ammorbidente);
	}

	@Override
	public void setInFunzioneOn() throws Exception {
		
		super.setInFunzioneOn();
		
		if (getQuant_detersivo() < getProgrammaAttuale().getCons_detersivo()
			|| getQuant_ammorbidente() < getProgrammaAttuale().getCons_ammorbidente()) {

			setInFunzioneOff();
			removeGettoni(getProgrammaAttuale().getGettoni());
			
			throw new Exception("Condizioni non soddisfatte");
		}
		
		removeQuantDetersivo(getProgrammaAttuale().getCons_detersivo());
		removeQuantAmmorbidente(getProgrammaAttuale().getCons_ammorbidente());
	}
	
	public int getQuant_detersivo() {
		return quant_detersivo;
	}
	public void setQuant_detersivo(int quant_detersivo) {
		this.quant_detersivo = quant_detersivo;
	}
	public void addQuantDetersivo(int quantDetersivo) throws Exception {
		
		if (quantDetersivo <= 0 
			|| quantDetersivo + getQuant_detersivo() > CAP_DETERSIVO)
			throw new Exception("Condizioni non soddisfatte");
		
		setQuant_detersivo(getQuant_detersivo() + quantDetersivo);
	}
	public void removeQuantDetersivo(int quantDetersivo) throws Exception {
		
		if (quantDetersivo <= 0 
			|| getQuant_detersivo() - quantDetersivo <= 0)
			throw new Exception("Condizioni non soddisfatte");
		
		setQuant_detersivo(getQuant_detersivo() - quantDetersivo);
	}
	public int getQuant_ammorbidente() {
		return quant_ammorbidente;
	}
	public void setQuant_ammorbidente(int quant_ammorbidente) {
		this.quant_ammorbidente = quant_ammorbidente;
	}
	public void addQuantAmmorbidente(int quantAmmorbidente) throws Exception {
		
		if (quantAmmorbidente <= 0 
			|| quantAmmorbidente + getQuant_ammorbidente() > CAP_AMMORBIDENTE)
			throw new Exception("Condizioni non soddisfatte");
		
		setQuant_ammorbidente(getQuant_ammorbidente() + quantAmmorbidente);
	}
	public void removeQuantAmmorbidente(int quantAmmorbidente) throws Exception {
		
		if (quantAmmorbidente <= 0 
			|| getQuant_ammorbidente() - quantAmmorbidente <= 0)
			throw new Exception("Condizioni non soddisfatte");
		
		setQuant_ammorbidente(getQuant_ammorbidente() - quantAmmorbidente);
	}

	@Override
	public String[] getTableString() {
		
		String[] res = super.getTableString();
		res[7] = "" + getQuant_detersivo() + "/" + CAP_DETERSIVO;
		res[8] = "" + getQuant_ammorbidente() + "/" + CAP_AMMORBIDENTE;

		return res;
	}
	@Override
	public void printMyPrograms() {
		
		super.printMyPrograms(true);
	}
}

