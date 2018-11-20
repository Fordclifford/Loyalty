package com.kcbgroup.loyalty.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class CustomUserDetailsContextMapper extends LdapUserDetailsMapper {
	
	@Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx,
    		String username, Collection<? extends GrantedAuthority> authorities) {
        return userDetailsService.loadUserByUsername(username.toLowerCase());
    }

}
