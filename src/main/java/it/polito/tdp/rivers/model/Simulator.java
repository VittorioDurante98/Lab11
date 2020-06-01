package it.polito.tdp.rivers.model;

import java.time.Duration;

import java.time.LocalDate;
import java.util.PriorityQueue;

//import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	// PARAMETRI DI SIMULAZIONE
	private River selezionato;
	private double c;
	private double k;
	private double q;
	private double fOutMin;
	private double fMedio;
	//private Duration change = Duration.ofHours(24);
	private LocalDate giornoInizio;
//	private int mese = 30;
	private double cSomma;
	//private RiversDAO dao;
	
	// OUTPUT DA CALCOLARE
	private double cMedio;
	private int giorni;
	
	// STATO DEL SISTEMA
	private double fIn;
	private double fOut;
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> coda;
	
	// INIZIALIZZAZIONE
	public void init(River r, double k) {
	   // this.dao = new RiversDAO();
		this.giorni=0;
		this.fIn=0;
		this.fOut=0;
		this.selezionato= r;
		this.fMedio= selezionato.getFlowAvg()*3600*24;
		this.k= k;
		this.q= k*fMedio*30;
		this.c= q/2;
		this.cSomma=c;
		this.coda = new PriorityQueue<Event>();
		this.giornoInizio = selezionato.getFlows().get(0).getDay();
		for (int i=0; i<selezionato.getFlows().size(); i++) {
			Flow f= selezionato.getFlows().get(i);
			Event e= new Event(giornoInizio, EventType.DAY, f);
			coda.add(e);
			giornoInizio= giornoInizio.plusDays(1);
		}
		
	}
	
	// ESECUZIONE
	public String run() {
		while (!coda.isEmpty()) {
			Event e= coda.poll();
			this.process(e);
		}
		this.cMedio=this.cSomma/(selezionato.getFlows().size()+1);
		return "Giorni senza acqua: "+ this.giorni+"\nLivello medio nella diga: "+this.cMedio;
	}

	private void process(Event e) {
		this.fIn = e.getFlusso().getFlow()*3600*24;
		this.fOutMin= 0.8*fMedio;
		switch (e.getType()) {
		case DAY:
			c+=fIn;
			if (Math.random()<0.05) {
				fOutMin*=10;
			}
			if (c>q) {
				fOut= fOutMin + c - q;
				c= c - fOut;
				cSomma+= c;
				break;
			}
			if (c>=fOutMin) {
				fOut= fOutMin;
				c=c-fOut;
				cSomma+=c;
				break;
			}else {
				fOut=c;
				c=0;
				giorni++;
			}
			
			break;
		}
	}
	

}
