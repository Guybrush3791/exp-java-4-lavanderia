package org.java.lavanderia.obj;

import org.java.lavanderia.abs.Macchina;

public class Comando {

	private String nome;
	private String descrizione;
	private String descParametro;
	private boolean suMacchina;
	
	public Comando(String nome, String descrizione) {
		
		setNome(nome);
		setDescrizione(descrizione);
		
		setDescParametro(null);
		setSuMacchina(true);
	}
	public Comando(String nome, String descrizione, boolean suMacchina) {
		
		this(nome, descrizione);
		
		setSuMacchina(false);
	}
	public Comando(String nome, String descrizione, String descParametro) {
		
		this(nome, descrizione);
		
		setDescParametro(descParametro);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescParametro() {
		return descParametro;
	}
	public void setDescParametro(String descParametro) {
		this.descParametro = descParametro;
	}
	public boolean hasParametroSecondario() {
		
		return getDescParametro() != null;
	}
	public boolean isSuMacchina() {
		return suMacchina;
	}
	public void setSuMacchina(boolean suMacchina) {
		this.suMacchina = suMacchina;
	}
	
	/**
	 * new Comando("apri", "Apre lo sportello della macchina"),
			new Comando("chiudi", "Chiude lo sportello della macchina"),
			new Comando("gettoni", "Inserisce i gettoni specificati nella macchina specificata", "numero_gettoni"),
			new Comando("lista", "Visualizza l'elenco dei programmi della macchina specificata"),
			new Comando("programma", "Imposta il programma indicato nella macchina indicata", "numero_programma"),
			new Comando("avvia", "Avvia la macchina"),
			new Comando("ferma", "Ferma la macchina"),
			new Comando("detersivo", "Ricarica il detersivo sulla macchina specificata", "quantita'"),
			new Comando("ammorbidente", "Ricarica l'ammorbidente sulla macchina specificata", "quantita'"),
			new Comando("esci", "Termina il programma", false),
	 * @throws Exception 
	 */
	
	
	public void action(String data, Macchina[] macchine) throws Exception {
		
		String[] sData = data.split(" ");
		int nMacchina = Integer.valueOf(sData[1]);
		Macchina selMacchina = null;
		for (Macchina m : macchine) {
			
			if (m.getId() == nMacchina) {
				selMacchina = m;
			}
		}
		if (selMacchina == null) throw new Exception("Macchina non trovata");		
		
		Integer secParam = sData.length > 2 
							? Integer.valueOf(sData[2])
							: null;
		if (secParam != null && secParam <= 0) throw new Exception("Valore non consistente");

		switch(getNome()) {
		
			case "apri": selMacchina.setAperta(true); break;
			case "chiudi": selMacchina.setAperta(false); break;
				
			case "gettoni": 
				if (secParam == null) throw new Exception("Numero gettoni assente");
				selMacchina.addGettoni(secParam);
			break;
				
			case "lista": selMacchina.printMyPrograms(); break;
				
			case "programma": 
				selMacchina.setProgrammaAttuale(secParam);
			break;
				
			case "avvia": selMacchina.setInFunzioneOn(); break;
			case "ferma": selMacchina.setInFunzioneOff(); break;
				
			case "detersivo": {
				if (!selMacchina.isLavatrice()) throw new Exception("Impossibile ricaricare il detersivo in un asciugatrice");
				
				Lavatrice lav = (Lavatrice) selMacchina;
				lav.addQuantDetersivo(secParam);
			}
			break;
				
			case "ammorbidente": {
				if (!selMacchina.isLavatrice()) throw new Exception("Impossibile ricaricare il ammorbidente in un asciugatrice");
				
				Lavatrice lav = (Lavatrice) selMacchina;
				lav.addQuantAmmorbidente(secParam);
			}
			break;
		}
	}
	
	@Override
	public String toString() {
		
		return getNome() 
					+ (
							isSuMacchina()
							? " <numero_macchina>"
							: ""
					) + (
							descParametro == null 
							? "" 
							: " <" + getDescParametro() + ">"
					) + " | " + getDescrizione()
		;
	}
}
