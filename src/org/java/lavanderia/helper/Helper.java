package org.java.lavanderia.helper;

import java.util.Random;

import org.java.lavanderia.abs.Macchina;
import org.java.lavanderia.obj.Asciugatrice;
import org.java.lavanderia.obj.Comando;
import org.java.lavanderia.obj.Lavatrice;

import com.bethecoder.ascii_table.ASCIITable;

public class Helper {

	public static final Comando[] COMANDI = {
			new Comando("apri", "Apre lo sportello della macchina"),
			new Comando("chiudi", "Chiude lo sportello della macchina"),
			new Comando("gettoni", "Inserisce i gettoni specificati nella macchina specificata", "numero_gettoni"),
			new Comando("lista", "Visualizza l'elenco dei programmi della macchina specificata"),
			new Comando("programma", "Imposta il programma indicato nella macchina indicata", "numero_programma"),
			new Comando("avvia", "Avvia la macchina"),
			new Comando("ferma", "Ferma la macchina"),
			new Comando("detersivo", "Ricarica il detersivo sulla macchina specificata", "quantita'"),
			new Comando("ammorbidente", "Ricarica l'ammorbidente sulla macchina specificata", "quantita'"),
			new Comando("esci", "Termina il programma", false),
	};
	
	public static void printVersion() {
		
		System.out.println("Terminale controllo lavanderia v1.0");
	}
	public static void printMachines(Macchina[] macchine) {
		
		String[] headers = Macchina.getHeaders();
		String[][] data = new String[macchine.length][];
		for (int x=0;x<macchine.length;x++) {
			
			Macchina m = macchine[x];
			data[x] = m.getTableString();
		}
		ASCIITable.getInstance()
			.printTable(headers, data);
	}
	
	public static void printComandi(Comando[] comandi) {
		
		for (Comando c : comandi) {
			
			System.out.println(c);
		}
	}
	public static Comando getComando(String fullComando) throws Exception {
		
		String[] partComando = fullComando.contains(" ")
					? fullComando.split(" ")
					: new String[]{ fullComando };
		int partComandoLng = partComando.length;
		String nomeComando = partComando[0];
		for (Comando c : COMANDI) {
			
			if (nomeComando.equals(c.getNome())) {
				
				if (c.isSuMacchina() && partComandoLng < 2)
					throw new Exception("Numero macchina mancante");
				if (c.hasParametroSecondario() && partComandoLng < 3) 
					throw new Exception("Parametro secondario mancante");
				
				if (!c.isSuMacchina() && partComandoLng >= 2) 
					throw new Exception("Numero non richiesto");
				if (!c.hasParametroSecondario() && partComandoLng >= 3) 
					throw new Exception("Parametro secondario non richiesto");
				
				return c;
			}
		}
		
		throw new Exception("Comando non trovato");
	}
	
	public static Macchina[] getInitMacchine() {
		
		Random rnd = new Random();
		
		return new Macchina[] {
			new Lavatrice(1, rnd.nextInt(0, 16), 
					rnd.nextBoolean(), 
					rnd.nextInt(0, Lavatrice.CAP_DETERSIVO),
					rnd.nextInt(0, Lavatrice.CAP_AMMORBIDENTE)),
			new Lavatrice(2, rnd.nextInt(0, 16), 
					rnd.nextBoolean(), 
					rnd.nextInt(0, Lavatrice.CAP_DETERSIVO),
					rnd.nextInt(0, Lavatrice.CAP_AMMORBIDENTE)),
			new Lavatrice(3, rnd.nextInt(0, 16), 
					rnd.nextBoolean(), 
					rnd.nextInt(0, Lavatrice.CAP_DETERSIVO),
					rnd.nextInt(0, Lavatrice.CAP_AMMORBIDENTE)),
			
			new Asciugatrice(4, rnd.nextInt(0, 16), rnd.nextBoolean()),
			new Asciugatrice(5, rnd.nextInt(0, 16), rnd.nextBoolean())
		};
	}
	
	public static void initPrint(Macchina[] macchine) {
		
		Helper.printVersion();
		System.out.println("\nStato lavanderia:");
		Helper.printMachines(macchine);
		
		System.out.println("\nProgrammi lavatrice:");
		macchine[0].printMyPrograms();
		
		System.out.println("\nComandi:");
		Helper.printComandi(COMANDI);
	}
}
