package com.me.finalPro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.me.finalPro.dao.MovieDAO;
import com.me.finalPro.exception.UserException;
import com.me.finalPro.pojo.Movie;

@Controller
public class ServiceController {
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@RequestMapping(value = "/service.htm", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxService(HttpServletRequest request) throws UserException {
		String keyword = request.getParameter("keyword");
		String searchBy = request.getParameter("searchBy");
		System.out.println("keyword "+keyword);
		System.out.println("searchby "+searchBy);
		List<Movie> result = movieDao.searchMovie(keyword, searchBy);
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(result);
		return jsonResponse;
	}
	
}
