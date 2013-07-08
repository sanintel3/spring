package com.cts.security.openid;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private String email;	 

	   public CustomUserDetails(String username, Collection<GrantedAuthority> authorities,String email) {

	       super(username, "unused", authorities);
	       this.email = email;

	   }
	   
	   public String getEmail(){
		   return email;
	   }

	   
}