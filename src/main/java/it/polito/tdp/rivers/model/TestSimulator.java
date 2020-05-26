package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

public class TestSimulator {

	public static void main(String[] args) {
		
		Simulator sim = new Simulator();
		RiversDAO dao = new RiversDAO();
		River fiume = dao.getAllRivers().get(3);
		
		sim.run(fiume, 5, dao.getAvgFlow(fiume));
	}

}
