package it.polito.tdp.rivers.model;

public class Test {

	public static void main(String[] args) {
		Model m =new Model();
		Simulator sim = new Simulator();
		River r = new River(1,"Jokulsa Eystri River");
		
		sim.init(m.getRiver(r), 40);
		System.out.println(sim.run());
	}

}
