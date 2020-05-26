package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	
	public Model(){
		dao = new RiversDAO();
	}
	
	public List<River> getAllRivers(){
		return this.dao.getAllRivers();
	}
	
	public List<Flow> getAllRilevamenti(River fiume){
		return this.dao.getRilevazioniFiume(fiume);
	}
	public double getAvgFlow(River fiume) {
		return this.dao.getAvgFlow(fiume);
	}
}
