package com.me.finalPro.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.me.finalPro.exception.TheatreException;
import com.me.finalPro.exception.UserException;
import com.me.finalPro.pojo.Movie;
import com.me.finalPro.pojo.Theatre;

public class MovieDAO extends DAO{

	
	public List<Movie> getLatest() throws UserException{
		
		try {
			
			begin();
			String hql = "from Movie m ORDER BY m.addedOn";
			Query q = getSession().createQuery(hql);	
//			q.setString("keyword", "%"+keyword+"%");
		
			List<Movie> list = q.getResultList();
			commit();
			return list;
			
		}catch(HibernateException e) {
			System.out.println("Exception");
			throw new UserException("Could not get Movie ",e);
		}
	}
	
	public Movie getMovie(int movieID) throws UserException{
		try {
			
			begin();
			String hql = "from Movie m WHERE m.movieID = :movieid";
			Query q = getSession().createQuery(hql);	
			q.setInteger("movieid", movieID);
		
			Movie movie = (Movie) q.uniqueResult();
			commit();
			return movie;
			
		}catch(HibernateException e) {
			System.out.println("Exception");
			throw new UserException("Could not get Movie ",e);
		}
	}
	
	public List<Movie> searchMovie(String keyword, String searchBy) throws UserException{
		
		try {
			begin();
			System.out.println("searchby: "+searchBy+" keyword: "+keyword);
			String hql = "from Movie as m where m."+searchBy+" like :keyword";
			Query q = getSession().createQuery(hql);
			
			q.setString("keyword", "%"+keyword+"%");
		
			List<Movie> list = q.getResultList();
			commit();
			return list;
		}catch (HibernateException e) {
			System.out.println("Exception");
			throw new UserException("Could not get Movie ",e);
		}
	}

	public Movie addMovie(Movie movie) throws UserException{
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().save(movie);
			commit();
			return movie;
		}
		catch(HibernateException e) {
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<Movie> list() throws UserException{
		try {
			begin();
			Query q = getSession().createQuery("from Movie");
			List<Movie> list = q.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
            rollback();
            throw new UserException("Could not list the Movie", e);
        }
	}

	public Movie get(String name) throws UserException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Movie where movietitle = :name");
			q.setString("name", name);
			Movie movie = (Movie) q.uniqueResult();
			commit();
			return movie;
			
 		}catch(HibernateException e) {
 			rollback();
 			throw new UserException("Could not obtain the named movie " + name + " " + e.getMessage());
		}
	}
	
}
