package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private List<River> fiumi;
	private Simulator sim;
	
	public Model() {
		this.dao= new RiversDAO();
		this.fiumi = new ArrayList<>(dao.getAllRivers());
		this.sim = new Simulator();
	}
	
	public List<River> getFiumi() {
		return fiumi;
	}
	
	public River getRiver(River r) {
		River f= dao.getAllData(r);
		return f;
	}

	public River trovaFiume(String nomeFiume) {
		River fiume= null;
		for (River river : fiumi) {
			if(river.getName().equals(nomeFiume))
				fiume= new River(river.getId(), river.getName());
		}
		return fiume;
	}

	public Simulator getSim() {
		return sim;
	}
	

	
}
