package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	public List<Flow> getRilevazioniFiume(River fiume){
		
		final String sql = "SELECT DATE(DAY) as gg, flow " + 
				"FROM flow " + 
				"WHERE river = ?";
		
		List<Flow> rilevamenti = new LinkedList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, fiume.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDate data = res.getDate("gg").toLocalDate();
				rilevamenti.add(new Flow(data, res.getDouble("flow"), fiume));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rilevamenti;
	}
	
	public double getAvgFlow(River fiume) {
		final String sql ="SELECT AVG(flow) as avg " + 
				"FROM flow " + 
				"WHERE river = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, fiume.getId());
			ResultSet res = st.executeQuery();

			res.next();
			double num = res.getDouble("avg");
			conn.close();
			return num;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
	
	public double getAvgFlowByDay(River fiume, int giorno) {
		final String sql ="SELECT AVG(flow) as avg " + 
				"FROM flow " + 
				"WHERE river = ? AND DAY(DAY)=?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, fiume.getId());
			st.setInt(2, giorno);
			ResultSet res = st.executeQuery();

			res.next();
			double num = res.getDouble("avg");
			conn.close();
			return num;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
}
