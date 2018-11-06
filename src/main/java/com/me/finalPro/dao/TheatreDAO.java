package com.me.finalPro.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.me.finalPro.exception.TheatreException;
import com.me.finalPro.pojo.Theatre;

public class TheatreDAO extends DAO{
	
	public Theatre create(String name) throws TheatreException{
		try {
			begin();
			Theatre theatre = new Theatre();
			theatre.setName(name);
			getSession().save(theatre);
			commit();
			return theatre;
		} catch (HibernateException e) {
			rollback();
            //throw new AdException("Could not create the category", e);
            throw new TheatreException("Exception while creating theatre: " + e.getMessage());
		}
	}

	public List<Theatre> list() throws TheatreException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Theatre");
			List<Theatre> list = q.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
            rollback();
            throw new TheatreException("Could not list the theatre", e);
        }
	}
	
	public Theatre get(String name) throws TheatreException{
		try {
			begin();
			Query q = getSession().createQuery("from Theatre where name=:name");
			q.setString("name", name);
			Theatre theatre = (Theatre) q.uniqueResult();
			commit();
			return theatre;
		}catch (HibernateException e) {
            rollback();
            throw new TheatreException("Could not obtain the named theatre " + name + " " + e.getMessage());
        }
	}

	public void update(Theatre theatre) throws TheatreException{
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().update(theatre);
			commit();
		}catch (HibernateException e) {
            rollback();
            throw new TheatreException("Could not save the Theatre", e);
        }
	}
	
}
