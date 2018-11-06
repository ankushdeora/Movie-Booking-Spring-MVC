package com.me.finalPro.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.finalPro.dao.MovieDAO;
import com.me.finalPro.dao.ScreenDAO;
import com.me.finalPro.dao.TheatreDAO;
import com.me.finalPro.exception.ScreenException;
import com.me.finalPro.exception.TheatreException;
import com.me.finalPro.exception.UserException;
import com.me.finalPro.pojo.Movie;
import com.me.finalPro.pojo.Screen;
import com.me.finalPro.pojo.Theatre;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	@Qualifier("theatreDao")
	TheatreDAO theatreDAO;
	
	@Autowired
	@Qualifier("screenDao")
	ScreenDAO screenDAO;
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToAdminHome(HttpServletRequest request) throws Exception {
		return "admin-home";
	}
	
	@RequestMapping(value="theatre/add", method = RequestMethod.GET)
	protected ModelAndView initializeTheatreForm() {
		return new ModelAndView("admin-theatre", "theatre", new Theatre());
	} 
	
	@RequestMapping(value="theatre/add", method = RequestMethod.POST)
	protected ModelAndView addTheatre(@ModelAttribute("theatre") Theatre theatre, BindingResult result) throws Exception{
		try {
			theatre = theatreDAO.create(theatre.getName());
		}catch (TheatreException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		return new ModelAndView("success-theatre", "theatre", theatre);
	}
	
	@RequestMapping(value="theatre/screen/add", method = RequestMethod.GET)
	protected ModelAndView initializeScreenForm() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("theatres", theatreDAO.list());
		mv.addObject("screen", new Screen());
		mv.addObject("movies", movieDAO.list());
		mv.setViewName("admin-screen");
		return mv;
	} 
	
	@RequestMapping(value="theatre/screen/add", method = RequestMethod.POST)
	protected ModelAndView addScreen(HttpServletRequest request, @ModelAttribute("screen") Screen screen, BindingResult result) throws Exception {
		try {
			System.out.println(request.getParameter("theatre"));
			Theatre t = theatreDAO.get(request.getParameter("theatre"));
			Movie m = movieDAO.get(request.getParameter("movie"));
			
			screen.setTheatre(t);
			screen.setMovie(m);
			screen = screenDAO.create(screen);
			
			t.getScreens().add(screen);
			theatreDAO.update(t);
			return new ModelAndView("success-screen", "screen", screen);
		}catch(ScreenException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
		
	} 
	
	@RequestMapping(value="/movie/add", method = RequestMethod.GET)
	protected ModelAndView initializeMovieForm() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie", new Movie());
		mv.setViewName("admin-movie");
		return mv;
	}
	
	@RequestMapping(value="/movie/add", method = RequestMethod.POST)
	protected ModelAndView addMovie(HttpServletRequest request, @ModelAttribute("movie") Movie movie, BindingResult result) throws Exception {
		try {
			System.out.println("movieController -> addNewMovie");
			Movie m = movieDAO.addMovie(movie);
			
			return new ModelAndView("success-movie");
		}catch(UserException e) {
			System.out.println(e);
			return null;
		}
	}
}
