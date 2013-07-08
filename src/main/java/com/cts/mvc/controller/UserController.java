package com.cts.mvc.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.cts.model.User;
import com.cts.service.UserService;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	/*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView home() throws Exception {

		ModelAndView mav = new ModelAndView("home");
		
		return mav;
	}
	
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public ModelAndView loginForm() throws Exception {

		ModelAndView mav = new ModelAndView("login1");

		return mav;
	}
	
	@RequestMapping(value = "login", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login() throws Exception {

		ModelAndView mav = new ModelAndView("login1");

		return mav;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView listUsers() throws Exception {

		ModelAndView mav = new ModelAndView("users");

		mav.addObject("users", userService.getAllUsers());

		return mav;
	}

	@RequestMapping(value = "showForm", method = RequestMethod.GET)
	public ModelAndView showForm() {

		ModelAndView mav = new ModelAndView("userForm");
		mav.addObject("user", new User());

		return mav;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid User user, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {
			return new ModelAndView("userForm");
		} else {
			user.setPassword("pwd");
			userService.saveUser(user);
			return new ModelAndView("redirect:/user/list");
		}

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView editUser(@Valid User user, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {
			return new ModelAndView("showForm");
		} else {
			userService.saveUser(user);
			return new ModelAndView("redirect:/user/hibernate/list");
		}

	}
	
	@RequestMapping(value = "exception/null", method = RequestMethod.GET)
	public ModelAndView throwNullPointerExceptoin() throws Exception {
		throw new NullPointerException();
	}
	
	@RequestMapping(value = "exception/pageNotFound", method = RequestMethod.GET)
	public ModelAndView throwIOExceptoin() throws Exception {
		throw new IOException();
	}
	
	
	@RequestMapping(value = "access/webcontext", method = RequestMethod.GET)
	public ModelAndView accessWebContext(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("errors");
		WebApplicationContext context = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		MessageSource messageSource = (MessageSource)context.getBean("messageSource");
		mav.addObject("errorMsg",messageSource.getMessage("contextMsg", null, Locale.ENGLISH));
		return mav;
	}
	
	@RequestMapping(value = "accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() throws Exception {
		ModelAndView mav = new ModelAndView("errors");
		mav.addObject("errorMsg", "You are not authorized to access this page");
		return mav;
	}
	
	
	@RequestMapping(value = "sessionError", method = RequestMethod.GET)
	public ModelAndView sessionError() throws Exception {
		ModelAndView mav = new ModelAndView("errors");
		mav.addObject("errorMsg", "You have some active sessions opened at the moment and can't login to new session");
		return mav;
	}

}


