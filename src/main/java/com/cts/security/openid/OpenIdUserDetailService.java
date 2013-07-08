package com.cts.security.openid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

@Service("openIdUserDetailsService")
public class OpenIdUserDetailService implements
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
	
	private final Map<String, CustomUserDetails> registeredUsers = new HashMap<String, CustomUserDetails>();

	private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils
			.createAuthorityList("ROLE_USER");
	
	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {

		String id = token.getIdentityUrl();
		CustomUserDetails user = registeredUsers.get(id);

	       if (user != null) {
	           return user;
	       }
	       String email = null;

	       List<OpenIDAttribute> attributes = token.getAttributes();

	       for (OpenIDAttribute attribute : attributes) {

	           if (attribute.getName().equals("email")) {

	               email = attribute.getValues().get(0);

	           }
	       }

	 
	       user = new CustomUserDetails(id, DEFAULT_AUTHORITIES,email);

	       registeredUsers.put(id, user);	 

	       return user;
	}
}