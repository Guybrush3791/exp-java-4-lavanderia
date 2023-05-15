package org.java.lavanderia;

import java.util.Scanner;

import org.java.lavanderia.abs.Macchina;
import org.java.lavanderia.helper.Helper;
import org.java.lavanderia.obj.Comando;

public class Program {

	Scanner sc = new Scanner(System.in);
	private Macchina[] macchine = Helper.getInitMacchine();
	
	public Program() {
		
		Helper.initPrint(macchine);
		
		run();
	}
	
	public void run() {
		
		boolean res = true;
		while(res) {
			
			res = runComando();
		}
		
		System.out.println("Bye bye");
	}
	public boolean runComando() {
		
		
		
		System.out.println("");
		String cmdStr = sc.nextLine();
		Comando cmd = null;
		
		try {
			
			cmd = Helper.getComando(cmdStr);
			if (cmd.getNome().equals("esci")) return false;
			
			cmd.action(cmdStr, macchine);		
			
			Helper.printMachines(macchine);
		} catch(Exception e) {
			
			System.err.println("Errore lettura comando: " + e.getMessage());
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		new Program();
	}
}
