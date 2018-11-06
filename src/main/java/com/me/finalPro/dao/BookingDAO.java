package com.me.finalPro.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.me.finalPro.exception.BookingException;
import com.me.finalPro.pojo.Booking;
import com.me.finalPro.pojo.User;

public class BookingDAO extends DAO{
	
	public Booking create(Booking booking) throws Exception {
		try {
			begin();
			getSession().save(booking);
			commit();
			return booking;
		}catch(HibernateException e) {
			rollback();
			throw new BookingException("Exception while creating booking: " + e.getMessage());
		}
	}
	
	public List<Booking> list(User user) throws Exception {
		try {
			begin();
			System.out.println("usr: "+user);
			Query q = getSession().createQuery("FROM Booking as b where b.user=:user");
			q.setParameter("user", user);
			
			List<Booking> list= q.getResultList();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new BookingException("Exception while getting list for user= "+ user +": " + e.getMessage());
		}
	}
}
