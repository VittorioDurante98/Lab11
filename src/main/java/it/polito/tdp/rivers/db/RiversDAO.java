package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public River getAllData(River r) {
		River result;
		List<Flow> flows = new ArrayList<>();
		String sql ="SELECT DAY, f.flow, r.id FROM flow AS f, river AS r WHERE r.id=f.river AND r.name=? ORDER BY DAY ASC";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, r.getName());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),new River(res.getInt("id"), r.getName())));

			}
			
			result = new River(r.getId(), r.getName());
			result.setFlows(flows);
			result.setFlowAvg(calcolaMedia(flows));
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public double calcolaMedia(List<Flow> flows) {
		double somma=0;
		double media=0;
		for (Flow f : flows) {
			somma+=f.getFlow();
		}
		media=somma/flows.size();
		return media;
	}
}
