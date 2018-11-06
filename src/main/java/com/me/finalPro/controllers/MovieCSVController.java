package com.me.finalPro.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.finalPro.dao.MovieCsvDAO;
import com.me.finalPro.pojo.Movie;

@Controller
public class MovieCSVController {
	
	@Autowired
	@Qualifier("movieCsvDao")
	MovieCsvDAO movieCsvDAO;
	
	@RequestMapping(value = "/csvMovie/insert.htm", method = RequestMethod.POST)
	public String readCsv(HttpServletRequest request, ModelMap map) {
		String fileName = request.getParameter("csvFileName");
		
		HttpSession session = request.getSession();
		session.setAttribute("fileName", fileName);
		
		HashMap<Integer, Movie> movies = movieCsvDAO.readCsv(fileName);
		if(movies != null) {
			map.addAttribute("movies", movies);
			session.setAttribute("movies", movies);
			return "csv-movie";
		}
		return null;
	}
	
	@RequestMapping(value = "/csvMovie/insert.htm", method = RequestMethod.GET)
	public String csvPage() {
		return "csv-movie";
	}
	
	@RequestMapping(value = "csvMovie/addToDB.htm", method = RequestMethod.POST)
	public ModelAndView addToDB(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<Integer, Movie> hashMovie = (HashMap<Integer, Movie>) session.getAttribute("movies");
		int i = movieCsvDAO.addToData(hashMovie);
		
		return new ModelAndView("csv-movieSuccess", "count", i);
	}
}
