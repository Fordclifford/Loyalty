package com.kcbgroup.loyalty.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kcbgroup.loyalty.model.Role;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.RoleRepository;
import com.kcbgroup.loyalty.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		List<Role> roles = roleRepository.findAll();
		
		if (!user.isPresent()) {
            throw new UsernameNotFoundException(username + " was not found");
        }
		
		if (user.get().getEnabled().equals("No")) {
			throw new DisabledException(username + " has been disabled");
		}
		
        return new CustomUserDetails(roles, user.get());
	}
	
	public User newUser(User user) {
		User userNew = new User();
		userNew.setUsername(user.getUsername().toLowerCase());
		userNew.setFirstName(user.getFirstName());
		userNew.setLastName(user.getLastName());
		userNew.setEmail(user.getEmail());
		userNew.setRole(user.getRole());
		userNew.setEnabled(user.getEnabled());
		userNew.setDao(user.getDao());
    	return userRepository.save(userNew);
	}
	
	public User editUser(Long id, User user) {
		User userEdit = userRepository.getOne(id);
		userEdit.setUsername(user.getUsername().toLowerCase());
		userEdit.setFirstName(user.getFirstName());
		userEdit.setLastName(user.getLastName());
		userEdit.setEmail(user.getEmail());
		userEdit.setRole(user.getRole());
		userEdit.setEnabled(user.getEnabled());
		userEdit.setDao(user.getDao());
    	return userRepository.save(userEdit);
	}

}
