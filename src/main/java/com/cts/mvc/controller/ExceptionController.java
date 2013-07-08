package com.cts.mvc.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Value("${pageNotFoundMsg}")
	private String pageNotFoundMsg;

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleException(NullPointerException ex) {
		ModelAndView mav = new ModelAndView("errors");
		mav.addObject("errorMsg", messageSource.getMessage("internalErrorMsg", null, Locale.ENGLISH));
		return mav;
	}
	
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex) {
		ModelAndView mav = new ModelAndView("errors");
		mav.addObject("errorMsg", pageNotFoundMsg);
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String overrideStatus(IOException ex) {
		return "errorContent";
	}

}
