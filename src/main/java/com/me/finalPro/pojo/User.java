package com.me.finalPro.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.me.finalPro.pojo.Email;
import com.me.finalPro.pojo.Person;

@Entity
@Table(name = "user_table")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {
	
	@Column(name = "userName")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Email email;
	
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;
	
	@Column(name = "role")
	private String role;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.role = "customer";
	}

	public User() {
	
	}

	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return username;
	}
}

