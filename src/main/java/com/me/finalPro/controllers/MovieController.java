package com.me.finalPro.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.finalPro.dao.BookingDAO;
import com.me.finalPro.dao.MovieDAO;
import com.me.finalPro.dao.ScreenDAO;
import com.me.finalPro.dao.TheatreDAO;
import com.me.finalPro.exception.BookingException;
import com.me.finalPro.exception.UserException;
import com.me.finalPro.pojo.Booking;
import com.me.finalPro.pojo.Movie;
import com.me.finalPro.pojo.Screen;
import com.me.finalPro.pojo.Theatre;
import com.me.finalPro.pojo.User;

@Controller
@RequestMapping("/movie/*")
public class MovieController {
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("screenDao")
	ScreenDAO screenDAO;
	
	@Autowired
	@Qualifier("bookingDao")
	BookingDAO bookingDAO;
	
	@Autowired
	@Qualifier("theatreDao")
	TheatreDAO theatreDAO;
	
	
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public String browseMovies(HttpServletRequest request) throws UserException {
		List<Movie> latestMovies = movieDao.getLatest();
		request.setAttribute("latest", latestMovies);
		return "user-home";
	}
	
	@RequestMapping(value = "/movie/book", method = RequestMethod.GET)
	public ModelAndView getMovieById(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String movieID = request.getParameter("movieID");
//		System.out.println("movieID: "+movieID);
		
		List<Theatre> listOfTheatres = new ArrayList<Theatre>();
		
		try {
			Movie m = movieDao.getMovie(Integer.parseInt(movieID));
			List<Screen> screens = screenDAO.getAllPlaying(m);
			
			for(Screen s : screens) {
				if(!listOfTheatres.contains(s.getTheatre())) {
					listOfTheatres.add(s.getTheatre());
				}
			}
			
			mv.addObject("movie", m);
			session.setAttribute("listOfScreens", screens);
			mv.addObject("listOfTheatres", listOfTheatres);
//			mv.addObject("booking", new Booking());
//			System.out.println(listOfTheatres.get(0).getName());
			mv.setViewName("movie-book");
			return mv;
		}
		catch(NumberFormatException e) {
			System.out.println("Number Format exception");
			return null;
		}
//		return new ModelAndView("movie-book", "movie", m);
	}
	
	@RequestMapping(value = "/screen/book", method = RequestMethod.POST)
	public ModelAndView screenSelection(HttpServletRequest request) throws UserException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<Screen> screens = (List<Screen>) session.getAttribute("listOfScreens");
		List<Screen> theatreScreen = new ArrayList<Screen>();
		String theatreName = request.getParameter("theatre");
		Theatre theatre = null;
		for(Screen s : screens) {
			if(s.getTheatre().getName().equals(theatreName)) {
				theatreScreen.add(s);
				if(theatre == null) {
					theatre = s.getTheatre();
				}
			}
		}
		System.out.println("theatre: "+theatre);
		Movie movie = movieDao.get(request.getParameter("movie"));
		mv.addObject("theatreScreen", theatreScreen);
		mv.addObject("theatre", theatre);
		mv.addObject("movie", movie);
		mv.addObject("booking", new Booking());
		mv.setViewName("movie-book1");
		return mv;
	}
	
	@RequestMapping(value="/bookFinal", method = RequestMethod.POST)
	public ModelAndView bookTicket(HttpServletRequest request, @ModelAttribute("booking") Booking booking, BindingResult result) throws Exception{
		try {
			HttpSession session = request.getSession(false);
			Theatre t = theatreDAO.get(request.getParameter("theatre"));
			Movie m = movieDao.get(request.getParameter("movie"));
			Screen s = t.getScreen(request.getParameter("screen"));
			User u = (User) session.getAttribute("user");
			booking.setMovie(m);
			booking.setScreen(s);
			booking.setTheatre(t);
			booking.setUser(u);
			u.getBookings().add(booking);
			booking = bookingDAO.create(booking);
			
			return new ModelAndView("success-book", "booking", booking);
		}catch(BookingException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/movie/search", method = RequestMethod.GET)
	public String searchDisplay() {	
		return "movie-search";
	}
	
	
	@RequestMapping(value = "/movie/myBookings", method = RequestMethod.GET)
	public ModelAndView myBookings(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(false);
		List<Booking> list = bookingDAO.list((User)session.getAttribute("user"));
		System.out.println(list.get(0).getBooking_id());
		ModelAndView mv = new ModelAndView();
		mv.addObject("bookingList", list);
		mv.setViewName("user-bookings");
		return mv;
	}
	
}
