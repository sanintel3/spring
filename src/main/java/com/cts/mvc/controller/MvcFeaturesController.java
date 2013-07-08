package com.cts.mvc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller("mvcFeaturesController")
public class MvcFeaturesController {

	@RequestMapping(value="/modelMap")
	public String withModelMap(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "viewName";
		// return new Model(); //RequestToViewNameTranslator needs to be
		// configured
		// return new Map();
		// return new View();
		// void //directly write content to response
	}

	@RequestMapping(value = "/{firstName}/{date}/{boolean}")
	public String withPathVariables(
			@PathVariable(value = "firstName") String name,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) Date day,
			@PathVariable Boolean booleanValue) {
		return "viewName";
	}

	@RequestMapping(value = "/{firstName}", params = "employeeId=1234", headers = "content-type=text/html", consumes = "application/json", produces = "application/json")
	public String withNarrowingRequestMapping() {
		return "viewName";
	}

	@RequestMapping(value="/input")
	@ResponseBody
	public String accessInputParams(
			@RequestParam(value = "param1", defaultValue = "true", required = false) Boolean reqValue,
			@RequestHeader(value = "header1") String headerValue,
			@RequestBody(required = false) String reqBody,
			@CookieValue("JSESSIONID") String cookieValue) {

		return "responseContent"; // uses messageconverters
	}

	@RequestMapping(value="/entities")
	public ResponseEntity<String> withEntities(HttpEntity<byte[]> requestEntity) {
		return new ResponseEntity<String>("responseConrent", null,
				HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/redirect")
	public View withRedict() {
		// return "redirect:url";
		// return "forward:url";
		return new RedirectView("url"); // all modelAttributes exposed as query
										// parameters		
	}

	@RequestMapping(value="/userDetails")
	public void accessUserDetails() {
		OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		List<OpenIDAttribute> attributes = token.getAttributes();

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
		} else {
			String username = principal.toString();
		}
	}

}
