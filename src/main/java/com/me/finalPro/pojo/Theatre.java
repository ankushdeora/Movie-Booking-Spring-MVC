package com.me.finalPro.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="theatre_table")
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="theatreID", unique = true, nullable = false)
    private long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="theatre")
	private List<Screen> screens;

	@Transient
	private int numOfScreens;
	
	
	public void initilizeScreen() {
		screens = new ArrayList<Screen>(this.numOfScreens);
		int i = 1;
		for(Screen s: screens) {
			s.setId(i);
			s.setName("Screen "+i++);
			s.setTheatre(this);
		}
	}
	
	public Screen getScreen(String name) {
		for(Screen s: this.screens) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
		
	public int getNumOfScreens() {
		return numOfScreens;
	}

	public void setNumOfScreens(int numOfScreens) {
		this.numOfScreens = numOfScreens;
	}

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
		this.name = name;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
