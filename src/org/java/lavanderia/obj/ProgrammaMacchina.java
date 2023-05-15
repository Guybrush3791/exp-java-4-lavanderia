package org.java.lavanderia.obj;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaMacchina {

	private final boolean lavatrice;
	
	private int id;
	private String nome;
	private int durata;
	private int gettoni;
	private int cons_detersivo;
	private int cons_ammorbidente;
	
	private ProgrammaMacchina(boolean lavatrice, int id, String nome, int durata,
			 int gettoni) {

		this.lavatrice = lavatrice;
		
		setId(id);
		setNome(nome);
		setDurata(durata);
		setGettoni(gettoni);
	}
	public ProgrammaMacchina(int id, String nome, int durata,
							 int gettoni) {
		
		this(false, id, nome, durata, gettoni);
	}
	public ProgrammaMacchina(int id, String nome, int durata,
							 int gettoni, int cons_detersivo, 
							 int cons_ammorbidente) {
		this(true, id, nome, durata, gettoni);
		
		setCons_detersivo(cons_detersivo);
		setCons_ammorbidente(cons_ammorbidente);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public int getGettoni() {
		return gettoni;
	}
	public void setGettoni(int gettoni) {
		this.gettoni = gettoni;
	}
	public int getCons_detersivo() {
		return cons_detersivo;
	}
	public void setCons_detersivo(int cons_detersivo) {
		this.cons_detersivo = cons_detersivo;
	}
	public int getCons_ammorbidente() {
		return cons_ammorbidente;
	}
	public void setCons_ammorbidente(int cons_ammorbidente) {
		this.cons_ammorbidente = cons_ammorbidente;
	}
	
	public String[] getTableString() {
		
		List<String> res = new ArrayList<>();
		res.add("" + getId());
		res.add(getNome());
		res.add("" + getDurata());
		res.add("" + getGettoni());
		
		if (lavatrice) {
			
			res.add("" + getCons_detersivo());
			res.add("" + getCons_ammorbidente());
		}
		
		return res.toArray(new String[]{});
	}
	
	public static String[] getHeaders(boolean lavatrice) {
		
		List<String> res = new ArrayList<>();
		res.add("Numero");
		res.add("Nome");
		res.add("Durata (minuti)");
		res.add("Gettoni");
		
		if (lavatrice) {
			res.add("Consumo detersivo (ml)");
			res.add("Consumo ammorbidente (ml)");
		}
		
		return res.toArray(new String[] {});
	}
}
