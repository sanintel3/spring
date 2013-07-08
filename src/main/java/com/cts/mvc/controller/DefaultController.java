package com.cts.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("defaultHandler")
public class DefaultController {
	
	@RequestMapping
	public ModelAndView defaultHandler() {
		ModelAndView mav = new ModelAndView("errors");
		mav.addObject("errorMsg", "This is Default handler error page");
		return mav;
	}

}
