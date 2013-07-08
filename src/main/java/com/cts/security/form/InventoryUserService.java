package com.cts.security.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("inventoryUserService")
public class InventoryUserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		String password = null;
		String role = null;
		//fetch ifl users table using username and if it's resolved hard code the password (should match with myloginfilter password), fetch role from db
		//if it's not resolved in ifl users, query non-ifl users and populate username,password,role with values stored in db.
		
		
		getRequestParam();
		
		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + role);
		
		//For successful authentication, below username and password should match with data populated in myloginfilter UsernamePasswordAuthenticationToken object 
		return new User(username, password, authorities);
		
		
		//if user not exists throw		
		/*throw new UsernameNotFoundException(username
		+ " could not be found");*/
	}
	
	private void getRequestParam(){
		
		//approach 1
		HttpServletRequest request = 
				  ((ServletRequestAttributes) RequestContextHolder.
				    currentRequestAttributes()).
				    getRequest(); 
		
		
		//approach 2 autowire HttpServletRequest and add below listener to web.xml 
		
		/*
		 *  @Autowired
  			private HttpServletRequest request;
  		 * <listener>
	    	<listener-class>
	        	org.springframework.web.context.request.RequestContextListener
	    	</listener-class>
			</listener>*/
		
		
	}
	
	

}
