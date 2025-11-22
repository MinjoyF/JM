package com.sid.demo.security;

import com.sid.demo.entities.AppUser;
import com.sid.demo.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  private final AppUserRepository repo;

  public JpaUserDetailsService(AppUserRepository repo) {
    this.repo = repo;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {

    AppUser user = repo.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException("User not found");

    return User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .authorities(user.getRoles().toArray(new String[0]))
      .build();
  }
}
