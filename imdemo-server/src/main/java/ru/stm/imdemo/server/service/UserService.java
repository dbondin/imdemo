package ru.stm.imdemo.server.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.stm.imdemo.server.domain.User;
import ru.stm.imdemo.server.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(username).get();
		return new UserDetails() {

			private static final long serialVersionUID = 740474398751629116L;

			@Override
			public boolean isEnabled() {
				return user.isActive();
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return user.isActive();
			}

			@Override
			public boolean isAccountNonLocked() {
				return user.isActive();
			}

			@Override
			public boolean isAccountNonExpired() {
				return user.isActive();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return user.getRoles().stream().map(role -> new GrantedAuthority() {
					private static final long serialVersionUID = -3074797866212836289L;

					@Override
					public String getAuthority() {
						return role.getName();
					}
				}).collect(Collectors.toSet());
			}
		};
	}
}
