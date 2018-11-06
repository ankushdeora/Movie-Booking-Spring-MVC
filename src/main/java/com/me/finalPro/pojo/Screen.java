package com.me.finalPro.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "screen_table")
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="screenID", unique = true, nullable = false)
    private long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="theatre_id")
	private Theatre theatre;
	
	@OneToOne
	private Movie movie;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("screen- setName");
		this.name = name;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		System.out.println("screen- setTheatre");
		this.theatre = theatre;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@Override
	public String toString() {
		if(name == null) {
			return "ScreenName";
		}
		return name;
	}
}
