package com.me.finalPro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.finalPro.dao.MovieDAO;
import com.me.finalPro.dao.UserDAO;
import com.me.finalPro.exception.UserException;
import com.me.finalPro.pojo.Movie;
import com.me.finalPro.pojo.User;
import com.me.finalPro.validator.UserValidator;



@Controller
@RequestMapping("/user/*")
public class LoginController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
//	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
//	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception{
		return "user-dashboard";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception{
		HttpSession session = (HttpSession) request.getSession();
		
		try {
			System.out.println("loginUser");
			
			User u  = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			
			if(u == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}

			session.setAttribute("user", u);
			if(u.getRole().equals("admin")) {
				return "admin-home";
			}
			else {
				
				return "user-dashboard";
			}
			
		} catch (UserException e) {
			System.out.println("Exception: "+e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}
	}
	
	@RequestMapping(value = "/user/dashboard", method = RequestMethod.GET)
	protected String dashBoard() {
		return "user-dashboard";
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.println("registerUser");
		
		return new ModelAndView("register-user", "user", new User());
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user, 
			BindingResult result) throws Exception{
		
		validator.validate(user, result);
		
		if(result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}
		try {
			System.out.println("registerNewUser");
			User u = userDao.register(user);
			
//			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("success-register", "user", u);
			
		}catch(UserException e) {
			System.out.println("Exception: "+e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/logout", method= RequestMethod.GET) 
	protected ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute("user", null);
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
}
