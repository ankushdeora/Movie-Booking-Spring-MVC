package com.me.finalPro.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.me.finalPro.exception.ScreenException;
import com.me.finalPro.pojo.Movie;
import com.me.finalPro.pojo.Screen;

public class ScreenDAO extends DAO{
	public Screen create(Screen screen) throws ScreenException{
		try {
			begin();
			getSession().save(screen);
			commit();
			return screen;
		}catch(HibernateException e) {
			rollback();
			throw new ScreenException("Exception while creating screen: " + e.getMessage());
		}
	}
	
	public List<Screen> getAllPlaying(Movie movie) throws ScreenException{
		try {
			begin();
			Query q = getSession().createQuery("From Screen where movie = :movie");
			q.setParameter("movie", movie);
			List<Screen> list = q.getResultList(); 
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new ScreenException("Exception while getting all screens for: "+ movie +" "+ e.getMessage());
		}
	}
}
