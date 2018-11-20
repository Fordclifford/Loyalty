package com.kcbgroup.loyalty.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Splitter;
import com.kcbgroup.loyalty.model.Role;
import com.kcbgroup.loyalty.model.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 6701975245772179681L;
	private static final String ROLE_PREFIX = "ROLE_";
	private List<Role> roles;
	private User user;
	
    public CustomUserDetails(List<Role> roles, User user) {
    	this.roles = roles;
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		String userRole = user.getRole();
		authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + userRole));
		Role role = roles.stream().filter(item -> item.getName().equals(userRole)).findFirst().orElse(new Role());
		List<String> privileges = Splitter.on(',').trimResults().splitToList(role.getPrivileges());
	    privileges.stream().map(SimpleGrantedAuthority::new).forEach(authorities::add);
	    return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled().equals("Yes");
	}

}
