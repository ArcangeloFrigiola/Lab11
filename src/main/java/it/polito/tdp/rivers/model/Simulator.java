package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {

	//PARAMETRI DELLA SIMULAZIONE
	private  double capienzaTotale; //Q
	private double flussoMedio; //fAvg
	private double capienzaAttuale; //C
	private double flussoOutMin;
	private RiversDAO dao;
	
	//PARAMETRI DA CALCOLARE
	private int giorniNotErogMinima;
	private double capacitaMedia;

	//INIZIALIZZAZIONE
	private PriorityQueue<Event> initialize(River fiume, double K, double fAvg) {
		
		RiversDAO dao = new RiversDAO();
		this.capienzaTotale = K*fAvg*(30*3600*24);
		this.capienzaAttuale = this.capienzaTotale/2; //Cin = Q/2
		this.flussoMedio = fAvg;
		this.flussoOutMin = 0.8*this.flussoMedio;
		
		PriorityQueue<Event> queue = new PriorityQueue<>();
		LocalDate giorno = LocalDate.of(2000, 1, 1);
		int countGiorni = 1;
		
		while(countGiorni<366){
		
			Event nuovo = new Event(EventType.INGRESSO_FLUSSO, dao.getAvgFlowByDay(fiume, giorno.getDayOfMonth()), countGiorni);
			queue.add(nuovo);
			countGiorni++;
			giorno = giorno.plusDays(1);
			System.out.println(nuovo.toString());
		}
		
		return queue;
		
	}

	public void run(River fiume, double fattoreScala, double flussoMedio) {
		
		PriorityQueue<Event> queue = initialize(fiume, fattoreScala, flussoMedio);
		
	}
}
