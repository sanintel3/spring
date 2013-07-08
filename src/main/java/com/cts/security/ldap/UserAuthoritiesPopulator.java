package com.cts.security.ldap;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

@Service("userAuthoritiesPopulator")
public class UserAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Override
	public Collection<GrantedAuthority> getGrantedAuthorities(
			DirContextOperations userData, String userName) {
		
		return AuthorityUtils
		.createAuthorityList("ROLE_USER");

	}
}
