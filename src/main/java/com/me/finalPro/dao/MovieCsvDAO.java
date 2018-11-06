package com.me.finalPro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.hibernate.query.Query;

import com.me.finalPro.pojo.Movie;

public class MovieCsvDAO extends DAO{
	
	public HashMap<Integer, Movie> readCsv(String filename){
		Connection connection = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.relique.jdbc.csv.CsvDriver");
			connection = DriverManager.getConnection("jdbc:relique:csv:/Users/ankushdeora/Desktop/finalCsv");
            stmt = connection.createStatement();
            System.out.println("in try ---------------");
		}catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
        }
		
		try {
			String query = "SELECT Movie_id, Movie_title, lead_actor, lead_actress, genre, year FROM "+ filename;
			ResultSet results = stmt.executeQuery(query);
			System.out.println("movieCsvDAO");
			HashMap<Integer, Movie> hashRS = new HashMap<Integer, Movie>();
			int i = 1;
			
			while (results.next()) {
				Movie m = new Movie();
				int j = 1;
				m.setMovieID(results.getInt(j++));
				m.setMovieTitle(results.getString(j++));
				m.setLeadActor(results.getString(j++));
				m.setLeadActress(results.getString(j++));
				m.setGenre(results.getString(j++));
				m.setYear(results.getInt(j++));
				hashRS.put(i, m);
				i++;
			}
			return hashRS;
		}catch (SQLException ex) {
            System.out.println(ex);
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException" + ex.getMessage());
            }
        }
		return null;
	}
	
	
	public int addToData(HashMap<Integer, Movie> hashMovie) {
		begin();
		int i = 0;
		for(Movie m: hashMovie.values()) {
			getSession().save(m);
			i++;
		}
		commit();
		return i;
	}
}
