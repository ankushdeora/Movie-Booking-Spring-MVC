package com.me.finalPro.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieID", unique = true, nullable = false)
	private int movieID;
	
	@Column(name = "movietitle")
	private String movieTitle;
	
	@Column(name = "leadactor")
    private String leadActor;
	
	@Column(name = "leadActress")
    private String leadActress;
	
	@Column(name = "genre")
    private String genre;
	
	@Column(name = "year")
    private int year;

//	@Column(name = "availFrom")
//    private String availFrom;
//    
//	@Column(name = "availTo")
//    private String availTo;
	
	@Column(name = "addedOn")
	private String addedOn;
//    private List<E> reviews;
    
	
	public Movie() {
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sf.format(new Date());
		this.setAddedOn(date);
	}
	
    public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getLeadActor() {
        return leadActor;
    }

    public void setLeadActor(String leadActor) {
        this.leadActor = leadActor;
    }

    public String getLeadActress() {
        return leadActress;
    }

    public void setLeadActress(String leadActress) {
        this.leadActress = leadActress;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}
    
	@Override
    public String toString() {
		if(movieTitle == null){
			return "movieTitle";
		}
    	return movieTitle;
    }
}
